package tafelServer.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

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
	private final int koordinatorID;
	TafelServer tafelServer;
	
	@Resource
	WebServiceContext wsContext;
	
	public ServerComWebserviceImpl(TafelServer tafelServer2) {
		tafelServer = tafelServer2;
		koordinatorID = 1;
	}

	@Override
	public String receiveMessage(int messageID, int userID, int abtNr, String inhalt, Date time, int group) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.receiveMessage(messageID, userID, abtNr, inhalt, time, group);
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	@Override
	public String registerServer(int abtNr, String address) {
		if (tafelServer != null) {
			String answer = "";
			try {
//				MessageContext mc = wsContext.getMessageContext();
//				HttpExchange req = (HttpExchange) mc.get("com.sun.xml.ws.http.exchange"); 
//				answer = "Client IP = " + req.getRemoteAddress();
//			    System.out.println(answer); 
//				answer = tafelServer.registerTafel(abtNr, new URL(address));
			} catch (NumberFormatException e) {
				tafelServer.printStackTrace(e);
				answer = "Number Format Error";
			} 
//			catch (TafelException e) {
//				answer = e.getMessage();
//			} catch (MalformedURLException e) {
//				answer = e.getMessage();
//			}
			return answer;
		}
		return null;
	}

	@Override
	public String deletePublic(int msgID, int group) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.deletePublic(msgID, koordinatorID, group);
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	@Override
	public String modifyPublic(int msgID, int abtNr, int group, String inhalt) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.modifyPublic(msgID, inhalt, koordinatorID);
			} catch (TafelException e) {
				answer = e.getMessage();
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
				answer = tafelServer.receiveMessage(message);
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

}
