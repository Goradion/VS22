package client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.Message;

/**
 * author Armin + Michael + Andrea
 * 
 */
public class ClientGui extends JFrame{
    
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1637588311280497997L;
	// Nötige Panels und Frames
    //private JFrame mainGUI; // Fenster zum anzeigen der Panels
    private LoginGUI loginGUI; //Wird immer angezeigt
    private LoggedInGUI loggedInGUI; // Wird angezeigt sobald eingeloggt
    private NewMessageGUI newMessageGUI; // wird angezigt für neue Nachricht
    private EditMessageGUI editMessageGUI; // wird angezeigt für bearbeiten einer Nachricht
    private ShowAllMessagesGUI showAllMessagesGUI; // wird angezeigt bei allen Nachrichten zeigen
    
    
    private JPanel upperPanel;
    private JPanel lowerPanel;
    
        
    private void initialize(Integer[] abteilungen){
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
    public ClientGui(String title, Integer[] abteilungen){
    	super(title);
        initialize(abteilungen);
    }

  public Message getMsgID(){
	   Message id;	   
	   id = showAllMessagesGUI.getMessage();
	   return id;
  }
    
    public void showLoggedIn(int userID, Map<Integer, Message> msgs){
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
    
    
    public void showShowMessages(Map<Integer, Message> msgMap){
        //Zeige Login Panel + loggedIn Panel + ShowMessages Panel
    	showAllMessagesGUI = new ShowAllMessagesGUI(msgMap);
        this.setPreferredSize(new Dimension(670,350));
        this.setSize(new Dimension(670,350));
    	lowerPanel.removeAll();
    	lowerPanel.add(showAllMessagesGUI);
    	this.repaint();
    }
    
    public void showEditMessage(Message msg){
        //Zeige Login Panel + loggedIn Panel + Edit Message Panel
    	loggedInGUI.setMenue(new String[] { "Nachricht ändern","Neue Nachricht", "Zeige alle Nachrichten"});
    	editMessageGUI = new EditMessageGUI(msg);
    	lowerPanel.removeAll();
    	lowerPanel.add(editMessageGUI);

        this.setPreferredSize(new Dimension(620,350));
        this.setSize(new Dimension(620,350));
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
    
    public QueryCommand getQueryCommand(){
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
    
    public Integer getAbteilung(){
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
    public void setPublish(boolean enable, Integer groups[]){
    	
    	showAllMessagesGUI.setPublish(enable, groups);
    }
    public void setConectivity(boolean active){
    	loginGUI.setConectivity(active);
    }
    public boolean pruefeGruppe1(){
    	return showAllMessagesGUI.pruefegruppe1();
    	
    }
    public boolean pruefeGruppe2(){
    	return showAllMessagesGUI.pruefegruppe2();
    	
    }
    public boolean pruefeGruppe3(){
    	return showAllMessagesGUI.pruefegruppe3();
    	
    }
    public boolean pruefeGruppe4(){
    	return showAllMessagesGUI.pruefegruppe4();
    	
    }
    
    public String getEditedMessageText(){
    	return editMessageGUI.getEditText();
    }
  
}

