package org.sigaim.siie.interfaces.reportmanagement;

import org.sigaim.siie.interfaces.IdentifiedReturnValue;

public class ReturnValueCreatePerformer extends IdentifiedReturnValue {
	private String serialized;
	
	public ReturnValueCreatePerformer() {
		
	}
	
	public ReturnValueCreatePerformer(String requestId, String serialized) {
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
