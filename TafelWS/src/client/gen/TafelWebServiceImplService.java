
package client.gen;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TafelWebServiceImplService", targetNamespace = "http://webservice.tafelServer/", wsdlLocation = "http://localhost:8080/TafelWS/tafelws?wsdl")
public class TafelWebServiceImplService
    extends Service
{

    private final static URL TAFELWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException TAFELWEBSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName TAFELWEBSERVICEIMPLSERVICE_QNAME = new QName("http://webservice.tafelServer/", "TafelWebServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/TafelWS/tafelws?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TAFELWEBSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        TAFELWEBSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public TafelWebServiceImplService() {
        super(__getWsdlLocation(), TAFELWEBSERVICEIMPLSERVICE_QNAME);
    }

    public TafelWebServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TAFELWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public TafelWebServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, TAFELWEBSERVICEIMPLSERVICE_QNAME);
    }

    public TafelWebServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TAFELWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public TafelWebServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TafelWebServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TafelWebService
     */
    @WebEndpoint(name = "TafelWebServiceImplPort")
    public TafelWebService getTafelWebServiceImplPort() {
        return super.getPort(new QName("http://webservice.tafelServer/", "TafelWebServiceImplPort"), TafelWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TafelWebService
     */
    @WebEndpoint(name = "TafelWebServiceImplPort")
    public TafelWebService getTafelWebServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.tafelServer/", "TafelWebServiceImplPort"), TafelWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TAFELWEBSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw TAFELWEBSERVICEIMPLSERVICE_EXCEPTION;
        }
        return TAFELWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
