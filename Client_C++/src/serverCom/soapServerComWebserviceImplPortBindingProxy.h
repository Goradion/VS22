/* soapServerComWebserviceImplPortBindingProxy.h
   Generated by gSOAP 2.8.44 for F:\GIT\Active\VS22\Client_C++\src\serverCom\serverCom.h

gSOAP XML Web services tools
Copyright (C) 2000-2017, Robert van Engelen, Genivia Inc. All Rights Reserved.
The soapcpp2 tool and its generated software are released under the GPL.
This program is released under the GPL with the additional exemption that
compiling, linking, and/or using OpenSSL is allowed.
--------------------------------------------------------------------------------
A commercial use license is available from Genivia Inc., contact@genivia.com
--------------------------------------------------------------------------------
*/

#ifndef soapServerComWebserviceImplPortBindingProxy_H
#define soapServerComWebserviceImplPortBindingProxy_H
#include "soapH.h"

    class SOAP_CMAC ServerComWebserviceImplPortBindingProxy {
      public:
        /// Context to manage proxy IO and data
        struct soap *soap;
        bool soap_own; ///< flag indicating that this context is owned by this proxy when context is shared
        /// Endpoint URL of service 'ServerComWebserviceImplPortBindingProxy' (change as needed)
        const char *soap_endpoint;
        /// Variables globally declared in F:\GIT\Active\VS22\Client_C++\src\serverCom\serverCom.h, if any
        /// Construct a proxy with new managing context
        ServerComWebserviceImplPortBindingProxy();
        /// Copy constructor
        ServerComWebserviceImplPortBindingProxy(const ServerComWebserviceImplPortBindingProxy& rhs);
        /// Construct proxy given a shared managing context
        ServerComWebserviceImplPortBindingProxy(struct soap*);
        /// Constructor taking an endpoint URL
        ServerComWebserviceImplPortBindingProxy(const char *endpoint);
        /// Constructor taking input and output mode flags for the new managing context
        ServerComWebserviceImplPortBindingProxy(soap_mode iomode);
        /// Constructor taking endpoint URL and input and output mode flags for the new managing context
        ServerComWebserviceImplPortBindingProxy(const char *endpoint, soap_mode iomode);
        /// Constructor taking input and output mode flags for the new managing context
        ServerComWebserviceImplPortBindingProxy(soap_mode imode, soap_mode omode);
        /// Destructor deletes non-shared managing context only (use destroy() to delete deserialized data)
        virtual ~ServerComWebserviceImplPortBindingProxy();
        /// Initializer used by constructors
        virtual void ServerComWebserviceImplPortBindingProxy_init(soap_mode imode, soap_mode omode);
        /// Return a copy that has a new managing context with the same engine state
        virtual ServerComWebserviceImplPortBindingProxy *copy() SOAP_PURE_VIRTUAL;
        /// Copy assignment
        ServerComWebserviceImplPortBindingProxy& operator=(const ServerComWebserviceImplPortBindingProxy&);
        /// Delete all deserialized data (uses soap_destroy() and soap_end())
        virtual void destroy();
        /// Delete all deserialized data and reset to default
        virtual void reset();
        /// Disables and removes SOAP Header from message by setting soap->header = NULL
        virtual void soap_noheader();
        /// Get SOAP Header structure (i.e. soap->header, which is NULL when absent)
        virtual ::SOAP_ENV__Header *soap_header();
        /// Get SOAP Fault structure (i.e. soap->fault, which is NULL when absent)
        virtual ::SOAP_ENV__Fault *soap_fault();
        /// Get SOAP Fault string (NULL when absent)
        virtual const char *soap_fault_string();
        /// Get SOAP Fault detail as string (NULL when absent)
        virtual const char *soap_fault_detail();
        /// Close connection (normally automatic, except for send_X ops)
        virtual int soap_close_socket();
        /// Force close connection (can kill a thread blocked on IO)
        virtual int soap_force_close_socket();
        /// Print fault
        virtual void soap_print_fault(FILE*);
    #ifndef WITH_LEAN
    #ifndef WITH_COMPAT
        /// Print fault to stream
        virtual void soap_stream_fault(std::ostream&);
    #endif
        /// Write fault to buffer
        virtual char *soap_sprint_fault(char *buf, size_t len);
    #endif
        /// Web service operation 'receiveMessage' (returns SOAP_OK or error code)
        virtual int receiveMessage(ns1__receiveMessage *ns1__receiveMessage_, ns1__receiveMessageResponse &ns1__receiveMessageResponse_)
        { return this->receiveMessage(NULL, NULL, ns1__receiveMessage_, ns1__receiveMessageResponse_); }
        virtual int receiveMessage(const char *soap_endpoint, const char *soap_action, ns1__receiveMessage *ns1__receiveMessage_, ns1__receiveMessageResponse &ns1__receiveMessageResponse_);
        /// Web service operation 'receiveMessageCorba' (returns SOAP_OK or error code)
        virtual int receiveMessageCorba(ns1__receiveMessageCorba *ns1__receiveMessageCorba_, ns1__receiveMessageCorbaResponse &ns1__receiveMessageCorbaResponse_)
        { return this->receiveMessageCorba(NULL, NULL, ns1__receiveMessageCorba_, ns1__receiveMessageCorbaResponse_); }
        virtual int receiveMessageCorba(const char *soap_endpoint, const char *soap_action, ns1__receiveMessageCorba *ns1__receiveMessageCorba_, ns1__receiveMessageCorbaResponse &ns1__receiveMessageCorbaResponse_);
        /// Web service operation 'deletePublicMessage' (returns SOAP_OK or error code)
        virtual int deletePublicMessage(ns1__deletePublicMessage *ns1__deletePublicMessage_, ns1__deletePublicMessageResponse &ns1__deletePublicMessageResponse_)
        { return this->deletePublicMessage(NULL, NULL, ns1__deletePublicMessage_, ns1__deletePublicMessageResponse_); }
        virtual int deletePublicMessage(const char *soap_endpoint, const char *soap_action, ns1__deletePublicMessage *ns1__deletePublicMessage_, ns1__deletePublicMessageResponse &ns1__deletePublicMessageResponse_);
        /// Web service operation 'modifyPublicMessage' (returns SOAP_OK or error code)
        virtual int modifyPublicMessage(ns1__modifyPublicMessage *ns1__modifyPublicMessage_, ns1__modifyPublicMessageResponse &ns1__modifyPublicMessageResponse_)
        { return this->modifyPublicMessage(NULL, NULL, ns1__modifyPublicMessage_, ns1__modifyPublicMessageResponse_); }
        virtual int modifyPublicMessage(const char *soap_endpoint, const char *soap_action, ns1__modifyPublicMessage *ns1__modifyPublicMessage_, ns1__modifyPublicMessageResponse &ns1__modifyPublicMessageResponse_);
        /// Web service operation 'registerServer' (returns SOAP_OK or error code)
        virtual int registerServer(ns1__registerServer *ns1__registerServer_, ns1__registerServerResponse &ns1__registerServerResponse_)
        { return this->registerServer(NULL, NULL, ns1__registerServer_, ns1__registerServerResponse_); }
        virtual int registerServer(const char *soap_endpoint, const char *soap_action, ns1__registerServer *ns1__registerServer_, ns1__registerServerResponse &ns1__registerServerResponse_);
    };
#endif
