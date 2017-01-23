package tafelServer.webservice;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

public class TafelWsPublisher {

	public static void main(String[] args) {
		Endpoint endpoint = Endpoint.publish("http://localhost:8080/TafelWS/tafelws", new TafelWebServiceImpl());
		JOptionPane.showMessageDialog(null, "Server beenden");
		endpoint.stop();
	}

}
