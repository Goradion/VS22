package tafelServer;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import verteilteAnzeigetafel.Anzeigetafel;
import verteilteAnzeigetafel.Message;

public class TafelGUI implements Observer {

	private final JFrame window;
	private JTextArea localMessages;
	private JScrollPane localScroll;
	private Map<Integer, JTextArea> gruppenMessages;

	public JFrame getWindow() {
		return window;
	}

	private Map<Integer, JScrollPane> gruppenScroll;
	private Set<Integer> gruppen;

	TafelGUI(int abteilung, TafelServer ts, Set<Integer> set) {
		this.window = new JFrame("Abteilung " + Integer.toString(abteilung));
		this.gruppen = set;
		gruppenMessages = new HashMap<Integer, JTextArea>();
		gruppenScroll = new HashMap<Integer, JScrollPane>();
		for (Integer i : set) {
			JTextArea jTextArea = new JTextArea();
			JScrollPane jScrollPane = new JScrollPane(jTextArea);
			jScrollPane.setBorder(new TitledBorder("Gruppe " + i));
			jTextArea.setEditable(false);
			gruppenMessages.put(i, jTextArea);
			gruppenScroll.put(i, jScrollPane);
		}

		this.localMessages = new JTextArea();
		localMessages.setEditable(false);

		this.localScroll = new JScrollPane(localMessages);
		localScroll.setBorder(new TitledBorder("Local messages"));

		window.setLayout(new GridLayout(set.size() + 1, 1));

		window.add(localScroll);
		for (JScrollPane jScrollPane : gruppenScroll.values()) {
			window.add(jScrollPane);
		}
		
		window.setPreferredSize(new Dimension(400, set.size() * 200 + 300));
		window.setSize(new Dimension(400, set.size() * 200 + 300));
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		window.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		Anzeigetafel at = (Anzeigetafel) o;
		localMessages.setText(null);
		for (Message m : at.getLocalMsgs()) {
			localMessages.append("MessageID: " + m.getMessageID() + "    " + "UserID: " + m.getUserID() + "    " 
			        + "Zeit: " + m.getTime() + '\n' + "        " + m.getInhalt() + '\n' + '\n');
		}
		for (Integer i : gruppen) {
			JTextArea jTextArea = gruppenMessages.get(i);
			jTextArea.setText(null);
			for (Message m : at.getGroupMsgs(i)) {
				jTextArea.append("MessageID: " + m.getMessageID() + "    " + "UserID: " + m.getUserID() + "    "
						+ "Zeit: " + m.getTime() + '\n' + "        " + m.getInhalt() + '\n' + '\n');
			}
		}
	}
}
