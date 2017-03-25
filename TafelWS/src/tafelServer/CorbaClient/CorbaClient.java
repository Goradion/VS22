package tafelServer.CorbaClient;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import VS2.MessageboardServerInterface;
import VS2.MessageboardServerInterfaceHelper;
import VS2.UserData;

/**
 * Klasse zum senden von Nachrichten vom SOAP-System zum CORBA-System
 */
public class CorbaClient {
	private ORB orb; // Referenz zum Ordb-Dienst
	private MessageboardServerInterface mbImpl; // Interface um mit dem Server
												// zu Kommiunizieren
	private String serverIP = "";
	private String serverPort = "";
	private String method = "DataServiceName1";
	private UserData userData = null; // Daten des angemeldeten Benutzers
	private boolean userConnected = false; // ist true wenn der Benutzer
											// erfolgreich angemeldet wurde
	private boolean serverConnected = false; // Ist true wenn die
												// connectToServer erfolgreich
												// war

	/* Konstruktor erwartet CORBA-Server IP und Port */
	public CorbaClient(String ip, String port) {
		this.serverIP = ip;
		this.serverPort = port;
		this.userData = new UserData();
		this.userData.userID = 12345;
		this.userData.password = "test";
		this.userData.userName = "User1";
	}

	/* Methode stellt die Verbindung zum Server her */
	public void connectToServer() throws Exception {
		// Parameter für den Server-Connect
		String[] param = new String[] { "-ORBInitialPort", serverPort, "-ORBInitialHost",
				this.serverIP };
		// Initialisiere ORB und beschaffe Zugang zum 'NameService'
		this.orb = ORB.init(param, null);
		// Root-Naming-Kontext
		org.omg.CORBA.Object objRef;
		objRef = this.orb.resolve_initial_references("NameService");
		// Use NamingContextExt instead of NamingContext. This is
		// part of the Interoperable naming Service.
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		// Objekt-Referenzname der Server-Klasse bei dem Orb-Dienst
		this.mbImpl = MessageboardServerInterfaceHelper.narrow(ncRef.resolve_str(this.method));
		this.serverConnected = true;
	}

	/* Methode meldet den Benutzer am Server an */
	public boolean connectUser(String userName, String passwort) {
		/* Pruefen ob Server verbunden ist um User-Login auszufuehren */
		if (this.serverConnected) {
			this.userConnected = true;
		}
		return userConnected;
	}

	/*
	 * Methode ruft die CreateNewMessage-Funktion des Corba-Servers auf
	 */
	public boolean createMessage(String message, String messageID, int serverNr, int userID) {

		return mbImpl.createMessageSoap(message, messageID, serverNr, this.userData);
	}

	/* löschen einer Nachricht des Corba-Servers */
	public boolean deleteMessage(String messageID, int userID) {

		return mbImpl.deleteMessageSoap(messageID, this.userData);
	}

	/* Methode zum aendern einer Nachricht */
	public boolean modifyMessage(String message, String messageID, int serverNr, int userID) {
		return mbImpl.modifyMessageSoap(message, messageID, serverNr, this.userData);
	}

	/**/
	public boolean isServerConnected() {
		return this.serverConnected;
	}

	/**/
	public boolean isUserConnected() {
		return this.userConnected;
	}

	/*
	 * Methode macht einen disconnect vom Server
	 */
	public void disconnectFromServer() {
		orb.destroy();
		this.serverConnected = false;
		this.userConnected = false;
	}
}
