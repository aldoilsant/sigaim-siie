package org.sigaim.siie.seql.engine.preprocessing;

import org.sigaim.siie.seql.engine.SEQLQueryPreprocessStage;
import org.sigaim.siie.seql.model.SEQLException;
import org.sigaim.siie.seql.model.SEQLQuery;

public class SEQLPrepreprocessingValidateArchetypeIdsStage implements SEQLQueryPreprocessStage {

	@Override
	public SEQLQuery preprocessQuery(SEQLQuery query) throws SEQLException {
		//Iterate over all from components, see if the archetype ids are available
		return query;
	}

}
