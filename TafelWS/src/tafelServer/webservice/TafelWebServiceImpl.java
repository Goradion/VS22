package tafelServer.webservice;

import java.util.LinkedList;

import javax.jws.WebService;

import verteilteAnzeigetafel.SoapableMessage;
import tafelServer.TafelServer;
import verteilteAnzeigetafel.Message;
import verteilteAnzeigetafel.TafelException;

@WebService(endpointInterface = "tafelServer.webservice.TafelWebService")
public class TafelWebServiceImpl implements TafelWebService {
	TafelServer tafelServer;

	// public TafelWebServiceImpl(int abteilungsID) {
	// super();
	// this.tafelServer = new TafelServer(abteilungsID);
	// }

	public TafelWebServiceImpl() {
		super();
		tafelServer = TafelServer.getServer();
	}

	public String createMessage(String inhalt, int user, int abtNr) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.createMessage(inhalt, user, abtNr);
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	public String deleteMessage(int messageID, int user) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.deleteMessage(messageID, user);
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	public String modifyMessage(int messageID, String inhalt, int user) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.modifyMessage(messageID, inhalt, user);
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}
	
	public String publishMessage(int messageID, int user, int group) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.publishMessage(messageID, user, group);
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	@Override
	public SoapableMessage[] showMessages(int user) {

		SoapableMessage[] answer;
		LinkedList<Message> userMessages = new LinkedList<Message>();
		try{
			userMessages = tafelServer.getMessagesByUserID(user);
		} catch( TafelException te){
			answer = new SoapableMessage[1];
			SoapableMessage sm = new SoapableMessage();
			sm.setInhalt(te.getMessage());
			answer[0] = sm;
		}
		answer = new SoapableMessage[userMessages.size()];
		/* create "soapable" answer */
		for(int i = 0; i < userMessages.size(); i++){
			SoapableMessage soM = new SoapableMessage();
			soM.setAbtNr(userMessages.get(i).getAbtNr());
			soM.setGruppen(userMessages.get(i).getGruppenAsArray());
			soM.setInhalt(userMessages.get(i).getInhalt());
			soM.setMessageID(userMessages.get(i).getMessageID());
			soM.setTime(userMessages.get(i).getTime());
			soM.setOeffentlich(userMessages.get(i).isOeffentlich());
			soM.setUserID(userMessages.get(i).getUserID());
			answer[i] = soM;
		}
		
		return answer;
	}

	@Override
	public String deletePublic(int msgID, int user, int group) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.deletePublic(msgID, user, group);
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	@Override
	public String modifyPublic(int msgID, int user, int group, String inhalt) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.modifyPublic(msgID, user, group, inhalt);
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	public String[] startTafelServer(int userID, int abtNr) {
		String reply[] = new String[1];

		if (!tafelServer.getAnzeigetafel().isCoordinator(userID)) {
		    reply[0] = "User " + userID + " has no permission!";
		} else {
    		if (tafelServer == null) {
    			TafelServer.startServer(abtNr);
    			tafelServer = TafelServer.getServer();
    			reply[0] = "TafelServer started successfully.";
    		} else {
    			reply[0] = "Server is already running.";
    		}
		}
		return reply;
	}

	public String[] stopTafelServer(int userID) {
	    String reply[] = new String[1];
	    
	    if (!tafelServer.getAnzeigetafel().isCoordinator(userID)) {
            reply[0] = "User " + userID + " has no permission!";
        } else {
            tafelServer.stopServer();
            reply[0] = "User stopped successfully.";
        }
		return reply;
	}
}
