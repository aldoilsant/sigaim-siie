package org.sigaim.siie.interfaces.reportmanagement;

import org.sigaim.siie.interfaces.IdentifiedReturnValue;
import org.sigaim.siie.seql.model.SEQLResultSet;

public class ReturnValueCreateHealthcareFacility extends IdentifiedReturnValue {
	private String serialized;
		
	public ReturnValueCreateHealthcareFacility() {
		
	}
	
	public ReturnValueCreateHealthcareFacility(String requestId, String serializedHealthcareFacility) {
		super(requestId);
		this.serialized=serializedHealthcareFacility;
	}
	public String getSerialized() {
		return serialized;
	}

	public void setSerialized(String serializedHealthcareFacility) {
		this.serialized=serializedHealthcareFacility;
	}
}
