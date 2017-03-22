/*
 * SoapRequest.h
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#ifndef REQUESTS_SOAPREQUEST_H_
# define REQUESTS_SOAPREQUEST_H_


# include <string>

# define MSGID_DEFAULT      0


class SoapRequest
{
    public:
        SoapRequest();
        SoapRequest(int messageID);
        virtual ~SoapRequest() {};

        virtual std::string deliverMe(std::string serverAddress) { return "SoapRequest - nothing to do here, wrong class!"; };

        int getMessageID() const { return messageID; };

    private:
        int messageID;

};


#endif /* REQUESTS_SOAPREQUEST_H_ */
