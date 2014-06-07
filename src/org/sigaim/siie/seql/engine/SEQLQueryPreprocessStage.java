package org.sigaim.siie.seql.engine;

import org.sigaim.siie.seql.engine.exceptions.SEQLException;
import org.sigaim.siie.seql.parser.model.SEQLQuery;

public interface SEQLQueryPreprocessStage {
	SEQLQuery preprocessQuery(SEQLQuery query) throws SEQLException;

}
