package org.sigaim.siie.seql.parser.model;

import java.util.ArrayList;
import java.util.List;

public class SEQLQuery {
	private List<SEQLSelectCondition> selectConditions;
	private SEQLFromCondition fromCondition;
	private SEQLWhereCondition whereCondition;
	
	public SEQLQuery() {
		selectConditions=new ArrayList<SEQLSelectCondition>();
		fromCondition=new SEQLFromCondition();
		whereCondition=new SEQLWhereCondition();
	}
	public void addSelectCondition(String path, String name) {
		selectConditions.add(new SEQLSelectCondition(path,name));
	}
	public SEQLFromCondition getFromCondition() {
		return fromCondition;
	}
	public SEQLWhereCondition getWhereCondition() {
		return whereCondition;
	}
	@Override public String toString() {
		return "FROM "+fromCondition.toString()+"\nWHERE "+whereCondition.toString();
	}
	public List<String> getSelectIdentifiedVariables() {
		List<String> ret=new ArrayList<String>();
		for(SEQLSelectCondition cond : selectConditions) {
			String var=cond.getIdentifiedVariableId();
			if(var!=null) {
				ret.add(var);
			}
		}
		return ret;
	}
}
