/*
 * OutboxThread.h
 *
 *  Created on: 19.03.2017
 *      Author: D. Zilch
 */

#ifndef OUTBOXTHREAD_H_
 #define OUTBOXTHREAD_H_


 #include <boost/thread.hpp>    // cant use c++11(<thread>) because of gsoap...

class OutboxThread
{
    public:
        OutboxThread() : myThread() {}

        ~OutboxThread();


        void start();


    private:
        boost::thread *myThread;
        bool stopThread = false;

        void threadMain();

};

#endif /* OUTBOXTHREAD_H_ */
