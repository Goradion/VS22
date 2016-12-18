package sertest;

import java.io.Serializable;

public class SerializeTest implements Serializable
{
	private static final long serialVersionUID = 1432204084180011590L;
	
	protected String liesMich;
	
	public SerializeTest (String text)
	{
		liesMich = new String(text);
	}
	
	public String printText ()
	{
		return liesMich;
	}
}