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
public interface ServerComWebservice {
	
	@WebMethod
	public boolean receiveMessage(int messageID, int userID, int abtNr, String inhalt, Date time);
	
	@WebMethod
	public boolean registerServer(int abtNr, String address);
	
	@WebMethod
	public boolean deletePublic(int msgID ,int abtNr, int group);
	
	@WebMethod
	public boolean modifyPublic(int msgID, int abtNr, int group, String inhalt);

}
