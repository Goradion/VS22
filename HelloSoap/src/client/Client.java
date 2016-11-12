package client;

import gen.anzeige.AnzeigeSoapServiceService;
import gen.anzeige.AnzeigeWebservice;


public class Client {
	
	public static void main( String[] args )
	  {
		AnzeigeWebservice port = new AnzeigeSoapServiceService().getAnzeigeWebservicePort();
		port.addMessage("Nachricht 1");
		port.addMessage("Nachricht 2");
		port.addMessage("Nachricht 3");
		
		System.out.println(port.getMessages().getItem());
		
		port.removeMessage("Nachricht 2");
		
		System.out.println(port.getMessages().getItem());
	  }
	
	
	
}
