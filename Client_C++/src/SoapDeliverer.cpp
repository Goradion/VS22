/*
 * SoapDeliverer.cpp
 *
 *  Created on: 19.03.2017
 *      Author: D. Zilch
 */

#include "SoapDeliverer.h"
#include "serverCom/ServerComWebserviceImplPortBinding.nsmap"


using namespace std;


SoapDeliverer::SoapDeliverer(int serverNr, string serverAddress)
{
    this->serverNr = serverNr;
    this->serverAddress = serverAddress;
//    this->service = new ServerComWebserviceImplPortBindingProxy(serverAddress.c_str());
}

SoapDeliverer::~SoapDeliverer()
{
//    service->destroy();
}


string SoapDeliverer::deliver(SoapRequest *soapRequest)
{
    return soapRequest->deliverMe(serverAddress);
}


//void SoapDeliverer::registerServer(int const serverNr)
//{
//    ns1__registerServer * arguments = new ns1__registerServer();
//    arguments->arg0 = serverNr;
//
//    ns1__registerServerResponse results;   // values: return_
//
//
//}



