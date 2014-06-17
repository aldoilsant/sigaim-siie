package org.sigaim.siie.db;

import java.util.List;

import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.dadl.exceptions.SemanticDADLException;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.seql.parser.model.SEQLPath;
import org.sigaim.siie.seql.parser.model.SEQLPathComponent;

public interface PersistenceManager {
	 ReferenceModelObjectId saveReferenceModelObject(Object object) throws PersistenceException;
	 Object solvePathFromObjectId(ReferenceModelObjectId oid) throws PersistenceException;
	 ReferenceModelObjectId saveReferenceModelObjectFromContentObject(ContentObject cobj) throws PersistenceException,  SemanticDADLException, ReferenceModelException;
	 void reset() throws PersistenceException;
	 void start() throws PersistenceException;
	 void stop() throws PersistenceException;
	 ReferenceModelObjectId getReferenceModelRoot();
	 List<ReferenceModelObjectId> selectMatchingObjectsForComponentAndParent(String referenceModelClass, String archetypeId, ReferenceModelObjectId parent) throws PersistenceException;
	 List<ReferenceModelObjectId> getDeepestRMObjectsForParentAndPath(ReferenceModelObjectId parent, List<SEQLPathComponent> components) throws PersistenceException;
	 ContentObject selectFromReferenceModelObjectId(ReferenceModelObjectId id) throws PersistenceException;
	 List<ReferenceModelObjectId> selectObjectsMatchingPathFromParent(ReferenceModelObjectId parent,SEQLPath subpath) throws PersistenceException;

}
