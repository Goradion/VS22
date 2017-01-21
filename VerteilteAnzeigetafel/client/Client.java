package client;

import java.awt.event.ActionEvent;

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
   
	public static void main(String[] args) {
		clientGui = new ClientGui("Tafel-Client");
		clientGui.actionLogin(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
		//clientGui.showNewMessage();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
	
	private static void loginActionPerformed(ActionEvent evt) {
		clientGui.showLoggedIn(1);
		clientGui.setMenue(new String[] {"Zeige alle Nachrichten","Neue Nachricht"});
	}
	
}
