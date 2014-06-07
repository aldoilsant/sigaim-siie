package org.sigaim.siie.rm.unbinddadlmodel;

import java.util.List;

import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;

public class UnbindingContentObject extends ContentObject {
	private ComplexObjectBlock complexObjectBlock;
	private List<AttributeValue> attributeValues;
	
	public UnbindingContentObject() {
		super(null,null);
	}
	@Override
	public ComplexObjectBlock getComplexObjectBlock() {
		return this.complexObjectBlock;
	}
	@Override
	public List<AttributeValue> getAttributeValues() {
		return this.attributeValues;
	}
	public void setComplexObjectBlock(ComplexObjectBlock block) {
		this.complexObjectBlock=block;
	}
	public void setAttributeValues(List<AttributeValue> attributeValues) {
		this.attributeValues=attributeValues;
	}
}
