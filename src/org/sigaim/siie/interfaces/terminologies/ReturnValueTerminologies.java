package org.sigaim.siie.interfaces.terminologies;

import java.util.Set;

import org.sigaim.siie.interfaces.IdentifiedReturnValue;
import org.sigaim.siie.iso13606.rm.CD;

public class ReturnValueTerminologies extends IdentifiedReturnValue {
	private Set<CD> terminologies;
	
	public ReturnValueTerminologies() {
		
	}
	public ReturnValueTerminologies(String requestId, Set<CD> terminologies) {
		super(requestId);
		this.terminologies=terminologies;
	}
	public Set<CD> getTerminologies() {
		return terminologies;
	}
	public void setTerminologies(Set<CD> terminologies) {
		this.terminologies = terminologies;
	}
	
}
