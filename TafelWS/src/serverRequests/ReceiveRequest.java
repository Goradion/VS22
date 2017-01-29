package serverRequests;

import java.util.Date;

import tafelServer.ServerRequestDeliverer;
import verteilteAnzeigetafel.Message;
import verteilteAnzeigetafel.TafelException;

public class ReceiveRequest extends ServerRequest {

	private static final long serialVersionUID = -3124875056829274290L;
	private Message msg;
	private int groupID;
	private Date time;
	/**
	 * Constructs a ReceiveRequest.
	 * @param msg
	 */
	public ReceiveRequest(Message msg, int groupID, Date time) {
		this.msg     = msg;
		this.groupID = groupID;
		this.time    = time;
	}
	/**
	 * Returns the full message to receive. (not only the text content)
	 * @return
	 */
	public Message getMessage(){
		return msg;
	}
	/**
	 * Returns the ID of the messages group.
	 * @return groupID
	 */
	public int getGroupID() {
		return groupID;
	}
	/**
	 * Returns the time the message was published.
	 * @return time
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * Returns the time the message was published.
	 * @return time
	 */
	public String getTimeStr() {
		return time.toString();
	}
	@Override
	public String deliverMe(ServerRequestDeliverer deliverer) throws TafelException {
		return deliverer.deliver(this);		
	}
}
