package client;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import client.gen.TafelWebService;
import client.gen.TafelWebServiceImplService;

public class Client implements Serializable {

	private static final long serialVersionUID = -6299053685373379874L;

	private String serverHostname = "localhost";
	private final String ABTEILUNG_1 = "192.168.178.10";
	private final String ABTEILUNG_2 = "192.168.178.11";
	private final String ABTEILUNG_3 = "192.168.178.12";
	private final String ABTEILUNG_4 = "192.168.178.13";
	private int userId;
	private int abtNr;

	
	public Client() {
		this.userId = 0;
		this.abtNr = 0;
	}

	private void setAbteilung(int abt) {
		switch (abt) {
		case 0:
			serverHostname = "localhost";
			break;
		case 1:
			serverHostname = ABTEILUNG_1;
			break;
		case 2:
			serverHostname = ABTEILUNG_2;
			break;
		case 3:
			serverHostname = ABTEILUNG_3;
			break;
		case 4:
			serverHostname = ABTEILUNG_4;
			break;
		default:
			serverHostname = "Unknown Host";
			break;
		}
	}

	public int getabtNr() {
		return abtNr;
	}

	public void setAbtNr(int abtNr) {
		this.abtNr = abtNr;
	}

	public int getUserID() {
		return userId;
	}

	public void setUserID(int userId) {
		this.userId = userId;
	}

	public void createMessage(int abt, String message, int userId) throws MalformedURLException {
		setAbteilung(abt);
		TafelWebService port = new TafelWebServiceImplService(new URL("http://"+serverHostname+":8080/TafelWS/tafelws?wsdl")).getTafelWebServiceImplPort();
		String reply = port.createMessage(message, userId, abt);
	}

	public void deleteMessage(int abt, int userId, int msgID) throws MalformedURLException {
		setAbteilung(abt);
		TafelWebService port = new TafelWebServiceImplService(new URL("http://"+serverHostname+":8080/TafelWS/tafelws?wsdl")).getTafelWebServiceImplPort();
		String reply = port.deleteMessage(msgID, userId);
		
	}

	public void changeMessage(int abt, int userId, int msgID, String newMessage) throws MalformedURLException {
		setAbteilung(abt);
		TafelWebService port = new TafelWebServiceImplService(new URL("http://"+serverHostname+":8080/TafelWS/tafelws?wsdl")).getTafelWebServiceImplPort();
		String reply = port.modifyMessage(msgID, newMessage, userId);
		
	}

	public String showMessages(int abt, int userId) throws MalformedURLException {
		setAbteilung(abt);
		TafelWebService port = new TafelWebServiceImplService(new URL("http://"+serverHostname+":8080/TafelWS/tafelws?wsdl")).getTafelWebServiceImplPort();
		List<String> reply = port.showMessages(userId);
		return null;
	}

	public void publishMessage(int abt, int msgId, int userId, int groupId) throws MalformedURLException {
		setAbteilung(abt);
		TafelWebService port = new TafelWebServiceImplService(new URL("http://"+serverHostname+":8080/TafelWS/tafelws?wsdl")).getTafelWebServiceImplPort();
		String reply = port.publishMessage(msgId, userId, groupId);
	}

	public static void main(String[] args) {
		Client client = new Client();
		

	}
}
