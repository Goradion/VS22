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
	public boolean receiveMessage(int messageID, int userID, int abtNr, String inhalt, Date time) {
		try {
			tafelServer.receiveMessage(messageID, userID, abtNr, inhalt, time);
		} catch (TafelException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean registerServer(int abtNr, String address) {
		String[] addressParts = address.split(":");
		try {
			tafelServer.registerTafel(abtNr, new InetSocketAddress(addressParts[0], Integer.parseInt(addressParts[1])));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TafelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deletePublic(int msgID, int abtNr, int group) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyPublic(int msgID, int abtNr, int group, String inhalt) {
		// TODO Auto-generated method stub
		return false;
	}

}
