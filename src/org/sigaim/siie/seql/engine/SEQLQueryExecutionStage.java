package org.sigaim.siie.seql.engine;

import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.seql.engine.exceptions.SEQLException;
import org.sigaim.siie.seql.parser.model.SEQLQuery;
import org.sigaim.siie.seql.parser.model.SEQLResultSet;

public interface SEQLQueryExecutionStage {
	SEQLResultSet runQuery(SEQLQuery query) throws SEQLException;
}
