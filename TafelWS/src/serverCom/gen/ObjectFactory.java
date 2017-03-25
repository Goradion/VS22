
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

    private final static QName _DeletePublicMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "deletePublicMessageResponse");
    private final static QName _ModifyMessageCorba_QNAME = new QName("http://webservice.tafelServer/", "modifyMessageCorba");
    private final static QName _ModifyPublicMessage_QNAME = new QName("http://webservice.tafelServer/", "modifyPublicMessage");
    private final static QName _ReceiveMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "receiveMessageResponse");
    private final static QName _ReceiveMessageCorba_QNAME = new QName("http://webservice.tafelServer/", "receiveMessageCorba");
    private final static QName _DeleteMessageCorba_QNAME = new QName("http://webservice.tafelServer/", "deleteMessageCorba");
    private final static QName _ModifyMessageCorbaResponse_QNAME = new QName("http://webservice.tafelServer/", "modifyMessageCorbaResponse");
    private final static QName _RegisterServerResponse_QNAME = new QName("http://webservice.tafelServer/", "registerServerResponse");
    private final static QName _RegisterServer_QNAME = new QName("http://webservice.tafelServer/", "registerServer");
    private final static QName _ReceiveMessageCorbaResponse_QNAME = new QName("http://webservice.tafelServer/", "receiveMessageCorbaResponse");
    private final static QName _DeleteMessageCorbaResponse_QNAME = new QName("http://webservice.tafelServer/", "deleteMessageCorbaResponse");
    private final static QName _ReceiveMessage_QNAME = new QName("http://webservice.tafelServer/", "receiveMessage");
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
     * Create an instance of {@link DeleteMessageCorbaResponse }
     * 
     */
    public DeleteMessageCorbaResponse createDeleteMessageCorbaResponse() {
        return new DeleteMessageCorbaResponse();
    }

    /**
     * Create an instance of {@link ReceiveMessageCorbaResponse }
     * 
     */
    public ReceiveMessageCorbaResponse createReceiveMessageCorbaResponse() {
        return new ReceiveMessageCorbaResponse();
    }

    /**
     * Create an instance of {@link RegisterServer }
     * 
     */
    public RegisterServer createRegisterServer() {
        return new RegisterServer();
    }

    /**
     * Create an instance of {@link DeleteMessageCorba }
     * 
     */
    public DeleteMessageCorba createDeleteMessageCorba() {
        return new DeleteMessageCorba();
    }

    /**
     * Create an instance of {@link ModifyMessageCorbaResponse }
     * 
     */
    public ModifyMessageCorbaResponse createModifyMessageCorbaResponse() {
        return new ModifyMessageCorbaResponse();
    }

    /**
     * Create an instance of {@link RegisterServerResponse }
     * 
     */
    public RegisterServerResponse createRegisterServerResponse() {
        return new RegisterServerResponse();
    }

    /**
     * Create an instance of {@link ReceiveMessageCorba }
     * 
     */
    public ReceiveMessageCorba createReceiveMessageCorba() {
        return new ReceiveMessageCorba();
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
     * Create an instance of {@link ModifyMessageCorba }
     * 
     */
    public ModifyMessageCorba createModifyMessageCorba() {
        return new ModifyMessageCorba();
    }

    /**
     * Create an instance of {@link DeletePublicMessageResponse }
     * 
     */
    public DeletePublicMessageResponse createDeletePublicMessageResponse() {
        return new DeletePublicMessageResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyMessageCorba }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "modifyMessageCorba")
    public JAXBElement<ModifyMessageCorba> createModifyMessageCorba(ModifyMessageCorba value) {
        return new JAXBElement<ModifyMessageCorba>(_ModifyMessageCorba_QNAME, ModifyMessageCorba.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveMessageCorba }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "receiveMessageCorba")
    public JAXBElement<ReceiveMessageCorba> createReceiveMessageCorba(ReceiveMessageCorba value) {
        return new JAXBElement<ReceiveMessageCorba>(_ReceiveMessageCorba_QNAME, ReceiveMessageCorba.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteMessageCorba }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "deleteMessageCorba")
    public JAXBElement<DeleteMessageCorba> createDeleteMessageCorba(DeleteMessageCorba value) {
        return new JAXBElement<DeleteMessageCorba>(_DeleteMessageCorba_QNAME, DeleteMessageCorba.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyMessageCorbaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "modifyMessageCorbaResponse")
    public JAXBElement<ModifyMessageCorbaResponse> createModifyMessageCorbaResponse(ModifyMessageCorbaResponse value) {
        return new JAXBElement<ModifyMessageCorbaResponse>(_ModifyMessageCorbaResponse_QNAME, ModifyMessageCorbaResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterServer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "registerServer")
    public JAXBElement<RegisterServer> createRegisterServer(RegisterServer value) {
        return new JAXBElement<RegisterServer>(_RegisterServer_QNAME, RegisterServer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveMessageCorbaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "receiveMessageCorbaResponse")
    public JAXBElement<ReceiveMessageCorbaResponse> createReceiveMessageCorbaResponse(ReceiveMessageCorbaResponse value) {
        return new JAXBElement<ReceiveMessageCorbaResponse>(_ReceiveMessageCorbaResponse_QNAME, ReceiveMessageCorbaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteMessageCorbaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "deleteMessageCorbaResponse")
    public JAXBElement<DeleteMessageCorbaResponse> createDeleteMessageCorbaResponse(DeleteMessageCorbaResponse value) {
        return new JAXBElement<DeleteMessageCorbaResponse>(_DeleteMessageCorbaResponse_QNAME, DeleteMessageCorbaResponse.class, null, value);
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
