package org.sigaim.siie.seql.parser.model;

public class SEQLFromComponent implements SEQLEvaluable{
	private String referenceModelClass;
	private String identifiedVariable;
	private String archetypeId;
	private SEQLFromComponent parent;
	
	public SEQLFromComponent(String referenceModelClass, String identifiedVariable, String archetypeId) {
		this.referenceModelClass=referenceModelClass;
		this.identifiedVariable=identifiedVariable;
		this.archetypeId=archetypeId;
	}
	
	public String getReferenceModelClass() {
		return referenceModelClass;
	}
	public String getIdentifiedVariable() {
		return identifiedVariable;
	}
	public String getArchetypeId() {
		return archetypeId;
	}
	public void setParent(SEQLFromComponent parent) {
		this.parent=parent;
	}
	public SEQLFromComponent getParent() {
		return parent;
	}
	@Override public String toString() {
		return referenceModelClass+" "+identifiedVariable+" "+archetypeId;
	}
}