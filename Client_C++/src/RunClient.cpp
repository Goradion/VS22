/*
 * RunClient.cpp
 *
 *  Created on: 17.03.2017
 *      Author: D. Zilch
 */


#include <iostream>
#include <unistd.h>
#include "OutboxThread.h"
//#include "SoapRequest.h"
#include "requests/RequestException.h"
#include "requests/SoapRequest.h"
#include "requests/DeleteRequest.h"
#include "requests/ModifyRequest.h"
#include "requests/SendRequest.h"
#include "requests/SoapException.h"
#include "SoapDeliverer.h"

//#include "serverCom/soapServerComWebserviceImplPortBindingProxy.h"
//#include "serverCom/ServerComWebserviceImplPortBinding.nsmap"

using namespace std;


int main ()
{
    try
    {
//        string address("http://192.168.153.100:8080/TafelWS/serverws");
//        ServerComWebserviceImplPortBindingProxy * service = new ServerComWebserviceImplPortBindingProxy(address.c_str());
//        ns1__receiveMessage * arguments = new ns1__receiveMessage();
//        arguments->arg0 = 3; // messageID
//        arguments->arg1 = 1; // userID
//        arguments->arg2 = 2; // abtNr
//        arguments->arg3 = "Hallo test";  // inhalt
//        arguments->arg4 =  "Fri Mar 17 16:26:13 CET 2017";   // time TODO passendes Datenformat für Time
//        arguments->arg5 = 1; // group
//        ns1__receiveMessageResponse results;
//        if ( service->receiveMessage(arguments, results) == SOAP_OK )
//            cout << "The receiveMessage: " << results.return_ << endl;  // get the methods (receiveMessage) return value
//        else
//            service->soap_stream_fault(cerr);
//
//        ns1__modifyPublicMessage * arguments2 = new ns1__modifyPublicMessage();
//        arguments2->arg0 = 3; // messageID
//        arguments2->arg1 = "Neuer Text"; // inhalt
//        ns1__modifyPublicMessageResponse results2;
//
//        if ( service->modifyPublicMessage(arguments2, results2) == SOAP_OK )
//            cout << "The modifyPublicMessage: " << results2.return_ << endl;  // get the methods (receiveMessage) return value
//        else
//            service->soap_stream_fault(cerr);
//        service->destroy(); // delete data and release memory
//
//        OutboxThread * out = new OutboxThread();
//        out->start();
//
//        usleep(22000);
//        delete out;
//        throw RequestException("Hallo Test");

        SoapDeliverer soapD(12, "http://localhost:8080/TafelWS/serverws");
        SoapRequest *soapR;

        soapR = new SoapRequest(1234);
        cout << soapD.deliver(soapR) << endl;
        soapR = new SendRequest(87, 1, 214, "Was Los?");
        cout << soapD.deliver(soapR) << endl;
        soapR = new DeleteRequest(1223134);
        cout << soapD.deliver(soapR) << endl;
        soapR = new ModifyRequest(12232231, "Neuer Text");
        cout << soapD.deliver(soapR) << endl;
        soapR = new SendRequest(96, 1, 214, "Was Los?");
        cout << soapD.deliver(soapR) << endl;
        cout << soapR->getMessageID() << endl;

    } catch ( const string& e )
    {
        cerr << "MAIN Error: " << e << endl;
    } catch ( const RequestException& re )
    {
        cerr << re << endl;
    } catch ( const SoapException& se )
    {
        cerr << se << endl;
    }
}
