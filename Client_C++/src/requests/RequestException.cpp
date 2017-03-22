/*
 * RequestException.cpp
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#include <sstream>
#include "RequestException.h"


using namespace std;


ostream& operator<<(ostream& os, const RequestException& re)
{
    return os << re.toString();
}

string RequestException::toString() const
{
    ostringstream o;
    o << "\nRequest Exception Message:" << '\n' << '\n'
            << message;
    return o.str();
}

