package client;

import java.util.List;

import javax.swing.*;

import verteilteAnzeigetafel.Message;
/**
 *
 * @author Ch4in
 */
public class showAllMessagesGUI {

private void generateMessageList(List<Message> msgs){
    // soll dynamisch eine Liste der Nachrichten mit Checkboxen erstellen
}
//Textfeld
private JTextField message; //Wichtig auf nicht editierbar setzen

// Button
private JButton publish;
private JButton delete;
private JButton change;

public showAllMessagesGUI(List<Message> msgs){ // Mit der Nachrichten Liste um diese dann zu generieren
        initialize();
}

private void initialize(){
    //Panel zusammenbauen
}

}
