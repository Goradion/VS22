package client;

import java.awt.event.*;
import java.util.List;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.event.CaretEvent;
import javax.swing.*;

import verteilteAnzeigetafel.Message;

/**
 *
 * @author Micha
 */

//TODO Deaktivierung Publish falls kein Koordinator
//bzw ausblenden, von aussen also Funktion nötig dafür

public class ShowAllMessagesGUI extends JPanel implements ActionListener{

    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton sendQueryButton;
    private javax.swing.JLabel jLabel;
    private javax.swing.JRadioButton changeMessage;
    private javax.swing.JRadioButton deleteMessage;
    private javax.swing.JRadioButton publishMessage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JTextField  messageIDTextField;
    private List<Message> msgs;

    
	private static final long serialVersionUID = -1673246373722125477L;


    public ShowAllMessagesGUI() {
    	
    	initialize();
    }
    
    
    public ShowAllMessagesGUI(List<Message> msgs){ // Mit der Nachrichten Liste um diese dann zu generieren

      initialize();
      this.msgs = msgs;
      fillTextField(msgs);
    }

                        
    private void fillTextField(List<Message> msgs) {
		
    	String text = "";
    	int width = 50;
    	for(Message m : msgs){
    		text += "MsgID"  + " :"+ m.getMessageID() + "\t Time: " + m.getTime()+"\n";
    	if(m.getInhalt().length() > width) {
    		for(int i = 0; i < m.getInhalt().length(); i = i+width){
    			if(i+width > m.getInhalt().length()){
    				text += m.getInhalt().substring(i);
    			} else {
    				text += m.getInhalt().substring(i, i+width) +  "\n";
    			}
    		}
    	} else {
    		text += m.getInhalt();
    	}
    	text += "\n" + "____________________________________"
    				+ "______________\n";
    	}
    	messageTextArea.setText(text);
	}


	private void initialize() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        changeMessage = new javax.swing.JRadioButton();
        deleteMessage = new javax.swing.JRadioButton();
        publishMessage = new javax.swing.JRadioButton();
        jLabel = new javax.swing.JLabel();
        messageIDTextField = new javax.swing.JTextField();
        sendQueryButton = new javax.swing.JButton();

        
        // ButtonGruppe
        buttonGroup1.add(deleteMessage);
        buttonGroup1.add(changeMessage);
        buttonGroup1.add(publishMessage);
        
        /**
         * TextArea Formatierungen
         */
        messageTextArea.setColumns(20);
        messageTextArea.setRows(5);
        jScrollPane1.setViewportView(messageTextArea);
   
        // text nicht editierbar 
        messageTextArea.setEditable(false);
        
        /**
         * change Formatierungen + Actionlistener
         */
        
        changeMessage.setText("Change Message");
        changeMessage.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				changeMessageItemStateChanged(evt);
			}
		});
        /**
         * delete Formatierungen
         */
        
        deleteMessage.setText("Delete Message");
        deleteMessage.addItemListener(new java.awt.event.ItemListener() {
		public void itemStateChanged(java.awt.event.ItemEvent evt) {
			deleteMessageItemStateChanged(evt);
			}
		});

        /**
         * publish Formatierungen + actionListener
         */
        
        publishMessage.setText("Publish Message");
        publishMessage.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				publishMessageItemStateChanged(evt);
			}
		});

        jLabel.setText("MsgID");
        /**
         * MessageIDTextField Actionlistener usw
         */
        messageIDTextField.setText("MsgID eingeben");
		
        messageIDTextField.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				messageIDTextFieldMouseClicked(evt);
			}
		});
		messageIDTextField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				messageIDTextFieldActionPerformed(evt);
			}
		});
		messageIDTextField.addCaretListener(new javax.swing.event.CaretListener(){

			@Override
			public void caretUpdate(CaretEvent e) {
				messageIDTextFieldCaretMoved(e);
			}
			
		});
		messageIDTextField.addFocusListener(new java.awt.event.FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				messageIDTextField.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				
			}
			
		});


        sendQueryButton.setText("Send query");
        sendQueryButton.setEnabled(false);
        
        
		sendQueryButton.addMouseListener(new java.awt.event.MouseAdapter() {
			
			/*		public void mouseClicked(java.awt.event.MouseEvent evt) {
				sendQueryButtonMouseClicked(evt);
			}*/
		});

        /**
         *  Design der einzelnen panels
         * 
         */
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(changeMessage)
                            .addComponent(deleteMessage)
                            .addComponent(publishMessage))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(420, 420, 420)
                .addComponent( messageIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(sendQueryButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                //.addGap(157, 157, 157)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(changeMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(publishMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent( messageIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendQueryButton)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

    }// </editor_fold>                        
    @Override
	/** 
	 * ActionListener 
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		
	}

//testfunktion für den send query button
	public void keyPressed(KeyEvent evt) {

		if (evt.getKeyCode() == KeyEvent.VK_ENTER)
			System.out.println("Button1 gedrueckt");
	}
/** 
 * MesssageTextFeld funktionen
 * @param evt
 */
	private void messageIDTextFieldMouseClicked(java.awt.event.MouseEvent evt) {// GEN_FIRST:event_messageIDTextFieldMouseClicked
		messageIDTextField.setText("");
	}
	
	private void messageIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	protected void messageIDTextFieldCaretMoved(CaretEvent e) {
		
		if(!isValidInput(messageIDTextField.getText())){
			sendQueryButton.setEnabled(false);
			messageIDTextField.setBorder(BorderFactory.createLineBorder(Color.red,2));
		} else {
			sendQueryButton.setEnabled(true);
			
			messageIDTextField.setBorder(BorderFactory.createLineBorder(Color.gray,1));
		}
	}
/**
 * Publish Message Funktion
 * @param evt
 */
	private void publishMessageItemStateChanged(java.awt.event.ItemEvent evt) {
		if (publishMessage.isSelected()) {	
			messageIDTextField.setEnabled(true);
		}
	}
/**
 * changeMessage Funktion	
 * @param evt
 */
	private void changeMessageItemStateChanged(java.awt.event.ItemEvent evt) {
		if (changeMessage.isSelected()) {
			
			messageIDTextField.setEnabled(true);

		}
	}
	
/**	
 * deleteMessage Funktion
 * @param evt
 */
	private void deleteMessageItemStateChanged(java.awt.event.ItemEvent evt) {
		if (deleteMessage.isSelected()) {
			messageIDTextField.setEnabled(true);
		}
	}
	
	
	public boolean isValidInput(String str) {
		boolean isValid = true;
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException exception) {
			isValid = false;
		}
		return isValid;
	}

	/**
	 * Funktionen für den senden Button
	 */

    public void SendButtonAddActionListener(java.awt.event.ActionListener listener){
    	sendQueryButton.addActionListener(listener);
    }
    
    public Message getMessage(){
    	
    	return msgs.get(Integer.parseInt(messageIDTextField.getText()));
    }

    public String getSelection(){
    	if(changeMessage.isSelected()){
    		return "change";
    	}
    	
    	if(deleteMessage.isSelected()){
    		return "delete";
    	}
    	
    	if(publishMessage.isSelected()){
    		return "publish";
    	} else {
    		return "error";
    	}
    	
    }
    
    public void setPublish(boolean enable){
    	
    	publishMessage.setVisible(enable);
    }


    
    
    
    
    
                 
	
	
}