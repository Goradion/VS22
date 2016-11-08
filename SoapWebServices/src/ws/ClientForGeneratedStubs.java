package ws;

import ws.gen.alexws.*;
import ws.gen.geoipservice.*;

public class ClientForGeneratedStubs
{
  public static void main( String[] args )
  {
    GeoIPServiceSoap ipService = new GeoIPService().getGeoIPServiceSoap();
    GeoIP geoIP = ipService.getGeoIP( "134.96.210.150" );
    System.out.println( "IP-Adresse kommt aus " +
                        geoIP.getCountryCode()  );

    AlexWebServices port = new MyWebServicesService().getAlexWebServicesPort();
    System.out.printf( "%s Dein BMI ist %s",
                       port.hello( "Alex" ),
                       port.wort("TEST"));
    
   
  }
}
