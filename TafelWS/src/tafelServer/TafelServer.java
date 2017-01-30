/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tafelServer;

import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.JFrame;

import serverRequests.DeletePublicRequest;
import serverRequests.ModifyPublicRequest;
import serverRequests.ReceiveRequest;
import serverRequests.ServerRequest;
import verteilteAnzeigetafel.Anzeigetafel;
import verteilteAnzeigetafel.Message;
import verteilteAnzeigetafel.TafelException;

/**
 *
 * @author Simon Bastian
 */
public class TafelServer {
    private final static String defaultPort = "8080";
    
	private HashMap<Integer, LinkedBlockingDeque<ServerRequest>> queueMap = new HashMap<Integer, LinkedBlockingDeque<ServerRequest>>();
	private HashMap<Integer, HashSet<Integer>> groupMap = new HashMap<Integer, HashSet<Integer>>();
	private HashMap<Integer, HashSet<LinkedBlockingDeque<ServerRequest>>> groupQueueMap = new HashMap<Integer, HashSet<LinkedBlockingDeque<ServerRequest>>>();
	private HashMap<Integer, URL> tafelAdressen = new HashMap<Integer, URL>();
	private HashMap<Integer, OutboxThread> outboxThreads = new HashMap<Integer, OutboxThread>();
	private HashMap<Integer, HeartbeatThread> heartbeatThreads = new HashMap<Integer, HeartbeatThread>();
	private Anzeigetafel anzeigetafel;
	private int abteilungsID;
	private TafelGUI gui;

	private static TafelServer tafelServerInstance = null;
	
	
	public static boolean startServer(int abteilung) {
		if (tafelServerInstance == null){
			tafelServerInstance = new TafelServer(abteilung);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean stopServer() {
	    // TODO stop stuff
	    return true;
	}

	public static TafelServer getServer() {
		return tafelServerInstance;
	}
	
	private TafelServer(int abteilungsID) {
		this.abteilungsID = abteilungsID;
		init();
		printMessages();
	}

	/**
	 * Initializes the TafelServer.
	 */
	private void init() {
		anzeigetafel = Anzeigetafel.loadStateFromFile(abteilungsID);
		if (anzeigetafel != null) {
			print("Anzeigetafel aus Datei geladen!");
		} else {
			anzeigetafel = new Anzeigetafel(abteilungsID);
			anzeigetafel.saveStateToFile();
			print("Neue Anzeigetafel erstellt!");
		}

		queueMap = loadQueueMapFromFile();
		loadGroupsFromFile();
		initGroupQueueMap();
		loadTafelAdressenFromFile();
		
		// GUI
		gui = new TafelGUI(anzeigetafel.getAbteilungsID(), this, groupMap.keySet());
		anzeigetafel.addObserver(gui);
		anzeigetafel.updateState();
	}
	
	/**
	 * Registers a TafelServer if possible.
	 * 
	 * @param abteilungsID
	 * @param address
	 * @throws TafelException
	 *             if the given abteilungsID is the own abteilungsID
	 */
	public synchronized String registerTafel(int abteilungsID, URL address) throws TafelException {
		if (this.abteilungsID == abteilungsID) {
			throw new TafelException("Die eigene Abteilung wird nicht registriert");
		}
		
		if (tafelAdressen.containsKey(abteilungsID)) {
			if (!tafelAdressen.get(abteilungsID).equals(address)) {
			    //  dies ändert NICHT die Adressen in HeartbeatThread / OutboxThread! getestet. Zumindest nicht direkt.
				tafelAdressen.replace(abteilungsID, address);
				
				// added
				outboxThreads.get(abteilungsID).setTargetAddress(address);
				heartbeatThreads.get(abteilungsID).setTargetAddress(address);
			}

		} else {
			tafelAdressen.put(abteilungsID, address);
		}
		if (!queueMap.containsKey(abteilungsID)) {
			addQueue(abteilungsID);
		} 
		
		activateHeartbeat(abteilungsID);
		activateQueue(abteilungsID);
		saveTafelAdressenToFile();
		saveQueueMapToFile();
		
		return "TafelServer " + abteilungsID + ", Adresse: " + address.toString() + " registriert!";
	}
	
	/**
	 * Activated the message queue for the given abteilungID.
	 * 
	 * @param abteilungsID
	 */
	public synchronized void activateQueue(int abteilungsID) {

		if (!outboxThreads.containsKey(abteilungsID) || !outboxThreads.get(abteilungsID).isAlive()) {
			OutboxThread obt = new OutboxThread(abteilungsID, tafelAdressen.get(abteilungsID),
					queueMap.get(abteilungsID), this);

			outboxThreads.put(abteilungsID, obt);
			obt.start();
		} else { // else Queue already active
		    outboxThreads.get(abteilungsID).doNotify();   // wake him up
		}
	}

	/**
	 * Activates a Heartbeat for the given abteilungsID.
	 * 
	 */
	public synchronized void activateHeartbeat(int remoteAbteilungsID) {
		if (!heartbeatThreads.containsKey(remoteAbteilungsID) || !heartbeatThreads.get(remoteAbteilungsID).isAlive()) {
			HeartbeatThread hbt = new HeartbeatThread(this.abteilungsID, remoteAbteilungsID, tafelAdressen.get(remoteAbteilungsID), this);
			heartbeatThreads.put(remoteAbteilungsID, hbt);
			hbt.start();
		}
	}
	
	private void initGroupQueueMap() {
		groupQueueMap.clear();

		for (Entry<Integer, HashSet<Integer>> entry : groupMap.entrySet()) {
			Integer groupId = entry.getKey();
			HashSet<Integer> groupMembers = entry.getValue();
			HashSet<LinkedBlockingDeque<ServerRequest>> queues = new HashSet<LinkedBlockingDeque<ServerRequest>>();
			for (Integer groupMember : groupMembers) {
				if (groupMember.intValue() != abteilungsID) {
					if (queueMap.containsKey(groupMember)){
						queues.add(queueMap.get(groupMember));
					}
				}
			}
			groupQueueMap.put(groupId, queues);
		}
		print("Group Queues initialized: " + groupQueueMap);
	}

	private boolean addQueue(Integer abteilung){
		if (abteilung.intValue() == abteilungsID){
			return false;
		}
		LinkedBlockingDeque<ServerRequest> queue = new LinkedBlockingDeque<ServerRequest>();
		queueMap.put(abteilung, queue);
		for ( Entry<Integer, HashSet<Integer>> entry : groupMap.entrySet()){
			Integer groupId = entry.getKey();
			HashSet<Integer> groupMembers = entry.getValue();
			if (groupMembers.contains(abteilung)){
				groupQueueMap.get(groupId).add(queue);
			}
		}
		return true;
	}
	
	/**
	 * Writes the message queues for each Abteilung to a file.
	 */
	public synchronized void saveQueueMapToFile() {
		FileOutputStream fileoutput = null;
		ObjectOutputStream objoutput = null;
		try {
			fileoutput = new FileOutputStream("./.queueMap" + abteilungsID);
			objoutput = new ObjectOutputStream(fileoutput);
			objoutput.writeObject(queueMap);
		} catch (FileNotFoundException e) {
			printStackTrace(e);
		} catch (IOException e) {
			printStackTrace(e);
		} finally {
			try {
				objoutput.close();
			} catch (IOException e) {
				printStackTrace(e);
			}
		}
	}
	
	/**
	 * Writes the addresses of the registered TafelServers to a file.
	 */
	public synchronized void saveTafelAdressenToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("./tafelAdressen" + abteilungsID))) {
			for (int i : tafelAdressen.keySet()) {
				URL address =  tafelAdressen.get(i);
				writer.write(i + " " + address.toString() + "\n");
			}
		} catch (IOException e) {
			printStackTrace(e);
		}

	}
	
	/**
	 * Loads the message queues for each Abteilung from a file.
	 * 
	 * @return queueMap
	 */
	@SuppressWarnings("unchecked")
	private HashMap<Integer, LinkedBlockingDeque<ServerRequest>> loadQueueMapFromFile() {
		HashMap<Integer, LinkedBlockingDeque<ServerRequest>> qMap = new HashMap<Integer, LinkedBlockingDeque<ServerRequest>>();
		FileInputStream fileInput = null;
		ObjectInputStream objinput = null;
		try {
			fileInput = new FileInputStream("./.queueMap" + abteilungsID);
			objinput = new ObjectInputStream(fileInput);
			Object obj = objinput.readObject();
			qMap = (HashMap<Integer, LinkedBlockingDeque<ServerRequest>>) obj;
			print("Queue-Backup geladen!");

		} catch (FileNotFoundException e) {
			print("Kein Queue-Backup gefunden. Erstelle neues Backup...");
			saveQueueMapToFile();
		} catch (IOException | ClassNotFoundException e) {
			print("Fehler beim Lesen des Queue-Backups");
			printStackTrace(e);
		} finally {
			try {
				if (objinput != null) {
					objinput.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return qMap;
	}

	private void loadTafelAdressenFromFile() {
		int lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader("./tafelAdressen" + abteilungsID))) {
			String address = "";
			while ((address = reader.readLine()) != null) {
				lines++;
				String[] addressParts = address.split(" ");
				try {
					registerTafel(Integer.parseInt(addressParts[0]),
							new URL(addressParts[1]));
				} catch (NumberFormatException e) {
					print("NumberFormatException in line " + lines + " " + e.getMessage());
					e.printStackTrace();
				} catch (TafelException e) {
					printStackTrace(e);
				}
			}
			print("Tafel Adressen geladen.");
		} catch (FileNotFoundException e) {
		    print("Keine tafelAdressen" + abteilungsID + " Datei gefunden.");
			printStackTrace(e);
		} catch (IOException e) {
		    print("Fehler beim Lesen der tafelAdressen" + abteilungsID + " Datei.");
			printStackTrace(e);
		}
	}

	private void loadGroupsFromFile() {
		int lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader("./tafelGruppen"))) {
			String address = "";
			while ((address = reader.readLine()) != null) {
				lines++;
				String[] groupParts = address.split(":");
				int groupId = Integer.parseInt(groupParts[0]);
				String[] stringMembers = groupParts[1].split(",");
				// Nur Gruppe einlesen, wenn die Tafel auch darin enthalten ist!
				if (Arrays.asList(stringMembers).contains(Integer.toString(abteilungsID))) {
    				HashSet<Integer> intMembers = new HashSet<Integer>();
    				try {
    					for (int i = 0; i < stringMembers.length; i++) {
    						intMembers.add(Integer.parseInt(stringMembers[i]));
    					}
    					groupMap.put(groupId, intMembers);
    
    				} catch (NumberFormatException e) {
    					print("NumberFormatException in line " + lines + " " + e.getMessage());
    					e.printStackTrace();
    				}
				}
			}
			print("Groups loaded: " + groupMap);
		} catch (FileNotFoundException e) {
		    print("Keine tafelGruppen Datei gefunden.");
			printStackTrace(e);
		} catch (IOException e) {
		    print("Fehler beim Lesen der tafelGruppen Datei.");
			printStackTrace(e);
		}
	}

	/**
	 * Outputs a message on the TafelSever.
	 * 
	 * @param nachricht
	 *            the message
	 */
	public synchronized void print(String nachricht) {
		System.out.println(new Time(System.currentTimeMillis()) + ": " + nachricht);
	}

	public synchronized void printStackTrace(Exception e) {
		e.printStackTrace();
	}

	/**
	 * Outputs all messages of the Anzeigetafel.
	 */
	public synchronized void printMessages() {
		System.out.println(anzeigetafel.toString());
	}

	/**
	 * Returns the anzeigetafel data of the TafelServer
	 * 
	 * @return anzeigetafel
	 */
	public synchronized Anzeigetafel getAnzeigetafel() {
		return anzeigetafel;
	}

	/**
	 * Returns the abteilungsID of the TafelServer
	 * 
	 * @return abteilungsID
	 */
	public synchronized int getAbteilungsID() {
		return abteilungsID;
	}

	/**
	 * Returns the addresses of the registered TafelServers.
	 * 
	 * @return tafelAdressen
	 */
	public HashMap<Integer, URL> getTafelAdressen() {
		return tafelAdressen;
	}
	
	public String getAddressPort(int abtNr) {
	    if (!tafelAdressen.containsKey(abtNr)) {
	        return defaultPort;
	    }
        return tafelAdressen.get(abtNr).toString().split(":")[2].split("/")[0];
    }

	/**
	 * Returns the assigned outboxThread by abteilungsID.
	 * 
	 * @return outboxThreads
	 */
	public HashMap<Integer, OutboxThread> getOutboxThreads() {
		return outboxThreads;
	}

	/**
	 * Returns the message queues by abteilungsID.
	 * 
	 * @return queueMap
	 */
	public HashMap<Integer, LinkedBlockingDeque<ServerRequest>> getQueueMap() {
		return queueMap;
	}
	
	/**
	 * Gets the messages of a given userID if possible.
	 * 
	 * @param userID
	 * @return the messages of the given userID
	 * @throws TafelException
	 *             if Anzeigetafel does not recognize the userID.
	 */
	public synchronized LinkedList<Message> getMessagesByUserID(int userID) throws TafelException {
		// print("Showing Messages to user " + userID);

		return anzeigetafel.getMessagesByUserID(userID);
	}
	

	public synchronized String createMessage(String inhalt, int user, int abtNr) throws TafelException {
	    if (abteilungsID != abtNr) {
	        throw new TafelException("Abteilung " + abtNr + " ist hier nicht berechtigt!");
	    }
		int msgID = anzeigetafel.createMessage(inhalt, user, abtNr, false);
		
		anzeigetafel.saveStateToFile();
		return "Nachricht mit ID=" + msgID + " erstellt!";
	}
	
	public String deleteMessage(int messageID, int user) throws TafelException {
		String antwort = "Nachricht mit ID=" + messageID + " gelöscht!";
		anzeigetafel.deleteMessage(messageID, user);
		
		anzeigetafel.saveStateToFile();
		return antwort;
	}
	
	public String modifyMessage(int messageID, String inhalt, int user) throws TafelException {
		String antwort = "Nachricht mit ID=" + messageID + " geändert!";
		anzeigetafel.modifyMessage(messageID, inhalt, user);

		anzeigetafel.saveStateToFile();
		return antwort;
	}

	/**
	 * Publishes a message if possible. Set the message to public and try to
	 * deliver it to other TafelServers.
	 * 
	 * @param messageID
	 * @param userID
	 * @throws InterruptedException
	 *             if a Message Queue was interrupted.
	 * @throws TafelException
	 *             if the Anzeigetafel rejects the publication.
	 */
	public synchronized String publishMessage(int messageID, int userID, int groupID) throws TafelException {
		String antwort = "Nachricht mit ID=" + messageID + " veröffentlicht!";
		
		anzeigetafel.publishMessage(messageID, userID, groupID);
		System.out.println(groupQueueMap);
		for (LinkedBlockingDeque<ServerRequest> q : groupQueueMap.get(groupID)) {
		    if (q != null) {
			    q.add(new ReceiveRequest(anzeigetafel.getMessages().get(messageID), groupID, new Date()));
		    }
		}
		
		saveQueueMapToFile();
		anzeigetafel.saveStateToFile();
		return antwort;
	}

	public synchronized String deletePublic(int messageID, int userID, int groupID) throws TafelException {
		String antwort = "Nachricht mit ID=" + messageID + " gelöscht in Gruppe:" + groupID + "!";

		if ( !groupMap.containsKey(groupID) ) {
			return "TafelServer ist nicht in gegebener Gruppe=" + groupID + "!";
		}
		
		anzeigetafel.deletePublic(messageID, userID, groupID);

		for (LinkedBlockingDeque<ServerRequest> q : groupQueueMap.get(groupID)) {
			try {
			    if (q != null) {
	                q.put(new DeletePublicRequest(messageID, groupID));
			    }
			} catch (InterruptedException e) {
				print("Message mit ID=" + messageID + " wird nicht überall gelöscht werden!");
			}
		}
		
		saveQueueMapToFile();
		return antwort;
	}

	public synchronized String modifyPublic(int messageID, int userID, int groupID, String newMessage) throws TafelException {
		String antwort = "Nachricht mit ID=" + messageID + " geändert in Gruppe:" + groupID + "!";

		if ( !groupMap.containsKey(groupID) ) {
			return "TafelServer ist nicht in gegebener Gruppe=" + groupID + "!";
		}
		
		anzeigetafel.modifyPublic(messageID, newMessage, userID);
		
		for (LinkedBlockingDeque<ServerRequest> q : groupQueueMap.get(groupID)) {
			try {
			    if (q != null) {
	                q.put(new ModifyPublicRequest(messageID, groupID, newMessage));
			    }
			} catch (InterruptedException e) {
				print("Message mit ID=" + messageID + " wird nicht überall geändert werden!");
			}
		}
		
		saveQueueMapToFile();
		return antwort;
	}


	/* -- Server WS Methods -- */

	public synchronized boolean receiveMessage(int messageID, int userID, int abtNr, String inhalt, Date time, int group) throws TafelException {
		if ( !groupMap.containsKey(group) ) {
			throw new TafelException("TafelServer ist nicht in gegebener Gruppe=" + group + "!");
		}
		anzeigetafel.receiveMessage(new Message(messageID, userID, abtNr, inhalt, true, time), group);
		
		anzeigetafel.saveStateToFile();
		return true;
	}
	
	public synchronized boolean receiveMessage(Message message) throws TafelException {
		for (Integer i : message.getGruppen()) {
			if (groupMap.containsKey(i)) {
			    anzeigetafel.receiveMessage(message, i);
			}
		}
		return true;
	}
	
	public synchronized boolean deletePublicMessage(int msgID, int group) throws TafelException {
		if ( !groupMap.containsKey(group) ) {
			throw new TafelException("TafelServer ist nicht in gegebener Gruppe=" + group + "!");
		}
		anzeigetafel.deletePublicMessage(msgID, group);
		
		anzeigetafel.saveStateToFile();
		return true;
	}
	
	public synchronized boolean modifyPublicMessage(int messageID, String inhalt, int group) throws TafelException {
		if ( !groupMap.containsKey(group) ) {
			throw new TafelException("TafelServer ist nicht in gegebener Gruppe=" + group + "!");
		}
		anzeigetafel.modifyPublicMessage(messageID, group, inhalt);

		anzeigetafel.saveStateToFile();
		return true;
	}
	
	public void setShutdownOnClose(WindowListener wl){
		JFrame guiWindow = gui.getWindow();
		guiWindow.addWindowListener(wl);
	}
	
	public  Set<Integer> getGroupIds(){
		return groupMap.keySet();
	}
	
}
