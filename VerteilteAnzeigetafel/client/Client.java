package client;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

import verteilteAnzeigetafel.Message;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Armin
 */


public class Client {
   private static ClientGui clientGui;
   private static String[] menue;
   private static List<Message> msgs;
   
	public static void main(String[] args) {
		String[] abteilungen = new String[] {"Managment","Finanzen"};
		msgs = new ArrayList<Message>();
		msgs.add(new Message("Nachricht", 1, 2, false, 0));
		msgs.add(new Message("Noch eine Nachricht die wirklich sehr lang sein soll lkjsdfioasopekpoaiksgöokaosdgköadskglkasdfäpgkösdkfglkadäofgkäoadkfgopakeärgkaeokegopaklerohkaösdgjlisrötlrgäpylkdöogijlsdykfgklüxäötkhöoxöfzäjöoxflhäxÖfläphäXrtu",1,2,false,1));
		
		clientGui = new ClientGui("Tafel-Client",abteilungen);
		menue = new String[] {"Zeige alle Nachrichten","Neue Nachricht"};
		clientGui.actionLogin(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
		
   }
	
	private static void loginActionPerformed(ActionEvent evt) {
		if(clientGui.getUserid() > 0){
			clientGui.showLoggedIn(clientGui.getUserid(), msgs);
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
		}
	}
	
	private static void menueSelected(ItemEvent evt) {
		switch(clientGui.getSelectedMenue()){
		case "Zeige alle Nachrichten" : 
				clientGui.showShowMessages(msgs);
				break;
		case "Neue Nachricht" : 
				clientGui.showNewMessage();
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
	
	private static void sendNewMessage(ActionEvent evt){
		clientGui.setNewMessageState("Nachricht versendet!");
		clientGui.repaint();
	}
	
	private static void sendQueryActionPerformed(ActionEvent evt){
		if(clientGui.getQueryCommand().equals("delete")){
			
		}
		if(clientGui.getQueryCommand().equals("change")){
			clientGui.showEditMessage(clientGui.getSelectedMessage());
		}
		if(clientGui.getQueryCommand().equals("publish")){
			
		}
		if(clientGui.getQueryCommand().equals("error")){
			//Was machen wir dann? :D
		}
	}
}
