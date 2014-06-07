package org.sigaim.siie.seql.parser.model;


public class SEQLOperation implements SEQLEvaluable {
	public enum SEQLBooleanOperator {
		AND,
		OR,
		XOR,
		NOT, //Unary
		CONTAINS,
		EXISTS,
		EQUALITY,
		INEQUALITY,
		GT,
		GE,
		LT,
		LE

	};

	private SEQLEvaluable opn1;
	private SEQLEvaluable opn2;
	private SEQLBooleanOperator op;
	
	public SEQLOperation() {
		opn1=null;
		opn2=null;
		op=null;
	}
	public SEQLOperation(SEQLEvaluable opn1, SEQLEvaluable opn2, SEQLBooleanOperator op){
		this.opn1=opn1;
		this.opn2=opn2;
		this.op=op;
	}
	
	public SEQLEvaluable getLeftOperand() {
		return opn1;
	}
	public SEQLEvaluable getRightOperand() {
		return opn2;
	}
	public SEQLBooleanOperator getOperator() {
		return op;
	}
	public void setLeftOperand(SEQLEvaluable opn1) {
		this.opn1=opn1;
	}
	public void setRightOperand(SEQLEvaluable opn2) {
		this.opn2=opn2;
	}
	public void setOperator(SEQLBooleanOperator op) {
		this.op=op;
	}
	public void setOperator(String sop) {
		this.op=SEQLBooleanOperator.valueOf(sop);
	}
	@Override public String toString() {
		if(opn2!=null) {
			return "("+opn1.toString()+") "+op.toString()+" ("+opn2.toString()+")";
		} else return op.toString()+"("+opn1.toString()+")";
	}
}
