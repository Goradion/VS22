package tafelServer;

import java.net.URL;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

import serverCom.gen.ServerComWebservice;
import serverCom.gen.ServerComWebserviceImplService;
import serverRequests.ServerRequest;

public class OutboxThread extends Thread {
	private int remoteAbteilungsID;
	private URL targetAddress;
	private LinkedBlockingDeque<ServerRequest> messageQueue;
	private TafelServer tafelServer;
	private ConnectionMonitor monitor;
	private AtomicBoolean running;
	private AtomicBoolean manualStopped;
	private ServerRequest request = null;

	/**
	 * Constructs a new OutboxThread
	 * 
	 * @param abteilungsID
	 *            Who I deliver to.
	 * @param adress
	 *            Where to deliver.
	 * @param messageQueue
	 *            Here are the messages to deliver.
	 * @param tafelServer
	 *            The tafelServer where status messages are sent.
	 */
	public OutboxThread(int abteilungsID, URL targetAdress, LinkedBlockingDeque<ServerRequest> messageQueue,
			TafelServer tafelServer) {
		super();
		this.running = new AtomicBoolean(true);
		this.manualStopped = new AtomicBoolean(false);
		this.remoteAbteilungsID = abteilungsID;
		this.targetAddress = targetAdress;
		this.messageQueue = messageQueue;
		this.tafelServer = tafelServer;
		this.monitor = new ConnectionMonitor();
	}
	
	private synchronized void setRequest(ServerRequest request) {
	    this.request = request;
	}
	
	private synchronized ServerRequest getRequest() {
        return request;
    }
	
	private synchronized void clearRequest() {
	    request = null;
	}
	
	private synchronized boolean isRequestSet() {
	    return request != null;
	}
	
	private void startIt() {
	    manualStopped.set(false);
	    running.set(true);
	}

	public void stopIt() {
	    manualStopped.set(true);
	    if (isWaiting()) {
	        doNotify();
	    } else {
	        if (!isRequestSet()) // resume from take, nur wenn er auch wirklich bei take ist, ansonsten soll er die Runde ja fertig arbeiten
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

	private void doWait() {
		synchronized (monitor) {
			try {
			    monitor.wait();
			} catch (InterruptedException e) {
			}
		}
	}

	public void doNotify() {
		synchronized (monitor) {
		    monitor.notify();
		}
	}

	/**
	 * Attempts to deliver the messages from the messageQueue.
	 */
	public void run() {
		try {
		    startIt();
		    deliverMessages();
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

	private void deliverMessages() throws InterruptedException{
		ServerComWebserviceImplService serverComWebserviceImplService = null;
	    ServerComWebservice port = null;
		ServerRequestDeliverer deliverer = null;
		while (isRunning()) {
			try {
				if (port == null ) {
					serverComWebserviceImplService = new ServerComWebserviceImplService(getTargetAddress());
				    port = serverComWebserviceImplService.getServerComWebserviceImplPort();
				    deliverer = new ServerRequestDeliverer(port);
				}
				tafelServer.print(getMyName() + ": Other WS address: " + serverComWebserviceImplService.getWSDLDocumentLocation());
				
//				tafelServer.print(getMyName() + " läuft!\n" + messageQueue);
				setRequest(messageQueue.take());

				String returned = deliverer.deliver(getRequest());
				tafelServer.print(getMyName() + ": Deliverer returned: " + returned);
				
				tafelServer.saveQueueMapToFile();
				clearRequest();
			} catch (Exception e) {
				if (isRequestSet()) {
					messageQueue.putFirst(getRequest());
					
					tafelServer.saveQueueMapToFile();
					clearRequest();
				}
				if (isInterrupted()){
					throw new InterruptedException(getMyName() + " wurde unterbrochen!");
				}
				tafelServer.print(getMyName() + " paused! " + e.getMessage());
				doWait();
				tafelServer.print(getMyName() + " resumed!");
			}
		}
	}
	/**
	 * Return a name to distinguish which OutboxThread is talking.
	 * 
	 * @return a name
	 */
	private String getMyName() {
		return "OutboxThread " + remoteAbteilungsID;
	}
}
