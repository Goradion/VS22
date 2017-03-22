/*
 * ModifiyRequest.cpp
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#include <sstream>
#include "ModifyRequest.h"
#include "RequestException.h"
#include "SoapException.h"
#include "../serverCom/soapServerComWebserviceImplPortBindingProxy.h"


using namespace std;


ModifyRequest::ModifyRequest(int messageID, string inhalt) :
            SoapRequest(messageID)
{
    if ( inhalt.empty() )
        throw RequestException("SendRequest - Constructor: inhalt is empty!");

    this->inhalt = inhalt;
}


string ModifyRequest::deliverMe(string serverAddress)
{
    ServerComWebserviceImplPortBindingProxy * service = new ServerComWebserviceImplPortBindingProxy(serverAddress.c_str());

    ns1__modifyPublicMessage * arguments = new ns1__modifyPublicMessage();
    arguments->arg0 = getMessageID();
    char * curInhalt = new char[inhalt.size() + 1];
    copy(inhalt.begin(), inhalt.end(), curInhalt);
    curInhalt[inhalt.size()] = '\0';
    arguments->arg1 = curInhalt;

    ns1__modifyPublicMessageResponse results;   // values: return_

    if ( service->modifyPublicMessage(arguments, results) == SOAP_OK )
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

    return "ModifyRequest";
}

