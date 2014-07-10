package org.sigaim.siie.db;

import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;

public interface DBSerializer {
	String extendSerialization(SingleAttributeObjectBlock serializingBlock, long uniqueIdentifier) throws ReferenceModelException;
}
