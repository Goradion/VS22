package tafelServer.webservice;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

import tafelServer.TafelServer;

public class TafelWsPublisher {
	public static final String STOPWS = "Stop web service";
	
	private Endpoint clientEndpoint = Endpoint.create(new TafelWebServiceImpl());
	private Endpoint serverEndpoint = Endpoint.create(new ServerComWebserviceImpl());
	
	private String address = "localhost";
	private int abteilung = 1;
	private TafelServer tafelServer = null;
	
	
	public TafelWsPublisher(String address, int abteilung, TafelServer tafelServer) {
		super();
		this.address = address;
		this.abteilung = abteilung;
		this.tafelServer = tafelServer;
	}

	public static void main(String[] args) {
		int abteilung = 1;
		String port = "8080";
		String address = "";
		if (args.length >= 1) {
			try {
				abteilung = Integer.parseInt(args[0]);
			} catch (NumberFormatException nfe) {
				// default to abteilung 1;
			}
		}
		if (args.length >= 2) {
			address = args[1];
		}
		if (args.length >= 3) {
		    port = args[2];
        }

		TafelServer.startServer(abteilung);
		final TafelWsPublisher publisher = new TafelWsPublisher(address, abteilung, TafelServer.getServer());
		
		
		publisher.clientEndpoint.publish("http://" + publisher.address + ":" + port + "/TafelWS/tafelws");
		publisher.serverEndpoint.publish("http://" + publisher.address + ":" + port + "/TafelWS/serverws");
		
		publisher.setShutdownOnClose(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				JFrame window = (JFrame)e.getSource();
				int confirmed = JOptionPane.showConfirmDialog(null, 
				        "Are you sure you want to exit the program?\n This will stop TafelServer "+publisher.abteilung, "Sure wanna exit?",
				        JOptionPane.YES_NO_OPTION);

				    if (confirmed == JOptionPane.YES_OPTION) {
				    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    	window.dispose();
				    	publisher.clientEndpoint.stop();
						publisher.serverEndpoint.stop();
				    }
			}

			@Override
			public void windowClosed(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void setShutdownOnClose(WindowListener wl){
		tafelServer.setShutdownOnClose(wl);
	}
}
