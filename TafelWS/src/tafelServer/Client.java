/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tafelServer;

import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import javax.xml.datatype.DatatypeFactory;

import client.gen.TafelWebService;
import client.gen.TafelWebServiceImplService;
import serverCom.gen.ServerComWebservice;
import serverCom.gen.ServerComWebserviceImplService;
import serverRequests.ReceiveRequest;
import serverRequests.ServerRequest;
import verteilteAnzeigetafel.Anzeigetafel;
import verteilteAnzeigetafel.Message;
import verteilteAnzeigetafel.TafelException;

import java.io.*;

/**
 *
 * @author noone
 */

public class Client {
	public static final int SERVER_PORT = 10001;
	public static final String SERVER_HOSTNAME = "localhost";

	public static void main(String[] args) throws Exception {
		DateFormat formatTime = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
		String timeD = new Date().toString();
		System.out.println(timeD);
		System.out.println(formatTime.parse(timeD));
		String time = "Thu Jan 26 06:12:30 CET 2017";
		System.out.println(formatTime.parse(time));
		
		ServerComWebservice port = new ServerComWebserviceImplService(new URL("http://localhost:8080/TafelWS/serverws")).getServerComWebserviceImplPort();
		System.out.println(port.modifyPublicMessage(1,""));
//		ConnectionMonitor monitor1 = new ConnectionMonitor();
//		ConnectionMonitor monitor2 = new ConnectionMonitor();
//		OutboxThread outboxThread1 = new OutboxThread(1, new URL("http://localhost:8080"), null, null);
//		outboxThread1.start();
//		TafelWebServiceImplService service = new TafelWebServiceImplService();
//		System.out.println("Service erstellt...");
//		TafelWebService port = service.getTafelWebServiceImplPort();
//		System.out.println("Port erstellt...");

//		LinkedBlockingDeque<ServerRequest> q = new LinkedBlockingDeque<ServerRequest>();
//		TafelServer ts = new TafelServer();
//		q.add(new ReceiveRequest(new Message("qq", 1, 2, true, 4711)));
//		q.add(new ReceiveRequest(new Message("qqq", 1, 2, true, 4712)));
//		new OutboxThread(1, new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT), q, ts).start();
//		q.add(new ReceiveRequest(new Message("qqqq", 1, 2, true, 4713)));
//		new HeartbeatThread(2, new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT), ts).start();
		
//		try {
//			Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
//			//ServerRequest sr = ServerRequest.buildShowMyMessagesRequest(1);
//			ServerRequest sr = ServerRequest.buildCreateRequest("test", 1, 1);
//			ObjectOutputStream oout = new ObjectOutputStream(socket.getOutputStream());
//			// sr =
//			// ServerRequest.buildShowMyMessagesRequest(ServerRequestType.SHOW_MY_MESSAGES,
//			// 1);
//			//oout.writeObject(sr);
//			//Thread.sleep(5000);
//			// byte[] b = new byte [128];
//			// InputStream stream = socket.getInputStream();
//			// while (stream.available() == 0);
//			// stream.read (b);
//			// System.out.println(new String(b));
////			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
////			LinkedList<Message> userMessages = (LinkedList<Message>) input.readObject();
////			System.out.println(userMessages.toString());
//			//oout.writeObject(null);
//			socket.close();
//		} catch (UnknownHostException e) {
//			// Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// Auto-generated catch block
//			e.printStackTrace();
//		}
////		 catch (ClassNotFoundException e) {
////			// Auto-generated catch block
////			e.printStackTrace();
////		}
	}
}
