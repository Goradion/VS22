/*
 * SoapException.h
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#ifndef REQUESTS_SOAPEXCEPTION_H_
# define REQUESTS_SOAPEXCEPTION_H_


# include <exception>
# include <string>


class SoapException : public std::exception
{
    public:
        SoapException(const std::string& error) : message(error){};
        ~SoapException() {};

        char const * getMessage() const { return message.c_str(); };

        friend std::ostream& operator<<(std::ostream& os, const SoapException& se);

        std::string toString() const;


    private:
        std::string message;

};


#endif /* REQUESTS_SOAPEXCEPTION_H_ */
