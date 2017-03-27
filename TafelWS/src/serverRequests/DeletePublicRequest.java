package serverRequests;

import tafelServer.ServerRequestDeliverer;
import verteilteAnzeigetafel.TafelException;

public class DeletePublicRequest extends UserRequest {

	private static final long serialVersionUID = -8145101718329410770L;
	private int messageID;
	private int groupID;
	/**
	 * Constructs a DeletePublicRequest.
	 * @param messageID
	 * @param userID
	 */
	public DeletePublicRequest(int messageID, int groupID) {
		super(1);
		this.messageID = messageID;
		this.groupID   = groupID;
	}
	/**
	 * Returns the ID of the message to delete.
	 * @return messageID
	 */
	public int getMessageID() {
		return messageID;
	}
	/**
	 * Returns the ID of the messages group.
	 * @return groupID
	 */
	public int getGroupID() {
		return groupID;
	}
	@Override
	public String deliverMe(ServerRequestDeliverer deliverer) throws TafelException {
		return deliverer.deliver(this);		
	}
}
