package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemListener;
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
    private LoggedInGUI loggedInGUI; // Wird angezeigt sobald eingeloggt
    private NewMessageGUI newMessageGUI; // wird angezigt für neue Nachricht
    private EditMessageGUI editMessageGUI; // wird angezeigt für bearbeiten einer Nachricht
    private ShowAllMessagesGUI showAllMessagesGUI; // wird angezeigt bei allen Nachrichten zeigen
    
    
    private JPanel upperPanel;
    private JPanel lowerPanel;
    
    
    private String[] abteilungen;
    
    private JTextArea localMessages;
        
    private void initialize(String[] abteilungen){
        //Baue alle Panel und Frames zusammen
    	loginGUI = new LoginGUI(abteilungen);

    	
    	//erstelle eigene Panels zum organisieren
    	upperPanel = new JPanel();
    	lowerPanel = new JPanel();
    	setLayout(new BorderLayout());
    	upperPanel.add(loginGUI,BorderLayout.LINE_START);
    	this.add(lowerPanel, BorderLayout.PAGE_END);
    	this.add(upperPanel, BorderLayout.PAGE_START);
        this.setPreferredSize(new Dimension(350,150));
        this.setSize(new Dimension(350,150));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    	this.setVisible(true);
    }
    public ClientGui(String title, String[] abteilungen){
    	super(title);
        initialize(abteilungen);
    }

    
    public void showLoggedIn(int userID, List<Message> msgs){
    	if(upperPanel.getComponentCount() != 2){
	    	loggedInGUI = new LoggedInGUI();
	    	upperPanel.add(loggedInGUI,BorderLayout.LINE_START);
    	}
    	loginGUI.setUserId(userID);
        this.setPreferredSize(new Dimension(670,350));
        this.setSize(new Dimension(670,350));
    	showAllMessagesGUI = new ShowAllMessagesGUI(msgs);
    	lowerPanel.removeAll();
    	lowerPanel.add(showAllMessagesGUI);
        //Zeige Login Panel + loggedIn Panel
    	this.repaint();
    }
    
    public void showNewMessage(){
        //Zeige Login Panel + loggedIn Panel + New Message Panel
        this.setPreferredSize(new Dimension(550,250));
        this.setSize(new Dimension(550,250));
        newMessageGUI = new NewMessageGUI();
    	lowerPanel.removeAll();
    	lowerPanel.add(newMessageGUI);
    	this.repaint();
    }
    
    
    public void showShowMessages(List<Message> msgList){
        //Zeige Login Panel + loggedIn Panel + ShowMessages Panel
    	showAllMessagesGUI = new ShowAllMessagesGUI(msgList);
        this.setPreferredSize(new Dimension(670,350));
        this.setSize(new Dimension(670,350));
    	lowerPanel.removeAll();
    	lowerPanel.add(showAllMessagesGUI);
    	this.repaint();
    }
    
    public void showEditMessage(Message msg){
        //Zeige Login Panel + loggedIn Panel + Edit Message Panel
    	loggedInGUI.setMenue(new String[] { "Nachricht �ndern","Neue Nachricht", "Zeige alle Nachrichten"});
    	editMessageGUI = new EditMessageGUI();
    	lowerPanel.removeAll();
    	lowerPanel.add(editMessageGUI);

        this.setPreferredSize(new Dimension(600,350));
        this.setSize(new Dimension(600,350));
    	this.repaint();
    }
    
    public void actionLogin(java.awt.event.ActionListener listener){
        loginGUI.AnmeldeButtonAddActionListener(listener);
    }
    
    public void actionMenuSelect(ItemListener itemListener){
		loggedInGUI.addItemListenerMenue(itemListener);
    }
    
    public void actionSendNewMessage(java.awt.event.ActionListener listener){
        newMessageGUI.SendenButtonAddActionListener(listener);   
    }
    
    public void addActionSendQuery(java.awt.event.ActionListener listener){
		showAllMessagesGUI.SendButtonAddActionListener(listener);
    }
    
    public void addActionChangeButton(java.awt.event.ActionListener listener){
    	editMessageGUI.addAenderButtonAction(listener);
    }

    public Message getSelectedMessage(){
    	return showAllMessagesGUI.getMessage();
    }
    
    public String getQueryCommand(){
    	return showAllMessagesGUI.getSelection();
    }
    
    public void setMenue(String[] menue){
    	loggedInGUI.setMenue(menue);
    	this.repaint();
    }
    
    public int getUserid(){
    	String userID = loginGUI.getUserId();
    	userID = userID.toLowerCase();
    	if(userID.matches("koordinator")){
    		return 1;
    	}
    	try {
	    	if(Integer.parseInt(userID) < 0){
	    		return -1;
	    	}
    		return Integer.parseInt(userID);
    	} catch (Exception e) {
    		return -1;
    	}

    }
    
    public String getAbteilung(){
    	return loginGUI.getAbteilung();
    }
    
    public String getSelectedMenue(){
    	return loggedInGUI.getMenue();
    }
    
    public String getNewMessage(){
    	return newMessageGUI.getMessage();
    }
    
    public void setNewMessageState(String status){
    	newMessageGUI.setMessageState(status);
    	this.repaint();
    	newMessageGUI.repaint();
    }
}
