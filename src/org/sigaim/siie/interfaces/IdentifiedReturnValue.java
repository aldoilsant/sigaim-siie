package org.sigaim.siie.interfaces;

import org.sigaim.siie.seql.model.SEQLResultSet;

public class IdentifiedReturnValue {
	private String requestId;
	private String reasonCode;
	
	public IdentifiedReturnValue() {
		
	}
	
	public IdentifiedReturnValue(String requestId) {
		this.requestId=requestId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId=requestId;
	}
	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
}
