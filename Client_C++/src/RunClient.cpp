/**
 *
 * @file	RunClient.cpp
 *
 * @date 	17.03.2017
 * @author 	D. Zilch
 *
 */


#include <iostream>
#include <string>
#include "serverCom/soapServerComWebserviceImplPortBindingProxy.h"
#include "serverCom/ServerComWebserviceImplPortBinding.nsmap"

using namespace std;


int main ()
{
    try
    {
        ServerComWebserviceImplPortBindingProxy service;
        ns1__receiveMessage * arguments = new ns1__receiveMessage();
        arguments->arg0 = 1; // messageID
        arguments->arg1 = 1; // userID
        arguments->arg2 = 1; // abtNr
        arguments->arg3 = "Hallo test";  // inhalt
        arguments->arg4 =  "Fri Mar 17 16:26:13 CET 2017";   // time TODO passendes Datenformat für Time
        arguments->arg5 = 1; // group
        ns1__receiveMessageResponse results;
        if ( service.receiveMessage(arguments, results) == SOAP_OK )
            cout << "The receiveMessage went: " << results.return_ << endl;  // get the methods (receiveMessage) return value
        else
            service.soap_stream_fault(cerr);
        service.destroy(); // delete data and release memory
    } catch ( const string& e )
    {
        cerr << "MAIN Error: " << e << endl;
    }
}
