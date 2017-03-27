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

	
	public Message(int msgID, int userID, int abtNr, String inhalt, boolean oeffentlich, Date time) {
		super();
		init(inhalt, userID, abtNr, oeffentlich, msgID, time);
	}
	
	public Message(String inhalt, int userID, int abtNr, boolean oeffentlich, int msgID) {
		super();
		init(inhalt, userID, abtNr, oeffentlich, msgID, new Date());
	}
	
	public Message(String inhalt, int userID, int abtNr, boolean oeffentlich, int msgID, int group) {
		super();
		init(inhalt, userID, abtNr, oeffentlich, msgID, new Date());
		this.gruppen.add(group);
	}
	
	public Message(String inhalt, int userID, int abtNr, boolean oeffentlich, int msgID, int[] groups) {
		super();
		init(inhalt, userID, abtNr, oeffentlich, msgID, new Date());
		for ( int g : groups )
			this.gruppen.add(g);
	}
	
	public Message(String inhalt, int userID, int abtNr, boolean oeffentlich, int msgID, HashSet<Integer> groups) {
		super();
		init(inhalt, userID, abtNr, oeffentlich, msgID, new Date());
		this.gruppen.addAll(groups);
	}
	
	public Message(SoapableMessage soapableMessage){
		super();
		init(soapableMessage.getInhalt(), 
				soapableMessage.getUserID(), 
				soapableMessage.getAbtNr(), 
				soapableMessage.isOeffentlich(), 
				soapableMessage.getMessageID(), 
				soapableMessage.getTime());
		for(int i = 0 ; i < soapableMessage.getGruppen().length; i++){
			this.gruppen.add(soapableMessage.getGruppen()[i]);
		}
	}
	
	private void init(String inhalt, int userID, int abtNr, boolean oeffentlich, int msgID, Date time) {
		this.inhalt 	 = inhalt;
		this.userID 	 = userID;
		this.abtNr 		 = abtNr;
		this.oeffentlich = oeffentlich;
		this.messageID 	 = msgID;
		this.time 		 = time;
	}
	
	@Override
	public String toString() {
		return "Message [messageID=" + messageID + ", userID=" + userID + ", abtNr=" + abtNr + ", inhalt=" + inhalt
				+ ", oeffentlich=" + oeffentlich + ", time=" + time + ", Gruppen="+gruppen.toString()+"]" + "\n";
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
	

	public Integer[] getGruppenAsArray(){
		Integer[] g = new Integer[gruppen.size()];
		gruppen.toArray(g);
		return g;
	}

	public void setGruppen(HashSet<Integer> gruppen) {
		this.gruppen = gruppen;
	}
	
	public String format(){
		return "MessageID: " + getMessageID() + "    UserID: " + getUserID() + "    AbteilungsID " + getAbtNr()
				+ "    Zeit: " + getTime() + '\n' + getInhalt() + '\n' + '\n';
	}

}
