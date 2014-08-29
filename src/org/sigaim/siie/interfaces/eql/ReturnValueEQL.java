package org.sigaim.siie.interfaces.eql;

import javax.xml.bind.annotation.XmlRootElement;

import org.sigaim.siie.interfaces.IdentifiedReturnValue;

public class ReturnValueEQL extends IdentifiedReturnValue{
	private String serialized;

	public ReturnValueEQL() {
		
	}
	
	public ReturnValueEQL(String requestId, String serialized) {
		super(requestId);
		this.serialized=serialized;
	}
 
	public String getSerialized() {
		return serialized;
	}

	public void setSerialized(String serialized) {
		this.serialized = serialized;
	} 
}
