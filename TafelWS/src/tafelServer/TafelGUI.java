package tafelServer;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import verteilteAnzeigetafel.Anzeigetafel;
import verteilteAnzeigetafel.Message;

public class TafelGUI implements Observer {
    
    private final JFrame window;
    private JTextArea localMessages;
    private JTextArea globalMessages;
    private JScrollPane localScroll;
    private JScrollPane globalScroll;
    private JTextArea[] gruppenMessages;
    public JFrame getWindow() {
		return window;
	}

	private JScrollPane[] gruppenScroll;
    private int[] gruppen;
    
    TafelGUI(int abteilung, TafelServer ts,int[] gruppen){
        this.window = new JFrame("Abteilung "+Integer.toString(abteilung));
        this.gruppen = gruppen;
        gruppenMessages = new JTextArea[gruppen.length];
        gruppenScroll = new JScrollPane[gruppen.length];
        for(int i = 0; i< gruppen.length;i++){
        	gruppenMessages[i] = new JTextArea();
        	gruppenMessages[i].setEditable(false);
        	gruppenScroll[i] = new JScrollPane (); 
        	
        	           
        	
        	
        }

        
        for(int i = 0; i< gruppen.length;i++){
        	this.gruppenScroll[i] = new JScrollPane(gruppenMessages[i]);
            gruppenScroll[i].setBorder(new TitledBorder("Gruppe " + gruppen[i] ));
        }

        this.localMessages = new JTextArea();
        localMessages.setEditable(false);

        
        this.globalMessages = new JTextArea();
        globalMessages.setEditable(false);

        
        
        this.localScroll = new JScrollPane(localMessages);
        localScroll.setBorder(new TitledBorder("Local messages"));

        
        this.globalScroll = new JScrollPane(globalMessages);
        globalScroll.setBorder(new TitledBorder("Global messages"));

        
        window.setLayout(new GridLayout(gruppen.length + 2,1));
        
        
        window.add(localScroll);
        for(int i = 0; i < gruppen.length;i++)
        {
        	window.add(gruppenScroll[i]);
        }
        window.add(globalScroll);
        
        window.setPreferredSize(new Dimension(400,gruppen.length *200 +300));
        window.setSize(new Dimension(400,gruppen.length *200 +300));
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        window.setVisible(true);
       
    }
    
    @Override
    public void update(Observable o, Object arg) {
        Anzeigetafel at = (Anzeigetafel)o;
        localMessages.setText(null);
        globalMessages.setText(null);
        for(Message m: at.getLocalMsgs()){
            localMessages.append("UserID: " + m.getUserID()+'\t'+"Zeit: "
                    +m.getTime()+'\n'+m.getInhalt()+'\n'+'\n');
        }
        for(Message m: at.getGlobalMsgs()){
            globalMessages.append("Abt: "+m.getAbtNr()+'\t'+"UserID: "
                    + m.getUserID()+'\t'+"Zeit: "+m.getTime()+'\n'
                    +m.getInhalt()+'\n'+'\n');
        }
        for(int i = 0; i < gruppen.length;i++){
	        for(Message m: at.getGroupMsgs(gruppen[i])){
	            gruppenMessages[i].append("Abt: "+m.getAbtNr()+'\t'+"UserID: "
	                    + m.getUserID()+'\t'+"Zeit: "+m.getTime()+'\n'
	                    +m.getInhalt()+'\n'+'\n');
	        }
        }
    }    
}
