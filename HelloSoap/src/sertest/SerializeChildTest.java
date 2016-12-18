package sertest;

public class SerializeChildTest extends SerializeTest
{
	private static final long serialVersionUID = -4388299846099227432L;

	private String myText;
	
	public SerializeChildTest (String text, String text2)
	{
		super(text);
		myText = new String(text2);
	}
	
	public String printTextTwice ()
	{
		return liesMich+"\n"+liesMich;
	}
	
	public String printMyText ()
	{
		return myText;
	}
}