package corbaConnect;

/**
* VS2/ConnectInformationDataHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from VS2.idl
* Monday, March 27, 2017 11:08:53 AM CEST
*/

public final class ConnectInformationDataHolder implements org.omg.CORBA.portable.Streamable
{
  public corbaConnect.ConnectInformationData value = null;

  public ConnectInformationDataHolder ()
  {
  }

  public ConnectInformationDataHolder (corbaConnect.ConnectInformationData initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corbaConnect.ConnectInformationDataHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corbaConnect.ConnectInformationDataHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corbaConnect.ConnectInformationDataHelper.type ();
  }

}