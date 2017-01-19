package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import verteilteAnzeigetafel.Message;

/**
 *
 * @author Armin
 */
public class ClientGui extends JFrame{
    
    
    
    // Nötige Panels und Frames
    //private JFrame mainGUI; // Fenster zum anzeigen der Panels
    private LoginGUI loginGUI; //Wird immer angezeigt
    private JPanel loggedInGUI; // Wird angezeigt sobald eingeloggt
    private JPanel newMessageGUI; // wird angezigt für neue Nachricht
    private JPanel publishMessageGUI; // Wird angezeigt für veröffentlichen
    private JPanel editMessageGUI; // wird angezeigt für bearbeiten einer Nachricht
    private JPanel showAllMessagesGUI; // wird angezeigt bei allen Nachrichten zeigen
    
    
    private JPanel upperPanel;
    
    
    private String[] abteilungen;
    
    private JTextArea localMessages;
    
    private void initialize(){
        //Baue alle Panel und Frames zusammen
    	loginGUI = new LoginGUI();
    	abteilungen = new String[2];
    	abteilungen[0] = "Managment";
    	abteilungen[1] = "Finanzen";
    	loginGUI.initialize(abteilungen);
    	
    	//erstelle eigene Panels zum organisieren
    	upperPanel = new JPanel();
    	setLayout(new BorderLayout());
    	upperPanel.add(loginGUI,BorderLayout.LINE_START);
    	this.add(upperPanel, BorderLayout.PAGE_START);
        this.setPreferredSize(new Dimension(400,300));
        this.setSize(new Dimension(400,300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    	this.setVisible(true);
    }
    public ClientGui(String title){
    	super(title);
        initialize();
    }

    
    public void showLoggedIn(int id){
        //Zeige Login Panel + loggedIn Panel
    }
    
    public void showNewMessage(){
        //Zeige Login Panel + loggedIn Panel + New Message Panel
    }
    
    public void showPublishMessage(Message msg){
        //Zeige Login Panel + loggedIn Panel + Publish MessagePanel
    }
    
    public void showShowMessages(List<Message> msgList){
        //Zeige Login Panel + loggedIn Panel + ShowMessages Panel
    }
    
    public void showEditMessage(Message msg){
        //Zeige Login Panel + loggedIn Panel + Edit Message Panel
    }
    
    //Dummys
    public ActionListener actionLogin(){
		return null;   
        //Übergebe Listener für LoginButton
    }
    
    public ActionListener actionMenuSelect(){
		return null;
        //Übergebe Listener für Menüauswahl
    }
    
    public ActionListener actionSendNewMessage(){
		return null;
        //Übergebe Listener für Sende neue Nachricht
    }
    
    public ActionListener actionDeleteSelected(){
		return null;
        //Übergebe Listener für lösche ausgewählte in show all messages
    }
    public ActionListener actionEditMessage(){
		return null;
        //Übergebe Listener für Ändern Button gedrückt
    }
    
    public ActionListener actionPublishMessages(){
		return null;
        //Übergebe Listener für Veröffentlichen Button gedrückt
    }
}
