/*
 * OutboxThread.cpp
 *
 *  Created on: 19.03.2017
 *      Author: D. Zilch
 */

#include <iostream>
#include <unistd.h>
#include "OutboxThread.h"


using namespace std;
using namespace boost;



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

    while ( !stopThread )
    {
        // Do something useful, e.g:
        cout << "Hallo " << numb++ << endl;
        usleep(5000);
    }
}


