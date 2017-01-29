
package serverCom.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the serverCom.gen package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReceiveSoapableMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "receiveSoapableMessageResponse");
    private final static QName _SoapableMessage_QNAME = new QName("http://webservice.tafelServer/", "SoapableMessage");
    private final static QName _RegisterServerResponse_QNAME = new QName("http://webservice.tafelServer/", "registerServerResponse");
    private final static QName _DeletePublicMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "deletePublicMessageResponse");
    private final static QName _RegisterServer_QNAME = new QName("http://webservice.tafelServer/", "registerServer");
    private final static QName _ModifyPublicMessage_QNAME = new QName("http://webservice.tafelServer/", "modifyPublicMessage");
    private final static QName _ReceiveMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "receiveMessageResponse");
    private final static QName _ReceiveMessage_QNAME = new QName("http://webservice.tafelServer/", "receiveMessage");
    private final static QName _ReceiveSoapableMessage_QNAME = new QName("http://webservice.tafelServer/", "receiveSoapableMessage");
    private final static QName _ModifyPublicMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "modifyPublicMessageResponse");
    private final static QName _DeletePublicMessage_QNAME = new QName("http://webservice.tafelServer/", "deletePublicMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: serverCom.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeletePublicMessage }
     * 
     */
    public DeletePublicMessage createDeletePublicMessage() {
        return new DeletePublicMessage();
    }

    /**
     * Create an instance of {@link ModifyPublicMessageResponse }
     * 
     */
    public ModifyPublicMessageResponse createModifyPublicMessageResponse() {
        return new ModifyPublicMessageResponse();
    }

    /**
     * Create an instance of {@link ReceiveMessage }
     * 
     */
    public ReceiveMessage createReceiveMessage() {
        return new ReceiveMessage();
    }

    /**
     * Create an instance of {@link ReceiveSoapableMessage }
     * 
     */
    public ReceiveSoapableMessage createReceiveSoapableMessage() {
        return new ReceiveSoapableMessage();
    }

    /**
     * Create an instance of {@link ModifyPublicMessage }
     * 
     */
    public ModifyPublicMessage createModifyPublicMessage() {
        return new ModifyPublicMessage();
    }

    /**
     * Create an instance of {@link ReceiveMessageResponse }
     * 
     */
    public ReceiveMessageResponse createReceiveMessageResponse() {
        return new ReceiveMessageResponse();
    }

    /**
     * Create an instance of {@link RegisterServer }
     * 
     */
    public RegisterServer createRegisterServer() {
        return new RegisterServer();
    }

    /**
     * Create an instance of {@link DeletePublicMessageResponse }
     * 
     */
    public DeletePublicMessageResponse createDeletePublicMessageResponse() {
        return new DeletePublicMessageResponse();
    }

    /**
     * Create an instance of {@link SoapableMessage }
     * 
     */
    public SoapableMessage createSoapableMessage() {
        return new SoapableMessage();
    }

    /**
     * Create an instance of {@link RegisterServerResponse }
     * 
     */
    public RegisterServerResponse createRegisterServerResponse() {
        return new RegisterServerResponse();
    }

    /**
     * Create an instance of {@link ReceiveSoapableMessageResponse }
     * 
     */
    public ReceiveSoapableMessageResponse createReceiveSoapableMessageResponse() {
        return new ReceiveSoapableMessageResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveSoapableMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "receiveSoapableMessageResponse")
    public JAXBElement<ReceiveSoapableMessageResponse> createReceiveSoapableMessageResponse(ReceiveSoapableMessageResponse value) {
        return new JAXBElement<ReceiveSoapableMessageResponse>(_ReceiveSoapableMessageResponse_QNAME, ReceiveSoapableMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapableMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "SoapableMessage")
    public JAXBElement<SoapableMessage> createSoapableMessage(SoapableMessage value) {
        return new JAXBElement<SoapableMessage>(_SoapableMessage_QNAME, SoapableMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "registerServerResponse")
    public JAXBElement<RegisterServerResponse> createRegisterServerResponse(RegisterServerResponse value) {
        return new JAXBElement<RegisterServerResponse>(_RegisterServerResponse_QNAME, RegisterServerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePublicMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "deletePublicMessageResponse")
    public JAXBElement<DeletePublicMessageResponse> createDeletePublicMessageResponse(DeletePublicMessageResponse value) {
        return new JAXBElement<DeletePublicMessageResponse>(_DeletePublicMessageResponse_QNAME, DeletePublicMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterServer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "registerServer")
    public JAXBElement<RegisterServer> createRegisterServer(RegisterServer value) {
        return new JAXBElement<RegisterServer>(_RegisterServer_QNAME, RegisterServer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyPublicMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "modifyPublicMessage")
    public JAXBElement<ModifyPublicMessage> createModifyPublicMessage(ModifyPublicMessage value) {
        return new JAXBElement<ModifyPublicMessage>(_ModifyPublicMessage_QNAME, ModifyPublicMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "receiveMessageResponse")
    public JAXBElement<ReceiveMessageResponse> createReceiveMessageResponse(ReceiveMessageResponse value) {
        return new JAXBElement<ReceiveMessageResponse>(_ReceiveMessageResponse_QNAME, ReceiveMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "receiveMessage")
    public JAXBElement<ReceiveMessage> createReceiveMessage(ReceiveMessage value) {
        return new JAXBElement<ReceiveMessage>(_ReceiveMessage_QNAME, ReceiveMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveSoapableMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "receiveSoapableMessage")
    public JAXBElement<ReceiveSoapableMessage> createReceiveSoapableMessage(ReceiveSoapableMessage value) {
        return new JAXBElement<ReceiveSoapableMessage>(_ReceiveSoapableMessage_QNAME, ReceiveSoapableMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyPublicMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "modifyPublicMessageResponse")
    public JAXBElement<ModifyPublicMessageResponse> createModifyPublicMessageResponse(ModifyPublicMessageResponse value) {
        return new JAXBElement<ModifyPublicMessageResponse>(_ModifyPublicMessageResponse_QNAME, ModifyPublicMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePublicMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "deletePublicMessage")
    public JAXBElement<DeletePublicMessage> createDeletePublicMessage(DeletePublicMessage value) {
        return new JAXBElement<DeletePublicMessage>(_DeletePublicMessage_QNAME, DeletePublicMessage.class, null, value);
    }

}
