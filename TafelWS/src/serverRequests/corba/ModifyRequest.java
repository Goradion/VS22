package serverRequests.corba;

import tafelServer.CorbaRequestDeliver;

public class ModifyRequest extends CorbaRequest{

	private static final long serialVersionUID = -7451190292959553007L;
	private String newMessage;
	private int ServerNr;
	
	public ModifyRequest(String newMessage, String messageID, int userID) {
		super(messageID, userID);
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
