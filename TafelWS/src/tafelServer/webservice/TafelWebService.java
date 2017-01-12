package tafelServer.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface TafelWebService{
	@WebMethod
	public String[] createMessage(String[] message);
	
	@WebMethod
	public String[] deleteMessage(String[] message);
	
	@WebMethod
	public String[] modifyMessage(String[] message);
	
	@WebMethod
	public String[] showMessage(String[] message);
	
	@WebMethod
	public String[] startTafelServer(String[] message);
}