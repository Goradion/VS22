package tafelServer;

import serverCom.gen.ServerComWebservice;
import serverRequests.CreateRequest;
import serverRequests.DeletePublicRequest;
import serverRequests.DeleteRequest;
import serverRequests.ModifyPublicRequest;
import serverRequests.ModifyRequest;
import serverRequests.PublishRequest;
import serverRequests.ReceiveRequest;
import serverRequests.RegisterRequest;
import serverRequests.ServerRequest;
import serverRequests.ShowMyMessagesRequest;
import verteilteAnzeigetafel.TafelException;

public class ServerRequestDeliverer {

	ServerComWebservice webservice;

	/**
	 * Construcs a new ServerRequestdeliverr
	 * 
	 * @param tafelServer
	 * @param anzeigetafel
	 */
	public ServerRequestDeliverer(ServerComWebservice webservice) {
		super();
		this.webservice = webservice;
	}

	/**
	 * Instructs a ServerRequest to indentify itself.
	 * 
	 * @param serverRequest
	 * @return an answer
	 * @throws TafelException
	 *             if the anzeigetafel rejects the request.
	 * @throws InterruptedException
	 *             if the handling was interrupted
	 */
	public String deliver(ServerRequest serverRequest) throws TafelException, InterruptedException {
		return serverRequest.deliverMe(this);
	}

	/**
	 * delivers a request to create a new message.
	 * 
	 * @param createRequest
	 * @return an answer
	 * @throws TafelException
	 *             if the anzeigetafel rejects the request.
	 */
	public String deliver(CreateRequest createRequest) throws TafelException {
		// TODO implement this method
		return null;
	}

	/**
	 * delivers a request to delete a message.
	 * 
	 * @param deleteRequest
	 * @return an answer
	 * @throws TafelException
	 *             if the anzeigetafel rejects the request.
	 */
	public String deliver(DeleteRequest deleteRequest) throws TafelException {
		// TODO implement this method
		return null;
	}

	/**
	 * delivers a request to delete a public message.
	 * 
	 * @param deletePublicRequest
	 * @return an answer
	 * @throws TafelException
	 *             if the anzeigetafel rejects the request.
	 */
	public String deliver(DeletePublicRequest deletePublicRequest) throws TafelException {
		// TODO implement this method
		return null;
	}

	/**
	 * delivers a request to modify a message. Initiates the
	 * deliver(ModifyPublicRequest) method in case the message is public.
	 * 
	 * @param modifyRequest
	 * @return a answer
	 * @throws TafelException
	 *             if the anzeigetafel rejects the request.
	 */
	public String deliver(ModifyRequest modifyRequest) throws TafelException {
		// TODO implement this method
		return null;
	}

	/**
	 * delivers a request to modify a public messages, usually initiated by the
	 * deliver(ModifyRequest) method.
	 * 
	 * @param modifyPublicRequest
	 * @return an answer
	 * @throws TafelException
	 *             if anzeigetafel rejects the request
	 */
	public String deliver(ModifyPublicRequest modifyPublicRequest) throws TafelException {
		// TODO implement this method
		return null;
	}

	/**
	 * delivers a request to publish a message.
	 * 
	 * @param publishRequest
	 * @return an aswer
	 * @throws InterruptedException
	 *             if the handling was interrupted.
	 * @throws TafelException
	 *             if the anzeigetafel rejects the request.
	 */
	public String deliver(PublishRequest publishRequest) throws InterruptedException, TafelException {
		// TODO implement this method
		return null;
	}

	/**
	 * delivers a request to show a user his or her messages.
	 * 
	 * @param showMyMessagesRequest
	 * @return the messages as a String
	 * @throws TafelException
	 */
	public String deliver(ShowMyMessagesRequest showMyMessagesRequest) throws TafelException {
		// TODO implement this method
		return null;
	}

	/**
	 * Hanldes a Request to receive a message from another TafelServer.
	 * 
	 * @param receiveRequest
	 * @return an answer
	 * @throws TafelException
	 *             if the anzeigetafel rejects the request.
	 */
	public String deliver(ReceiveRequest receiveRequest) throws TafelException {
		// TODO implement this method
		return null;
	}

	/**
	 * delivers a request to register a TafelServer.
	 * 
	 * @param registerRequest
	 * @return an answer
	 * @throws TafelException
	 *             if the TafelServer rejects the request.
	 */
	public String deliver(RegisterRequest registerRequest) throws TafelException {
		// TODO implement this method
		return null;
	}

}
