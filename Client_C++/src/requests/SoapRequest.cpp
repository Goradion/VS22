/*
 * SoapRequest.cpp
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#include "SoapRequest.h"
#include "RequestException.h"


static int const MSGID_MIN = 0;


SoapRequest::SoapRequest() :
            messageID(MSGID_DEFAULT)
{}

SoapRequest::SoapRequest(int messageID)
{
    if ( messageID < MSGID_MIN )
        throw RequestException("SoapRequest - Constructor: messageID is negative!");

    this->messageID = messageID;
}

