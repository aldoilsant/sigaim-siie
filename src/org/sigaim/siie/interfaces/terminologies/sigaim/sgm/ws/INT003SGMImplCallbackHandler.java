
/**
 * INT003SGMImplCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws;

    /**
     *  INT003SGMImplCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class INT003SGMImplCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public INT003SGMImplCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public INT003SGMImplCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for rEQUEST_VERSIONS_ARQUETYPES method
            * override this method for handling normal response from rEQUEST_VERSIONS_ARQUETYPES operation
            */
           public void receiveResultrEQUEST_VERSIONS_ARQUETYPES(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_VERSIONS_ARQUETYPESResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_VERSIONS_ARQUETYPES operation
           */
            public void receiveErrorrEQUEST_VERSIONS_ARQUETYPES(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for fIND_ARQUETYPE method
            * override this method for handling normal response from fIND_ARQUETYPE operation
            */
           public void receiveResultfIND_ARQUETYPE(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.FIND_ARQUETYPEResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from fIND_ARQUETYPE operation
           */
            public void receiveErrorfIND_ARQUETYPE(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_SYNONYMS method
            * override this method for handling normal response from rEQUEST_SYNONYMS operation
            */
           public void receiveResultrEQUEST_SYNONYMS(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_SYNONYMSResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_SYNONYMS operation
           */
            public void receiveErrorrEQUEST_SYNONYMS(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for fIND_ARQUETYPE_VERSION method
            * override this method for handling normal response from fIND_ARQUETYPE_VERSION operation
            */
           public void receiveResultfIND_ARQUETYPE_VERSION(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.FIND_ARQUETYPE_VERSIONResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from fIND_ARQUETYPE_VERSION operation
           */
            public void receiveErrorfIND_ARQUETYPE_VERSION(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_GLOSARY_VERSION method
            * override this method for handling normal response from rEQUEST_GLOSARY_VERSION operation
            */
           public void receiveResultrEQUEST_GLOSARY_VERSION(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_GLOSARY_VERSIONResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_GLOSARY_VERSION operation
           */
            public void receiveErrorrEQUEST_GLOSARY_VERSION(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_VERSIONS_ARQUETYPES_ACTUAL method
            * override this method for handling normal response from rEQUEST_VERSIONS_ARQUETYPES_ACTUAL operation
            */
           public void receiveResultrEQUEST_VERSIONS_ARQUETYPES_ACTUAL(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_VERSIONS_ARQUETYPES_ACTUALResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_VERSIONS_ARQUETYPES_ACTUAL operation
           */
            public void receiveErrorrEQUEST_VERSIONS_ARQUETYPES_ACTUAL(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_VERSIONS_ARQUETYPES_VERSION method
            * override this method for handling normal response from rEQUEST_VERSIONS_ARQUETYPES_VERSION operation
            */
           public void receiveResultrEQUEST_VERSIONS_ARQUETYPES_VERSION(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_VERSIONS_ARQUETYPES_VERSIONResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_VERSIONS_ARQUETYPES_VERSION operation
           */
            public void receiveErrorrEQUEST_VERSIONS_ARQUETYPES_VERSION(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_CONCEPTS method
            * override this method for handling normal response from rEQUEST_CONCEPTS operation
            */
           public void receiveResultrEQUEST_CONCEPTS(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_CONCEPTSResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_CONCEPTS operation
           */
            public void receiveErrorrEQUEST_CONCEPTS(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_ACTUAL_GLOSARY_VERSION method
            * override this method for handling normal response from rEQUEST_ACTUAL_GLOSARY_VERSION operation
            */
           public void receiveResultrEQUEST_ACTUAL_GLOSARY_VERSION(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_ACTUAL_GLOSARY_VERSIONResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_ACTUAL_GLOSARY_VERSION operation
           */
            public void receiveErrorrEQUEST_ACTUAL_GLOSARY_VERSION(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_VERSIONS_TERMINOLOGY_ACTUAL method
            * override this method for handling normal response from rEQUEST_VERSIONS_TERMINOLOGY_ACTUAL operation
            */
           public void receiveResultrEQUEST_VERSIONS_TERMINOLOGY_ACTUAL(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_VERSIONS_TERMINOLOGY_ACTUALResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_VERSIONS_TERMINOLOGY_ACTUAL operation
           */
            public void receiveErrorrEQUEST_VERSIONS_TERMINOLOGY_ACTUAL(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_ADL_ARQUETYPES method
            * override this method for handling normal response from rEQUEST_ADL_ARQUETYPES operation
            */
           public void receiveResultrEQUEST_ADL_ARQUETYPES(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_ADL_ARQUETYPESResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_ADL_ARQUETYPES operation
           */
            public void receiveErrorrEQUEST_ADL_ARQUETYPES(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_TERMONOLOGIES method
            * override this method for handling normal response from rEQUEST_TERMONOLOGIES operation
            */
           public void receiveResultrEQUEST_TERMONOLOGIES(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_TERMONOLOGIESResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_TERMONOLOGIES operation
           */
            public void receiveErrorrEQUEST_TERMONOLOGIES(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_VERSIONS_TERMINOLOGY_VERSION method
            * override this method for handling normal response from rEQUEST_VERSIONS_TERMINOLOGY_VERSION operation
            */
           public void receiveResultrEQUEST_VERSIONS_TERMINOLOGY_VERSION(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_VERSIONS_TERMINOLOGY_VERSIONResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_VERSIONS_TERMINOLOGY_VERSION operation
           */
            public void receiveErrorrEQUEST_VERSIONS_TERMINOLOGY_VERSION(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_VERSIONS_TERMINOLOGY method
            * override this method for handling normal response from rEQUEST_VERSIONS_TERMINOLOGY operation
            */
           public void receiveResultrEQUEST_VERSIONS_TERMINOLOGY(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_VERSIONS_TERMINOLOGYResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_VERSIONS_TERMINOLOGY operation
           */
            public void receiveErrorrEQUEST_VERSIONS_TERMINOLOGY(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_ARQUETYPE method
            * override this method for handling normal response from rEQUEST_ARQUETYPE operation
            */
           public void receiveResultrEQUEST_ARQUETYPE(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_ARQUETYPEResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_ARQUETYPE operation
           */
            public void receiveErrorrEQUEST_ARQUETYPE(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rEQUEST_ARQUETYPES method
            * override this method for handling normal response from rEQUEST_ARQUETYPES operation
            */
           public void receiveResultrEQUEST_ARQUETYPES(
                    org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.REQUEST_ARQUETYPESResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rEQUEST_ARQUETYPES operation
           */
            public void receiveErrorrEQUEST_ARQUETYPES(java.lang.Exception e) {
            }
                


    }
    