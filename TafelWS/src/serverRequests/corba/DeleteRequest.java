package serverRequests.corba;

import tafelServer.CorbaRequestDeliver;

public class DeleteRequest extends CorbaRequest{

	private static final long serialVersionUID = 7584144995090553618L;

	public DeleteRequest(String messageID, int userID) {
		super(messageID, userID);
	}

	@Override
	public void deliverMe(CorbaRequestDeliver deliverer) {
		deliverer.deliver(this);		
	}

	
}
