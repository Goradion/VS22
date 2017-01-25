package serverRequests;

import tafelServer.ServerRequestDeliverer;
import verteilteAnzeigetafel.TafelException;

public class DeletePublicRequest extends UserRequest {

	private static final long serialVersionUID = -8145101718329410770L;
	int messageID;
	/**
	 * Constructs a DeletePublicRequest.
	 * @param messageID
	 * @param userID
	 */
	public DeletePublicRequest(int messageID) {
		super(1);
		this.messageID = messageID;
	}
	/**
	 * Returns the ID of the message to delete.
	 * @return messageID
	 */
	public int getMessageID() {
		return messageID;
	}
	@Override
	public String deliverMe(ServerRequestDeliverer deliverer) throws TafelException {
		return deliverer.deliver(this);		
	}
}
