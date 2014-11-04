
/**
 * INT003SGMImplDatabaseExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws;

public class INT003SGMImplDatabaseExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1415099727094L;
    
    private org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.INT003SGMImplDatabaseException faultMessage;

    
        public INT003SGMImplDatabaseExceptionException() {
            super("INT003SGMImplDatabaseExceptionException");
        }

        public INT003SGMImplDatabaseExceptionException(java.lang.String s) {
           super(s);
        }

        public INT003SGMImplDatabaseExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public INT003SGMImplDatabaseExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.INT003SGMImplDatabaseException msg){
       faultMessage = msg;
    }
    
    public org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.INT003SGMImplDatabaseException getFaultMessage(){
       return faultMessage;
    }
}
    