package tafelServer.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;


@WebService
@SOAPBinding(style = Style.DOCUMENT, parameterStyle = ParameterStyle.WRAPPED, use = Use.LITERAL)
public interface ServerComWebservice {
	
    @WebMethod
    public String receiveMessage(int messageID, int userID, int abtNr, String inhalt, String time, int group);
    
	@WebMethod
	public String receiveMessageCorba(int messageID, int userID, int serverNr, String inhalt, String time, boolean oeffentlich);
	
	@WebMethod
    public String deleteMessageCorba(int msgID);
	
	@WebMethod
	public String modifyMessageCorba(int msgID, String inhalt);
	
	@WebMethod
	public String registerServer(int abtNr);
	
	@WebMethod
	public String deletePublicMessage(int msgID , int group);
	
	@WebMethod
	public String modifyPublicMessage(int msgID, String inhalt);

}
