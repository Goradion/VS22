package server;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(name ="AnzeigeWebservice")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AnzeigeSoapService {
	
	ArrayList<String> messages = new ArrayList<String>();
	
	@WebMethod
	  public void addMessage( String msg ) {
		messages.add(msg);
	  }

	@WebMethod
	  public void removeMessage( String msg ) {
		messages.remove(msg);
	  }
	
	 @WebMethod
	  public String[] getMessages() {
	    return messages.toArray(new String[0]);
	  }
}
