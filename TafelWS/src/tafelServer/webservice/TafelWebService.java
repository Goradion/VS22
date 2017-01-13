package tafelServer.webservice;

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
	public String[] createMessage(String[] message);
	
	@WebMethod
	public String[] deleteMessage(String[] message);
	
	@WebMethod
	public String[] modifyMessage(String[] message);
	
	@WebMethod
	public String[] showMessage(String[] message);
	
	@WebMethod
	public String[] startTafelServer(String[] message);
	
	@WebMethod
	public String[] stopTafelServer(String[] message);
}