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
		this.fullPath=path;
		this.compile();
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
		this.fullPath+=pathComponent+"/";
		this.compile();
	}
	public void addPathComponent(SEQLPathComponent component) {
		this.fullPath+=component.toString();
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
	@Override public String toString() {
		return fullPath;
	}
	@Override public Object clone() {
		return new SEQLPath(this.fullPath);
	}
	public class SEQLPathComponent {
		private String pathComponent;
		private boolean hasPredicate;
		private String pathIdentifier;
		private String pathPredicate;

		public SEQLPathComponent(String pathComponent){
			this.pathComponent=pathComponent;
			if(pathComponent.contains(Character.toString('['))) {
				this.hasPredicate=true;
				String[] parts=pathComponent.split("[");
				this.pathIdentifier=parts[0];
				this.pathPredicate=parts[1].replace("]", "");
			} else {
				this.hasPredicate=false;
				this.pathIdentifier=pathComponent;
			}
		}
		public String getPathComponent() {
			return pathComponent;
		}
		public String getPathIdentifier() {
			return pathIdentifier;
		}
		public String getPathPredicate() {
			return pathPredicate;
		}
		public boolean hasPredicate() {
			return hasPredicate;
		}
		@Override public String toString() {
			return this.pathComponent;
		}
	}
}
