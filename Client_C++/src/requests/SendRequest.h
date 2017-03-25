/*
 * SendRequest.h
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#ifndef REQUESTS_SENDREQUEST_H_
# define REQUESTS_SENDREQUEST_H_


# include "SoapRequest.h"


class SendRequest : public SoapRequest
{
    public:
        SendRequest(int messageID, int userID, int serverNr, std::string inhalt, bool global);
        virtual ~SendRequest() {};

        virtual std::string deliverMe(std::string serverAddress);

    private:
        int userID;
        int serverNr;
        std::string inhalt;
        bool global;
};


#endif /* REQUESTS_SENDREQUEST_H_ */
