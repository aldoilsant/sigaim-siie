package org.sigaim.siie.seql.engine;

import org.sigaim.siie.seql.engine.exceptions.SEQLException;
import org.sigaim.siie.seql.parser.model.SEQLQuery;

public interface SEQLQueryExecutionStage {
	SEQLQuery runQuery(SEQLQuery query) throws SEQLException;
}
