package org.sigaim.siie.seql.parser.model;

public class SEQLPathPredicate implements SEQLEvaluable{
	private String key1;
	private String key2;
	private SEQLOperation operation;

	public SEQLPathPredicate(String key1, String key2) {
		this.key1=key1;
		this.key2=key2;
	}
	public SEQLPathPredicate(SEQLOperation operation) {
		this.operation=operation;
	}
	public boolean isComplex() {
		return this.operation!=null;
	}
	public String getKey1() {
		return key1;
	}
	public String getKey2() {
		return key2;
	}
	public SEQLOperation getOperation() {
		return operation;
	}
	@Override public String toString() {
		if(this.operation!=null) {
			return operation.toString();
		} else if(this.key2!=null) {
			return key1+","+key2;
		} else return key1;
	}
}
