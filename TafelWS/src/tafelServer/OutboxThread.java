package tafelServer;

import java.net.URL;
import java.util.concurrent.LinkedBlockingDeque;

import serverCom.gen.ServerComWebservice;
import serverCom.gen.ServerComWebserviceImplService;
import serverRequests.ServerRequest;

public class OutboxThread extends Thread {
	private int abteilungsID;
	private URL targetAddress;
	private LinkedBlockingDeque<ServerRequest> messageQueue;
	private TafelServer tafelServer;
	private ConnectionMonitor monitor;
	private boolean running;
	private boolean waiting;
	private boolean manualStopped;
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
		this.running = true;
		this.waiting = false;
		this.manualStopped = false;
		this.abteilungsID = abteilungsID;
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
	
	private synchronized void startIt () {
	    manualStopped = false;
	    running = true;
	}

	public synchronized void stopIt () {
	    manualStopped = true;
	    if (isWaiting()) {
	        doNotify();
	    } else {
	        if (!isRequestSet()) // resume from take, nur wenn er auch wirklich bei take ist, ansonsten soll er die Runde ja fertig arbeiten
	            this.interrupt(); 
	    }
        running = false;
    }
	
	public synchronized boolean isRunning () {
        return running;
    }
	
	private synchronized void setThreadIsWaiting () {
	    waiting = true;
    }

    private synchronized void setThreadIsResumed () {
        waiting = false;
    }
	
    public synchronized boolean isWaiting () {
	    return waiting;
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
			    setThreadIsWaiting();
			    monitor.wait();
			} catch (InterruptedException e) {
			}
		}
	}

	public void doNotify() {
		synchronized (monitor) {
		    monitor.notify();
		    setThreadIsResumed();
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
			if (!manualStopped) {
			    tafelServer.printStackTrace(e);
			}  
		} finally {
		    if (manualStopped) {
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
					throw new InterruptedException();
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
		return "OutboxThread " + abteilungsID;
	}
}
