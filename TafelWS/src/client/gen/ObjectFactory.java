
package client.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client.gen package. 
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

    private final static QName _StartTafelServerResponse_QNAME = new QName("http://webservice.tafelServer/", "startTafelServerResponse");
    private final static QName _DeleteMessage_QNAME = new QName("http://webservice.tafelServer/", "deleteMessage");
    private final static QName _ShowMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "showMessageResponse");
    private final static QName _CreateMessage_QNAME = new QName("http://webservice.tafelServer/", "createMessage");
    private final static QName _CreateMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "createMessageResponse");
    private final static QName _ShowMessage_QNAME = new QName("http://webservice.tafelServer/", "showMessage");
    private final static QName _ModifyMessage_QNAME = new QName("http://webservice.tafelServer/", "modifyMessage");
    private final static QName _StartTafelServer_QNAME = new QName("http://webservice.tafelServer/", "startTafelServer");
    private final static QName _DeleteMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "deleteMessageResponse");
    private final static QName _ModifyMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "modifyMessageResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ModifyMessageResponse }
     * 
     */
    public ModifyMessageResponse createModifyMessageResponse() {
        return new ModifyMessageResponse();
    }

    /**
     * Create an instance of {@link DeleteMessageResponse }
     * 
     */
    public DeleteMessageResponse createDeleteMessageResponse() {
        return new DeleteMessageResponse();
    }

    /**
     * Create an instance of {@link StartTafelServer }
     * 
     */
    public StartTafelServer createStartTafelServer() {
        return new StartTafelServer();
    }

    /**
     * Create an instance of {@link ModifyMessage }
     * 
     */
    public ModifyMessage createModifyMessage() {
        return new ModifyMessage();
    }

    /**
     * Create an instance of {@link CreateMessageResponse }
     * 
     */
    public CreateMessageResponse createCreateMessageResponse() {
        return new CreateMessageResponse();
    }

    /**
     * Create an instance of {@link ShowMessage }
     * 
     */
    public ShowMessage createShowMessage() {
        return new ShowMessage();
    }

    /**
     * Create an instance of {@link CreateMessage }
     * 
     */
    public CreateMessage createCreateMessage() {
        return new CreateMessage();
    }

    /**
     * Create an instance of {@link DeleteMessage }
     * 
     */
    public DeleteMessage createDeleteMessage() {
        return new DeleteMessage();
    }

    /**
     * Create an instance of {@link ShowMessageResponse }
     * 
     */
    public ShowMessageResponse createShowMessageResponse() {
        return new ShowMessageResponse();
    }

    /**
     * Create an instance of {@link StartTafelServerResponse }
     * 
     */
    public StartTafelServerResponse createStartTafelServerResponse() {
        return new StartTafelServerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTafelServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "startTafelServerResponse")
    public JAXBElement<StartTafelServerResponse> createStartTafelServerResponse(StartTafelServerResponse value) {
        return new JAXBElement<StartTafelServerResponse>(_StartTafelServerResponse_QNAME, StartTafelServerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "deleteMessage")
    public JAXBElement<DeleteMessage> createDeleteMessage(DeleteMessage value) {
        return new JAXBElement<DeleteMessage>(_DeleteMessage_QNAME, DeleteMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "showMessageResponse")
    public JAXBElement<ShowMessageResponse> createShowMessageResponse(ShowMessageResponse value) {
        return new JAXBElement<ShowMessageResponse>(_ShowMessageResponse_QNAME, ShowMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "createMessage")
    public JAXBElement<CreateMessage> createCreateMessage(CreateMessage value) {
        return new JAXBElement<CreateMessage>(_CreateMessage_QNAME, CreateMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "createMessageResponse")
    public JAXBElement<CreateMessageResponse> createCreateMessageResponse(CreateMessageResponse value) {
        return new JAXBElement<CreateMessageResponse>(_CreateMessageResponse_QNAME, CreateMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "showMessage")
    public JAXBElement<ShowMessage> createShowMessage(ShowMessage value) {
        return new JAXBElement<ShowMessage>(_ShowMessage_QNAME, ShowMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "modifyMessage")
    public JAXBElement<ModifyMessage> createModifyMessage(ModifyMessage value) {
        return new JAXBElement<ModifyMessage>(_ModifyMessage_QNAME, ModifyMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTafelServer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "startTafelServer")
    public JAXBElement<StartTafelServer> createStartTafelServer(StartTafelServer value) {
        return new JAXBElement<StartTafelServer>(_StartTafelServer_QNAME, StartTafelServer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "deleteMessageResponse")
    public JAXBElement<DeleteMessageResponse> createDeleteMessageResponse(DeleteMessageResponse value) {
        return new JAXBElement<DeleteMessageResponse>(_DeleteMessageResponse_QNAME, DeleteMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "modifyMessageResponse")
    public JAXBElement<ModifyMessageResponse> createModifyMessageResponse(ModifyMessageResponse value) {
        return new JAXBElement<ModifyMessageResponse>(_ModifyMessageResponse_QNAME, ModifyMessageResponse.class, null, value);
    }

}
