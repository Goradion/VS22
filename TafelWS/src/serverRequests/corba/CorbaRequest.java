package serverRequests.corba;

import VS2.UserData;
import tafelServer.CorbaRequestDeliver;

public abstract class CorbaRequest {
	private String messageID;
	private UserData userData;
	
	
	public CorbaRequest(String messageID, UserData userData) {
		super();
		this.messageID = messageID;
		this.userData = userData;
	}

	public String getMessageID() {
		return messageID;
	}

	public UserData getUserData() {
		return userData;
	}
	
	public abstract void deliverMe(CorbaRequestDeliver deliverer);
}
