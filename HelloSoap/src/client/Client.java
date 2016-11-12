package client;

import gen.anzeige.AnzeigeSoapServiceService;
import gen.anzeige.AnzeigeWebservice;

public class Client {

	public static void main(String[] args) {
		AnzeigeWebservice port = new AnzeigeSoapServiceService().getAnzeigeWebservicePort();
		if ( args.length >= 1 ) {
			switch (args[0]) {
				case "add":
					for ( int i = 1; i < args.length; i++ ) {
						if ( port.addMessage(args[i]) )
							System.out.println("Nachricht '" + args[i] + "' erstellt!");
						else
							System.out.println("Nachricht '" + args[i] + "' nicht erstellt!");
					}
					break;
				case "del":
						if ( port.delMessage(Integer.parseInt(args[1])) )
							System.out.println("Nachricht mit Index " + args[1] + " geloescht!");
						else
							System.out.println("Nachticht mit Index " + args[1] + " nicht geloescht!");
					break;
				case "collatz":
						System.out.println(port.collatz(Integer.parseInt(args[1])).getItem());
					break;
				case "rm":
						if ( port.removeMessage(args[1]) )
							System.out.println("Nachricht '" + args[1] + "' geloescht!");
						else
							System.out.println("Nachricht '" + args[1] + "' nicht geloescht!");
					break;
				case "clear":
						port.clearMessages();
						System.out.println("Alle Nachrichten geloescht!");
					break;
				case "show":
						System.out.println(port.getMessages().getItem());
					break;
				default:
						System.out.println("usage: show | add <messages> | del <index> | collatz <startnumber> | rm <message> | clear");
					break;
			}
		} else {
			System.out.println("usage: show | add <messages> | del <index> | collatz <startnumber> | rm <message> | clear");
		}
		// port.addMessage("Nachricht 1");
		// port.addMessage("Nachricht 2");
		// System.out.println(port.getMessages().getItem());
		// port.delMessage(1);
		// System.out.println(port.getMessages().getItem());
		// port.addMessage("Nachricht 3");
		// System.out.println(port.getMessages().getItem());

	}

}
