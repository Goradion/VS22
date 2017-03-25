package serverRequests.corba;

import tafelServer.CorbaRequestDeliver;

public class DeleteRequest extends CorbaRequest{

	public DeleteRequest(String messageID, int userID) {
		super(messageID, userID);
	}

	@Override
	public void deliverMe(CorbaRequestDeliver deliverer) {
		deliverer.deliver(this);		
	}

	
}
