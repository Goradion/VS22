package tafelServer.webservice;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import tafelServer.TafelServer;

@WebService(endpointInterface = "tafelServer.webservice.TafelWebService")
@SOAPBinding(style = Style.DOCUMENT, parameterStyle = ParameterStyle.WRAPPED, use = Use.LITERAL)
public class TafelWebServiceImpl implements TafelWebService {
	TafelServer tafelServer;

	public synchronized String[] createMessage(String[] message) {
		if (tafelServer != null) {
			//create message
		}
		return null;
	}

	public synchronized String[] deleteMessage(String[] message) {
		if (tafelServer != null) {
			//delete message
		}
		return null;
	}

	public synchronized String[] modifyMessage(String[] message) {
		if (tafelServer != null) {
			//modify message
		}
		return null;
	}

	public synchronized String[] showMessage(String[] message) {
		if (tafelServer != null) {
			//show message
		}
		return null;
	}

	public String[] startTafelServer(String[] message) {
		String reply[] = new String[1];
		
		// TODO implement access permissions for starting the server
		
		if (tafelServer == null) {
			tafelServer = new TafelServer(message);
			tafelServer.start();
			reply[0] = "TafelServer started successfully.";
		} else {
			reply[0] = "Server is already running.";
		}
		return reply;
	}

}
