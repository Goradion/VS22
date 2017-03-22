/*
 * SoapDeliverer.h
 *
 *  Created on: 19.03.2017
 *      Author: D. Zilch
 */

#ifndef SOAPDELIVERER_H_
# define SOAPDELIVERER_H_


# include <string>
# include "serverCom/soapServerComWebserviceImplPortBindingProxy.h"
# include "requests/SoapRequest.h"


class SoapDeliverer
{
    public:
        SoapDeliverer(int serverNr, std::string serverAddress);
        ~SoapDeliverer();

        std::string deliver(SoapRequest *soapRequest);

//        void registerServer(int serverNr); // TODO HeartbeatThread?

    private:
        int serverNr;
//        ServerComWebserviceImplPortBindingProxy *service;
        std::string serverAddress;

};

#endif /* SOAPDELIVERER_H_ */
