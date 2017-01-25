package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import client.gen.TafelWebService;
import client.gen.TafelWebServiceImplService;

public class WSClient {
	// Regular expression for IP-Address
	private static final Pattern PATTERN = Pattern
			.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	public static void main(String[] args) throws MalformedURLException {
		TafelWebService port = null;
		if (args.length >= 1) {
			if (isValidIpAddress(args[0])) {
				port = new TafelWebServiceImplService(new URL(args[0])).getTafelWebServiceImplPort();
			} else {
				port = new TafelWebServiceImplService().getTafelWebServiceImplPort();
			}
		} else {
			port = new TafelWebServiceImplService().getTafelWebServiceImplPort();
		}
		List<String> request = new ArrayList<String>();
//		List<String> reply = port.startTafelServer(request);
//		System.out.println(reply.get(0));
	}

	private static boolean isValidIpAddress(String ip) {
		return PATTERN.matcher(ip).matches();
	}
}
