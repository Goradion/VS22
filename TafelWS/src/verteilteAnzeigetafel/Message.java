package verteilteAnzeigetafel;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {

	private static final long serialVersionUID = 7162932561989401921L;
	private int messageID;
	private int userID;
	private int abtNr;
	private String inhalt;
	private boolean oeffentlich;
	private LocalDateTime time;

	public Message(int messageID, int userID, int abtNr, String inhalt, boolean oeffentlich, LocalDateTime time) {
		super();
		this.messageID = messageID;
		this.userID = userID;
		this.abtNr = abtNr;
		this.inhalt = inhalt;
		this.oeffentlich = oeffentlich;
		this.time = time;
	}

	public Message(String inhalt, int userID, int abtNr, boolean oeffentlich, int msgID) {
		this.inhalt = inhalt;
		this.userID = userID;
		this.abtNr = abtNr;
		this.oeffentlich = oeffentlich;
		time = LocalDateTime.now();
		this.messageID = msgID;

	}

	@Override
	public String toString() {
		return "Message [messageID=" + messageID + ", userID=" + userID + ", abtNr=" + abtNr + ", inhalt=" + inhalt
				+ ", oeffentlich=" + oeffentlich + ", time=" + time + "]" + "\n";
	}

	public int getAbtNr() {
		return abtNr;
	}

	public int getMessageID() {
		return messageID;
	}

	public int getUserID() {
		return userID;
	}

	public String getInhalt() {
		return inhalt;
	}

	public boolean isOeffentlich() {
		return oeffentlich;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setOeffentlich() {
		this.oeffentlich = true;
	}

}
