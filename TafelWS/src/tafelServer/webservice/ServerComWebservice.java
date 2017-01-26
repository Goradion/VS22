package tafelServer.webservice;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import verteilteAnzeigetafel.SoapableMessage;

@WebService
@SOAPBinding(style = Style.DOCUMENT, parameterStyle = ParameterStyle.WRAPPED, use = Use.LITERAL)
public interface ServerComWebservice {
	
	@WebMethod
	public String receiveMessage(int messageID, int userID, int abtNr, String inhalt, String time, int group);
	
	@WebMethod
	public String receiveSoapableMessage(SoapableMessage soapableMessage);
	
	@WebMethod
	public String registerServer(int abtNr, String address);
	
	@WebMethod
	public String deletePublicMessage(int msgID , int group);
	
	@WebMethod
	public String modifyPublicMessage(int msgID, int group, String inhalt);

}
