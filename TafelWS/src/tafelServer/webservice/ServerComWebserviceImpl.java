package tafelServer.webservice;

import java.net.InetSocketAddress;
import java.util.Date;

import javax.jws.WebService;

import tafelServer.TafelServer;
import verteilteAnzeigetafel.TafelException;

@WebService
public class ServerComWebserviceImpl implements ServerComWebservice {
	TafelServer tafelServer;
	
	public ServerComWebserviceImpl(TafelServer tafelServer2) {
		tafelServer = tafelServer2;
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
			String[] addressParts = address.split(":");
			try {
				answer = tafelServer.registerTafel(abtNr, new InetSocketAddress(addressParts[0], Integer.parseInt(addressParts[1])));
			} catch (NumberFormatException e) {
				tafelServer.printStackTrace(e);
				answer = "Number Format Error";
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	@Override
	public String deletePublic(int msgID, int group) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.deletePublic(msgID, group);
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
				answer = tafelServer.modifyMessage(msgID, inhalt, 1);	//user 1 = Coordiantor
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

}
