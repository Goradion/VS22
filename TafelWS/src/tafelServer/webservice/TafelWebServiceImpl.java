package tafelServer.webservice;

import java.util.Date;

import javax.jws.WebService;

import tafelServer.TafelServer;
import verteilteAnzeigetafel.TafelException;

@WebService(endpointInterface = "tafelServer.webservice.TafelWebService")
public class TafelWebServiceImpl implements TafelWebService {
	TafelServer tafelServer;

	public String createMessage(String inhalt, int user, int abtNr, boolean oeffentlich) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.createMessage(inhalt, user, abtNr, oeffentlich);
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	public String deleteMessage(int messageID, int user) {
		if (tafelServer != null) {
			//delete message
		}
		return null;
	}
	
	public String modifyMessage(int messageID, String inhalt, int user) {
		if (tafelServer != null) {
			//modify message
		}
		return null;
	}

	
	public String publishMessage(int messageID, int user) {
		
		return null;
	}
	
	public String[] startTafelServer(int antNr) {
		String reply[] = new String[1];
		
		// TODO implement access permissions for starting the server
		
		if (tafelServer == null) {
			tafelServer = new TafelServer(antNr);
			reply[0] = "TafelServer started successfully.";
		} else {
			reply[0] = "Server is already running.";
		}
		return reply;
	}

	public String[] stopTafelServer(String[] message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] showMessages(int user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean receiveMessage(int messageID, int userID, int abtNr, String inhalt, boolean oeffentlich,
			Date time) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerServer(int abtNr, String address) {
		// TODO Auto-generated method stub
		return false;
	}

}
