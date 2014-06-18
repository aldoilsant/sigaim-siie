package org.sigaim.siie.dadl;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.DADLParser;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.ObjectBlock;
import org.openehr.am.parser.PrimitiveObjectBlock;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.openehr.rm.support.basic.Interval;
import org.sigaim.siie.seql.annotations.DADL;

public class OpenEHRDADLManager implements DADLManager {

	@Override
	public ContentObject parseDADL(InputStream is) {
		DADLParser dadlParser=new DADLParser(is);
		try {
			ContentObject obj=dadlParser.parse();
			return obj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	private String serializeSingleAttributeObjectBlock(SingleAttributeObjectBlock block) {
		String ret="";
		for(AttributeValue value : block.getAttributeValues()) {
			ret+=this.serializeAttributeValue(value);
		}
		return ret;
	}
	public String serialize(SimpleValue value) {
		return this.serializeSimpleValue(value);
	}
	@Override
	public String serializeSimpleValue(SimpleValue value) {
		if(value.getValue() instanceof String) {
			return "\""+value.getValue().toString()+"\"";
		} else if(value.getValue() instanceof Double) {
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
			otherSymbols.setDecimalSeparator('.');
			DecimalFormat df = new DecimalFormat("##.#######",otherSymbols);
			df.setMinimumFractionDigits(1);
			String ret=df.format((Double)value.getValue());
			return ret;
		} else return value.getValue().toString();
	}
	private String serializeSimpleIntervalValue(Interval<Comparable> interval) {
		return interval.toString();
	}
	private String serializePrimitiveObjectBlock(PrimitiveObjectBlock block) {
		String ret="";
		if(block==null) return ret;
		if(block.getSimpleIntervalValue()!=null) {
			ret+=this.serializeSimpleIntervalValue(block.getSimpleIntervalValue());
		} else if(block.getSimpleListValue()!=null) {
			for(SimpleValue value: block.getSimpleListValue()) {
				ret+=this.serializeSimpleValue(value);
			}
		} else if(block.getSimpleValue()!=null) {
			ret+=this.serializeSimpleValue(block.getSimpleValue());
		} else if(block.getTermCode()!=null) {
			ret+=block.getTermCode();
		} else if(block.getTermCodeListValue()!=null) {
			for(String term : block.getTermCodeListValue()) {
				ret+=term;
			}
		}
		return ret;
	}
	private String serializeKeyedObject(KeyedObject obj) {
		String ret="";
		ret+="["+this.serializeSimpleValue(obj.getKey())+"]=<"+this.serializeObjectBlock(obj.getObject())+">";
		return ret;
	}
	private String serializeMultipleAttributeObjectBlock(MultipleAttributeObjectBlock block) {
		String ret="";
		for(KeyedObject obj : block.getKeyObjects()) {
			ret+=this.serializeKeyedObject(obj);
		}
		return ret;
	}
	private String serializeComplexObjectBlock(ComplexObjectBlock block) {
		String ret="";
		if(block instanceof MultipleAttributeObjectBlock) {
			ret+=this.serializeMultipleAttributeObjectBlock((MultipleAttributeObjectBlock)block);
		} else {
			ret+=this.serializeSingleAttributeObjectBlock((SingleAttributeObjectBlock)block);
		}
		return ret;
	}
	private String serializeObjectBlock(ObjectBlock block) {
		String ret="";
		if(block instanceof ComplexObjectBlock) {
			ret+=this.serializeComplexObjectBlock((ComplexObjectBlock)block);
		} else {
			ret+=this.serializePrimitiveObjectBlock((PrimitiveObjectBlock)block);
		}
		return ret;
		
	}
	private String serializeAttributeValue(AttributeValue value) {
		String ret="";
		ret+=value.getId()+" = <"+this.serializeObjectBlock(value.getValue())+">";
		return ret;

	}

	public Class<?> getRootClass(ContentObject obj) {
		return null;
	}
	@Override
	public String serialize(ContentObject obj, boolean packed) {
		String ret="";
		if(obj.getAttributeValues()!=null) {
			List<AttributeValue> values=obj.getAttributeValues();
			for(AttributeValue value : values) {
				ret+=this.serializeAttributeValue(value);
			}
		} else {
			ret+="<"+this.serializeComplexObjectBlock(obj.getComplexObjectBlock())+">";
		}
		//Indent
		if(packed) return ret;
		int tabCount=0;
		StringBuilder builder=new StringBuilder(ret);
		for(int i=0;i<builder.length();i++) {
			if(builder.charAt(i)=='<') {
				tabCount++;
			} else if(builder.charAt(i)=='>') {
				tabCount--;
			}
			if(builder.length()<=i) break;
			if( builder.charAt(i)=='>' || builder.charAt(i)=='<') {
					for(int j=0;j<tabCount;j++) {
						builder.insert(i+1, '\t');
					}
					builder.insert(i+1, '\n');
				int rTabCount=builder.charAt(i)=='>'?tabCount:tabCount-1;
				builder.insert(i++, '\n');
				for(int j=0;j<rTabCount;j++) {
					builder.insert(i++, '\t');
				}
			}
		}
		return builder.toString();
	}
	@Override
	public String serialize(SingleAttributeObjectBlock obj) {
		return this.serializeSingleAttributeObjectBlock(obj);
	}
}
