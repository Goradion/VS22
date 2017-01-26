package verteilteAnzeigetafel;

import java.util.Date;
import java.util.HashSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SoapableMessage")
public class SoapableMessage {
	@XmlElement
	private static final long serialVersionUID = 7162932561989401921L;
	@XmlElement
	private int messageID;
	@XmlElement
	private int userID;
	@XmlElement
	private int abtNr;
	@XmlElement
	private String inhalt;
	@XmlElement
	private boolean oeffentlich;
	@XmlElement
	private HashSet<Integer> groups = new HashSet<Integer>();
	@XmlElement
	private Date time;

	public SoapableMessage() {
	}

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
		Integer[] gGr = new Integer[groups.size()];
		groups.toArray(gGr);
		return gGr;
	}

	public void setGruppen(Integer[] groups) {
		for (int i = 0; i < groups.length; i++) {
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
