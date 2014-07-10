package org.sigaim.siie.interfaces.eql;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.sigaim.siie.rm.exceptions.RejectException;

public interface IntSIIE001EQL {
	ReturnValueEQL query(String requestId, String eqlQuery) throws RejectException;
}
