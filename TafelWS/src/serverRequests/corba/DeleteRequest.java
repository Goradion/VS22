package serverRequests.corba;

import VS2.UserData;
import tafelServer.CorbaRequestDeliver;

public class DeleteRequest extends CorbaRequest{

	public DeleteRequest(String messageID, UserData userData) {
		super(messageID, userData);
	}

	@Override
	public void deliverMe(CorbaRequestDeliver deliverer) {
		deliverer.deliver(this);		
	}

	
}
