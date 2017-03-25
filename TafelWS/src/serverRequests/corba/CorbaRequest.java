package serverRequests.corba;

import tafelServer.CorbaRequestDeliver;

public abstract class CorbaRequest {
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
