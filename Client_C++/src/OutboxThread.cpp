/*
 * OutboxThread.cpp
 *
 *  Created on: 19.03.2017
 *      Author: D. Zilch
 */

#include <iostream>
#include <unistd.h>
#include "OutboxThread.h"
#include "SoapDeliverer.h"


using namespace std;
using namespace boost;

// TODO Queue der Requests, und ne add methode, zum hinzufuegen neuer Requests von außen


OutboxThread::~OutboxThread()
{
    stopThread = true;
//            if ( myThread->joinable() )
        myThread->join();
        std::cout << "Stopped" << std::endl;
}


void OutboxThread::start()
{
    myThread = new boost::thread(&OutboxThread::threadMain, this);
}


void OutboxThread::threadMain()
{
    int numb = 1;
    SoapDeliverer *deliverer = new SoapDeliverer(serverID, targetAddress);

    while ( !stopThread )
    {
        if ( addressChanged )
        {
            delete deliverer;
            deliverer = new SoapDeliverer(serverID, targetAddress);
            addressChanged = false;
        }

        // Do something useful, e.g:
        cout << "Hallo " << numb++ << endl;
        usleep(5000);
        // TODO richtige Implementierung
    }
}


