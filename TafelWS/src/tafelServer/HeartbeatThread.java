package tafelServer;

import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

import serverCom.gen.ServerComWebservice;
import serverCom.gen.ServerComWebserviceImplService;

public class HeartbeatThread extends Thread {
	private static final int sleepTime = 5000;
	private int eigeneAbteilungsID;
	private int remoteAbteilungsID;
	private URL targetAddress;
	private TafelServer tafelServer;
	private boolean connected;
	private AtomicBoolean running;
	private AtomicBoolean manualStopped;

	/**
	 * Construcs a new HeartbeatThread
	 * 
	 * @param abteilungsID
	 * @param url
	 * @param tafelServer
	 */
	public HeartbeatThread(int eigeneAbteilungsID, int abteilungsID, URL url, TafelServer tafelServer) {
		super();
		this.connected = false;
		this.running = new AtomicBoolean(true);
        this.manualStopped = new AtomicBoolean(false);
		this.eigeneAbteilungsID = eigeneAbteilungsID;
		this.remoteAbteilungsID = abteilungsID;
		this.targetAddress = url;
		this.tafelServer = tafelServer;
	}
	
	private void startIt() {
	    manualStopped.set(false);
        running.set(true);
    }

    public void stopIt() {
        manualStopped.set(true);
        if (isWaiting()) {
            this.interrupt(); 
        }
        running.set(false);
    }
    
    public boolean isRunning() {
        return running.get();
    }
    
    public boolean isWaiting() {
        return getState() == Thread.State.TIMED_WAITING || getState() == Thread.State.WAITING;
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
		startIt();
		try {
			while (isRunning()) {
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
						throw new InterruptedException(getMyName() + " wurde unterbrochen!");
					}
					
				}
				Thread.sleep(sleepTime);
			}
		} catch (InterruptedException e) {
		    if (!manualStopped.get()) {
		        tafelServer.printStackTrace(e);
		    }
		} finally {
            if (manualStopped.get()) {
                tafelServer.print(getMyName() + " was stopped normal.");
            }
        }
	}
	
	private String getMyName() {
        return "HeartbeatThread " + remoteAbteilungsID;
    }

}
