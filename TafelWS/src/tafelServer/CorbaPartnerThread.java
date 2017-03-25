package tafelServer;

import java.net.URL;
import java.util.concurrent.LinkedBlockingDeque;

import serverRequests.corba.CorbaRequest;
import tafelServer.CorbaClient.CorbaClient;

public class CorbaPartnerThread extends Thread {
	private LinkedBlockingDeque<CorbaRequest> messageQueue;
	private TafelServer tafelServer;
	private URL targetAddress;
	private int eigeneAbteilungsID;
	private int remoteAbteilungsID;

	public CorbaPartnerThread(LinkedBlockingDeque<CorbaRequest> messageQueue, TafelServer tafelServer,
			URL targetAddress, int eigeneAbteilungsID, int remoteAbteilungsID) {
		super();
		this.messageQueue = messageQueue;
		this.tafelServer = tafelServer;
		this.targetAddress = targetAddress;
		this.eigeneAbteilungsID = eigeneAbteilungsID;
		this.remoteAbteilungsID = remoteAbteilungsID;
	}
	
	public void run(){
		CorbaClient soapClient = null;
		CorbaRequestDeliver deliverer = null;
		while(true){
			try {
				if (soapClient == null) {
					soapClient = new CorbaClient(targetAddress.getHost(), targetAddress.getPort());
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

}
