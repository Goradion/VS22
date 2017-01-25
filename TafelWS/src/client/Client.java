package client;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;

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
 * @author Armin + Moser + Andrea
 */


public class Client {
   private static ClientGui clientGui;
   
   private static NewMessageGUI newMessageGui;
   private static String[] menue;
   private static List<Message> msgs;
   
	public static void main(String[] args) {
		String[] abteilungen = new String[] {"Managment","Finanzen","Technik"};
		msgs = new ArrayList<Message>();
		msgs.add(new Message("Nachricht", 1, 2, false, 0));
		msgs.add(new Message("Noch eine Nachricht die wirklich sehr lang sein soll lkjsdfioasopekpoaiksgöokaosdgköadskglkasdfäpgkösdkfglkadäofgkäoadkfgopakeärgkaeokegopaklerohkaösdgjlisrötlrgäpylkdöogijlsdykfgklüxäötkhöoxöfzäjöoxflhäxÖfläphäXrtu ENDE DER NACHTICHT",1,2,false,1));
		
		clientGui = new ClientGui("Tafel-Client",abteilungen);
		menue = new String[] {"Zeige alle Nachrichten","Neue Nachricht"};
		clientGui.actionLogin(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
		clientGui.setConectivity(false);
		
   }
	
	private static void loginActionPerformed(ActionEvent evt) {
		if(clientGui.getUserid() > 0){
			clientGui.showLoggedIn(clientGui.getUserid(), msgs);
			clientGui.setMenue(menue);
			
			clientGui.setPublish(clientGui.getUserid() == 1);
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
			clientGui.setConectivity(true);
		}
	}
	
	private static void menueSelected(ItemEvent evt) {
		switch(clientGui.getSelectedMenue()){
		case "Zeige alle Nachrichten" : 
				clientGui.setMenue(new String[] {"Zeige alle Nachrichten", "Neue Nachricht"});
				
				clientGui.showShowMessages(msgs);
				clientGui.setPublish(clientGui.getUserid() == 1);
				clientGui.addActionSendQuery(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						sendQueryActionPerformed(evt);
					}
				});
				break;
		case "Neue Nachricht" : 
				clientGui.setMenue(new String[] {"Neue Nachricht", "Zeige alle Nachrichten"});
				clientGui.showNewMessage();
				clientGui.setPublish(clientGui.getUserid() == 1);
				clientGui.actionSendNewMessage(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						sendNewMessage(evt);
			            }
				});
				break;
		default :  
			clientGui.showLoggedIn(clientGui.getUserid(), msgs);
			clientGui.setMenue(menue);
			break;
		}
	}
	
	private static void sendNewMessage(ActionEvent evt) throws MalformedURLException{
		//TODO Nachricht versenden lassen
		int userID=0;
		String message ="";
		String abteilung ="";
		userID = clientGui.getUserid();
		message = clientGui.getNewMessage();
		abteilung = clientGui.getAbteilung();

		if ((userID > 0) && message != "" && abteilung != "" )
		{
			TafelWebService port = new TafelWebServiceImplService(new URL("http://localhost:8080/TafelWS/tafelws?wsdl")).getTafelWebServiceImplPort();
		    port.createMessage(message, userID, Integer.parseInt(abteilung), false);
		    clientGui.setNewMessageState("Nachricht versendet!");
			clientGui.repaint();
		}
		else{
			clientGui.setNewMessageState("Fehler in UserID, Message oder Abteilung");
			clientGui.repaint();
		}
		
		
	}
	
	
	
	private static void sendQueryActionPerformed(ActionEvent evt) throws MalformedURLException{
		if(clientGui.getQueryCommand().equals("delete")){
			int userID=0;
			Message msgID;
			userID = clientGui.getUserid();
  			msgID = clientGui.getMsgID();
  
  			if ((userID > 0) && msgID > 0 )
  			{
  				TafelWebService port = new TafelWebServiceImplService(new URL("http://localhost:8080/TafelWS/tafelws?wsdl")).getTafelWebServiceImplPort();
  				port.deleteMessage(Integer.parseInt(msgID),userID);
  			}
  			
		}
		if(clientGui.getQueryCommand().equals("change")){
			clientGui.showEditMessage(clientGui.getSelectedMessage());
			clientGui.setPublish(clientGui.getUserid() == 1);
			clientGui.addActionChangeButton((new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						changeMessage(evt);
			            }
				}));
		}
		if(clientGui.getQueryCommand().equals("publish")){
			
			int userID=0;
			Message msgID;
			userID = clientGui.getUserid();
  			msgID = clientGui.getMsgID();
  			boolean g_one, g_two, g_three,g_four = false; 
  			
  			g_one = clientGui.pruefeGruppe1();
  			g_two = clientGui.pruefeGruppe2();
  			g_three = clientGui.pruefeGruppe3();
  			g_four = clientGui.pruefeGruppe4();
  						
  			
		if ((userID == 1) && msgID > 0 )
		{	
			
			TafelWebService port = new TafelWebServiceImplService(new URL("http://localhost:8080/TafelWS/tafelws?wsdl")).getTafelWebServiceImplPort();
			port.publishMessage(msgID, userID, g_one,g_two,g_three,g_four);			
		}
			
			
		
		
		if(clientGui.getQueryCommand().equals("error")){
			//Was machen wir dann? :D
		}
	}

	private static void changeMessage(ActionEvent evt) {
		// TODO Auto-generated method stub
		Message myMsg = clientGui.getSelectedMessage();
	//TODO Nachricht ändern und speichern
		clientGui.showShowMessages(msgs);
		
	}
}