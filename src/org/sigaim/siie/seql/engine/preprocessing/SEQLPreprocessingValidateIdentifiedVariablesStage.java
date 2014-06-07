package org.sigaim.siie.seql.engine.preprocessing;

import org.sigaim.siie.seql.engine.SEQLQueryPreprocessStage;
import org.sigaim.siie.seql.engine.exceptions.SEQLException;
import org.sigaim.siie.seql.parser.model.SEQLQuery;


public class SEQLPreprocessingValidateIdentifiedVariablesStage implements SEQLQueryPreprocessStage {

	private void validateIdentifiedVariables(SEQLQuery query) throws SEQLException {
		
	}
	@Override
	public SEQLQuery preprocessQuery(SEQLQuery query) throws SEQLException {
		this.validateIdentifiedVariables(query);
		return query;
	}

}
