package verteilteAnzeigetafel;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class Anzeigetafel extends Observable implements Serializable {

	private static final long serialVersionUID = 4032175286694659532L;
	private String TAFELNAME;
	private final static String TAFELPFAD = "./.ANZEIGETAFEL/";
	private String lastID;
	private final int abteilungsID;
	private int messageAnzahl;
	private int msgLaufNr;
	private final int koordinatorID;
	private LinkedHashMap<Integer, Message> messages;
	/* msgID, msg */
	private HashSet<Integer> userIDs;
	private HashMap<Integer, LinkedList<Integer>> userMsgs;

	/* userID, List<msgID> */

	public Anzeigetafel(int abtNr) {
		this.abteilungsID = abtNr;
		this.koordinatorID = 1;
		this.TAFELNAME = "tafel" + abtNr;
		this.messageAnzahl = 0;
		lastID = null;
		this.messages = new LinkedHashMap<Integer, Message>();
		this.userMsgs = new HashMap<Integer, LinkedList<Integer>>();
		this.userIDs = new HashSet<Integer>();
		/* Fuege 5 users ein, die zu dieser Anzeigetafel gehoeren */
		for (int i = 1; i < 6; i++) {
			userIDs.add(i);
		}
		for (int i = 1; i < 6; i++) {
			userMsgs.put(i, new LinkedList<Integer>());
		}
	}

	public synchronized boolean isUser(int userID) {
		return userIDs.contains(userID);
	}

	public synchronized boolean isCoordinator(int userID) {
		return userID == koordinatorID;
	}

	/**
	 * Changes the content of a message if the enquirer is a valid user and has
	 * the needed permissions.
	 * 
	 * @param messageID
	 * @param inhalt
	 * @param user
	 * @throws TafelException
	 */
	public synchronized void modifyMessage(int messageID, String inhalt, int user) throws TafelException {

		if (messages.containsKey(messageID)) {
			Message curMessage = messages.get(messageID);
			if (curMessage.getAbtNr() != abteilungsID) {
				throw new TafelException("Keine Berechtigung für diese Nachricht!");
			}
			if (user == curMessage.getUserID() || isCoordinator(user)) {
				Message msg = new Message(inhalt, curMessage.getUserID(), curMessage.getAbtNr(),
						curMessage.isOeffentlich(), curMessage.getMessageID());
				messages.replace(messageID, msg);
			} else {
				throw new TafelException("User " + user + " nicht berechtigt zum Modifizieren");
			}
		} else {
			throw new TafelException("Keine Message mit ID " + messageID + " gefunden!");
		}
		updateState();
	}

	/**
	 * Sets a message from "non-public" to "public" if it's a valid message and
	 * the enquirer is the coordinator.
	 * 
	 * @param messageID
	 * @param user
	 * @throws TafelException
	 */
	public synchronized void publishMessage(int messageID, int user, int gruppe) throws TafelException {
		if (!messages.containsKey(messageID)) {
			throw new TafelException("Keine Message mit ID " + messageID + " gefunden!");
		}
		Message curMessage = messages.get(messageID);
		if (curMessage.getAbtNr() != abteilungsID) {
			throw new TafelException("Keine Berechtigung für diese Nachricht!");
		}
		if (isCoordinator(user)) {
			curMessage.setOeffentlich();
			curMessage.addGroup(gruppe);
		} else {
			throw new TafelException("Keine Berechtigung zum Publizieren!");
		}
		updateState();
	}

	/**
	 * Deletes a message if it's a valid message and the enquirer has needed
	 * permissions.
	 * 
	 * @param messageID
	 * @param user
	 * @throws TafelException
	 */
	public synchronized void deleteMessage(int messageID, int user) throws TafelException {
		if (messages.containsKey(messageID)) {
			Message currentMessage = messages.get(messageID);
			if (currentMessage.getAbtNr() != abteilungsID) {
				throw new TafelException("Keine Berechtigung für diese Nachricht!");
			}
			
			if (currentMessage.isOeffentlich()) {
				throw new TafelException("Nachricht " + messageID + " ist oeffentlich!");
			}
			
			if (user == messages.get(messageID).getUserID() || isCoordinator(user)) {
				userMsgs.get(messages.get(messageID).getUserID()).remove(new Integer(messageID));
				messages.remove(messageID, messages.get(messageID));
			}
			 else {
				throw new TafelException("User " + user + " nicht berechtigt zum Loeschen");
			}
		} else {
			throw new TafelException("Keine Message mit ID " + messageID + " gefunden!");
		}
		updateState();
	}

	/**
	 * Creates a new Message if the enquirer is a valid user and has needed
	 * permissions.
	 * 
	 * @param inhalt
	 * @param user
	 * @param oeffentlich
	 * @return id of the created message
	 * @throws TafelException
	 */
	public synchronized int createMessage(String inhalt, int user, boolean oeffentlich)
			throws TafelException {
		if (!userIDs.contains(user)) {
			throw new TafelException("Keine Berechtigung zum Erstellen!");
		}
		int msgID = Integer.parseInt(getNewMsgID(user));
		Message nMsg = new Message(inhalt, user, abteilungsID, oeffentlich, msgID);
		messages.put(nMsg.getMessageID(), nMsg);
		/* noch kein user da */
		if (!userMsgs.containsKey(user)) {
			userMsgs.put(user, new LinkedList<Integer>());
		}
		userMsgs.get(user).add(msgID);
		messageAnzahl++;
		msgLaufNr++;

		updateState();
		return msgID;
	}

	private String getNewMsgID(int userID) {
		lastID = "" + abteilungsID + userID + msgLaufNr;
		return lastID;
	}

	public synchronized int getAbteilungsID() {
		return abteilungsID;
	}

	public synchronized int getMessageAnzahl() {
		return messageAnzahl;
	}

	public synchronized int getKoordinatorID() {
		return koordinatorID;
	}

	public synchronized HashMap<Integer, Message> getMessages() {
		return messages;
	}

	// public HashMap getGlobalMessages() {
	// return globalMessages;
	// }
	public synchronized HashSet<Integer> getUserIDs() {
		return userIDs;
	}

	public synchronized String getLastID() {
		return lastID;
	}

	/**
	 * Saves Anzeigetafel to a file.
	 */
	public synchronized void saveStateToFile() {

		FileOutputStream fileoutput = null;
		ObjectOutputStream objoutput = null;
		try {
			if (!new File(TAFELPFAD).exists()) {
				new File(TAFELPFAD).mkdirs();
			}
			fileoutput = new FileOutputStream(TAFELPFAD + TAFELNAME);
			objoutput = new ObjectOutputStream(fileoutput);
			objoutput.writeObject(this);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Anzeigetafel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Anzeigetafel.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				objoutput.close();
			} catch (IOException ex) {
				Logger.getLogger(Anzeigetafel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * Loads an instance of Anzeigetafel from file if it's available.
	 * 
	 * @return Anzeigetafel
	 * @throws verteilteAnzeigetafel.TafelException
	 */
	public synchronized static Anzeigetafel loadStateFromFile(int abtNr) {
		Anzeigetafel at = null;

		FileInputStream fileinput = null;
		ObjectInputStream objinput = null;
		try {
			fileinput = new FileInputStream(TAFELPFAD + "tafel" + abtNr);
			objinput = new ObjectInputStream(fileinput);
			at = (Anzeigetafel) objinput.readObject();
		} catch (FileNotFoundException ex) {
			System.out.println("keine tafeldatei gefunden!");
			// Logger.getLogger(Anzeigetafel.class.getName()).log(Level.SEVERE,
			// null, ex);
		} catch (IOException | ClassNotFoundException ex) {
			Logger.getLogger(Anzeigetafel.class.getName()).log(Level.SEVERE, null, ex);
		}

		return at;
	}

	/**
	 * Returns a list of messages to the provided user id.
	 * 
	 * @param userID
	 * @return list of messages
	 * @throws TafelException
	 */
	public synchronized LinkedList<Message> getMessagesByUserID(int userID) throws TafelException {
		if (!userMsgs.containsKey(userID)) {
			throw new TafelException("Kein User" + userID + "gefunden! ");
		}
		if (isCoordinator(userID)) {
			LinkedList<Message> msgs = getLocalMsgs();
			msgs.addAll(getGlobalMsgs());
			return msgs;
		}
		LinkedList<Integer> umsgIDs = userMsgs.get(userID);
		LinkedList<Message> uMsgs = new LinkedList<Message>();
		for (Integer element : umsgIDs) {
			uMsgs.add(messages.get(element)); 
		}
		return uMsgs;
	}

	/**
	 * Returns the state of Anzeigetafel in a string-form.
	 * 
	 * @return
	 */
	@Override
	public synchronized String toString() {
		String str = "";
		for (HashMap.Entry<Integer, Message> entry : messages.entrySet()) {
			str = str + "User: " + entry.getValue().getUserID() + " / " + entry.getValue() + "\n\t\t"
					+ entry.getValue().getInhalt() + "\n";

		}
		return str;
	}

	/**
	 * Receives a message and puts it into the messages-list.
	 * 
	 * @param msg
	 * @throws TafelException
	 */
	public synchronized void receiveMessage(Message msg, int group) throws TafelException {
	    if (msg.getAbtNr() == abteilungsID) {
	        throw new TafelException("msg.getAbtNr()==abteilungsID");
	    }
	    int msgID = msg.getMessageID();
	    if (!messages.containsKey(msgID)) {
	        messages.put(msgID, msg);
	        userMsgs.get(msg.getUserID()).add(msgID); 
	    } else {
    		throw new TafelException("Message mit ID: " + msgID + ", ist bereits vorhanden!" );
    	}
	    messages.get(msgID).addGroup(group);
	    
	    updateState();
	}
	
	public synchronized void receiveMessageCorba(Message msg) throws TafelException {
		int msgID = msg.getMessageID();
    	if (!messages.containsKey(msgID)) {
    		messages.put(msgID, msg);
    		userMsgs.get(msg.getUserID()).add(msgID); 
    	} else {
    		throw new TafelException("Message mit ID: " + msgID + ", ist bereits vorhanden!" );
    	}
    	
		updateState(); // TODO in GUI Feld fuer Corba Messages
	}
        
        
	

	/**
	 * Returns a list of local messages.
	 * 
	 * @return
	 */
	public synchronized LinkedList<Message> getLocalMsgs() {
		LinkedList<Message> pm = new LinkedList<Message>();
		for (HashMap.Entry<Integer, Message> entry : messages.entrySet()) {
			if (!entry.getValue().isOeffentlich()) {
				pm.add(entry.getValue());
			}
		}
		return pm;
	}

	/**
	 * Returns a list of global (published) messages.
	 * 
	 * @return
	 */
	public synchronized LinkedList<Message> getGlobalMsgs() {
		LinkedList<Message> lm = new LinkedList<Message>();
		for (HashMap.Entry<Integer, Message> entry : messages.entrySet()) {
			if (entry.getValue().isOeffentlich()) {
				lm.add(entry.getValue());
			}
		}
		return lm;
	}

	public Message getMessageByID(int messageID) {
		return messages.get(messageID);
	}
	
	public HashSet<Message> getGroupMsgs(int group){
		HashSet<Message> groupMessages = new HashSet<Message>();
		for(Message m : messages.values()){
			if(m.getGruppen().contains(group))
				groupMessages.add(m);
		}
		return groupMessages;
	}
	public int getUserByMessage(int messageID) throws TafelException {
		int user = 0;
		for (Integer userID : userMsgs.keySet()) {
			for (Integer message : userMsgs.get(userID)) {
				if (message == messageID) {
					user = userID;
					break;
				}
			}
		}
		if (user == 0)
			throw new TafelException("Kein User zu Message " + messageID + " gefunden!");
		return user;
	}

	public void updateState() {
		setChanged();
		notifyObservers();
	}

	public synchronized void deletePublic(int messageID, int user, int group) throws TafelException {

		if (messages.containsKey(messageID)) {
			Message curMessage = messages.get(messageID);
			if (!curMessage.isOeffentlich()) {
				throw new TafelException("Nachricht mit ID =" + messageID + " nicht öffentlich!");
			}
			if (curMessage.getAbtNr() != abteilungsID) {
				throw new TafelException("Keine Berechtigung für diese Nachricht!");
			}
			if (isCoordinator(user)) {
				curMessage.removeGroup(group);
				/*
				 * if ( !curMessage.isOeffentlich() ) {
				 * messages.remove(messageID); userMsgs.get(user).remove(new
				 * Integer(messageID)); }
				 */
			} else {
				throw new TafelException("User " + user + " nicht berechtigt zum Loeschen");
			}
		} else {
			throw new TafelException("Keine Message mit ID " + messageID + " gefunden!");
		}
		updateState();
	}

	public synchronized void deletePublicMessage(int messageID, int group) throws TafelException {

		if (messages.containsKey(messageID)) {
			Message curMessage = messages.get(messageID);
			if (curMessage.getAbtNr() == abteilungsID) {
				throw new TafelException("Keine externe Nachricht!");
			}
			if (!curMessage.getGruppen().contains(group)) {
                throw new TafelException("Nachricht ist nicht in Gruppe " + group + "!");
            }
			curMessage.removeGroup(group);
			if (!curMessage.isOeffentlich()) {
				messages.remove(messageID);
				userMsgs.get(curMessage.getUserID()).remove(new Integer(messageID));
			}
		} else {
			throw new TafelException("Keine Message mit ID " + messageID + " gefunden!");
		}

		updateState();
	}
	
	public synchronized void deleteMessageCorba(int messageID) throws TafelException {

        if (messages.containsKey(messageID)) {
            Message curMessage = messages.get(messageID);
            if (!curMessage.isOeffentlich()) {
                messages.remove(messageID);
                userMsgs.get(curMessage.getUserID()).remove(new Integer(messageID));
            } else {
                throw new TafelException("Message " + messageID + " ist oeffentlich in Gruppen " + curMessage.getGruppen().toString());
            }
        } else {
            throw new TafelException("Keine Message mit ID " + messageID + " gefunden!");
        }

        updateState();
    }

	public synchronized void modifyPublic(int messageID, String inhalt, int user) throws TafelException {

		if (messages.containsKey(messageID)) {
			Message curMessage = messages.get(messageID);
			if (!curMessage.isOeffentlich()) {
				throw new TafelException("Nachricht mit ID =" + messageID + " nicht öffentlich!");
			}
			if (curMessage.getAbtNr() != abteilungsID) {
				throw new TafelException("Keine Berechtigung für diese Nachricht!");
			}
			if (isCoordinator(user)) {
				Message msg = new Message(inhalt, curMessage.getUserID(), curMessage.getAbtNr(),
						curMessage.isOeffentlich(), curMessage.getMessageID(), curMessage.getGruppen());
				messages.replace(messageID, msg);
			} else {
				throw new TafelException("User " + user + " nicht berechtigt zum Loeschen");
			}
		} else {
			throw new TafelException("Keine Message mit ID " + messageID + " gefunden!");
		}
		updateState();
	}

	public synchronized void modifyPublicMessage(int messageID, String inhalt) throws TafelException {

		if (messages.containsKey(messageID)) {
			Message curMessage = messages.get(messageID);
			if (curMessage.getAbtNr() == abteilungsID) {
				throw new TafelException("Keine externe Nachricht!");
			}
			Message msg = new Message(inhalt, curMessage.getUserID(), curMessage.getAbtNr(), curMessage.isOeffentlich(),
					curMessage.getMessageID(), curMessage.getGruppen());
			messages.replace(messageID, msg);
		} else {
			throw new TafelException("Keine Message mit ID " + messageID + " gefunden!");
		}
		updateState();
	}
}
