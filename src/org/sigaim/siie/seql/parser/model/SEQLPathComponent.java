package org.sigaim.siie.seql.parser.model;

public class SEQLPathComponent {
	private String pathComponent;
	private boolean hasPredicate;
	private String pathIdentifier;
	private String pathPredicate;

	public SEQLPathComponent(String pathComponent){
		this.pathComponent=pathComponent;
		if(pathComponent.contains("[")) {
			this.hasPredicate=true;
			String[] parts=pathComponent.split("\\[");
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
