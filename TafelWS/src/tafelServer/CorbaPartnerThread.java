package tafelServer;

import java.util.concurrent.LinkedBlockingDeque;

import serverRequests.corba.CorbaRequest;
import tafelServer.CorbaClient.CorbaClient;

public class CorbaPartnerThread extends Thread {
	public static final int MIN_SLEEP_TIME = 1000;
	public static final int MAX_SLEEP_TIME = 10000;

	private LinkedBlockingDeque<CorbaRequest> messageQueue;
	private TafelServer tafelServer;
	private String targetIP;
	private String targetPort;
	// might get used later
	@SuppressWarnings("unused")
	private int eigeneAbteilungsID;
	@SuppressWarnings("unused")
	private int remoteAbteilungsID;
	private int sleepTime = MIN_SLEEP_TIME;

	public CorbaPartnerThread(LinkedBlockingDeque<CorbaRequest> messageQueue, TafelServer tafelServer, String targetIP,
			String targetPort, int eigeneAbteilungsID, int remoteAbteilungsID) {
		super();
		this.messageQueue = messageQueue;
		this.tafelServer = tafelServer;
		this.targetIP = targetIP;
		this.targetPort = targetPort;
		this.eigeneAbteilungsID = eigeneAbteilungsID;
		this.remoteAbteilungsID = remoteAbteilungsID;
	}

	public void run() {
		tafelServer.print("PartnerThread läuft...");
		try {
			deliverMessages();
		} catch (InterruptedException e) {
			tafelServer.printStackTrace(e);
		}
	}

	public void deliverMessages() throws InterruptedException {
		CorbaClient corbaClient = null;
		CorbaRequestDeliver deliverer = null;
		CorbaRequest request = null;
		boolean connected = false;
		while (true) {

			try {
				if (corbaClient == null) {
					corbaClient = new CorbaClient(targetIP, targetPort);
				}
				if (deliverer == null) {
					tafelServer.print("Connecting to partner...");
					deliverer = new CorbaRequestDeliver(corbaClient);
					connected = true;
					tafelServer.print("Connected to partner!");
				}
				if (!connected){
					tafelServer.print("Reconnecting to partner...");
					corbaClient.connectToServer();
					connected = true;
					tafelServer.print("Connected to partner!");
				}
				request = messageQueue.take();
				deliverer.deliver(request);
				request = null;
				tafelServer.savePartnerQueueToFile();
				sleepTime = MIN_SLEEP_TIME;
			} catch (Exception e) {
				if (request != null) {
					messageQueue.putFirst(request);
					tafelServer.savePartnerQueueToFile();
					request = null;
				}
				tafelServer.print("Partner not available! "+ e.getMessage() +" Trying again in " + sleepTime + " milliseconds.");
				connected = false;
				sleep(sleepTime);
				if (sleepTime < MAX_SLEEP_TIME) {
					sleepTime += MIN_SLEEP_TIME;
				}
				if (isInterrupted()) {
					throw new InterruptedException();
				}
			}
		}

	}

}
