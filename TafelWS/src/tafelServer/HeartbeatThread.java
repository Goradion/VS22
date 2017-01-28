package tafelServer;

import java.net.URL;

import serverCom.gen.ServerComWebservice;
import serverCom.gen.ServerComWebserviceImplService;

public class HeartbeatThread extends Thread {
	private static final int sleepTime = 5000;
	private int eigeneAbteilungsID;
	private int remoteAbteilungsID;
	private URL targetAddress;
	private TafelServer tafelServer;
	private boolean connected = false;

	/**
	 * Construcs a new HeartbeatThread
	 * 
	 * @param abteilungsID
	 * @param url
	 * @param tafelServer
	 */
	public HeartbeatThread(int eigeneAbteilungsID, int abteilungsID, URL url, TafelServer tafelServer) {
		super();
		this.eigeneAbteilungsID = eigeneAbteilungsID;
		this.remoteAbteilungsID = abteilungsID;
		this.targetAddress = url;
		this.tafelServer = tafelServer;
	}
	
	public synchronized void setTargetAddress(URL targetAddress) {
        this.targetAddress = targetAddress;
    }
	
	public synchronized URL getTargetAddress() {
        return targetAddress;
    }

	/**
	 * The HeartbeatThread tries to contact its assigned TafelServer via the
	 * given adress in intervals given in \var sleepTime;
	 */
	public void run() {
		ServerComWebservice port = null;
		try {
			while (true) {
				try {
					if (port == null) {
						port = new ServerComWebserviceImplService(getTargetAddress()).getServerComWebserviceImplPort();
					}
					port.registerServer(eigeneAbteilungsID);
					if (!connected){
						tafelServer.print("Connected to Abteilung " + remoteAbteilungsID + " " + getTargetAddress());
						connected = true;
					}
															
				} catch (Exception e) {
					if (connected){
						tafelServer.print("Disconnected from Abteilung " + remoteAbteilungsID + " " + getTargetAddress());
						connected = false;
					}
					
					if (isInterrupted()) {
						throw new InterruptedException();
					}
					
				}
				Thread.sleep(sleepTime);
			}
		} catch (InterruptedException e) {
			tafelServer.print("HeartbeatThread " + remoteAbteilungsID + " wurde unterbrochen!");
		}
	}

}
