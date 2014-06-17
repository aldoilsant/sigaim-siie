package org.sigaim.siie.seql.parser.model;

import java.util.List;

import org.sigaim.siie.seql.parser.model.SEQLPathComponent;


public class SEQLSelectCondition {
	private SEQLPath path;
	private String name;
	
	public SEQLSelectCondition(String path) {
		this(path,null);
	}
	public SEQLSelectCondition(String path, String name) {
		this.path=new SEQLPath(path);
		this.name=name;
	}
	public boolean isAbsolute() {
		return path.isAbsolute();
	}
	public SEQLPath getPath() {
		return path;
	}
	public String getIdentifiedVariableId() {
		if(!this.isAbsolute()){
			return path.getFirstPathComponent().getPathIdentifier();
		} else return null;
	}
}
