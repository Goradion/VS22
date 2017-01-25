package tafelServer;

import serverCom.gen.ServerComWebservice;
import serverRequests.DeletePublicRequest;
import serverRequests.ModifyPublicRequest;
import serverRequests.ReceiveRequest;
import serverRequests.ServerRequest;
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

}
