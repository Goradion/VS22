package tafelServer.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import verteilteAnzeigetafel.Message;
import verteilteAnzeigetafel.SoapableMessage;

@WebService
@SOAPBinding(style = Style.DOCUMENT, parameterStyle = ParameterStyle.WRAPPED, use = Use.LITERAL)
public interface TafelWebService{
	
	@WebMethod
	public String createMessage(String inhalt, int user);
	
	@WebMethod
	public String deleteMessage(int messageID, int user);
	
	@WebMethod
	public String modifyMessage(int messageID, String inhalt, int user);
	
	@WebMethod
	public SoapableMessage[] showMessages(int user);
	
	@WebMethod
	public Integer[] getGroupIds();
	
	@WebMethod
	public Integer[] getGroupMembers(int group);
	
	@WebMethod
	public String publishMessage(int messageID, int user, int group);
	
	@WebMethod
	public String deletePublic(int msgID , int user, int group);
	
	@WebMethod
	public String modifyPublic(int msgID, int user, String inhalt);
	
	@WebMethod
	public String[] startTafelServer(int userID, int abtNr);
	
	@WebMethod
	public String[] stopTafelServer(int userID);
	
}