package org.sigaim.siie.db.sql;

import org.sigaim.siie.db.ReferenceModelObjectId;
import org.sigaim.siie.seql.parser.model.SEQLPath;

public class SQLReferenceModelObjectId implements ReferenceModelObjectId{
	private Class<?> objectClass;
	private SEQLPath idPath;
	
	public SQLReferenceModelObjectId() {
		
	}
	public SQLReferenceModelObjectId(Class<?> objectClass, SEQLPath idPath) {
		this.setObjectClass(objectClass);
		this.setUniqueIdPath(idPath);
	}
	@Override
	public Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public SEQLPath getUniqueIdPath() {
		return null;
	}
	public void setObjectClass(Class<?> objectClass) {
		this.objectClass=objectClass;
	}
	public void setUniqueIdPath(SEQLPath idPath) {
		this.idPath=idPath;
	}
}
