package serverRequests.corba;

import tafelServer.CorbaRequestDeliver;

public class CreateRequest extends CorbaRequest{

	private static final long serialVersionUID = -4537407830742487703L;
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
