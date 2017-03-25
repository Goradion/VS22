package serverRequests.corba;

import VS2.UserData;
import tafelServer.CorbaRequestDeliver;

public class ModifyRequest extends CorbaRequest{

	private String newMessage;
	private int ServerNr;
	
	public ModifyRequest(String newMessage, String messageID, UserData userData) {
		super(messageID, userData);
		this.newMessage = newMessage;
	}

	
	public String getNewMessage() {
		return newMessage;
	}

	public int getServerNr() {
		return ServerNr;
	}

	@Override
	public void deliverMe(CorbaRequestDeliver deliverer) {
		deliverer.deliver(this);
	}

}
