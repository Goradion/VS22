package tafelServer;

import java.net.URL;
import java.util.concurrent.LinkedBlockingDeque;

import client.gen.TafelWebServiceImplService;
import serverRequests.ServerRequest;
import tafelServer.webservice.ServerComWebservice;

public class OutboxThread extends Thread {
	int abteilungsID;
	URL target;
	LinkedBlockingDeque<ServerRequest> messageQueue;
	TafelServer tafelServer;
	ConnectionMonitor monitor;

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
		this.abteilungsID = abteilungsID;
		this.target = targetAdress;
		this.messageQueue = messageQueue;
		this.tafelServer = tafelServer;
		this.monitor = new ConnectionMonitor();
	}

	public void doWait() {
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
			deliverMessages();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		tafelServer.print(getMyName() + " läuft!\n" + messageQueue.toString());

		//
		// Socket socket = null;
		// ServerRequest request = null;
		// try {
		// while (true) {
		// if (socket == null || socket.isClosed()) {
		// socket = new Socket();
		// socket.connect(adress);
		// }
		//
		// request = messageQueue.take();
		// tafelServer.saveQueueMapToFile();
		// ObjectOutputStream oout = new
		// ObjectOutputStream(socket.getOutputStream());
		// oout.writeObject(request);
		// // oout.flush();
		// request = null;
		// }
		// } catch (InterruptedException e) {
		// tafelServer.print(getMyName() + " wurde unterbrochen!");
		// ObjectOutputStream oout;
		// try {
		// oout = new ObjectOutputStream(socket.getOutputStream());
		// oout.writeObject(null);
		// } catch (IOException e1) {
		// tafelServer.print(getMyName() + " konnte sich nicht verabschieden!");
		// }
		//
		// } catch (IOException e) {
		// tafelServer.print(getMyName() + ": " + adress.toString() + " nicht
		// erreichbar! " + e.getMessage());
		// } finally {
		// if (request != null) {
		// try {
		// messageQueue.putFirst(request);
		// tafelServer.saveQueueMapToFile();
		// } catch (InterruptedException e) {
		// tafelServer.print(
		// "Request " + request + " ging auf dem Weg zu Abteilung " +
		// abteilungsID + " verloren");
		// }
		// }
		// try {
		// if (socket != null && !socket.isClosed()) {
		// socket.close();
		// tafelServer.print(getMyName() + ": Socket geschlossen!");
		// }
		// } catch (IOException e) {
		// tafelServer.print(getMyName() + ": Fehler beim Schließen des Sockets"
		// + e.getMessage());
		// e.printStackTrace();
		// }
		// }

	}

	private void deliverMessages() throws InterruptedException{
		ServerComWebservice port = null;
		ServerRequest request = null;
		while (true) {
			try {
//				port = new ServerC;
				request = messageQueue.take();
				// tafelServer.saveQueueMapToFile();
			} catch (Exception e) {
				if (isInterrupted()){
					throw new InterruptedException();
				}
				tafelServer.print(getMyName() + " paused!");
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
