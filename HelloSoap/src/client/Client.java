package client;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

import gen.anzeige.AnzeigeSoapServiceService;
import gen.anzeige.AnzeigeWebservice;
import server.AnzeigeSoapService;

public class Client {
	
	public static void main( String[] args )
	  {
		AnzeigeWebservice port = new AnzeigeSoapServiceService().getAnzeigeWebservicePort();
		port.addMessage("Nachricht 1");
		port.addMessage("Nachricht 2");
		
		System.out.println(port.getMessages().getItem());
		
		
	  }
	
	
	
}
