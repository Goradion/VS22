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
import verteilteAnzeigetafel.Message;
import verteilteAnzeigetafel.SoapableMessage;
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
				answer =  "Done";
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
	public String receiveSoapableMessage(SoapableMessage soapableMessage) {
		Message message = new Message(soapableMessage);
		if (tafelServer != null) {
			String answer = "";
			try {
				tafelServer.receiveMessage(message);
				answer =  "Done";
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	@Override
	public String registerServer(int abtNr) {
		if (tafelServer != null) {
			String answer = "";
			try {
				MessageContext mc = wsContext.getMessageContext();
				HttpExchange req = (HttpExchange) mc.get("com.sun.xml.ws.http.exchange"); 
				InetSocketAddress remoteAddress = req.getRemoteAddress();
				String callerAddress = "http://" + remoteAddress.getHostName() + ":" + tafelServer.getAddressPort(abtNr) + "/TafelWS/serverws?wsdl";
//				InetSocketAddress localAddress = req.getLocalAddress();
//				String receiverAddress = "http://" + localAddress.getHostName() + ":" + tafelServer.getAddressPort(abtNr) + "/TafelWS/serverws?wsdl";
				tafelServer.print("ServerWS remote: "+callerAddress);
//				tafelServer.print("ServerWS local: "+receiverAddress);
				// TODO beide gleich, wenn auf einem System, gleich der "remote" adresse des aufrufenden programms
				//      sollten Server nicht auf einem physischen server laufen können?!
				answer = tafelServer.registerTafel(abtNr, new URL(callerAddress));
			} catch (NumberFormatException e) {
				tafelServer.printStackTrace(e);
				answer = "Number Format Error";
			} 
			catch (TafelException e) {
				tafelServer.printStackTrace(e);
				answer = e.getMessage();
			} catch (MalformedURLException e) {
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
				answer =  "Done";
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	@Override
	public String modifyPublicMessage(int msgID, int group, String inhalt) {
		if (tafelServer != null) {
			String answer = "";
			try {
				tafelServer.modifyPublicMessage(msgID, inhalt, group);
				answer =  "Done";
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}
}
