package org.sigaim.siie.interfaces;

import java.util.List;

import org.apache.log4j.Logger;
import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.db.DBSerializer;
import org.sigaim.siie.db.ReferenceModelObjectId;
import org.sigaim.siie.iso13606.rm.II;
import org.sigaim.siie.iso13606.rm.RecordComponent;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.seql.engine.QueryDomainHelper;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;
import org.sigaim.siie.utils.Utils;

public class IDDBSerializer implements DBSerializer, QueryDomainHelper {
	private ReferenceModelManager referenceModelManager;
	private DADLManager dadlManager;
 	
	  private static org.apache.log4j.Logger log = Logger.getLogger(IDDBSerializer.class);
	public IDDBSerializer(ReferenceModelManager referenceModelManager, DADLManager dadlManager) {
		this.referenceModelManager=referenceModelManager;
		this.dadlManager=dadlManager;
	}
	
	public String extendSerialization(SingleAttributeObjectBlock block, long uniqueIdentifier) throws ReferenceModelException {
		String referenceModelClassName=this.referenceModelManager.getReferenceModelClassName(block);
		if(Utils.classNameEquals(referenceModelClassName, "HealthcareFacility") ||
			Utils.classNameEquals(referenceModelClassName, "Performer") ||
			Utils.classNameEquals(referenceModelClassName, "SubjectOfCare")) {
			II ii= new II();
			ii.setRoot("org.sigaim");
			ii.setExtension(uniqueIdentifier+"");
			AttributeValue av=new AttributeValue("identifier", this.referenceModelManager.unbindGeneric(ii));
			block.getAttributeValues().add(av);
			//Assign the unique id to the "identifier" property
		} else if(Utils.classNameEquals(referenceModelClassName, "EhrExtract")) {
			//Assign the unique id to the "ehr_id" property
			II ii= new II();
			ii.setRoot("org.sigaim");
			ii.setExtension(uniqueIdentifier+"");
			AttributeValue av=new AttributeValue("ehr_id", this.referenceModelManager.unbindGeneric(ii));
			block.getAttributeValues().add(av);
		} else {
			Class<?> rmClass=this.referenceModelManager.referenceModelClassFromString(referenceModelClassName);
			if(this.referenceModelManager.isRMObjectClass(rmClass) && RecordComponent.class.isAssignableFrom(rmClass)) {
				II ii= new II();
				ii.setRoot("org.sigaim");
				ii.setExtension(uniqueIdentifier+"");
				AttributeValue av=new AttributeValue("rc_id", this.referenceModelManager.unbindGeneric(ii));
				block.getAttributeValues().add(av);	
				if(Utils.classNameEquals(referenceModelClassName, "Composition")) {
					//Search for audit info
					for(AttributeValue attr : block.getAttributeValues()) {
						if(attr.getId().equals("committal")) {
							SingleAttributeObjectBlock auditInfoBlock=(SingleAttributeObjectBlock)attr.getValue();
							boolean hasPreviousVersion=false;
							for(AttributeValue attr2 : auditInfoBlock.getAttributeValues()) {
								if(attr2.getId().equals("previous_version")) {
									hasPreviousVersion=true;
									break;
								}
							}
							if(!hasPreviousVersion) {
								//Then set the version set id
								AttributeValue av2=new AttributeValue("version_set_id", this.referenceModelManager.unbindGeneric(ii));
								auditInfoBlock.getAttributeValues().add(av2);
							}
							break;
						}
					}
				}
			} else {
				log.info("Class "+referenceModelClassName+" is not being assigned unique ids");
			}
		}
		return this.dadlManager.serialize(block);
	}

	@Override
	public boolean pathIsObjectId(String referenceModelClassName, SEQLPath path) {
		List<SEQLPathComponent> components=path.getPathComponents();
		path=new SEQLPath(components);
		String test=path.toString();
		if(Utils.classNameEquals(referenceModelClassName, "HealthcareFacility") ||
				Utils.classNameEquals(referenceModelClassName, "Performer") ||
				Utils.classNameEquals(referenceModelClassName, "SubjectOfCare")) {
			return test.equals("identifier/extension/");
		} else if(Utils.classNameEquals(referenceModelClassName, "EhrExtract")) {
			return test.equals("ehr_id/extension/");

		} else {
		 	Class<?> rmClass=this.referenceModelManager.referenceModelClassFromString(referenceModelClassName);
			if(this.referenceModelManager.isRMObjectClass(rmClass) && RecordComponent.class.isAssignableFrom(rmClass)) {
				return test.equals("rc_id/extension/");
			} else {
				return false;
			}
		}
	}

}
