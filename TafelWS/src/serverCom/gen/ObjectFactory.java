
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

    private final static QName _DeletePublic_QNAME = new QName("http://webservice.tafelServer/", "deletePublic");
    private final static QName _ModifyPublicResponse_QNAME = new QName("http://webservice.tafelServer/", "modifyPublicResponse");
    private final static QName _RegisterServerResponse_QNAME = new QName("http://webservice.tafelServer/", "registerServerResponse");
    private final static QName _DeletePublicResponse_QNAME = new QName("http://webservice.tafelServer/", "deletePublicResponse");
    private final static QName _ModifyPublic_QNAME = new QName("http://webservice.tafelServer/", "modifyPublic");
    private final static QName _RegisterServer_QNAME = new QName("http://webservice.tafelServer/", "registerServer");
    private final static QName _ReceiveMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "receiveMessageResponse");
    private final static QName _ReceiveMessage_QNAME = new QName("http://webservice.tafelServer/", "receiveMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: serverCom.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReceiveMessage }
     * 
     */
    public ReceiveMessage createReceiveMessage() {
        return new ReceiveMessage();
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
     * Create an instance of {@link ModifyPublic }
     * 
     */
    public ModifyPublic createModifyPublic() {
        return new ModifyPublic();
    }

    /**
     * Create an instance of {@link DeletePublicResponse }
     * 
     */
    public DeletePublicResponse createDeletePublicResponse() {
        return new DeletePublicResponse();
    }

    /**
     * Create an instance of {@link RegisterServerResponse }
     * 
     */
    public RegisterServerResponse createRegisterServerResponse() {
        return new RegisterServerResponse();
    }

    /**
     * Create an instance of {@link DeletePublic }
     * 
     */
    public DeletePublic createDeletePublic() {
        return new DeletePublic();
    }

    /**
     * Create an instance of {@link ModifyPublicResponse }
     * 
     */
    public ModifyPublicResponse createModifyPublicResponse() {
        return new ModifyPublicResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePublic }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "deletePublic")
    public JAXBElement<DeletePublic> createDeletePublic(DeletePublic value) {
        return new JAXBElement<DeletePublic>(_DeletePublic_QNAME, DeletePublic.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyPublicResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "modifyPublicResponse")
    public JAXBElement<ModifyPublicResponse> createModifyPublicResponse(ModifyPublicResponse value) {
        return new JAXBElement<ModifyPublicResponse>(_ModifyPublicResponse_QNAME, ModifyPublicResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePublicResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "deletePublicResponse")
    public JAXBElement<DeletePublicResponse> createDeletePublicResponse(DeletePublicResponse value) {
        return new JAXBElement<DeletePublicResponse>(_DeletePublicResponse_QNAME, DeletePublicResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyPublic }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "modifyPublic")
    public JAXBElement<ModifyPublic> createModifyPublic(ModifyPublic value) {
        return new JAXBElement<ModifyPublic>(_ModifyPublic_QNAME, ModifyPublic.class, null, value);
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

}
