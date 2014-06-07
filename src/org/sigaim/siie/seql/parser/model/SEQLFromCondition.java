package org.sigaim.siie.seql.parser.model;

import java.util.ArrayList;
import java.util.List;


public class SEQLFromCondition {
	private SEQLEvaluable root;
	
	public SEQLFromCondition() {
		
	}
	
	public SEQLFromCondition(SEQLEvaluable root) {
		this.root=root;
	}
	
	public SEQLEvaluable getRoot() {
		return root;
	}
	public void setRoot(SEQLEvaluable root) {
		this.root=root;
	}
	public SEQLFromComponent createFromComponent(String referenceModelClass, String identifiedVariable, String archetypeId) {
		return new SEQLFromComponent(referenceModelClass,identifiedVariable,archetypeId);
	}
	public List<String> getIdentifiedVariables() {
		List<String> ret=new ArrayList<String>();
		//Recursively evaluate the condition tree trying to find FromComponents, that will have the ids
		return null;
	}
	@Override public String toString() {
		return root.toString();
	}
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
}
