package tafelServer.CorbaClient;
/*
* Klasse zum Testen des Soap-Clients
*/

public class SoapClientTest
{
    public static void main(String args[]) throws Exception
    {
        if(args.length == 2)
        {   
            String serverIP = args[0];
            int serverPort = new Integer(args[1]);
            System.out.println("Starte Test\n");
            SOAPClientDialog soapClientDialog = new SOAPClientDialog(serverIP, serverPort);
            System.out.println("Test beendet\n");
        }
        else
        {
           System.out.println("Zu wenig oder zu viele Parameter!"); 
        }
    }
}
