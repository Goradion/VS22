/*
 * SendRequest.cpp
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#include <sstream>
#include <ctime>
#include "SendRequest.h"
#include "RequestException.h"
#include "SoapException.h"
#include "../serverCom/soapServerComWebserviceImplPortBindingProxy.h"


using namespace std;

static int const USERID_MIN = 0;


SendRequest::SendRequest(int messageID, int userID, int serverNr, string inhalt, bool global) :
            SoapRequest(messageID), global(global)
{
    if ( userID < USERID_MIN )
        throw RequestException("SendRequest - Constructor: userID is negative!");
    if ( inhalt.empty() )
        throw RequestException("SendRequest - Constructor: inhalt is empty!");

    this->userID = userID;
    this->serverNr = serverNr;
    this->inhalt = inhalt;
}


string SendRequest::deliverMe(string serverAddress)
{
    ServerComWebserviceImplPortBindingProxy * service = new ServerComWebserviceImplPortBindingProxy(serverAddress.c_str());

    ns1__receiveMessageCorba * arguments = new ns1__receiveMessageCorba();
    arguments->arg0 = getMessageID();
    arguments->arg1 = userID;
    arguments->arg2 = serverNr;
    char * curInhalt = new char[inhalt.size() + 1];
    copy(inhalt.begin(), inhalt.end(), curInhalt);
    curInhalt[inhalt.size()] = '\0';
    arguments->arg3 = curInhalt;

    time_t tim = time(NULL);
    arguments->arg4 = asctime(localtime(&tim));
    arguments->arg5 = global;

    ns1__receiveMessageCorbaResponse results;   // values: return_

    if ( service->receiveMessageCorba(arguments, results) == SOAP_OK )
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

    return "SendRequest";
}
