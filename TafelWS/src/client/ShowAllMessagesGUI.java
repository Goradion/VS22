package client;

import java.awt.event.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.event.CaretEvent;
import javax.swing.*;

import verteilteAnzeigetafel.Message;

/**
 *
 * @author Micha
 */

//TODO Checkboxen f�r publish mit integrieren
//bzw ausblenden, von aussen also Funktion n�tig daf�r

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
    private Map<Integer, Message> msgs;
    private javax.swing.JCheckBox gruppe1;
    private javax.swing.JCheckBox gruppe2;
    private javax.swing.JCheckBox gruppe3;
    private javax.swing.JCheckBox gruppe4;

    
	private static final long serialVersionUID = -1673246373722125477L;


    public ShowAllMessagesGUI() {
    	
    	initialize();
    }
    
    
    public ShowAllMessagesGUI(Map<Integer, Message> msgs){ // Mit der Nachrichten Liste um diese dann zu generieren

      initialize();
      this.msgs = msgs;
      fillTextField(msgs.values());
    }

                        
    private void fillTextField(Collection<Message> msgs) {
		
    	String text = "";
    	int width = 50;
    	for(Message m : msgs){
    		text += "MsgID"  + " :"+ m.getMessageID() + "\t Time: " + m.getTime()+"\n";
    		for(int i = 0;i < m.getGruppenAsArray().length;i++){
				if(i == 0){
					text +="Published in: ";
				}
    			if(i == m.getGruppenAsArray().length-1){
    				text += m.getGruppenAsArray()[i] + "";
    			} else {

    				text += m.getGruppenAsArray()[i] +",";
    			}
    		}
    		text += "\n";
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
        gruppe1 = new javax.swing.JCheckBox();
        gruppe2= new javax.swing.JCheckBox();
        gruppe3= new javax.swing.JCheckBox();
        gruppe4= new javax.swing.JCheckBox();

        gruppe1.setText("Gruppe1");
        gruppe2.setText("Gruppe2");
        gruppe3.setText("Gruppe3");
        gruppe4.setText("Gruppe4");
   /*     
        gruppe1.addActionListener(this);
        gruppe2.addActionListener(this);
        gruppe3.addActionListener(this);
        gruppe4.addActionListener(this);
 */       

        
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
        
		messageIDTextField.addKeyListener(new java.awt.event.KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				
				
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{
					sendQueryButton.doClick();
					
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}


			});
        messageIDTextField.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				messageIDTextFieldMouseClicked(evt);
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
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(gruppe4))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(32, 32, 32)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(publishMessage)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(gruppe3))
	                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                .addComponent(deleteMessage)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(gruppe2))
	                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                .addComponent(changeMessage)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(gruppe1)))))
	                .addGap(22, 22, 22))
	            .addGroup(layout.createSequentialGroup()
	                .addGap(420, 420, 420)
	                .addComponent(messageIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(changeMessage)
	                            .addComponent(gruppe1))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(deleteMessage)
	                            .addComponent(gruppe2))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(publishMessage)
	                            .addComponent(gruppe3))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel)
	                            .addComponent(gruppe4))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(messageIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(sendQueryButton)))
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

    }// </editor_fold>                        
    @Override
	/** 
	 * ActionListener 
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		
	}

//testfunktion f�r den send query button

/** 
 * MesssageTextFeld funktionen
 * @param evt
 */
	private void messageIDTextFieldMouseClicked(java.awt.event.MouseEvent evt) {// GEN_FIRST:event_messageIDTextFieldMouseClicked
		messageIDTextField.setText("");
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
	 * Funktionen f�r den senden Button
	 */

    public void SendButtonAddActionListener(java.awt.event.ActionListener listener){
    	sendQueryButton.addActionListener(listener);
    	
    }
    
    public Message getMessage(){
    	
    	return msgs.get(Integer.parseInt(messageIDTextField.getText()));
    }

    /**
     * Furnktion f�r den 
     * @return
     */
    public QueryCommand getSelection(){
    	if(changeMessage.isSelected()){
    		return QueryCommand.CHANGE;
    	} else if(deleteMessage.isSelected()){
    		return QueryCommand.DELETE;
    	} else if(publishMessage.isSelected()){
    		return QueryCommand.PUBLISH;
    	} else {
    		return QueryCommand.ERROR;
    	}
    	
    }
    /**
     * Funktion f�r ein und  ausblenden von dem Publishbutton + Gruppencheckboxen  falls nur normaler user
     * @param enable
     */
    public void setPublish(boolean enable){
    	
    	publishMessage.setVisible(enable);
    	
    		gruppe1.setVisible(enable);
    		gruppe2.setVisible(enable);
    		gruppe3.setVisible(enable);
    		gruppe4.setVisible(enable);
    
    	
    }
  
    public boolean pruefegruppe1()
    {
    	return gruppe1.isSelected();
    }
    public boolean pruefegruppe2()
    {
    	return gruppe2.isSelected();
    }
    public boolean pruefegruppe3()
    {
    	return gruppe3.isSelected(); 
    }
    public boolean pruefegruppe4()
    {
    	return gruppe4.isSelected(); 
    }

	
}