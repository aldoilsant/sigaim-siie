package org.sigaim.siie.dadl;

import java.io.InputStream;

import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.seql.annotations.DADL;

public interface DADLManager {

	ContentObject parseDADL(InputStream dadlSource);
	String serialize(ContentObject obj, boolean packed);
	String serialize(SingleAttributeObjectBlock obj);
	String serialize(SimpleValue obj);
	String serializeSimpleValue(SimpleValue value);
	//Allow for swallow serialization 
	//Use indirect indexing for direct in-sql value comparison
	//Class<?> getRootClass(ContentObject obj);
}
