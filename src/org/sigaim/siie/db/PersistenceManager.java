package org.sigaim.siie.db;

import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.dadl.exceptions.SemanticDADLException;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;

public interface PersistenceManager {
	public ReferenceModelObjectId saveReferenceModelObject(Object object) throws PersistenceException;
	public Object solvePathFromObjectId(ReferenceModelObjectId oid) throws PersistenceException;
	public ReferenceModelObjectId saveReferenceModelObjectFromContentObject(ContentObject cobj) throws PersistenceException,  SemanticDADLException, ReferenceModelException;
	public void reset() throws PersistenceException;
	public void start() throws PersistenceException;
	public void stop() throws PersistenceException;
}
