package org.sigaim.siie.seql.engine;

import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.seql.model.SEQLException;
import org.sigaim.siie.seql.model.SEQLQuery;
import org.sigaim.siie.seql.model.SEQLResultSet;

public interface SEQLQueryExecutionStage {
	SEQLResultSet runQuery(SEQLQuery query) throws SEQLException;
}
