package serverRequests;

import tafelServer.ServerRequestDeliverer;
import verteilteAnzeigetafel.Message;
import verteilteAnzeigetafel.TafelException;

public class ReceiveRequest extends ServerRequest {

	private static final long serialVersionUID = -3124875056829274290L;
	private Message msg;
	/**
	 * Constructs a ReceiveRequest.
	 * @param msg
	 */
	public ReceiveRequest(Message msg) {
		this.msg = msg;
	}
	/**
	 * Returns the full message to receive. (not only the text content)
	 * @return
	 */
	public Message getMessage(){
		return msg;
	}
	@Override
	public String deliverMe(ServerRequestDeliverer deliverer) throws TafelException {
		return deliverer.deliver(this);		
	}
}
