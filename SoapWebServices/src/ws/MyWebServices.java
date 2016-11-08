package ws;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;

@WebService(name = "alexWebServices")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class MyWebServices {
	
	String str ="hallo";
	
	@WebMethod
	public String hello(String name) {
		return "Hello " + name + "!";
	}

	@WebMethod(operationName = "body-mass-index")
	@WebResult(name = "your-bmi")
	public double bmi(@WebParam(name = "height") double height, @WebParam(name = "weight") double weight) {
		return weight / ((height * height) / 10000);
	}
	
	@WebMethod
	public String wort(String str)
	{
		this.str = str;
		return str;
		
	}
}
