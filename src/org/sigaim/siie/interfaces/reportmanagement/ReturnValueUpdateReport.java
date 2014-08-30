package org.sigaim.siie.interfaces.reportmanagement;

import org.sigaim.siie.interfaces.IdentifiedReturnValue;

public class ReturnValueUpdateReport extends IdentifiedReturnValue {
	private String serialized;
	
	public ReturnValueUpdateReport() {
		
	}
	
	public ReturnValueUpdateReport(String requestId, String serialized) {
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