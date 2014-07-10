package org.sigaim.siie.interfaces;

import org.apache.log4j.Logger;
import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.db.DBSerializer;
import org.sigaim.siie.iso13606.rm.II;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.utils.Utils;

public class IDDBSerializer implements DBSerializer {
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
		} else if(this.referenceModelManager.isRMObjectClass(this.referenceModelManager.referenceModelClassFromString(referenceModelClassName))) {
			II ii= new II();
			ii.setRoot("org.sigaim");
			ii.setExtension(uniqueIdentifier+"");
			AttributeValue av=new AttributeValue("rc_id", this.referenceModelManager.unbindGeneric(ii));
			block.getAttributeValues().add(av);		
		} else {
			log.info("Class "+referenceModelClassName+" is not being assigned unique ids");
		}
		return this.dadlManager.serialize(block);
	}

}
