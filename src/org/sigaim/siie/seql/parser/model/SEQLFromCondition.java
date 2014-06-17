package org.sigaim.siie.seql.parser.model;

import java.util.ArrayList;
import java.util.List;

import org.sigaim.siie.seql.engine.exceptions.SEQLException;


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
	protected void populateFromComponents(SEQLEvaluable evaluable, List<SEQLFromComponent> ret) {
		if(evaluable==null) return;
		if(evaluable instanceof SEQLOperation) {
			SEQLOperation operation=(SEQLOperation) evaluable;
			this.populateFromComponents(operation.getLeftOperand(), ret);
			this.populateFromComponents(operation.getRightOperand(), ret);
		} else {
			SEQLFromComponent component=(SEQLFromComponent) evaluable;
			if(component.getIdentifiedVariable()!=null) {
				ret.add(component);
			}
		}
	}
	public List<SEQLFromComponent> getFromComponents() {
		List<SEQLFromComponent> ret=new ArrayList<SEQLFromComponent>();
		this.populateFromComponents(this.getRoot(), ret);
		return ret;
	}
	public SEQLFromComponent getFromComponentFromIdentifiedVariable(String identifiedVariable) {
		List<SEQLFromComponent> components=this.getFromComponents();
		for(SEQLFromComponent component : components) {
			if(component.getIdentifiedVariable()!=null && component.getIdentifiedVariable().equals(identifiedVariable)){
				return component;
			}
		}
		return null;
	}
	public List<String> getIdentifiedVariables() throws SEQLException {
		List<SEQLFromComponent> components=this.getFromComponents();
		List<String> identifiedVariables=new ArrayList<String>();
		for(SEQLFromComponent component : components) {
			String identifiedVariable=component.getIdentifiedVariable();
			if(identifiedVariables.contains(identifiedVariable)) {
				throw new SEQLException("Duplicate identified variable "+identifiedVariable);
			}
			identifiedVariables.add(identifiedVariable);
		}
		return identifiedVariables;
	}
	@Override public String toString() {
		return root.toString();
	}

}
