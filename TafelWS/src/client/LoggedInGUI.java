package client;

/**
 *
 * @author Ch4in
 */
public class LoggedInGUI extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	
	private javax.swing.JComboBox<String> menueBox;
    private javax.swing.JLabel jLabel1;

    
	/**
     * Creates new form LoggedInGUI
     */
    public LoggedInGUI() {
        initComponents();
    }
                  
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        menueBox = new javax.swing.JComboBox<>();

        jLabel1.setText("Menü");

        menueBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(menueBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menueBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }                     

    
    public void addItemListenerMenue(java.awt.event.ItemListener listener){
    	menueBox.addItemListener(listener);
    }
    public void setMenue(String[] menue){
    	//TODO Menue auf ändere Nachricht setzen / wenn nachricht versendet dann auf alle nachrichten  anzeigen setzen
    	menueBox.setModel(new javax.swing.DefaultComboBoxModel<>(menue));    
    }
    public String getMenue(){
    	return (String) menueBox.getSelectedItem();
    }

             
}
