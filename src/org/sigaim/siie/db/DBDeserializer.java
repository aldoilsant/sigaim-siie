package org.sigaim.siie.db;

import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.db.exceptions.PersistenceException;

public interface DBDeserializer {
	boolean acceptRMID(PersistenceManager persistenceManager, ReferenceModelObjectId id, Object context) throws PersistenceException;
	boolean acceptDeserializedObject(PersistenceManager persistenceManager, ReferenceModelObjectId id, ContentObject deserialized, Object context) throws PersistenceException;

}
