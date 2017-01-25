package verteilteAnzeigetafel;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

public class SoapableMessage implements Serializable {
	private static final long serialVersionUID = 7162932561989401921L;
	private int messageID;
	private int userID;
	private int abtNr;
	private String inhalt;
	private boolean oeffentlich;
	private HashSet<Integer> groups = new HashSet<Integer>();
	private Date time;
	
	public SoapableMessage(){}
	
	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getAbtNr() {
		return abtNr;
	}
	public void setAbtNr(int abtNr) {
		this.abtNr = abtNr;
	}
	public String getInhalt() {
		return inhalt;
	}
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}
	public boolean isOeffentlich() {
		return oeffentlich;
	}
	public void setOeffentlich(boolean oeffentlich) {
		this.oeffentlich = oeffentlich;
	}
	public Integer[] getGruppen() {
		return (Integer[]) this.groups.toArray();
	}
	public void setGruppen(Integer[] groups) {
		for(int i = 0 ; i<groups.length; i++){
			this.groups.add(groups[i]);
		}
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
