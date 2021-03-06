
package serverCom.gen;

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
@WebService(name = "ServerComWebservice", targetNamespace = "http://webservice.tafelServer/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServerComWebservice {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deletePublicMessage", targetNamespace = "http://webservice.tafelServer/", className = "serverCom.gen.DeletePublicMessage")
    @ResponseWrapper(localName = "deletePublicMessageResponse", targetNamespace = "http://webservice.tafelServer/", className = "serverCom.gen.DeletePublicMessageResponse")
    @Action(input = "http://webservice.tafelServer/ServerComWebservice/deletePublicMessageRequest", output = "http://webservice.tafelServer/ServerComWebservice/deletePublicMessageResponse")
    public String deletePublicMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "modifyPublicMessage", targetNamespace = "http://webservice.tafelServer/", className = "serverCom.gen.ModifyPublicMessage")
    @ResponseWrapper(localName = "modifyPublicMessageResponse", targetNamespace = "http://webservice.tafelServer/", className = "serverCom.gen.ModifyPublicMessageResponse")
    @Action(input = "http://webservice.tafelServer/ServerComWebservice/modifyPublicMessageRequest", output = "http://webservice.tafelServer/ServerComWebservice/modifyPublicMessageResponse")
    public String modifyPublicMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "receiveMessage", targetNamespace = "http://webservice.tafelServer/", className = "serverCom.gen.ReceiveMessage")
    @ResponseWrapper(localName = "receiveMessageResponse", targetNamespace = "http://webservice.tafelServer/", className = "serverCom.gen.ReceiveMessageResponse")
    @Action(input = "http://webservice.tafelServer/ServerComWebservice/receiveMessageRequest", output = "http://webservice.tafelServer/ServerComWebservice/receiveMessageResponse")
    public String receiveMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        int arg5);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "registerServer", targetNamespace = "http://webservice.tafelServer/", className = "serverCom.gen.RegisterServer")
    @ResponseWrapper(localName = "registerServerResponse", targetNamespace = "http://webservice.tafelServer/", className = "serverCom.gen.RegisterServerResponse")
    @Action(input = "http://webservice.tafelServer/ServerComWebservice/registerServerRequest", output = "http://webservice.tafelServer/ServerComWebservice/registerServerResponse")
    public String registerServer(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "receiveSoapableMessage", targetNamespace = "http://webservice.tafelServer/", className = "serverCom.gen.ReceiveSoapableMessage")
    @ResponseWrapper(localName = "receiveSoapableMessageResponse", targetNamespace = "http://webservice.tafelServer/", className = "serverCom.gen.ReceiveSoapableMessageResponse")
    @Action(input = "http://webservice.tafelServer/ServerComWebservice/receiveSoapableMessageRequest", output = "http://webservice.tafelServer/ServerComWebservice/receiveSoapableMessageResponse")
    public String receiveSoapableMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        SoapableMessage arg0);

}
