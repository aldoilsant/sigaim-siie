package org.sigaim.siie.seql.parser.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sigaim.siie.utils.Utils;

public class SEQLPath implements SEQLEvaluable {
	private boolean isAbsolute;
	private String fullPath;
	private List<String> stringPathComponents;
	private List<SEQLPathComponent> pathComponents;
	
	public SEQLPath(String path) {
		if(path==null) this.fullPath="";
		else this.fullPath=path;
		this.compile();
	}
	public SEQLPath(List<SEQLPathComponent> components) {
		this.fullPath="";
		this.addPathComponents(components);
	}
	protected void compile() {
		if(fullPath.startsWith("/")) {
			this.isAbsolute=true;
		}
		
		this.stringPathComponents=new ArrayList<String>(Arrays.asList(fullPath.split("/")));
		pathComponents=new ArrayList<SEQLPathComponent>();
		for(String pathComponent : stringPathComponents) {
			pathComponents.add(new SEQLPathComponent(pathComponent));
		}
	}
	public void addPathComponent(String pathComponent) {
		if(this.fullPath.length()>0 && this.fullPath.charAt(this.fullPath.length()-1)!='/') {
			StringBuilder builder=new StringBuilder(this.fullPath);
			builder.append('/');
			this.fullPath=builder.toString();
		}
		this.fullPath+=pathComponent+"/";
		this.compile();
	}
	public void addPathComponent(SEQLPathComponent component) {
		this.addPathComponent(component.toString());
 	}
	public void addPathComponents(List<SEQLPathComponent> components) {
		for(SEQLPathComponent component : components) {
			this.addPathComponent(component);
		}
	}
	public SEQLPath toUppercaseNotation() {
		return new SEQLPath(Utils.toUppercaseNotation(this.getFullPath()));
	}
	public String getFullPath() {
		return fullPath;
	}
	public boolean isAbsolute() {
		return isAbsolute;
	}
	public List<String> getStringPathComponents(){
		return stringPathComponents;
	}
	public List<SEQLPathComponent> getPathComponents() {
		return pathComponents;
	}
	public String getFirstStringPathComponent() {
		return stringPathComponents.get(0);
	}
	public SEQLPathComponent getFirstPathComponent() {
		return pathComponents.get(0);
	}
	public SEQLPathComponent getLastPathComponent() {
		return pathComponents.get(pathComponents.size()-1);
	}
	@Override public String toString() {
		return fullPath;
	}
	@Override public Object clone() {
		return new SEQLPath(this.fullPath);
	}
	public SEQLPath removeLastPathComponent() {
		List<SEQLPathComponent> newComponents=new ArrayList<SEQLPathComponent>();
		newComponents.addAll(this.getPathComponents());
		newComponents.remove(newComponents.size()-1);
		return new SEQLPath(newComponents);
	}
	public SEQLPath removeFirstPathComponent() {
		List<SEQLPathComponent> newComponents=new ArrayList<SEQLPathComponent>();
		newComponents.addAll(this.getPathComponents());
		newComponents.remove(0);
		return new SEQLPath(newComponents);
	}
}
