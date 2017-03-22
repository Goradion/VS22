/*
 * RequestException.h
 *
 *  Created on: 22.03.2017
 *      Author: D. Zilch
 */

#ifndef REQUESTS_REQUESTEXCEPTION_H_
# define REQUESTS_REQUESTEXCEPTION_H_


# include <exception>
# include <string>


class RequestException : public std::exception
{
    public:
        RequestException(const std::string& error) : message(error){};
        ~RequestException() {};

        char const * getMessage() const { return message.c_str(); };

        friend std::ostream& operator<<(std::ostream& os, const RequestException& re);

        std::string toString() const;


    private:
        std::string message;

};


#endif /* REQUESTS_REQUESTEXCEPTION_H_ */
