package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.datatype.XMLGregorianCalendar;

import client.gen.SoapableMessage;
import client.gen.TafelWebService;
import client.gen.TafelWebServiceImplService;
import verteilteAnzeigetafel.Message;

public class WSClient {
	// Regular expression for IP-Address
	private static final Pattern PATTERN = Pattern
			.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	public static void main(String[] args) throws MalformedURLException {
		TafelWebService port = null;
		if (args.length >= 1) {
//			if (isValidIpAddress(args[0])) {
				port = new TafelWebServiceImplService(new URL(args[0])).getTafelWebServiceImplPort();
//			} else {
//				port = new TafelWebServiceImplService().getTafelWebServiceImplPort();
//			}
		} else {
			port = new TafelWebServiceImplService().getTafelWebServiceImplPort();
		}
		List<String> request = new ArrayList<String>();
//		List<String> reply = port.startTafelServer(request);
//		System.out.println(reply.get(0));
//		List<String> reply = port.startTafelServer(1, 1);
//		System.out.println(reply.get(0));
//		List<String> reply2 = port.startTafelServer(1, 1);
//		System.out.println(reply2.get(0));
		
		System.out.println(port.startTafelServer(2, 1).get(0));
//		System.out.println(port.stopTafelServer(2).get(0));
//		System.out.println(port.stopTafelServer(1).get(0));
		System.out.println(port.startTafelServer(1, 1).get(0));
		for (int i = 0; i < 10; i++){
		    System.out.println(port.createMessage("message "+i, 1, 1));
		}
		
		System.out.println(port.publishMessage(112, 1, 1));
		System.out.println(port.publishMessage(1110, 1, 1));
		System.out.println(port.publishMessage(1121, 1, 1));
		
		List<SoapableMessage> userMessages = port.showMessages(1);
		
		LinkedList<Message> normalMessages = new LinkedList<Message>();
		for(SoapableMessage sm: userMessages){
			XMLGregorianCalendar cal = sm.getTime();
			GregorianCalendar gc = cal.toGregorianCalendar();			
			Date date = new Date(gc.getTimeInMillis());
			Message m = new Message(sm.getMessageID(),sm.getUserID(),sm.getAbtNr(),sm.getInhalt(),sm.isOeffentlich(),date);
			for(Integer i: sm.getGroups()){
				m.addGroup(i);
			}
			normalMessages.add(m);
		}
		System.out.println(port.publishMessage(0, 1, 2));
		for(Message m: normalMessages){
			System.out.println(m);
		}
		
		System.out.println(port.stopTafelServer(1));
	}

	private static boolean isValidIpAddress(String ip) {
		return PATTERN.matcher(ip).matches();
	}
}
