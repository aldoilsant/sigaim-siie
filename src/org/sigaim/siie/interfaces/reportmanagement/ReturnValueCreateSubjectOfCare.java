package org.sigaim.siie.interfaces.reportmanagement;

import org.sigaim.siie.interfaces.IdentifiedReturnValue;

public class ReturnValueCreateSubjectOfCare extends IdentifiedReturnValue {
	private String serialized;
	
	public ReturnValueCreateSubjectOfCare() {
		
	}
	
	public ReturnValueCreateSubjectOfCare(String requestId, String serialized) {
		super(requestId);
		this.serialized=serialized;
	}
	public String getSerialized() {
		return serialized;
	}

	public void setSerialized(String serialized) {
		this.serialized=serialized;
	}
}
