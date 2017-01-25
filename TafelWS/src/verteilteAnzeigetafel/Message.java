package verteilteAnzeigetafel;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import verteilteAnzeigetafel.SoapableMessage;

public class Message implements Serializable {

	private static final long serialVersionUID = 7162932561989401921L;
	private int messageID;
	private int userID;
	private int abtNr;
	private String inhalt;
	private boolean oeffentlich;
	private HashSet<Integer> gruppen = new HashSet<Integer>();
	private Date time;

	public Message(int messageID, int userID, int abtNr, String inhalt, boolean oeffentlich, Date time) {
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
		time = new Date();
		this.messageID = msgID;

	}
	
	public Message(SoapableMessage soapableMessage){
		super();
		this.messageID = soapableMessage.getMessageID();
		this.userID = soapableMessage.getUserID();
		this.abtNr = soapableMessage.getAbtNr();
		this.inhalt = soapableMessage.getInhalt();
		this.oeffentlich = soapableMessage.isOeffentlich();
		this.time = soapableMessage.getTime();
		for(int i = 0 ; i < soapableMessage.getGruppen().length; i++){
			this.gruppen.add(soapableMessage.getGruppen()[i]);
		}
	}

	@Override
	public String toString() {
		return "Message [messageID=" + messageID + ", userID=" + userID + ", abtNr=" + abtNr + ", inhalt=" + inhalt
				+ ", oeffentlich=" + oeffentlich + ", time=" + time + "]" + "\n";
	}

	public void addGroup(int gruppe){
		gruppen.add(gruppe);
	}
	
	public void removeGroup(int gruppe){
		gruppen.remove(gruppe);
		if(gruppen.isEmpty())
			this.oeffentlich=false;
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
	
	public void setInhalt (String inhalt){
		this.inhalt = inhalt;
	}

	public boolean isOeffentlich() {
		return oeffentlich;
	}

	public Date getTime() {
		return time;
	}

	public void setOeffentlich() {
		this.oeffentlich = true;
	}

	public HashSet<Integer> getGruppen() {
		return gruppen;
	}

	public void setGruppen(HashSet<Integer> gruppen) {
		this.gruppen = gruppen;
	}

}
