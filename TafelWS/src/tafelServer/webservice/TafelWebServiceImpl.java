package tafelServer.webservice;

import java.util.LinkedList;
import java.util.Date;

import javax.jws.WebService;

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
			} catch (InterruptedException e) {
				tafelServer.printStackTrace(e);
				answer = "Internal Server Error: e.getMessage()";
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

	public String[] startTafelServer(int abtNr) {
		String reply[] = new String[1];

		// TODO implement access permissions for starting the server

		if (tafelServer == null) {
			TafelServer.startServer(abtNr);
			tafelServer = TafelServer.getServer();
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

	// TODO format?
	@Override
	public String[] showMessages(int user) {
		int size = 0;
		boolean success = false;
		String errorMessage = "";
		LinkedList<Message> messagesByUserID = new LinkedList<Message>();
		try {
			messagesByUserID = tafelServer.getMessagesByUserID(user);
			size = messagesByUserID.size() + 1;
			success = true;
		} catch (TafelException e) {
			size = 2;
			errorMessage = e.getMessage();
		}
		String[] strings = new String[size];
		strings[0] = Boolean.toString(success);
		if (!success) {
			strings[1] = errorMessage;
		} else {
			for (int i = 1; i < strings.length; i++) {
				strings[i] = messagesByUserID.get(i - 1).toString();
			}
		}

		return strings;
	}

	@Override
	public String deletePublic(int msgID, int user, int group) {
		if (tafelServer != null) {
			String answer = "";
			try {
				answer = tafelServer.deletePublicMessage(msgID, user, group);
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
				answer = tafelServer.modifyPublicMessage(msgID, user, group, inhalt);
			} catch (TafelException e) {
				answer = e.getMessage();
			}
			return answer;
		}
		return null;
	}

}
