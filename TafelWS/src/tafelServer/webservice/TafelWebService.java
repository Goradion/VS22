package tafelServer.webservice;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, parameterStyle = ParameterStyle.WRAPPED, use = Use.LITERAL)
public interface TafelWebService{
	
	@WebMethod
	public String createMessage(String inhalt, int user, int abtNr, boolean oeffentlich);
	
	@WebMethod
	public String deleteMessage(int messageID, int user);
	
	@WebMethod
	public String modifyMessage(int messageID, String inhalt, int user);
	
	@WebMethod
	public String[] showMessages(int user);
	
	@WebMethod
	public String publishMessage(int messageID, int user);
	
	@WebMethod
	public boolean receiveMessage(int messageID, int userID, int abtNr, String inhalt, boolean oeffentlich, Date time);
	
	@WebMethod
	public boolean registerServer(int abtNr, String address);
	
	@WebMethod
	public String[] startTafelServer(int abtNr);
	
	@WebMethod
	public String[] stopTafelServer(String[] message);
}