
package client.gen;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "TafelWebService", targetNamespace = "http://webservice.tafelServer/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TafelWebService {


    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createMessage", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.CreateMessage")
    @ResponseWrapper(localName = "createMessageResponse", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.CreateMessageResponse")
    @Action(input = "http://webservice.tafelServer/TafelWebService/createMessageRequest", output = "http://webservice.tafelServer/TafelWebService/createMessageResponse")
    public String createMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "publishMessage", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.PublishMessage")
    @ResponseWrapper(localName = "publishMessageResponse", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.PublishMessageResponse")
    @Action(input = "http://webservice.tafelServer/TafelWebService/publishMessageRequest", output = "http://webservice.tafelServer/TafelWebService/publishMessageResponse")
    public String publishMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteMessage", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.DeleteMessage")
    @ResponseWrapper(localName = "deleteMessageResponse", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.DeleteMessageResponse")
    @Action(input = "http://webservice.tafelServer/TafelWebService/deleteMessageRequest", output = "http://webservice.tafelServer/TafelWebService/deleteMessageResponse")
    public String deleteMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "modifyMessage", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.ModifyMessage")
    @ResponseWrapper(localName = "modifyMessageResponse", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.ModifyMessageResponse")
    @Action(input = "http://webservice.tafelServer/TafelWebService/modifyMessageRequest", output = "http://webservice.tafelServer/TafelWebService/modifyMessageResponse")
    public String modifyMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deletePublic", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.DeletePublic")
    @ResponseWrapper(localName = "deletePublicResponse", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.DeletePublicResponse")
    @Action(input = "http://webservice.tafelServer/TafelWebService/deletePublicRequest", output = "http://webservice.tafelServer/TafelWebService/deletePublicResponse")
    public String deletePublic(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "modifyPublic", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.ModifyPublic")
    @ResponseWrapper(localName = "modifyPublicResponse", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.ModifyPublicResponse")
    @Action(input = "http://webservice.tafelServer/TafelWebService/modifyPublicRequest", output = "http://webservice.tafelServer/TafelWebService/modifyPublicResponse")
    public String modifyPublic(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "startTafelServer", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.StartTafelServer")
    @ResponseWrapper(localName = "startTafelServerResponse", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.StartTafelServerResponse")
    @Action(input = "http://webservice.tafelServer/TafelWebService/startTafelServerRequest", output = "http://webservice.tafelServer/TafelWebService/startTafelServerResponse")
    public List<String> startTafelServer(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "stopTafelServer", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.StopTafelServer")
    @ResponseWrapper(localName = "stopTafelServerResponse", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.StopTafelServerResponse")
    @Action(input = "http://webservice.tafelServer/TafelWebService/stopTafelServerRequest", output = "http://webservice.tafelServer/TafelWebService/stopTafelServerResponse")
    public List<String> stopTafelServer(
        @WebParam(name = "arg0", targetNamespace = "")
        List<String> arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "showMessages", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.ShowMessages")
    @ResponseWrapper(localName = "showMessagesResponse", targetNamespace = "http://webservice.tafelServer/", className = "client.gen.ShowMessagesResponse")
    @Action(input = "http://webservice.tafelServer/TafelWebService/showMessagesRequest", output = "http://webservice.tafelServer/TafelWebService/showMessagesResponse")
    public List<String> showMessages(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

}
