package org.sigaim.siie.rm.unbinddadlmodel;

import java.util.List;

import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;

public class UnbindingSingleAttributeObjectBlock extends SingleAttributeObjectBlock {
	private String typeIdentifier;
	private List<AttributeValue> attributeValues;
	
	public UnbindingSingleAttributeObjectBlock() {
		super(null,null);
	}
	@Override
	public String getTypeIdentifier() {
		return typeIdentifier;
	}
	public void setTypeIdentifier(String typeIdentifier) {
		this.typeIdentifier=typeIdentifier;
	}
	@Override
	public List<AttributeValue> getAttributeValues() {
		return attributeValues;
	}
	public void setAttributeValues(List<AttributeValue> attributeValues) {
		this.attributeValues=attributeValues;
	}
}
