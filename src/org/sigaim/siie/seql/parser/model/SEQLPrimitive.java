package org.sigaim.siie.seql.parser.model;

public class SEQLPrimitive implements SEQLEvaluable{
	private String value;
		
	public SEQLPrimitive(String value) {
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	@Override public String toString() {
		return this.getValue();
	}
}
