package org.sigaim.siie.seql.engine.preprocessing;

import java.util.ArrayList;
import java.util.List;

import org.sigaim.siie.seql.engine.SEQLQueryPreprocessStage;
import org.sigaim.siie.seql.engine.exceptions.SEQLException;
import org.sigaim.siie.seql.parser.model.SEQLFromComponent;
import org.sigaim.siie.seql.parser.model.SEQLQuery;


public class SEQLPreprocessingValidateIdentifiedVariablesStage implements SEQLQueryPreprocessStage {

	private void validateFromVariables(SEQLQuery query) throws SEQLException {
		List<SEQLFromComponent> components=query.getFromCondition().getFromComponents();
		List<String> identifiedVariables=new ArrayList<String>();
		for(SEQLFromComponent component : components) {
			String identifiedVariable=component.getIdentifiedVariable();
			if(identifiedVariables.contains(identifiedVariable)) {
				throw new SEQLException("Duplicate identified variable "+identifiedVariable);
			}
			identifiedVariables.add(identifiedVariable);
		}
	}
	@Override
	public SEQLQuery preprocessQuery(SEQLQuery query) throws SEQLException {
		query.getIdentifiedVariables(); //Validation is in the query object model
		return query;
	}

}
