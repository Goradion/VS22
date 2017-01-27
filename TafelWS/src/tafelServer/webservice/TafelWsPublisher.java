package tafelServer.webservice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.xml.ws.Endpoint;

import tafelServer.TafelServer;

public class TafelWsPublisher {
	public static final String STOPWS = "Stop web service";
	
	private Endpoint clientEndpoint = Endpoint.create(new TafelWebServiceImpl());
	private Endpoint serverEndpoint = Endpoint.create(new ServerComWebserviceImpl());
	
	private String address = "localhost";
	private int abteilung = 1;
	public static void main(String[] args) {
		final TafelWsPublisher publisher = new TafelWsPublisher();
		if (args.length >= 1) {
			try {
				publisher.abteilung = Integer.parseInt(args[0]);
			} catch (NumberFormatException nfe) {
				// default to abteilung 1;
			}
		}
		if (args.length >= 2) {
			publisher.address = args[1];
		}

		TafelServer.startServer(publisher.abteilung);
		
		publisher.clientEndpoint.publish("http://" + publisher.address + ":8080/TafelWS/tafelws");
		publisher.serverEndpoint.publish("http://" + publisher.address + ":8080/TafelWS/serverws");
		
		final JFrame jFrame = new JFrame("TafelWSPublisher: Tafel " + publisher.abteilung +" WS - published");
		jFrame.setSize(400, 200);
		jFrame.setResizable(false);

		final JButton jButton = new JButton(STOPWS);
		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				publisher.clientEndpoint.stop();
				publisher.serverEndpoint.stop();
				jFrame.setTitle("TafelWSPublisher: Tafel " + publisher.abteilung +" WS - stopped");
				jButton.setText("WS is stopped!");
			}

		});

		jFrame.add(jButton);
		jFrame.setVisible(true);

	}

}
