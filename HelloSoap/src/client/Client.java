package client;

import gen.anzeige.AnzeigeSoapServiceService;
import gen.anzeige.AnzeigeWebservice;

public class Client {

	public static void main(String[] args) {
		AnzeigeWebservice port = new AnzeigeSoapServiceService().getAnzeigeWebservicePort();
		if (args.length == 2) {
			if (args[0].equals("add")) {
				port.addMessage(args[1]);
				System.out.println("Nachricht '" + args[1] + "' erstellt!");
			} else {
				if (args[0].equals("del")) {
					if (port.delMessage(Integer.parseInt(args[1]))) {
						System.out.println("Nachricht " + args[1] + " geloescht!");
					} else {
						System.out.println("Nachtich " + args[1] + " nicht geloescht!");
					}
				} else {
					if (args[0].equals("collatz")) {
						System.out.println(port.collatz(Integer.parseInt(args[1])).getItem());
					} else {
						System.err.println("usage: show | add <message> | del <index>");
					}
				}
			}
		} else {
			if (args.length == 1) {
				if (args[0].equals("show")) {
					System.out.println(port.getMessages().getItem());
				}
			} else {
				System.err.println("usage: show | add <message> | del <index>");
			}
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
