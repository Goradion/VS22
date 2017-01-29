package serverRequests;

import java.io.Serializable;
import java.util.Date;

import tafelServer.ServerRequestDeliverer;
import verteilteAnzeigetafel.Message;
import verteilteAnzeigetafel.TafelException;

public abstract class ServerRequest implements Serializable {

	private static final long serialVersionUID = -2895374450189726605L;
	/**
	 * Instructs the ServerRequestHandler to handle this ServerRequest
	 * @param handler
	 * @return The answer to the ServerRequest
	 * @throws TafelException
	 * @throws InterruptedException
	 */
	
	public abstract String deliverMe(ServerRequestDeliverer deliverer) throws TafelException, InterruptedException;
	/**
	 * Returns whether the ServerRequest wants an answer to be read by a user.
	 * @return false default, otherwise as specified in subclass
	 */
	public boolean wantsAnswer(){
		return false;
	}
	/**
	 * creates a ReceiveRequest with the given parameters
	 * @param msg
	 * @return a ReceiveRequest
	 */
	public static ServerRequest buildReceiveRequest(Message msg, int group, Date time){
		return new ReceiveRequest(msg, group, time);
	}
}
