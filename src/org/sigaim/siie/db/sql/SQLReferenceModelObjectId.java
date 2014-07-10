package org.sigaim.siie.db.sql;

import org.sigaim.siie.db.ReferenceModelObjectId;
import org.sigaim.siie.seql.model.SEQLPath;

public class SQLReferenceModelObjectId implements ReferenceModelObjectId{
	private Class<?> objectClass;
	private SEQLPath uniqueIdPath;
	private SEQLPath referenceModelPath;
	private SEQLPath archetypePath;
	
	public SQLReferenceModelObjectId() {
		
	}
	public SQLReferenceModelObjectId(Class<?> objectClass, SEQLPath idPath, SEQLPath referenceModelPath, SEQLPath archetypePath) {
		this.setObjectClass(objectClass);
		this.setUniqueIdPath(idPath);
		this.setReferenceModelPath(referenceModelPath);
		this.setArchetypePath(archetypePath);
	}
	@Override
	public Class<?> getObjectClass() {
		return this.objectClass;
	}	
	public SEQLPath getUniqueIdPath() {
		return this.uniqueIdPath;
	}
	public SEQLPath getReferenceModelPath() {
		return this.referenceModelPath;
	}
	public SEQLPath getArchetypePath() {
		return this.archetypePath;
	}
	public void setObjectClass(Class<?> objectClass) {
		this.objectClass=objectClass;
	}
	public void setUniqueIdPath(SEQLPath uniqueIdPath) {
		this.uniqueIdPath=uniqueIdPath;
	}
	public void setReferenceModelPath(SEQLPath path) {
		this.referenceModelPath=path;
	}
	public void setArchetypePath(SEQLPath path) {
		this.archetypePath=path;
	}
	@Override public String toString() {
		return this.objectClass+"::"+this.getUniqueIdPath()+"::"+this.getReferenceModelPath()+"::"+this.getArchetypePath();
	}
	public long getUniqueId() {
		return Long.parseLong(this.getUniqueIdPath().getLastPathComponent().getPathIdentifier());
	}
}
