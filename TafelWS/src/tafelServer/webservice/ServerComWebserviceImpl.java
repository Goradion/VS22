package tafelServer.webservice;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.annotation.Resource;
import javax.jws.WebService;
//import javax.servlet.http.HttpServletRequest; JAVA-EE
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.sun.net.httpserver.HttpExchange;

import tafelServer.TafelServer;
import verteilteAnzeigetafel.TafelException;

@WebService(endpointInterface = "tafelServer.webservice.ServerComWebservice")
public class ServerComWebserviceImpl implements ServerComWebservice {
	@Resource
	WebServiceContext wsContext;

	private TafelServer tafelServer;

	public ServerComWebserviceImpl() {
		super();
		tafelServer = TafelServer.getServer();
	}

	@Override
	public String receiveMessage(int messageID, int userID, int abtNr, String inhalt, String time, int group) {
		if (tafelServer != null) {
			String answer = "";
			try {
				DateFormat formatTime = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
				tafelServer.receiveMessage(messageID, userID, abtNr, inhalt, formatTime.parse(time), group);
				answer = "Done";
			} catch (TafelException e) {
				answer = e.getMessage();
			} catch (ParseException e) {
				tafelServer.printStackTrace(e);
				answer = "Date Parse Error";
			}
			return answer;
		}
		return null;
	}

	@Override
	public String receiveMessageCorba(int messageID, int userID, int serverNr, String inhalt, String time,
			boolean oeffentlich) {
		if (tafelServer != null) {
			String answer = "";
			try {
				DateFormat formatTime = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy", Locale.ENGLISH);
				tafelServer.receiveMessageCorba(messageID, userID, serverNr, inhalt, formatTime.parse(time),
						oeffentlich);
				answer = "Done";
			} catch (TafelException e) {
				answer = e.getMessage();
			} catch (ParseException e) {
				tafelServer.printStackTrace(e);
				answer = "Date Parse Error";
			}
			tafelServer.print("receiveMessageCorba " + answer);
			return answer;
		}
		return null;
	}

	@Override
	public String deleteMessageCorba(int msgID) {
		if (tafelServer != null) {
			String answer = "";
			try {
				tafelServer.deleteMessageCorba(msgID);
				answer = "Done";
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			tafelServer.print("deleteMessageCorba " + answer);
			return answer;
		}
		return null;
	}

	@Override
	public String modifyMessageCorba(int msgID, String inhalt) {
		if (tafelServer != null) {
			String answer = "";
			try {
				tafelServer.modifyMessageCorba(msgID, inhalt);
				answer = "Done";
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			tafelServer.print("modifyMessageCorba " + answer);
			return answer;
		}
		return null;
	}

	@Override
	public String registerServer(int abtNr) { // TODO Übergabe der IP vom andern
												// Tafelserver
		if (tafelServer != null) {
			String answer = "";
			try {
				MessageContext mc = wsContext.getMessageContext();
				HttpExchange req = (HttpExchange) mc.get("com.sun.xml.ws.http.exchange");
//				InetSocketAddress remoteAddress = req.getRemoteAddress();
//				URL url = tafelServer.getTafelAdressen().get(abtNr);
//				String callerAddress = "http://" + remoteAddress.getHostName() + ":" + tafelServer.getAddressPort(abtNr)
//						+ "/TafelWS/serverws?wsdl";
				
				// InetSocketAddress localAddress = req.getLocalAddress();
				// String receiverAddress = "http://" +
				// localAddress.getHostName() + ":" +
				// tafelServer.getAddressPort(abtNr) + "/TafelWS/serverws?wsdl";
				// tafelServer.print("ServerWS remote: "+callerAddress);
				// tafelServer.print("ServerWS local: "+receiverAddress);
				// TODO beide gleich, wenn auf einem System, gleich der "remote"
				// adresse des aufrufenden programms
				// sollten Server nicht auf einem physischen server laufen
				// können?!
				answer = tafelServer.registerTafel(abtNr, tafelServer.getTafelAdressen().get(abtNr));
			} catch (TafelException e) {
				tafelServer.printStackTrace(e);
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	@Override
	public String deletePublicMessage(int msgID, int group) {
		if (tafelServer != null) {
			String answer = "";
			try {
				tafelServer.deletePublicMessage(msgID, group);
				answer = "Done";
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	@Override
	public String modifyPublicMessage(int msgID, String inhalt) {
		if (tafelServer != null) {
			String answer = "";
			try {
				tafelServer.modifyPublicMessage(msgID, inhalt);
				answer = "Done";
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}
}
