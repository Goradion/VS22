package server;

import sertest.*;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
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
	public boolean addMessage(String msg) {
		return messages.add(msg);
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
	public Integer[] collatz(int startzahl) {
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
	public boolean removeMessage(String msg) {
		return messages.remove(msg);
	}
	
	@WebMethod
	public void clearMessages() {
		messages.clear();
	}
	
	@WebMethod
	public String getObject(String object) {
		String out = null;
		try {
			SerializeTest setest = (SerializeTest) deserialize(object);
			
			out = setest.printText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
	
	@WebMethod
	public String getObjectChild(String object) {
		String out = null;
		try {
			SerializeChildTest setest = (SerializeChildTest) deserialize(object);
		
			out = setest.printText() + setest.printTextTwice() + setest.printMyText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
	
	@WebMethod
	public String getObjectByte(byte[] object) {
		String out = null;
		try {
			SerializeTest setest = (SerializeTest) deserialize(object);
			
			out = setest.printText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
	
	@WebMethod
	public String getObjectByteChild(byte[] object) {
		String out = null;
		try {
			SerializeChildTest setest = (SerializeChildTest) deserialize(object);
		
			out = setest.printText() + setest.printTextTwice() + setest.printMyText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	
	private Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
	}

	private Object deserialize(String data) throws IOException, ClassNotFoundException {
	    return deserialize(data.getBytes());
	}
	
}
