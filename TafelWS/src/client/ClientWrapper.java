package client;

import java.net.URL;
import java.util.List;

import javax.jws.WebMethod;
import javax.xml.ws.WebServiceException;

import client.gen.TafelWebService;
import client.gen.TafelWebServiceImplService;
import verteilteAnzeigetafel.SoapableMessage;

public class ClientWrapper {
	private URL targetUrl;
	private TafelWebServiceImplService tafelWebService;
	private TafelWebService port;
	private boolean connected = false;

	public ClientWrapper() {
		try {
			tafelWebService = new TafelWebServiceImplService();
			tafelWebService.getWSDLDocumentLocation();
			port = tafelWebService.getTafelWebServiceImplPort();
			connected = true;
		} catch (WebServiceException wse) {
			wse.printStackTrace();
		}
	}

	public URL getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(URL targetUrl) {
		this.targetUrl = targetUrl;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public void connect(URL targetUrl) {
		this.targetUrl = targetUrl;
		reconnect();

	}
	
	private void reconnect(){
		try {
			tafelWebService = new TafelWebServiceImplService(targetUrl);
			port = tafelWebService.getTafelWebServiceImplPort();
			connected = true;
		} catch (WebServiceException wse) {
			connected = false;
		}
	}

	public String createMessage(String inhalt, int user, int abtNr) {
		// TODO Auto-generated method stub
		if (!connected){
			reconnect();
		}
		try {
			return port.createMessage(inhalt, user, abtNr);
		} catch (WebServiceException wse) {
			connected = false;
		}
		return null;
	}

	public String deleteMessage(int messageID, int user) {
		// TODO Auto-generated method stub
		try {

		} catch (WebServiceException wse) {
			connected = false;
		}
		return port.deleteMessage(messageID, user);
	}

	public String modifyMessage(int messageID, String inhalt, int user) {
		// TODO Auto-generated method stub
		try {

		} catch (WebServiceException wse) {
			connected = false;
		}
		return port.modifyMessage(messageID, inhalt, user);
	}

	public String publishMessage(int messageID, int user, int group) {
		// TODO Auto-generated method stub
		try {

		} catch (WebServiceException wse) {
			connected = false;
		}
		return port.publishMessage(messageID, user, group);
	}

	public String deletePublic(int msgID, int user, int group) {
		// TODO Auto-generated method stub
		try {

		} catch (WebServiceException wse) {
			connected = false;
		}
		return port.deletePublic(msgID, user, group);
	}

	public String modifyPublic(int msgID, int user, int group, String inhalt) {
		// TODO Auto-generated method stub
		try {

		} catch (WebServiceException wse) {
			connected = false;
		}
		return port.modifyPublic(msgID, user, group, inhalt);
	}

	public List<Integer> getGroupIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<client.gen.SoapableMessage> showMessages(int user) {
		// TODO Auto-generated method stub
		try {

		} catch (WebServiceException wse) {
			connected = false;
		}
		return port.showMessages(user);
	}

	public List<String> startTafelServer(int userID, int abtNr) {
		// TODO Auto-generated method stub
		try {

		} catch (WebServiceException wse) {
			connected = false;
		}
		return port.startTafelServer(userID, abtNr);
	}

	public List<String> stopTafelServer(int userID) {
		// TODO Auto-generated method stub
		try {

		} catch (WebServiceException wse) {
			connected = false;
		}
		return port.stopTafelServer(userID);
	}

}
