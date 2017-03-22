/*
 * ModifiyRequest.h
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#ifndef REQUESTS_MODIFYREQUEST_H_
# define REQUESTS_MODIFYREQUEST_H_


# include "SoapRequest.h"


class ModifyRequest : public SoapRequest
{
    public:
        ModifyRequest(int messageID, std::string inhalt);
        virtual ~ModifyRequest() {};

        virtual std::string deliverMe(std::string serverAddress);

    private:
        std::string inhalt;

};


#endif /* REQUESTS_MODIFYREQUEST_H_ */
