package org.sigaim.siie.seql.engine.execution;

import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.seql.engine.SEQLQueryExecutionStage;
import org.sigaim.siie.seql.engine.SEQLQueryPostprocessStage;
import org.sigaim.siie.seql.engine.exceptions.SEQLException;
import org.sigaim.siie.seql.parser.model.SEQLEvaluable;
import org.sigaim.siie.seql.parser.model.SEQLQuery;



public class SEQLExecutionMemorySolverStage implements SEQLQueryExecutionStage {
	
	@Override
	public SEQLQuery runQuery(SEQLQuery query) throws SEQLException {
		SEQLEvaluable evaluable=query.getFromCondition().getRoot();
		return null;
	}
 
	protected class SEQLExecutionMemorySolverContext {
		private SEQLQuery query;

		public SEQLExecutionMemorySolverContext(SEQLQuery query) {
			this.query=query;
		}
		public SEQLQuery getQuery() {
			return query;
		}
	}
}
