package org.sigaim.siie.seql.engine;

import org.sigaim.siie.seql.model.SEQLException;
import org.sigaim.siie.seql.model.SEQLQuery;

public interface SEQLQueryPreprocessStage {
	SEQLQuery preprocessQuery(SEQLQuery query) throws SEQLException;

}
