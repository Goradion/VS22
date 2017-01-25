package tafelServer.webservice;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

import tafelServer.TafelServer;

public class TafelWsPublisher {

	public static void main(String[] args) {
		TafelServer tafelServer;
		int abteilung = 1;
		if (args.length >= 1) {
			try {
				abteilung = Integer.parseInt(args[0]);
			} catch (NumberFormatException nfe) {
				// default to abteilung 1;
			}
		}
		
		TafelServer.startServer(abteilung);
		Endpoint clientEndpoint = Endpoint.publish("http://localhost:8080/TafelWS/tafelws", new TafelWebServiceImpl());
		Endpoint serverEndpoint = Endpoint.publish("http://localhost:8080/TafelWS/serverws", new ServerComWebserviceImpl());
		JOptionPane.showMessageDialog(null, "Server beenden");
		clientEndpoint.stop();
		serverEndpoint.stop();

	}

}
