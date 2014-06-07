package org.sigaim.siie.rm;

import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.dadl.exceptions.SemanticDADLException;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.seql.parser.model.SEQLPath;

public interface ReferenceModelManager {
	Class<?> referenceModelClassFromString(String sclass);
	Class<?> getPathType(String sreferenceModelClass, SEQLPath path);
	Object bind(ContentObject obj) throws SemanticDADLException, ReferenceModelException;
	ContentObject unbind(Object root) throws ReferenceModelException;
	String getReferenceModelClassNameFromObjectBlock(SingleAttributeObjectBlock block);
}
