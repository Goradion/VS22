package tafelServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

import serverRequests.ServerRequest;
import tafelServer.webservice.ServerComWebservice;

public class HeartbeatThread extends Thread {
	private static final int sleepTime = 5000;
	private int abteilungsID;
	private URL adress;
	private TafelServer tafelServer;

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
				
				Thread.sleep(sleepTime);
			}
		} catch (InterruptedException e) {
			tafelServer.print("HeartbeatThread " + abteilungsID + " wurde unterbrochen!");
		}
	}

}
