package tafelServer.webservice;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Endpoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import serverCom.gen.SoapableMessage;
import serverCom.gen.ServerComWebserviceImplService;
import tafelServer.TafelServer;
import tafelServer.webservice.ServerComWebserviceImpl;


public class ServerComWebserviceTest {
    
    private Endpoint webservice = null;
    private serverCom.gen.ServerComWebservice port = null;
    private int eigeneAbteilung = 1;
    
    @Before
    public void startWebservice() throws IOException {
        Runtime.getRuntime().exec("cmd /c !deleteOutput.bat");
        
        TafelServer.startServer(eigeneAbteilung);
        // Publishes the SOAP Web Service
        webservice = Endpoint.publish("http://localhost:8080/TafelWS/serverws", new ServerComWebserviceImpl());
        assertTrue(webservice.isPublished());
        assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", webservice.getBinding().getBindingID());
        
        port = new ServerComWebserviceImplService().getServerComWebserviceImplPort();
    }
    
    @After
    public void stopWebservice() throws IOException {
        webservice.stop();
        assertFalse(webservice.isPublished());
        Runtime.getRuntime().exec("cmd /c !deleteOutput.bat");
        
        port = null;
    }

    @Test
    public void testReceiveMessage() {
        int messageID = 1;
        int userID = 2;
        int abtNr = 2;
        String inhalt = "Hallo";
        Date dTime = new Date();
        String time = dTime.toString();
        int group = 1;
        
        // tafelserver should not be null
        assertNotNull("Should not be null:", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        
        // receive normal message
        messageID = 11;
        assertEquals("Done", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        
        // receive message with same messageID
        messageID = 111;
        assertEquals("Done", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        
        // receive message with wrong date format
        messageID = 1111;
        assertEquals("Date should be wrong:", "Date Parse Error", port.receiveMessage(messageID, userID, abtNr, inhalt, "", group));
        
        // receive message with equal abtNr to own abtNr
        abtNr = eigeneAbteilung;
        assertEquals("AbtNr should be equal:", "msg.getAbtNr()==abteilungsID", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        
        // receive message not included group
        abtNr = 2;
        group = 4;
        assertEquals("Group should be wrong:", "TafelServer ist nicht in gegebener Gruppe=" + group + "!", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
    }

    @Test
    public void testReceiveSoapableMessage() throws DatatypeConfigurationException {
        int messageID = 2;
        int userID = 2;
        int abtNr = 2;
        String inhalt = "Hallo";
        Date dTime = new Date();
        GregorianCalendar greg = new GregorianCalendar();
        greg.setTime(dTime);
        XMLGregorianCalendar xgreg = DatatypeFactory.newInstance().newXMLGregorianCalendar(greg);
        int group = 1;
        
        SoapableMessage soap = new SoapableMessage();
        soap.setMessageID(messageID);
        soap.setUserID(userID);
        soap.setAbtNr(abtNr);
        soap.setInhalt(inhalt);
        soap.setTime(xgreg);
        soap.getGroups().add(group);

        // tafelserver should not be null
        assertNotNull("Should not be null:", port.receiveSoapableMessage(soap));
        
        // receive normal message
        messageID = 22;
        soap.setMessageID(messageID);
        assertEquals("Done", port.receiveSoapableMessage(soap));
        
        // receive message with same messageID
        messageID = 222;
        soap.setMessageID(messageID);
        assertEquals("Done", port.receiveSoapableMessage(soap));
        
        // receive message with equal abtNr to own abtNr
        messageID = 2222;
        soap.setMessageID(messageID);
        abtNr = eigeneAbteilung;
        soap.setAbtNr(abtNr);
        assertEquals("AbtNr should be equal:", "msg.getAbtNr()==abteilungsID", port.receiveSoapableMessage(soap));
        
        // receive message not included group
        abtNr = 2;
        soap.setAbtNr(abtNr);
        group = 3;
        soap.getGroups().add(group);
        assertEquals("Group should be wrong:", "Done", port.receiveSoapableMessage(soap));
    } 
    
    @Test
    public void testRegisterServer() {
        int abtNr = 2;
        
        // anmeldung normal 
        assertEquals("TafelServer " + abtNr + ", Adresse: http://127.0.0.1:8080/TafelWS/serverws?wsdl registriert!", port.registerServer(abtNr));
        
        // anmeldung eigene Tafel 
        abtNr = eigeneAbteilung;
        assertEquals("Die eigene Abteilung wird nicht registriert", port.registerServer(abtNr));
        
        // anmeldung normal? 
        abtNr = -1121344;
        assertEquals("TafelServer " + abtNr + ", Adresse: http://127.0.0.1:8080/TafelWS/serverws?wsdl registriert!", port.registerServer(abtNr));
        
        // invoke MalformedURLException?
    }
    
    @Test
    public void testDeletePublicMessage() {
        int messageID = 3;
        int userID = 2;
        int abtNr = 2;
        String inhalt = "Hallo";
        Date dTime = new Date();
        String time = dTime.toString();
        int group = 1;

        // delete normal
        assertEquals("Done", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        assertEquals("Done", port.deletePublicMessage(messageID, group));
        
        // delete Tafel not in group
        messageID = 33;
        assertEquals("Done", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        group = 4;
        assertEquals("TafelServer ist nicht in gegebener Gruppe=" + group + "!", port.deletePublicMessage(messageID, group));
        
        // delete Tafel is in group but wrong group for message
        group = 1;
        messageID = 333;
        assertEquals("Done", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        group = 2;
        assertEquals("Nachricht ist nicht in Gruppe " + group + "!", port.deletePublicMessage(messageID, group));
        
        // delete wrong messageID
        messageID = 333;
        assertEquals("Done", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        messageID = 3333;
        assertEquals("Keine Message mit ID " + messageID + " gefunden!", port.deletePublicMessage(messageID, group));
        
        // delete wrong group
        messageID = 3333;
        group = 1;
        assertEquals("Done", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        messageID = 33333;
        group = 4;
        assertEquals("TafelServer ist nicht in gegebener Gruppe=" + group + "!", port.deletePublicMessage(messageID, group));
        
        // delete wrong group & messageID
        messageID = 33333;
        group = 1;
        assertEquals("Done", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        messageID = 333333;
        group = 4;
        assertEquals("TafelServer ist nicht in gegebener Gruppe=" + group + "!", port.deletePublicMessage(messageID, group));
        
        // invoke, deleting internal message over extern server ws
    }
    
    @Test
    public void testModifyPublicMessage() {
        int messageID = 4;
        int userID = 2;
        int abtNr = 2;
        String inhalt = "Hallo";
        Date dTime = new Date();
        String time = dTime.toString();
        int group = 1;
        
        // modifiy normal
        assertEquals("Done", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        assertEquals("Done", port.modifyPublicMessage(messageID, "Neu"));

        // modifiy wrong messageID
        messageID = 444;
        assertEquals("Done", port.receiveMessage(messageID, userID, abtNr, inhalt, time, group));
        messageID = 4444;
        assertEquals("Keine Message mit ID " + messageID + " gefunden!", port.modifyPublicMessage(messageID, "Neu"));

        // invoke, modifiing internal message over extern server ws
    }

}
