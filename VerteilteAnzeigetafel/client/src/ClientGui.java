/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Armin
 */
public class ClientGui extends JFrame {
    
    
    
    // Nötige Panels und Frames
    private JFrame mainGUI = new JFrame("Tafel-Client"); // Fenster zum anzeigen der Panels
    private JPanel loginGUI = new JPanel(); //Wird immer angezeigt
    private JPanel loggedInGUI = new JPanel(); // Wird angezeigt sobald eingeloggt
    private JPanel newMessageGUI = new JPanel(); // wird angezigt für neue Nachricht
    private JPanel publishMessageGUI = new JPanel(); // Wird angezeigt für veröffentlichen
    private JPanel editMessageGUI = new JPanel(); // wird angezeigt für bearbeiten einer Nachricht
    private JPanel showAllMessagesGUI = new JPanel(); // wird angezeigt bei allen Nachrichten zeigen
    
    // Textfelder und Dropdowns
    
    // Buttons
    
    private void initialize(){
        //Baue alle Panel und Frames zusammen
    }
    public ClientGui(){
        initialize();
    }
    public void showLogin(){
        //Zeige nur das Login Panel
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
        //Übergebe Listener für LoginButton
    }
    
    public ActionListener actionMenuSelect(){
        //Übergebe Listener für Menüauswahl
    }
    
    public ActionListener actionSendNewMessage(){
        //Übergebe Listener für Sende neue Nachricht
    }
    
    public ActionListener actionDeleteSelected(){
        //Übergebe Listener für lösche ausgewählte in show all messages
    }
    public ActionListener actionEditMessage(){
        //Übergebe Listener für Ändern Button gedrückt
    }
    
    public ActionListener actionPublishMessages(){
        //Übergebe Listener für Veröffentlichen Button gedrückt
    }
}
