package serverRequests;

import tafelServer.ServerRequestDeliverer;
import verteilteAnzeigetafel.TafelException;

public class ModifyPublicRequest extends UserRequest {

	private static final long serialVersionUID = 5828113576693444289L;
	private int messageID;
	private int groupID;
	private String newMessage;
	/**
	 * Constructs a ModifyPublicRequest.
	 * @param messageID
	 * @param newMessage
	 * @param userID
	 */
	public ModifyPublicRequest(int messageID, int groupID, String newMessage) {
		super(1);
		this.messageID  = messageID;
		this.groupID    = groupID;
		this.newMessage = newMessage;
	}
	/**
	 * Returns the ID of the message to modify.
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
	/**
	 * Returns the new text content of the message.
	 * @return newMessage
	 */
	public String getNewMessage(){
		return newMessage;
	}
	
	@Override
	public String deliverMe(ServerRequestDeliverer deliverer) throws TafelException {
		return deliverer.deliver(this);		
	}
}