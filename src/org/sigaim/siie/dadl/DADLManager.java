package org.sigaim.siie.dadl;

import java.io.InputStream;

import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.seql.annotations.DADL;

public interface DADLManager {

	ContentObject parseDADL(InputStream dadlSource);
	public String serialize(ContentObject obj, boolean packed);
	public String serialize(SingleAttributeObjectBlock obj);
	//Allow for swallow serialization 
	//Use indirect indexing for direct in-sql value comparison
	//Class<?> getRootClass(ContentObject obj);
}
