/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tafelServer;

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
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingDeque;

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
	
	
	public static boolean startServer(int abteilung){
		if (tafelServerInstance == null){
			tafelServerInstance = new TafelServer(abteilung);
			return true;
		} else {
			return false;
		}
	}

	public static TafelServer getServer(){
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
		// hier habe ich den Gui-Part hinzugefügt

		gui = new TafelGUI(anzeigetafel.getAbteilungsID(), this, new int[]{2,3,4,4,4,4,4,4,44,4});
		anzeigetafel.addObserver(gui);
		anzeigetafel.updateState();

		// ende

		queueMap = loadQueueMapFromFile();
		loadTafelAdressenFromFile();
		loadGroupsFromFile();
		buildGroupQueueMap();
		System.out.println(groupMap);
		System.out.println(groupQueueMap);
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
				tafelAdressen.replace(abteilungsID, address);
			}

		} else {
			tafelAdressen.put(abteilungsID, address);
			saveQueueMapToFile();
		}
		if (!queueMap.containsKey(abteilungsID)) {
			queueMap.put(abteilungsID, new LinkedBlockingDeque<ServerRequest>());
		}
		activateHeartbeat(abteilungsID);
		activateQueue(abteilungsID);
		saveTafelAdressenToFile();
		
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
		} // else Queue already active
	}

	/**
	 * Activates a Heartbeat for the given abteilungsID.
	 * 
	 * @param abteilungsID
	 */
	public synchronized void activateHeartbeat(int abteilungsID) {
		if (!heartbeatThreads.containsKey(abteilungsID) || !heartbeatThreads.get(abteilungsID).isAlive()) {
			HeartbeatThread hbt = new HeartbeatThread(abteilungsID, tafelAdressen.get(abteilungsID), this);
			heartbeatThreads.put(abteilungsID, hbt);
			hbt.start();
		}
	}
	
	private void buildGroupQueueMap() {
		groupQueueMap.clear();

		for (Entry<Integer, HashSet<Integer>> entry : groupMap.entrySet()) {
			Integer groupId = entry.getKey();
			HashSet<Integer> groupMembers = entry.getValue();
			HashSet<LinkedBlockingDeque<ServerRequest>> queues = new HashSet<LinkedBlockingDeque<ServerRequest>>();

			for (Integer groupMember : groupMembers) {
				if (groupMember.intValue() != abteilungsID) {
					queues.add(queueMap.get(groupMember));
				}
			}
			groupQueueMap.put(groupId, queues);
		}
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
			print("Fehler beim lesen des Queue-Backups");
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

		} catch (FileNotFoundException e) {
			printStackTrace(e);
		} catch (IOException e) {
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

		} catch (FileNotFoundException e) {
			printStackTrace(e);
		} catch (IOException e) {
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
		
		for (LinkedBlockingDeque<ServerRequest> q : groupQueueMap.get(groupID)) {
			q.add(new ReceiveRequest(anzeigetafel.getMessages().get(messageID), groupID, new Date()));
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
		
		anzeigetafel.deletePublicMessage(messageID, userID, groupID);

		for (LinkedBlockingDeque<ServerRequest> q : groupQueueMap.get(groupID)) {
			try {
				q.put(new DeletePublicRequest(messageID, groupID));
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
		
		anzeigetafel.modifyPublicMessage(messageID, newMessage, userID);
		
		for (LinkedBlockingDeque<ServerRequest> q : groupQueueMap.get(groupID)) {
			try {
				q.put(new ModifyPublicRequest(messageID, groupID, newMessage));
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
	
	public synchronized boolean receiveMessage(Message message) throws TafelException{
		// TODO noch nicht konsistent
		for (Integer i : groupMap.keySet()){
			if(message.getGruppen().contains(i)){
				anzeigetafel.receiveMessage(message, i);
				return true;	// was wenn die erhaltene Message mehrere Gruppen enthält? es würde nur für die erste received 
			}
		}
		return false;
	}
	
	public synchronized boolean deletePublicMessage(int msgID, int group) throws TafelException {
		if ( !groupMap.containsKey(group) ) {
			throw new TafelException("TafelServer ist nicht in gegebener Gruppe=" + group + "!");
		}
		anzeigetafel.deletePublic(msgID, group);
		
		anzeigetafel.saveStateToFile();
		return true;
	}
	
	public synchronized boolean modifyPublicMessage(int messageID, String inhalt, int group) throws TafelException {
		if ( !groupMap.containsKey(group) ) {
			throw new TafelException("TafelServer ist nicht in gegebener Gruppe=" + group + "!");
		}
		anzeigetafel.modifyPublic(messageID, inhalt);

		anzeigetafel.saveStateToFile();
		return true;
	}
	
	
}
