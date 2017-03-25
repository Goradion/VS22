package tafelServer;

import VS2.UserData;
import serverRequests.corba.CorbaRequest;
import serverRequests.corba.CreateRequest;
import serverRequests.corba.DeleteRequest;
import serverRequests.corba.ModifyRequest;
import tafelServer.CorbaClient.CorbaClient;

public class CorbaRequestDeliver {
	
	private CorbaClient corbaClient;
	
	public CorbaRequestDeliver(CorbaClient corbaClient) {
		super();
		this.corbaClient = corbaClient;
	}
	

	public void setCorbaClient(CorbaClient corbaClient) {
		this.corbaClient = corbaClient;
	}


	public void deliver(CorbaRequest corbaRequest){
		corbaRequest.deliverMe(this);
	}

	public void deliver(CreateRequest createRequest) {
		UserData userData = createRequest.getUserData();
		//TODO user?
		corbaClient.createMessage(createRequest.getMessage(), createRequest.getMessageID(), createRequest.getServerNr());
		
	}

	public void deliver(DeleteRequest deleteRequest) {
		UserData userData = deleteRequest.getUserData();
		//TODO user?
		corbaClient.deleteMessage(deleteRequest.getMessageID());
		
	}

	public void deliver(ModifyRequest modifyRequest) {
		//TODO user? 
		corbaClient.modifyMessage(modifyRequest.getNewMessage(), modifyRequest.getMessageID(), modifyRequest.getServerNr());
		
		
	}

}
