package org.sigaim.siie.interfaces.terminologies;

import java.util.Set;

import org.sigaim.siie.interfaces.IdentifiedReturnValue;
import org.sigaim.siie.iso13606.rm.CDCV;

public class ReturnValueConcepts extends IdentifiedReturnValue {
	private Set<CDCV> concepts;
	
	public ReturnValueConcepts() {
		
	}
	public ReturnValueConcepts(String requestId, Set<CDCV> concepts) {
		super(requestId);
		this.concepts=concepts;
	}
	
	public Set<CDCV> getConcepts() {
		return concepts;
	}
	public void setConcepts(Set<CDCV> concepts) {
		this.concepts = concepts;
	}
}
