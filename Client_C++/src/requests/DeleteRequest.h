/*
 * DeleteRequest.h
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#ifndef REQUESTS_DELETEREQUEST_H_
# define REQUESTS_DELETEREQUEST_H_


# include "SoapRequest.h"


class DeleteRequest : public SoapRequest
{
    public:
        DeleteRequest(int messageID) : SoapRequest(messageID) {};
        virtual ~DeleteRequest() {};

        virtual std::string deliverMe(std::string serverAddress);

    private:


};


#endif /* REQUESTS_DELETEREQUEST_H_ */
