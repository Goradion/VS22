package client;

import java.awt.event.KeyEvent;

import verteilteAnzeigetafel.Message;
/**
 *
 * @author Ch4in
 */
public class EditMessageGUI extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private Message msg;
    // End of variables declaration     
    
    /**
     * Creates new form EditMessageGUI
     */
    public EditMessageGUI(Message msg) {
    	this.msg = msg;
        initComponents();
    }
                      
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();


        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText(msg.getInhalt());
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Nachricht bearbeiten");

        jButton1.setText("aendern");
      
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

    }                      
                                      

      public void addAenderButtonAction(java.awt.event.ActionListener listener){
    	  jButton1.addActionListener(listener);
      }
      
      public void setEditText(String text){
    	  jTextArea1.setText(text);
      }
      
      public String getEditText(){
    	  return jTextArea1.getText();
      }
}
