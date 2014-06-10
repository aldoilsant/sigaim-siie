package org.sigaim.siie.rm;

import java.util.List;
import java.util.Map;

import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.dadl.exceptions.SemanticDADLException;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.seql.parser.model.SEQLPath;
import org.sigaim.siie.seql.parser.model.SEQLPathComponent;

public interface ReferenceModelManager {
	Class<?> referenceModelClassFromString(String sclass);
	Class<?> getPathType(String sreferenceModelClass, SEQLPath path);
	Object bind(ContentObject obj) throws SemanticDADLException, ReferenceModelException;
	ContentObject unbind(Object root) throws ReferenceModelException;
	Map<SEQLPathComponent,SingleAttributeObjectBlock>  splitForRMObjectVsDataObject(SingleAttributeObjectBlock block) throws SemanticDADLException, ReferenceModelException;
	public String getReferenceModelClassName(SingleAttributeObjectBlock block) throws SemanticDADLException;
}
