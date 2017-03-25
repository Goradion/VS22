package serverRequests.corba;

import VS2.UserData;
import tafelServer.CorbaRequestDeliver;

public class CreateRequest extends CorbaRequest{

	private String message;
	private int ServerNr;
	
	
	
	public CreateRequest(String messageID, int userID, String message, int serverNr) {
		super(messageID, userID);
		this.message = message;
		ServerNr = serverNr;
	}

	public String getMessage() {
		return message;
	}
	
	public int getServerNr() {
		return ServerNr;
	}

	@Override
	public void deliverMe(CorbaRequestDeliver deliverer) {
		deliverer.deliver(this);		
	}
	
	
}
