/*
 * SoapRequest.cpp
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#include "SoapRequest.h"
#include "RequestException.h"



SoapRequest::SoapRequest() :
            messageID(MSGID_DEFAULT)
{}

SoapRequest::SoapRequest(int messageID)
{
    this->messageID = messageID;
}

