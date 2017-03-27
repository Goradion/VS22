package serverRequests.corba;

import java.io.Serializable;

import tafelServer.CorbaRequestDeliver;

public abstract class CorbaRequest implements Serializable {
	
	protected static final long serialVersionUID = 164552615903886460L;
		
	private String messageID;
	private int userID;
	
	
	public CorbaRequest(String messageID, int userID) {
		super();
		this.messageID = messageID;
		this.userID = userID;
	}

	public String getMessageID() {
		return messageID;
	}

	public int getUserID() {
		return userID;
	}
	
	public abstract void deliverMe(CorbaRequestDeliver deliverer);
}
