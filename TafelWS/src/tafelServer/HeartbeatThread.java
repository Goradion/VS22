package tafelServer;

import java.net.URL;

import serverCom.gen.ServerComWebservice;
import serverCom.gen.ServerComWebserviceImplService;

public class HeartbeatThread extends Thread {
	private static final int sleepTime = 5000;
	private int abteilungsID;
	private URL adress;
	private TafelServer tafelServer;
	private boolean connected = false;

	/**
	 * Construcs a new HeartbeatThread
	 * 
	 * @param abteilungsID
	 * @param url
	 * @param tafelServer
	 */
	public HeartbeatThread(int abteilungsID, URL url, TafelServer tafelServer) {
		super();
		this.abteilungsID = abteilungsID;
		this.adress = url;
		this.tafelServer = tafelServer;
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
						port = new ServerComWebserviceImplService(adress).getServerComWebserviceImplPort();
					}
					port.registerServer(abteilungsID, null);// TODO ziel ließt // die ip au
					if (!connected){
						tafelServer.print("Connected to Abteilung " + abteilungsID + " " + adress);
						connected = true;
					}
															
				} catch (Exception e) {
					if (connected){
						tafelServer.print("Disconnected from Abteilung " + abteilungsID + " " + adress);
						connected = false;
					}
					
					if (isInterrupted()) {
						throw new InterruptedException();
					}
					
				}
				Thread.sleep(sleepTime);
			}
		} catch (InterruptedException e) {
			tafelServer.print("HeartbeatThread " + abteilungsID + " wurde unterbrochen!");
		}
	}

}
