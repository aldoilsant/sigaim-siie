package org.sigaim.siie.seql.engine;

import org.sigaim.siie.db.ReferenceModelObjectId;
import org.sigaim.siie.seql.model.SEQLPath;

public interface QueryDomainHelper {
	public boolean pathIsObjectId(String referenceModelClassName, SEQLPath path);
}
