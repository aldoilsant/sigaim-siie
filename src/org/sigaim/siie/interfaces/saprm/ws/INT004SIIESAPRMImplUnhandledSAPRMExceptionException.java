
/**
 * INT004SIIESAPRMImplUnhandledSAPRMExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package org.sigaim.siie.interfaces.saprm.ws;

public class INT004SIIESAPRMImplUnhandledSAPRMExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1415098245371L;
    
    private org.sigaim.siie.interfaces.saprm.ws.INT004SIIESAPRMImplStub.INT004SIIESAPRMImplUnhandledSAPRMException faultMessage;

    
        public INT004SIIESAPRMImplUnhandledSAPRMExceptionException() {
            super("INT004SIIESAPRMImplUnhandledSAPRMExceptionException");
        }

        public INT004SIIESAPRMImplUnhandledSAPRMExceptionException(java.lang.String s) {
           super(s);
        }

        public INT004SIIESAPRMImplUnhandledSAPRMExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public INT004SIIESAPRMImplUnhandledSAPRMExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.sigaim.siie.interfaces.saprm.ws.INT004SIIESAPRMImplStub.INT004SIIESAPRMImplUnhandledSAPRMException msg){
       faultMessage = msg;
    }
    
    public org.sigaim.siie.interfaces.saprm.ws.INT004SIIESAPRMImplStub.INT004SIIESAPRMImplUnhandledSAPRMException getFaultMessage(){
       return faultMessage;
    }
}
    