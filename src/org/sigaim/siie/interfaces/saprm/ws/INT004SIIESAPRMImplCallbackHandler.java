
/**
 * INT004SIIESAPRMImplCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package org.sigaim.siie.interfaces.saprm.ws;

    /**
     *  INT004SIIESAPRMImplCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class INT004SIIESAPRMImplCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public INT004SIIESAPRMImplCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public INT004SIIESAPRMImplCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for analyzeText method
            * override this method for handling normal response from analyzeText operation
            */
           public void receiveResultanalyzeText(
                    org.sigaim.siie.interfaces.saprm.ws.INT004SIIESAPRMImplStub.AnalyzeTextResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from analyzeText operation
           */
            public void receiveErroranalyzeText(java.lang.Exception e) {
            }
                


    }
    