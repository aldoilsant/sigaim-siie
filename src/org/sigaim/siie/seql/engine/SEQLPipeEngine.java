package org.sigaim.siie.seql.engine;

import java.util.ArrayList;
import java.util.List;

import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.annotations.DADL;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.seql.model.SEQLException;
import org.sigaim.siie.seql.model.SEQLQuery;
import org.sigaim.siie.seql.model.SEQLResultSet;

public class SEQLPipeEngine implements SEQLEngine{
	private List<SEQLQueryPreprocessStage> preprocessStages;
	private List<SEQLQueryExecutionStage> executionStages;
	private List<SEQLQueryPostprocessStage> postprocessStages;
	
	public SEQLPipeEngine() {
		this.preprocessStages=new ArrayList<SEQLQueryPreprocessStage>();
		this.postprocessStages=new ArrayList<SEQLQueryPostprocessStage>();
		this.executionStages=new ArrayList<SEQLQueryExecutionStage>();
	}
	
	public void addPreprocessStage(SEQLQueryPreprocessStage stage) {
		this.preprocessStages.add(stage);
	}
	public void addExecutionStage(SEQLQueryExecutionStage stage) {
		this.executionStages.add(stage);
	}
	public void addPostprocessStage(SEQLQueryPostprocessStage stage) {
		this.postprocessStages.add(stage);
	}

	@DADL
	public SEQLResultSet runQuery(SEQLQuery query) throws SEQLException {
		SEQLQuery pquery=query;
		for(SEQLQueryPreprocessStage stage : preprocessStages) {
			pquery=stage.preprocessQuery(pquery);
		}
		SEQLResultSet set=null;
		for(SEQLQueryExecutionStage stage : executionStages) {
			set=stage.runQuery(pquery);
		}
		for(SEQLQueryPostprocessStage stage : postprocessStages) {
			set=stage.postProcessQueryResult(pquery, set);
		}
		return set;
	}
}
