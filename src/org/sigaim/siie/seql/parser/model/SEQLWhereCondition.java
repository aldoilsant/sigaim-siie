package org.sigaim.siie.seql.parser.model;

public class SEQLWhereCondition {
	private SEQLEvaluable root;
	public SEQLWhereCondition() {
		
	}
	public SEQLEvaluable getRoot() {
		return root;
	}
	public void setRoot(SEQLEvaluable root) {
		this.root=root;
	}
	@Override public String toString() {
		return root+"";
	}
}
