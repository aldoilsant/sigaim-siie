package org.sigaim.siie.db;

import java.util.List;
import java.util.Set;

import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.dadl.exceptions.SemanticDADLException;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;

public interface PersistenceManager {
	//Saving
	 ReferenceModelObjectId saveReferenceModelObject(Object object) throws PersistenceException;
	 ReferenceModelObjectId saveReferenceModelObjectWithSerializer(Object object, DBSerializer serializer) throws PersistenceException;
	 ReferenceModelObjectId saveReferenceModelObjectFromContentObject(ContentObject cobj) throws PersistenceException,  SemanticDADLException, ReferenceModelException;
	 ReferenceModelObjectId saveReferenceModelObjectFromContentObjectWithSerializer(ContentObject cobj,DBSerializer serializer) throws PersistenceException,  SemanticDADLException, ReferenceModelException;
	 ReferenceModelObjectId saveObjectToPathFromParent(SingleAttributeObjectBlock block, ReferenceModelObjectId parent, SEQLPathComponent component)  throws PersistenceException, SemanticDADLException, ReferenceModelException;
	 ReferenceModelObjectId saveObjectToPathFromParentWithSerializer(SingleAttributeObjectBlock block, ReferenceModelObjectId parent, SEQLPathComponent component, DBSerializer serializer)  throws PersistenceException, SemanticDADLException, ReferenceModelException;
	 
	 //Management
	 void reset() throws PersistenceException;
	 void start() throws PersistenceException;
	 void stop() throws PersistenceException;
	 
	 //Helpers
	 ReferenceModelObjectId getReferenceModelRoot();
	 Object solvePathFromObjectId(ReferenceModelObjectId oid) throws PersistenceException;
	 ReferenceModelObjectId getReferenceModelObjectIdFromUniqueId(long oid) throws PersistenceException;
	 SEQLPath getReferenceModelPathFoRMObject(ReferenceModelObjectId id) throws PersistenceException;
	 Class<?> getClassFromRMID(ReferenceModelObjectId id) throws PersistenceException;
	 //Selecting ids
	 List<ReferenceModelObjectId> selectMatchingObjectsForComponentAndParent(String referenceModelClass, String archetypeId, ReferenceModelObjectId parent,Boolean useAllVersions) throws PersistenceException;
	 List<ReferenceModelObjectId> getDeepestRMObjectsForParentAndPath(ReferenceModelObjectId parent, List<SEQLPathComponent> components) throws PersistenceException;
	 List<ReferenceModelObjectId> selectObjectsMatchingPathFromParent(ReferenceModelObjectId parent,SEQLPath subpath) throws PersistenceException;
	 int countObjectsMatchingPathFromParent(ReferenceModelObjectId parent,SEQLPath subpath) throws PersistenceException;
	 ReferenceModelObjectId getReferenceModelObjectIdFromReferenceModelPath(SEQLPath path) throws PersistenceException;
	 //Retrieve actual content. 
	 ContentObject selectFromReferenceModelObjectId(ReferenceModelObjectId id, Boolean deep) throws PersistenceException;
	ContentObject selectFromReferenceModelObjectId(
				ReferenceModelObjectId id, Boolean deep, Set<ReferenceModelObjectId> vertex)
				throws PersistenceException;
	 //Versions
	 void setAsNextVersionOf(ReferenceModelObjectId newVersion, ReferenceModelObjectId lastVersion) throws PersistenceException;
	 
	 //Atomic indexing
	 long readAtomicIndex(String indexName) throws PersistenceException;
	 void declareAtomicIndex(String indexName)  throws PersistenceException;
}
