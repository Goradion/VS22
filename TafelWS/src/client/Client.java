package client;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;

import client.gen.SoapableMessage;
import client.gen.TafelWebService;
import client.gen.TafelWebServiceImplService;
import verteilteAnzeigetafel.Message;
import verteilteAnzeigetafel.TafelException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Armin + Moser + Andrea + Simon
 */

public class Client {
	private static ClientGui clientGui;

	private static NewMessageGUI newMessageGui;
	private static String[] menue;
	private static Map<Integer, Message> msgs;
	private static TafelWebService port;
	private static URL wsdlURL;
	private static HashMap<Integer, URL> tafelAdressen = new HashMap<Integer, URL>();
	private static final int koordinatorID = 1;

	public static void main(String[] args) throws MalformedURLException {
//		if (args.length >= 1) {
//			wsdlURL = new URL(args[0]);
//		} else {
//			wsdlURL = new URL("http://10.9.41.69:8080/TafelWS/tafelws?wsdl");
//		}
		loadTafelAdressenFromFile();
		Set<Integer> keySet = tafelAdressen.keySet();
		Integer[] abteilungen = new Integer[keySet.size()];
		keySet.toArray(abteilungen);
		msgs = new HashMap<Integer, Message>();
		
		clientGui = new ClientGui("Tafel-Client", abteilungen);
		menue = new String[] { "Zeige alle Nachrichten", "Neue Nachricht" };
		clientGui.actionLogin(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loginActionPerformed(evt);
			}
		});
		clientGui.setConectivity(false);

	}

	private static void loginActionPerformed(ActionEvent evt) {
		if (clientGui.getUserid() > 0) {

			clientGui.showLoggedIn(clientGui.getUserid(), msgs);
			clientGui.setMenue(menue);

			clientGui.setPublish(clientGui.getUserid() == koordinatorID);
			clientGui.actionMenuSelect(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent evt) {
					menueSelected(evt);
				}
			});
			clientGui.addActionSendQuery(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					sendQueryActionPerformed(evt);
				}
			});
			clientGui.setConectivity(false);
			System.out.println(tafelAdressen.get(clientGui.getAbteilung()));
			port = new TafelWebServiceImplService(tafelAdressen.get(clientGui.getAbteilung())).getTafelWebServiceImplPort();
			clientGui.setConectivity(true);
			
			resetMenu();
		}
	}

	private static void menueSelected(ItemEvent evt) {
		switch (clientGui.getSelectedMenue()) {
		case "Zeige alle Nachrichten":
			resetMenu();
			break;
		case "Neue Nachricht":
			clientGui.setMenue(new String[] { "Neue Nachricht", "Zeige alle Nachrichten" });
			clientGui.showNewMessage();
			clientGui.setPublish(clientGui.getUserid() == koordinatorID);
			clientGui.actionSendNewMessage(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					sendNewMessage(evt);
				}
			});
			break;
		default:
			clientGui.showLoggedIn(clientGui.getUserid(), msgs);
			clientGui.setMenue(menue);
			break;
		}
	}

	private static void sendNewMessage(ActionEvent evt) {
		int userID = 0;
		String message = "";
		int abteilung = 0;
		userID = clientGui.getUserid();
		message = clientGui.getNewMessage();
		abteilung = clientGui.getAbteilung();

		// TODO ordentlich checken
		if ((userID > 0) && message != "" && abteilung != 0) {
			// TODO WRAP
			port.createMessage(message, userID, abteilung);
			clientGui.setNewMessageState("Nachricht versendet!");
			clientGui.repaint();
		} else {
			clientGui.setNewMessageState("Fehler in UserID, Message oder Abteilung");
			clientGui.repaint();
		}

	}

	private static void sendQueryActionPerformed(ActionEvent evt)
	// throws MalformedURLException
	{
		QueryCommand queryCommand = clientGui.getQueryCommand();
		switch (queryCommand) {
		case CHANGE:
			editMessage(evt);
			break;
		case DELETE:
			deleteMessage(evt);
			break;
		case ERROR:
			JOptionPane.showMessageDialog(clientGui, "Bitte vorher change, delete oder publish ankreuzen!");
			break;
		case PUBLISH:
			publishMessage(evt);
			break;
		default:
			JOptionPane.showMessageDialog(clientGui, "Unsupported QueryCommand!");
			break;
		}

		// if (clientGui.getQueryCommand().equals("delete")) {
		// int userID = 0;
		// Message msgID;
		// userID = clientGui.getUserid();
		// msgID = clientGui.getMsgID();
		//
		// // if ((userID > 0) && msgID > 0 )
		// // {
		// // TafelWebService port = new TafelWebServiceImplService(new
		// //
		// URL("http://localhost:8080/TafelWS/tafelws?wsdl")).getTafelWebServiceImplPort();
		// // port.deleteMessage(Integer.parseInt(msgID),userID);
		// // }
		//
		// }
		// if (clientGui.getQueryCommand().equals("change")) {
		// clientGui.showEditMessage(clientGui.getSelectedMessage());
		// clientGui.setPublish(clientGui.getUserid() == 1);
		// clientGui.addActionChangeButton((new java.awt.event.ActionListener()
		// {
		// public void actionPerformed(ActionEvent evt) {
		// submitChangedMessage(evt);
		// }
		// }));
		// }
		// if (clientGui.getQueryCommand().equals("publish")) {
		//
		// int userID = 0;
		// Message msgID;
		// userID = clientGui.getUserid();
		// msgID = clientGui.getMsgID();
		// boolean g_one, g_two, g_three, g_four = false;
		//
		// g_one = clientGui.pruefeGruppe1();
		// g_two = clientGui.pruefeGruppe2();
		// g_three = clientGui.pruefeGruppe3();
		// g_four = clientGui.pruefeGruppe4();
		// }
		//
		// // if ((userID == 1) && Integer.parseInt(msgID) > 0 )
		// // {
		// //
		// // TafelWebService port = new TafelWebServiceImplService(new
		// //
		// URL("http://localhost:8080/TafelWS/tafelws?wsdl")).getTafelWebServiceImplPort();
		// // port.publishMessage(msgID, userID, g_one,g_two,g_three,g_four);
		// // }
		//
		// if (clientGui.getQueryCommand().equals("error")) {
		// // Was machen wir dann? :D
		// }
	}

	private static void deleteMessage(ActionEvent evt) {
		int userid = clientGui.getUserid();
		Message selectedMessage = clientGui.getSelectedMessage();
		if (selectedMessage == null) {
			JOptionPane.showMessageDialog(clientGui, "Es gibt keine nachricht mit der gewälten ID!");
			return;
		}
		String oeffentlichString = selectedMessage.isOeffentlich() ? "oeffentliche" : "lokale";
		String confirmMessage = "Wollen Sie wirklich die " + oeffentlichString + " Nachricht mit der ID "
				+ selectedMessage.getMessageID() + " loeschen?";
		int confirmed = JOptionPane.showConfirmDialog(clientGui, confirmMessage, "", JOptionPane.YES_NO_OPTION);

		if (confirmed == JOptionPane.YES_OPTION) {
			port.deleteMessage(selectedMessage.getMessageID(), userid);
			resetMenu();
		}

	}

	private static void editMessage(ActionEvent evt) {
		Message selectedMessage = clientGui.getSelectedMessage();
		if (selectedMessage == null) {
			JOptionPane.showMessageDialog(clientGui, "Es gibt keine nachricht mit der gewählten ID!");
			return;
		}
		clientGui.showEditMessage(selectedMessage);
		clientGui.setPublish(clientGui.getUserid() == koordinatorID);
		clientGui.addActionChangeButton((new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				submitChangedMessage(evt);
			}
		}));
	}

	private static void publishMessage(ActionEvent evt) {
		Message selectedMessage = clientGui.getSelectedMessage();
		if (selectedMessage == null) {
			JOptionPane.showMessageDialog(clientGui, "Es gibt keine nachricht mit der gew�hlten ID!");
			return;
		}

		int gruppe = 0; // TODO gruppe auswaehlen
		// TODO WRAP und ordentlich machen
		if (clientGui.pruefeGruppe1()){
			port.publishMessage(selectedMessage.getMessageID(), clientGui.getUserid(), 1);
		}
		if (clientGui.pruefeGruppe2()){
			port.publishMessage(selectedMessage.getMessageID(), clientGui.getUserid(), 2);
		}
		if (clientGui.pruefeGruppe3()){
			port.publishMessage(selectedMessage.getMessageID(), clientGui.getUserid(), 3);
		}
		if (clientGui.pruefeGruppe4()){
			port.publishMessage(selectedMessage.getMessageID(), clientGui.getUserid(), 4);
		}
		
		
	}

	private static void submitChangedMessage(ActionEvent evt) {
		String newInhalt = clientGui.getEditedMessageText();
		Message myMsg = clientGui.getSelectedMessage();

		// TODO WRAP
		port.modifyMessage(myMsg.getMessageID(), newInhalt, clientGui.getUserid());
		resetMenu();
	}

	private static void updateMessages() {
		// TODO WRAP
		List<SoapableMessage> showMessages = port.showMessages(clientGui.getUserid());
		msgs.clear();
		for (SoapableMessage soapableMessage : showMessages) {
			if (soapableMessage.isOeffentlich() ){
			}
			Message message = new Message(soapableMessage.getMessageID(), soapableMessage.getUserID(),
					soapableMessage.getAbtNr(), soapableMessage.getInhalt(), soapableMessage.isOeffentlich(),
					soapableMessage.getTime().toGregorianCalendar().getTime());
			for (Integer i : soapableMessage.getGroups()) {
				message.addGroup(i);
			}
			msgs.put(soapableMessage.getMessageID(), message);
		}
		clientGui.showShowMessages(msgs);
	}

	private static void resetMenu() {
		clientGui.setMenue(new String[] { "Zeige alle Nachrichten", "Neue Nachricht" });
		updateMessages();
		clientGui.setPublish(clientGui.getUserid() == koordinatorID);
		clientGui.addActionSendQuery(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				sendQueryActionPerformed(evt);
			}
		});
	}
	
	private static void loadTafelAdressenFromFile() {
		int lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader("./tafelAdressen"))) {
			String address = "";
			while ((address = reader.readLine()) != null) {
				lines++;
				String[] addressParts = address.split(" ");
				try {
					tafelAdressen.put(Integer.parseInt(addressParts[0]), new URL(addressParts[1]));
				} catch (NumberFormatException e) {
					System.err.println("NumberFormatException in line: " + lines);
					e.printStackTrace();
				} catch (MalformedURLException e){
					System.err.println("MalformedURLException in line: " + lines);
				}
			}
			System.out.println("Tafel Adressen geladen.");
		} catch (FileNotFoundException e) {
			System.err.println("Keine tafelAdressen Datei gefunden.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fehler beim Lesen der tafelAdressen Datei.");
			e.printStackTrace();
		}
	}
}