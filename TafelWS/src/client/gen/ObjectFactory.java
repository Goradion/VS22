
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

    private final static QName _StopTafelServerResponse_QNAME = new QName("http://webservice.tafelServer/", "stopTafelServerResponse");
    private final static QName _CreateMessage_QNAME = new QName("http://webservice.tafelServer/", "createMessage");
    private final static QName _PublishMessage_QNAME = new QName("http://webservice.tafelServer/", "publishMessage");
    private final static QName _StopTafelServer_QNAME = new QName("http://webservice.tafelServer/", "stopTafelServer");
    private final static QName _ModifyMessage_QNAME = new QName("http://webservice.tafelServer/", "modifyMessage");
    private final static QName _ReceiveMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "receiveMessageResponse");
    private final static QName _StartTafelServer_QNAME = new QName("http://webservice.tafelServer/", "startTafelServer");
    private final static QName _ModifyMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "modifyMessageResponse");
    private final static QName _RegisterServerResponse_QNAME = new QName("http://webservice.tafelServer/", "registerServerResponse");
    private final static QName _StartTafelServerResponse_QNAME = new QName("http://webservice.tafelServer/", "startTafelServerResponse");
    private final static QName _DeleteMessage_QNAME = new QName("http://webservice.tafelServer/", "deleteMessage");
    private final static QName _PublishMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "publishMessageResponse");
    private final static QName _CreateMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "createMessageResponse");
    private final static QName _RegisterServer_QNAME = new QName("http://webservice.tafelServer/", "registerServer");
    private final static QName _ShowMessagesResponse_QNAME = new QName("http://webservice.tafelServer/", "showMessagesResponse");
    private final static QName _ShowMessages_QNAME = new QName("http://webservice.tafelServer/", "showMessages");
    private final static QName _ReceiveMessage_QNAME = new QName("http://webservice.tafelServer/", "receiveMessage");
    private final static QName _DeleteMessageResponse_QNAME = new QName("http://webservice.tafelServer/", "deleteMessageResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteMessageResponse }
     * 
     */
    public DeleteMessageResponse createDeleteMessageResponse() {
        return new DeleteMessageResponse();
    }

    /**
     * Create an instance of {@link ReceiveMessage }
     * 
     */
    public ReceiveMessage createReceiveMessage() {
        return new ReceiveMessage();
    }

    /**
     * Create an instance of {@link ShowMessages }
     * 
     */
    public ShowMessages createShowMessages() {
        return new ShowMessages();
    }

    /**
     * Create an instance of {@link RegisterServer }
     * 
     */
    public RegisterServer createRegisterServer() {
        return new RegisterServer();
    }

    /**
     * Create an instance of {@link ShowMessagesResponse }
     * 
     */
    public ShowMessagesResponse createShowMessagesResponse() {
        return new ShowMessagesResponse();
    }

    /**
     * Create an instance of {@link CreateMessageResponse }
     * 
     */
    public CreateMessageResponse createCreateMessageResponse() {
        return new CreateMessageResponse();
    }

    /**
     * Create an instance of {@link DeleteMessage }
     * 
     */
    public DeleteMessage createDeleteMessage() {
        return new DeleteMessage();
    }

    /**
     * Create an instance of {@link PublishMessageResponse }
     * 
     */
    public PublishMessageResponse createPublishMessageResponse() {
        return new PublishMessageResponse();
    }

    /**
     * Create an instance of {@link RegisterServerResponse }
     * 
     */
    public RegisterServerResponse createRegisterServerResponse() {
        return new RegisterServerResponse();
    }

    /**
     * Create an instance of {@link StartTafelServerResponse }
     * 
     */
    public StartTafelServerResponse createStartTafelServerResponse() {
        return new StartTafelServerResponse();
    }

    /**
     * Create an instance of {@link ModifyMessageResponse }
     * 
     */
    public ModifyMessageResponse createModifyMessageResponse() {
        return new ModifyMessageResponse();
    }

    /**
     * Create an instance of {@link ReceiveMessageResponse }
     * 
     */
    public ReceiveMessageResponse createReceiveMessageResponse() {
        return new ReceiveMessageResponse();
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
     * Create an instance of {@link StopTafelServer }
     * 
     */
    public StopTafelServer createStopTafelServer() {
        return new StopTafelServer();
    }

    /**
     * Create an instance of {@link CreateMessage }
     * 
     */
    public CreateMessage createCreateMessage() {
        return new CreateMessage();
    }

    /**
     * Create an instance of {@link PublishMessage }
     * 
     */
    public PublishMessage createPublishMessage() {
        return new PublishMessage();
    }

    /**
     * Create an instance of {@link StopTafelServerResponse }
     * 
     */
    public StopTafelServerResponse createStopTafelServerResponse() {
        return new StopTafelServerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StopTafelServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "stopTafelServerResponse")
    public JAXBElement<StopTafelServerResponse> createStopTafelServerResponse(StopTafelServerResponse value) {
        return new JAXBElement<StopTafelServerResponse>(_StopTafelServerResponse_QNAME, StopTafelServerResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PublishMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "publishMessage")
    public JAXBElement<PublishMessage> createPublishMessage(PublishMessage value) {
        return new JAXBElement<PublishMessage>(_PublishMessage_QNAME, PublishMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StopTafelServer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "stopTafelServer")
    public JAXBElement<StopTafelServer> createStopTafelServer(StopTafelServer value) {
        return new JAXBElement<StopTafelServer>(_StopTafelServer_QNAME, StopTafelServer.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "receiveMessageResponse")
    public JAXBElement<ReceiveMessageResponse> createReceiveMessageResponse(ReceiveMessageResponse value) {
        return new JAXBElement<ReceiveMessageResponse>(_ReceiveMessageResponse_QNAME, ReceiveMessageResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "modifyMessageResponse")
    public JAXBElement<ModifyMessageResponse> createModifyMessageResponse(ModifyMessageResponse value) {
        return new JAXBElement<ModifyMessageResponse>(_ModifyMessageResponse_QNAME, ModifyMessageResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PublishMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "publishMessageResponse")
    public JAXBElement<PublishMessageResponse> createPublishMessageResponse(PublishMessageResponse value) {
        return new JAXBElement<PublishMessageResponse>(_PublishMessageResponse_QNAME, PublishMessageResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterServer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "registerServer")
    public JAXBElement<RegisterServer> createRegisterServer(RegisterServer value) {
        return new JAXBElement<RegisterServer>(_RegisterServer_QNAME, RegisterServer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowMessagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "showMessagesResponse")
    public JAXBElement<ShowMessagesResponse> createShowMessagesResponse(ShowMessagesResponse value) {
        return new JAXBElement<ShowMessagesResponse>(_ShowMessagesResponse_QNAME, ShowMessagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowMessages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "showMessages")
    public JAXBElement<ShowMessages> createShowMessages(ShowMessages value) {
        return new JAXBElement<ShowMessages>(_ShowMessages_QNAME, ShowMessages.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tafelServer/", name = "deleteMessageResponse")
    public JAXBElement<DeleteMessageResponse> createDeleteMessageResponse(DeleteMessageResponse value) {
        return new JAXBElement<DeleteMessageResponse>(_DeleteMessageResponse_QNAME, DeleteMessageResponse.class, null, value);
    }

}