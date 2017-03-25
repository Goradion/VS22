package tafelServer;

import java.util.concurrent.LinkedBlockingDeque;

import serverRequests.corba.CorbaRequest;
import tafelServer.CorbaClient.CorbaClient;

public class CorbaPartnerThread extends Thread {
	private LinkedBlockingDeque<CorbaRequest> messageQueue;
	private TafelServer tafelServer;
	private String targetIP;
	private String targetPort;
	private int eigeneAbteilungsID;
	private int remoteAbteilungsID;

	public CorbaPartnerThread(LinkedBlockingDeque<CorbaRequest> messageQueue, TafelServer tafelServer,
			String targetIP, String targetPort, int eigeneAbteilungsID, int remoteAbteilungsID) {
		super();
		this.messageQueue = messageQueue;
		this.tafelServer = tafelServer;
		this.targetIP = targetIP;
		this.targetPort = targetPort;
		this.eigeneAbteilungsID = eigeneAbteilungsID;
		this.remoteAbteilungsID = remoteAbteilungsID;
	}
	
	public void run() {
		try {
			deliverMessages();
		} catch (InterruptedException e) {
			tafelServer.printStackTrace(e);
		}	
	}
	public void deliverMessages() throws InterruptedException{
		CorbaClient corbaClient = null;
		CorbaRequestDeliver deliverer = null;
		CorbaRequest request = null;
		while(true){
			try {
				if (corbaClient == null) {
					corbaClient = new CorbaClient(targetIP, targetPort);
					if (deliverer == null){
						deliverer = new CorbaRequestDeliver(corbaClient);
					} else {
						deliverer.setCorbaClient(corbaClient);
					}
				}
				request = messageQueue.take();
				deliverer.deliver(request);
				request = null;
//				tafelServer.saveQueueMapToFile();
			} catch (Exception e) {
				if (request != null) {
					messageQueue.putFirst(request);
					
//					tafelServer.saveQueueMapToFile();
					request = null;
				}
				if (isInterrupted()){
					throw new InterruptedException();
				}
			}
		}
		
	}

}
