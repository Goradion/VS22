package client;

import java.util.ArrayList;
import java.util.List;

import client.gen.TafelWebService;
import client.gen.TafelWebServiceImplService;

public class WSClient {

	public static void main(String[] args) {
		TafelWebService port = new TafelWebServiceImplService().getTafelWebServiceImplPort();
		List<String> request = new ArrayList<String>();
		List<String> reply = port.startTafelServer(request);
		System.out.println(reply.get(0));
	}

}
