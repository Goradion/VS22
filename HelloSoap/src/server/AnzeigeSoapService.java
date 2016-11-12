package server;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(name = "AnzeigeWebservice")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AnzeigeSoapService {

	ArrayList<String> messages = new ArrayList<String>();

	@WebMethod
	public void addMessage(String msg) {
		messages.add(msg);
	}

	@WebMethod
	public boolean delMessage(int index) {
		if (index >= 0 && index < messages.size()) {
			messages.remove(index);
			return true;
		}
		return false;
	}

	@WebMethod
	public String[] getMessages() {
		return messages.toArray(new String[messages.size()]);
	}
	
	@WebMethod
	public Integer[] collatz(int startzahl){
		List<Integer> zahlenfolge = new ArrayList<Integer>();
		int zahl = startzahl;
		if (zahl < 1) {
			return new Integer[0];
		}
		while (zahl > 1){
			zahlenfolge.add(zahl);
			if ((zahl % 2) == 0){
				zahl /= 2;
			} else {
				zahl = 3 * zahl + 1;
			}
		}
		zahlenfolge.add(zahl);
		return zahlenfolge.toArray(new Integer[zahlenfolge.size()]);
	}

	@WebMethod
	  public void removeMessage( String msg ) {
		messages.remove(msg);
	  }
	
}
