package tafelServer;

import serverRequests.corba.CorbaRequest;
import serverRequests.corba.CreateRequest;
import serverRequests.corba.DeleteRequest;
import serverRequests.corba.ModifyRequest;
import tafelServer.CorbaClient.CorbaClient;

public class CorbaRequestDeliver {
	
	private CorbaClient corbaClient;
	
	public CorbaRequestDeliver(CorbaClient corbaClient) throws Exception {
		super();
		this.corbaClient = corbaClient;
		corbaClient.connectToServer();
	}
	

	public void setCorbaClient(CorbaClient corbaClient) throws Exception {
		this.corbaClient = corbaClient;
		corbaClient.connectToServer();
	}


	public void deliver(CorbaRequest corbaRequest){
		corbaRequest.deliverMe(this);
	}

	public void deliver(CreateRequest createRequest) {
		corbaClient.createMessage(createRequest.getMessage(), createRequest.getMessageID(), createRequest.getServerNr(), createRequest.getUserID());
		
	}

	public void deliver(DeleteRequest deleteRequest) {
		corbaClient.deleteMessage(deleteRequest.getMessageID(), deleteRequest.getUserID());
		
	}

	public void deliver(ModifyRequest modifyRequest) {
		corbaClient.modifyMessage(modifyRequest.getNewMessage(), modifyRequest.getMessageID(), modifyRequest.getServerNr(),modifyRequest.getUserID());
		
		
	}

}
