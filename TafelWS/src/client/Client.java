package client;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import client.gen.SoapableMessage;
import client.gen.TafelWebService;
import client.gen.TafelWebServiceImplService;
import verteilteAnzeigetafel.Message;

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
	private static Set<Integer> groups;

	private static Integer[] groupArray;

	public static void main(String[] args) throws MalformedURLException {
		// if (args.length >= 1) {
		// wsdlURL = new URL(args[0]);
		// } else {
		// wsdlURL = new URL("http://10.9.41.69:8080/TafelWS/tafelws?wsdl");
		// }
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
			clientGui.setConectivity(false);
			port = new TafelWebServiceImplService(tafelAdressen.get(clientGui.getAbteilung()))
					.getTafelWebServiceImplPort();
			groups = new HashSet<Integer>(port.getGroupIds());
			groupArray = groups.toArray(new Integer[groups.size()]);
			clientGui.showLoggedIn(clientGui.getUserid(), msgs);
			clientGui.setPublish(clientGui.getUserid() == koordinatorID, groupArray);
			clientGui.setConectivity(true);

			clientGui.setMenue(menue);

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
			clientGui.setPublish(clientGui.getUserid() == koordinatorID, groupArray);
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

		// TODO ordentlich checken
		if ((userID > 0) && message != "") {
			// TODO WRAP
			print(port.createMessage(message, userID));
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
		// // print(port.deleteMessage(Integer.parseInt(msgID),userID));
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
		// // print(port.publishMessage(msgID, userID,
		// g_one,g_two,g_three,g_four));
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
			JOptionPane.showMessageDialog(clientGui, "Kein Zugriff auf die Nachricht mit der gewaelten ID!");
			return;
		}
		String oeffentlichString = selectedMessage.isOeffentlich() ? "oeffentliche" : "lokale";
		String confirmMessage = "Wollen Sie wirklich die " + oeffentlichString + " Nachricht mit der ID "
				+ selectedMessage.getMessageID() + " loeschen?";
		int confirmed = JOptionPane.showConfirmDialog(clientGui, confirmMessage, "", JOptionPane.YES_NO_OPTION);

		if (confirmed == JOptionPane.YES_OPTION) {
			if (selectedMessage.isOeffentlich()) {
				deletePublicMessage(selectedMessage.getMessageID());
			} else {
				print(port.deleteMessage(selectedMessage.getMessageID(), userid));
			}
			resetMenu();
		}

	}

	private static void deletePublicMessage(int msgID) {
		if (clientGui.pruefeGruppe1()) {
			print(port.deletePublic(msgID, clientGui.getUserid(), groupArray[0]));
		}
		if (clientGui.pruefeGruppe2()) {
			print(port.deletePublic(msgID, clientGui.getUserid(), groupArray[1]));
		}
		if (clientGui.pruefeGruppe3()) {
			print(port.deletePublic(msgID, clientGui.getUserid(), groupArray[2]));
		}
		if (clientGui.pruefeGruppe4()) {
			print(port.deletePublic(msgID, clientGui.getUserid(), groupArray[3]));
		}
		if (!clientGui.pruefeGruppe1() && !clientGui.pruefeGruppe2() && !clientGui.pruefeGruppe3()
				&& !clientGui.pruefeGruppe4()) {
			JOptionPane.showMessageDialog(clientGui, "Nachricht ist oeffentlich. Es muss mindestens eine Gruppe ausgewaehlt sein!");
		}
	}

	private static void editMessage(ActionEvent evt) {
		Message selectedMessage = clientGui.getSelectedMessage();
		if (selectedMessage == null) {
			JOptionPane.showMessageDialog(clientGui, "Es gibt keine nachricht mit der gewaehlten ID!");
			return;
		}
		clientGui.showEditMessage(selectedMessage);
		clientGui.setPublish(clientGui.getUserid() == koordinatorID, groupArray);
		clientGui.addActionChangeButton((new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				submitChangedMessage(evt);
			}
		}));
	}

	private static void publishMessage(ActionEvent evt) {
		Message selectedMessage = clientGui.getSelectedMessage();
		if (selectedMessage == null) {
			JOptionPane.showMessageDialog(clientGui, "Es gibt keine nachricht mit der gewaehlten ID!");
			return;
		}

		// TODO WRAP und ordentlich machen
		if (clientGui.pruefeGruppe1()) {
			print(port.publishMessage(selectedMessage.getMessageID(), clientGui.getUserid(), groupArray[0]));
		}
		if (clientGui.pruefeGruppe2()) {
			print(port.publishMessage(selectedMessage.getMessageID(), clientGui.getUserid(), groupArray[1]));
		}
		if (clientGui.pruefeGruppe3()) {
			print(port.publishMessage(selectedMessage.getMessageID(), clientGui.getUserid(), groupArray[2]));
		}
		if (clientGui.pruefeGruppe4()) {
			print(port.publishMessage(selectedMessage.getMessageID(), clientGui.getUserid(), groupArray[3]));
		}
		if (!clientGui.pruefeGruppe1() && !clientGui.pruefeGruppe2() && !clientGui.pruefeGruppe3()
				&& !clientGui.pruefeGruppe4()) {
			JOptionPane.showMessageDialog(clientGui, "Mindestens eine Gruppe muss ausgewaehlt sein!");
		} else {

			resetMenu();
		}

	}

	private static void submitChangedMessage(ActionEvent evt) {
		String newInhalt = clientGui.getEditedMessageText();
		Message myMsg = clientGui.getSelectedMessage();

		// TODO WRAP
		if (myMsg.isOeffentlich()) {
			print(port.modifyPublic(myMsg.getMessageID(), clientGui.getUserid(), newInhalt));
		} else {
			print(port.modifyMessage(myMsg.getMessageID(), clientGui.getUserid(), newInhalt));
		}
		resetMenu();
	}

	private static void updateMessages() {
		// TODO WRAP
		List<SoapableMessage> showMessages = port.showMessages(clientGui.getUserid());
		msgs.clear();
		for (SoapableMessage soapableMessage : showMessages) {
			if (soapableMessage.isOeffentlich()) {
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
		clientGui.setPublish(clientGui.getUserid() == koordinatorID, groupArray);
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
				} catch (MalformedURLException e) {
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

	private static void print(String nachricht) {
		System.out.println(new Time(System.currentTimeMillis()) + ": " + nachricht);
	}
}