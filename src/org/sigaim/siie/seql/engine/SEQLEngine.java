package org.sigaim.siie.seql.engine;

import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.seql.engine.exceptions.SEQLException;
import org.sigaim.siie.seql.parser.model.SEQLQuery;

public interface SEQLEngine {

	ContentObject runQuery(SEQLQuery query) throws SEQLException;
	
}
