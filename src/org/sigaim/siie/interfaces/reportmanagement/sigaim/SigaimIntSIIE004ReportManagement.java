package org.sigaim.siie.interfaces.reportmanagement.sigaim;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.log4j.Logger;
import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.IntegerValue;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.ReferenceModelObjectId;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.interfaces.IDDBSerializer;
import org.sigaim.siie.interfaces.reportmanagement.IntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateHealthcareFacility;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreatePerformer;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateReport;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateSubjectOfCare;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueUpdateReport;
import org.sigaim.siie.interfaces.saprm.INT004SIIESAPRMProxy;
import org.sigaim.siie.iso13606.rm.AuditInfo;
import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.iso13606.rm.Composition;
import org.sigaim.siie.iso13606.rm.EHRExtract;
import org.sigaim.siie.iso13606.rm.FunctionalRole;
import org.sigaim.siie.iso13606.rm.HealthcareFacility;
import org.sigaim.siie.iso13606.rm.II;
import org.sigaim.siie.iso13606.rm.Performer;
import org.sigaim.siie.iso13606.rm.SubjectOfCare;
import org.sigaim.siie.iso13606.rm.VersionStatus;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.exceptions.CSReason;
import org.sigaim.siie.rm.exceptions.RejectException;
import org.sigaim.siie.seql.engine.SEQLEngine;
import org.sigaim.siie.seql.engine.execution.SEQLExecutionMemorySolverStage;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;
import org.sigaim.siie.seql.model.SEQLPathPredicate;
import org.sigaim.siie.seql.model.SEQLQuery;
import org.sigaim.siie.seql.parser.SEQLErrorListener;
import org.sigaim.siie.seql.parser.SEQLModelListener;
import org.sigaim.siie.seql.parser.generated.SEQLLexer;
import org.sigaim.siie.seql.parser.generated.SEQLParser;

public class SigaimIntSIIE004ReportManagement implements IntSIIE004ReportManagement {
	private IDDBSerializer serializer;
	private II ehrSystemId;
	private PersistenceManager pmngr;
	private ReferenceModelManager rmngr;
	private DADLManager dmngr;
	private INT004SIIESAPRMProxy saprm;
	private SEQLEngine engine;
	private static org.apache.log4j.Logger log = Logger.getLogger(SigaimIntSIIE004ReportManagement.class);


	public SigaimIntSIIE004ReportManagement(PersistenceManager pmngr, ReferenceModelManager rmngr, DADLManager dmngr, INT004SIIESAPRMProxy saprm, SEQLEngine engine) {
		this.pmngr=pmngr;
		this.rmngr=rmngr;
		this.dmngr=dmngr;
		this.saprm=saprm;
		this.serializer=new IDDBSerializer(rmngr,dmngr);
		this.engine=engine;
		this.ehrSystemId=new II();
		this.ehrSystemId.setRoot("org.sigaim.siie");
	}
	
	@Override
	public ReturnValueCreateHealthcareFacility createHealthcareFacility(
			String requestId) throws RejectException {
		//Create a healthcare facility and insert it into all_healthcare_facilities path
		try {
				//Get the number of healthcare facilities
				ReferenceModelObjectId root=pmngr.getReferenceModelRoot();
				long id=pmngr.readAtomicIndex("all_healthcare_facilities");
				//int id=pmngr.countObjectsMatchingPathFromParent(root, new SEQLPath("all_healthcare_facilities"))+1;
				HealthcareFacility newFacility=new HealthcareFacility();
				SEQLPathComponent healthcarePathComponent=new SEQLPathComponent("all_healthcare_facilities["+id+"]");
				ReferenceModelObjectId saved=pmngr.saveObjectToPathFromParentWithSerializer((SingleAttributeObjectBlock)rmngr.unbindGeneric(newFacility), pmngr.getReferenceModelRoot(), healthcarePathComponent,serializer);
				ContentObject savedObject=pmngr.selectFromReferenceModelObjectId(saved,false);
				return new ReturnValueCreateHealthcareFacility(requestId,dmngr.serialize(savedObject,false));
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RejectException(requestId,CSReason.REAS02);
		}
	}

	@Override
	public ReturnValueCreateSubjectOfCare createSubjectOfCare(String requestId)
			throws RejectException {
		// TODO Auto-generated method stub
		try {
			
				//Get the number of healthcare facilities
				ReferenceModelObjectId root=pmngr.getReferenceModelRoot();
				long id=pmngr.readAtomicIndex("all_subjects_of_care");
				SubjectOfCare newSubjectOfCare=new SubjectOfCare();
				SEQLPathComponent healthcarePathComponent=new SEQLPathComponent("all_subjects_of_care["+id+"]");
				ReferenceModelObjectId saved=pmngr.saveObjectToPathFromParentWithSerializer((SingleAttributeObjectBlock)rmngr.unbindGeneric(newSubjectOfCare), pmngr.getReferenceModelRoot(), healthcarePathComponent,serializer);
				ContentObject savedObject=pmngr.selectFromReferenceModelObjectId(saved,false);
				//Create an EHR for the subject of care. We bind the subject of care to get the generated II
				SubjectOfCare bsc=(SubjectOfCare)rmngr.bind(savedObject);
				EHRExtract extract=new EHRExtract();
				GregorianCalendar gregorianCalendar = new GregorianCalendar();
			    DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
			    XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
				extract.setTimeCreated(now);
				//rm_id is always ISO 13606-1
				extract.setRmId("ISO 13606-1");
				extract.setSubjectOfCare(bsc.getIdentifier());
				//Save the ehr
				root=pmngr.getReferenceModelRoot();
				id=pmngr.readAtomicIndex("all_ehrs");
				SEQLPathComponent pathComponent=new SEQLPathComponent("all_ehrs["+id+"]");
				//Declare an atomic index for the ehr
				pmngr.declareAtomicIndex(pathComponent.toString());
				saved=pmngr.saveObjectToPathFromParentWithSerializer((SingleAttributeObjectBlock)rmngr.unbindGeneric(extract), pmngr.getReferenceModelRoot(), pathComponent,serializer);
				savedObject=pmngr.selectFromReferenceModelObjectId(saved,false);
				//Return the EHR
				return new ReturnValueCreateSubjectOfCare(requestId,dmngr.serialize(savedObject,false));			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RejectException(requestId,CSReason.REAS02);
		}
	}

	@Override
	public ReturnValueCreatePerformer createPerformer(String requestId)
			throws RejectException {
		try {
				//Get the number of healthcare facilities
				ReferenceModelObjectId root=pmngr.getReferenceModelRoot();
				long id=pmngr.readAtomicIndex("all_performers");
				Performer newPerformer=new Performer();
				SEQLPathComponent healthcarePathComponent=new SEQLPathComponent("all_performers["+id+"]");
				ReferenceModelObjectId saved=pmngr.saveObjectToPathFromParentWithSerializer((SingleAttributeObjectBlock)rmngr.unbindGeneric(newPerformer), pmngr.getReferenceModelRoot(), healthcarePathComponent,serializer);
				ContentObject savedObject=pmngr.selectFromReferenceModelObjectId(saved,false);
				return new ReturnValueCreatePerformer(requestId,dmngr.serialize(savedObject,false));
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RejectException(requestId,CSReason.REAS02);
		}
	}

	@Override
	public ReturnValueCreateReport createReport(String requestId,
			II ehrId, FunctionalRole composerId, String audioData,
			String textTranscription, CDCV reportStatus, II rootArchetypeId)
			throws RejectException {
		if(ehrId==null || composerId==null || reportStatus==null) {
			throw new RejectException(requestId,CSReason.REAS01);
		}
		try {
			if(textTranscription==null) {
				textTranscription="";
			}
			//Use the SAPRM to get an analyzed DADL
			log.debug("INVOKE SAPRM");
			InputStream dadl=saprm.analyzeText(textTranscription, rootArchetypeId,null);
			//The SAPRM gives us a composition object. Parse it
			ContentObject reportCompositionCo=dmngr.parseDADL(dadl);
			//We no longer bind the composition for performance. Just unbind the properties and assign directly to the contentobject
			
			AuditInfo auditInfo=new AuditInfo();
			//Set the ehr_system, commiter, version_status, reason_for_revision, version_set_id
			auditInfo.setEhrSystem(this.ehrSystemId);
			//Version status is always draft, given that the report has not been reviewed by the composer
			auditInfo.setVersionStatus(VersionStatus.DRAFT);
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
		    DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
		    XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
			auditInfo.setTimeCommitted(now);
			
			SingleAttributeObjectBlock auditInfoUnbinded=this.rmngr.getSingleAttributeObjectBlockFromContentObject(this.rmngr.unbind(auditInfo));
			SingleAttributeObjectBlock composerUnbinded=this.rmngr.getSingleAttributeObjectBlockFromContentObject(this.rmngr.unbind(composerId));
			SingleAttributeObjectBlock compositionBlock=this.rmngr.getSingleAttributeObjectBlockFromContentObject(reportCompositionCo);
			compositionBlock.getAttributeValues().add(new AttributeValue("committal",auditInfoUnbinded));
			compositionBlock.getAttributeValues().add(new AttributeValue("composer",composerUnbinded));
			
			/*
			Composition report=(Composition)rmngr.bind(reportCompositionCo);
			//An EHR must already exist IF the subject of care exists
			//The composer of the composition is the composer given in the input
			report.setComposer(composerId);
			//Prepare the audit info
			AuditInfo auditInfo=new AuditInfo();
			//Set the ehr_system, commiter, version_status, reason_for_revision, version_set_id
			auditInfo.setEhrSystem(this.ehrSystemId);
			//Version status is always draft, given that the report has not been reviewed by the composer
			auditInfo.setVersionStatus(VersionStatus.DRAFT);
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
		    DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
		    XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
			auditInfo.setTimeCommitted(now);
			//No reason for revision, as we are in the first commit
			//For now, we do not set the commiter (same as composer).
			report.setCommittal(auditInfo);*/
			//We do not need to set the version_set_id, it is not mandatory (given that is the same that the rc_id)

				//Add the composition to the extract. We need to know which EHR extract matches the client
				ReferenceModelObjectId ehr=pmngr.getReferenceModelObjectIdFromUniqueId(Long.parseLong(ehrId.getExtension()));
				SEQLPath queryPath=(SEQLPath)pmngr.getReferenceModelPathFoRMObject(ehr).clone();
				List<SEQLPathComponent> subComponents=queryPath.getPathComponents();
				subComponents.remove(0);
				SEQLPathComponent allEhrsComponent=subComponents.get(0);
				long id=pmngr.readAtomicIndex(allEhrsComponent.toString());
				allEhrsComponent=new SEQLPathComponent(allEhrsComponent.getPathIdentifier(), new SEQLPathPredicate("at0000",allEhrsComponent.getPathPredicate().getKey1()));
				SEQLPathComponent pathComponent=new SEQLPathComponent("all_compositions["+id+"]");
				//SingleAttributeObjectBlock unbinded=(SingleAttributeObjectBlock)rmngr.unbindGeneric(report);
				ReferenceModelObjectId saved=pmngr.saveObjectToPathFromParentWithSerializer(compositionBlock, ehr, pathComponent,serializer);
				//Retrieve the saved composition object (attributes only).
				ContentObject savedObject=pmngr.selectFromReferenceModelObjectId(saved,false);
				//ContentObject savedObject=pmngr.selectFromReferenceModelObjectId(saved);
				//Return the Composition object (attributes only) or alternatively the EHR_ID using the reference model manager  
				return new ReturnValueCreateReport(requestId,dmngr.serialize(savedObject,false));
		} catch(Exception e) { 
			e.printStackTrace();
			throw new RejectException(requestId,CSReason.REAS02);
		}
	}

	@Override
	public ReturnValueUpdateReport updateReport(String requestId, II reportId,
			String audioData, String textTranscription, CDCV reportStatus,
			II rootArchetypeId, VersionStatus reason) throws RejectException {
		try {
			//Use the report id to retrieve the reference model object of the previous
			//version of the EHR
			//We need to find a composition that matches the given rc_id. 
			//The rc_id is assigned by us so we can use the extension value as the unique object id in the database
			long oid=Long.parseLong(reportId.getExtension());
			ReferenceModelObjectId rmid=pmngr.getReferenceModelObjectIdFromUniqueId(oid);
			//We need to: create a new Composition, probably copy most of the information
			//Update the version set id attribute
			//Update the version table. 
			//Here, in the update report, we have a problem.
			//First, what if the text has changed and the CDCV stuff has changed. 
			//Second, what if the changes of the physician are relevant for the archetype values
			//For now, and as an experiment, I am just going to copy the original composition
			//AND add a new version
			//We assume the report id is the orig_parent_ref? 
			//Not clear. We should return, in update report, the last version. After all
			//it will be the one showing by default in EQL
			//So, first of all, implement with descendants, or alternatively, perform a deep copy
			//of the object within the database (probably the best)
			throw new RejectException(requestId,CSReason.REAS02);
		} catch(Exception e) { 
			throw new RejectException(requestId,CSReason.REAS02);
		}
	}

 
}
