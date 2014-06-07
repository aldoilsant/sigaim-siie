package org.sigaim.siie.rm.unbinddadlmodel;

import java.util.List;

import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;

public class UnbindingMultipleAttributeObjectBlock extends MultipleAttributeObjectBlock {
	private String typeIdentifier;
	private List<KeyedObject> keyObjects;
	
	public UnbindingMultipleAttributeObjectBlock() {
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
	public List<KeyedObject> getKeyObjects() {
		return keyObjects;
	}
	public void setKeyObjects(List<KeyedObject> keyObjects) {
		this.keyObjects=keyObjects;
	}
}
