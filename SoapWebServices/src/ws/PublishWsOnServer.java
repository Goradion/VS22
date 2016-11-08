package ws;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

public class PublishWsOnServer
{
  public static void main( String[] args )
  {
    Endpoint endpoint = Endpoint.publish( "http://10.9.43.126:45111/services",
                                          new MyWebServices() );
    JOptionPane.showMessageDialog( null, "Server beenden" );
    endpoint.stop();
  }
}
