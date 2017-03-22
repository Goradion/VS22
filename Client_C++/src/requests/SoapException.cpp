/*
 * SoapException.cpp
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#include <sstream>
#include "SoapException.h"


using namespace std;


ostream& operator<<(ostream& os, const SoapException& se)
{
    return os << se.toString();
}

string SoapException::toString() const
{
    ostringstream o;
    o << "\nSoap Exception Message:" << '\n' << '\n'
            << message;
    return o.str();
}

