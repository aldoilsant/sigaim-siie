package org.sigaim.siie.seql.engine;

import java.util.ArrayList;
import java.util.List;

import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.seql.annotations.DADL;
import org.sigaim.siie.seql.engine.exceptions.SEQLException;
import org.sigaim.siie.seql.parser.model.SEQLQuery;

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
	public ContentObject runQuery(SEQLQuery query) throws SEQLException {
		SEQLQuery pquery=query;
		for(SEQLQueryPreprocessStage stage : preprocessStages) {
			pquery=stage.preprocessQuery(pquery);
		}
		for(SEQLQueryExecutionStage stage : executionStages) {
			pquery=stage.runQuery(pquery);
		}
		ContentObject input=null;
		for(SEQLQueryPostprocessStage stage : postprocessStages) {
			input=stage.postProcessQueryResult(pquery,input);
		}
		return input;
	}
}
