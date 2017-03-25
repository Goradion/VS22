/*
 * OutboxThread.h
 *
 *  Created on: 19.03.2017
 *      Author: D. Zilch
 */

#ifndef OUTBOXTHREAD_H_
# define OUTBOXTHREAD_H_


# include <boost/thread.hpp>    // cant use c++11(<thread>) because of gsoap...


class OutboxThread
{
    public:
        OutboxThread(int serverID, std::string targetAddress) : myThread(), serverID(serverID), targetAddress(targetAddress) {};
        ~OutboxThread();


        void start();

        std::string getTargetAddress() { return targetAddress; }
        void setTargetAddress(std::string ta) { targetAddress = ta; addressChanged = true; }
        // TODO synchronization

    private:
        boost::thread *myThread;
        bool stopThread = false;
        bool addressChanged = false;
        int serverID;
        std::string targetAddress;

        void threadMain();

};

#endif /* OUTBOXTHREAD_H_ */
