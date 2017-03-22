/*
 * DeleteRequest.cpp
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#include <sstream>
#include "DeleteRequest.h"
#include "RequestException.h"
#include "SoapException.h"
#include "../serverCom/soapServerComWebserviceImplPortBindingProxy.h"


using namespace std;



string DeleteRequest::deliverMe(string serverAddress)
{
    ServerComWebserviceImplPortBindingProxy * service = new ServerComWebserviceImplPortBindingProxy(serverAddress.c_str());

    ns1__deletePublicMessageCorba * arguments = new ns1__deletePublicMessageCorba();
    arguments->arg0 = getMessageID();

    ns1__deletePublicMessageCorbaResponse results;   // values: return_

    if ( service->deletePublicMessageCorba(arguments, results) == SOAP_OK )
    {
        string returned(results.return_);  // get the methods (receiveMessage) return value
        service->destroy();
        return returned;
    } else
    {
        ostringstream o;
        service->soap_stream_fault(o);
        throw SoapException(o.str());
    }

    return "DeleteRequest";
}
