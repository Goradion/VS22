package VS2;


/**
* VS2/array_of_MessageDataHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from VS2.idl
* Saturday, March 25, 2017 2:41:42 PM CET
*/

abstract public class array_of_MessageDataHelper
{
  private static String  _id = "IDL:VS2/array_of_MessageData:1.0";

  public static void insert (org.omg.CORBA.Any a, VS2.MessageData[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static VS2.MessageData[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = VS2.MessageDataHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (VS2.array_of_MessageDataHelper.id (), "array_of_MessageData", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static VS2.MessageData[] read (org.omg.CORBA.portable.InputStream istream)
  {
    VS2.MessageData value[] = null;
    int _len0 = istream.read_long ();
    value = new VS2.MessageData[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = VS2.MessageDataHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, VS2.MessageData[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      VS2.MessageDataHelper.write (ostream, value[_i0]);
  }

}
