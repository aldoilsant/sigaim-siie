package org.sigaim.siie.interfaces.reportmanagement.sigaim;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.log4j.Logger;
import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.BooleanValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.IntegerValue;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.PrimitiveObjectBlock;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.ReferenceModelObjectId;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.interfaces.IDDBSerializer;
import org.sigaim.siie.interfaces.reportmanagement.IntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.reportmanagement.ReportStatus;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateHealthcareFacility;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreatePerformer;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateReport;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateSubjectOfCare;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueUpdateReport;
import org.sigaim.siie.interfaces.saprm.INT004SIIESAPRMProxy;
import org.sigaim.siie.iso13606.rm.AuditInfo;
import org.sigaim.siie.iso13606.rm.BL;
import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.iso13606.rm.Cluster;
import org.sigaim.siie.iso13606.rm.Composition;
import org.sigaim.siie.iso13606.rm.EHRExtract;
import org.sigaim.siie.iso13606.rm.Element;
import org.sigaim.siie.iso13606.rm.FunctionalRole;
import org.sigaim.siie.iso13606.rm.HealthcareFacility;
import org.sigaim.siie.iso13606.rm.II;
import org.sigaim.siie.iso13606.rm.Item;
import org.sigaim.siie.iso13606.rm.Performer;
import org.sigaim.siie.iso13606.rm.SubjectOfCare;
import org.sigaim.siie.iso13606.rm.VersionStatus;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.exceptions.CSReason;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.rm.exceptions.RejectException;
import org.sigaim.siie.seql.engine.SEQLEngine;
import org.sigaim.siie.seql.engine.execution.SEQLExecutionMemorySolverStage;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;
import org.sigaim.siie.seql.model.SEQLPathPredicate;
import org.sigaim.siie.seql.model.SEQLQuery;
import org.sigaim.siie.seql.model.SEQLResultSet;
import org.sigaim.siie.seql.parser.SEQLErrorListener;
import org.sigaim.siie.seql.parser.SEQLModelListener;
import org.sigaim.siie.seql.parser.generated.SEQLLexer;
import org.sigaim.siie.seql.parser.generated.SEQLParser;
import org.sigaim.siie.utils.Utils;

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
	protected AuditInfo createAuditInfo(ReportStatus rstatus, II previousVersionId, II versionSetId) throws DatatypeConfigurationException {
		AuditInfo auditInfo=new AuditInfo();
		//Set the ehr_system, commiter, version_status, reason_for_revision, version_set_id
		auditInfo.setEhrSystem(this.ehrSystemId);
		auditInfo.setVersionStatus(rstatus.getVersionStatus());
		//Regarding the information archetype, the SAPRM takes care of that
		//We only need to check signed and confirmed, but this goes in updatereport
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
	    DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
	    XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
		auditInfo.setTimeCommitted(now);
		if(previousVersionId!=null) {
			auditInfo.setPreviousVersion(previousVersionId);
		}
		if(versionSetId!=null) {
			auditInfo.setVersionSetId(versionSetId);
		}
		return auditInfo;
	}
	protected ReferenceModelObjectId storeNewComposition(II ehrId,SingleAttributeObjectBlock compositionBlock) throws PersistenceException, ReferenceModelException{
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
		return saved;
	}

	protected void replaceBlValueForBlock(SingleAttributeObjectBlock elementBlock, boolean value) {
		for(AttributeValue pAttr : elementBlock.getAttributeValues()) {
			if(pAttr.getId().equals("value")) {
				SingleAttributeObjectBlock blBlock=(SingleAttributeObjectBlock)pAttr.getValue(); 																						
				AttributeValue blAttr=null;
				for(AttributeValue bAttr : blBlock.getAttributeValues()) {
					if(bAttr.getId().equals("value")) {
						blAttr=bAttr;
						break; 
					}
				}
				//Replace the attribute value
				blBlock.getAttributeValues().remove(blAttr);
				PrimitiveObjectBlock pblock=new PrimitiveObjectBlock(null,new BooleanValue(value),null,null,null,null);
				blAttr=new AttributeValue(blAttr.getId(),pblock);
				blBlock.getAttributeValues().add(blAttr);
				break;
			}
		}
	}
	protected void updateRawReportState(SingleAttributeObjectBlock compositionBlock, ReportStatus rstatus) throws ReferenceModelException{
		for(AttributeValue attr : compositionBlock.getAttributeValues()) {
			if(attr.getId().equals("content")) {
				//Find the entry for the information archetype. This is a multipleattributeobjectblock
				MultipleAttributeObjectBlock contents=(MultipleAttributeObjectBlock)attr.getValue();
				for(KeyedObject obj: contents.getKeyObjects()) {
					if(obj.getObject() instanceof SingleAttributeObjectBlock) {
						SingleAttributeObjectBlock content=(SingleAttributeObjectBlock) obj.getObject();
						if(Utils.classNameEquals(this.rmngr.getReferenceModelClassName(content),"section")) {
							if(this.rmngr.getArchetypeNodeIdForRMObject(content).equals("at0007")) {
								//Found the section, get the entry
								for(AttributeValue attrs : content.getAttributeValues()) {
									if(attrs.getId().equals("members")) {
										//Found the information entry
										MultipleAttributeObjectBlock infoEntries=(MultipleAttributeObjectBlock)attrs.getValue();
										SingleAttributeObjectBlock entry=(SingleAttributeObjectBlock)infoEntries.getKeyObjects().get(0).getObject();
										log.debug("Found information entry");
										//Find cluster at0020
										for(AttributeValue attre : entry.getAttributeValues()) {
											if(attre.getId().equals("items")) {
												MultipleAttributeObjectBlock infoClusters=(MultipleAttributeObjectBlock)attre.getValue();
												for(KeyedObject objc: infoClusters.getKeyObjects()) {
													if(objc.getObject() instanceof SingleAttributeObjectBlock) {
														SingleAttributeObjectBlock contentc=(SingleAttributeObjectBlock) objc.getObject();
														if(Utils.classNameEquals(this.rmngr.getReferenceModelClassName(contentc),"cluster")) {
															if(this.rmngr.getArchetypeNodeIdForRMObject(contentc).equals("at0020")) {
															//Found the cluster with report state
																for(AttributeValue attri : contentc.getAttributeValues()) {
																	if(attri.getId().equals("parts")) {
																		MultipleAttributeObjectBlock parts=(MultipleAttributeObjectBlock)attri.getValue();
																		//Replace the values for indexes 1, 2, 5 //Dictated, signed, confirmed
																		for(KeyedObject statePart : parts.getKeyObjects()) {
																			SingleAttributeObjectBlock elementBlock=(SingleAttributeObjectBlock)statePart.getObject();
																			if(statePart.getKey().getValue().equals(new Integer(1))) {
																				this.replaceBlValueForBlock(elementBlock, rstatus.isDictated());
																			} else if(statePart.getKey().getValue().equals(new Integer(2))) {
																				this.replaceBlValueForBlock(elementBlock, rstatus.isSigned());
																			} else if(statePart.getKey().getValue().equals(new Integer(5))) {
																				this.replaceBlValueForBlock(elementBlock, rstatus.isConfirmed());
																			}
																		}
																		return;
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	@Override
	public ReturnValueCreateReport createReport(String requestId,
			II ehrId, FunctionalRole composerId, 
			String textTranscription, boolean dictated, II rootArchetypeId)
			throws RejectException {
		if(ehrId==null || composerId==null) {
			throw new RejectException(requestId,CSReason.REAS01);
		}
		try {
			if(textTranscription==null) {
				textTranscription="";
			}
			ReportStatus rstatus=new ReportStatus();
			rstatus.setDictated(dictated);
			//Use the SAPRM to get an analyzed DADL
			 //dictated) { Talk to charo about SAPRM, non-dicated, single report
			log.debug("INVOKE SAPRM");
			InputStream dadl=saprm.analyzeText(textTranscription, rootArchetypeId,null,true);
			//The SAPRM gives us a composition object. Parse it
			ContentObject reportCompositionCo=dmngr.parseDADL(dadl);
			//We no longer bind the composition for performance. Just unbind the properties and assign directly to the contentobject
			AuditInfo auditInfo=createAuditInfo(rstatus,null,null);

			SingleAttributeObjectBlock auditInfoUnbinded=this.rmngr.getSingleAttributeObjectBlockFromContentObject(this.rmngr.unbind(auditInfo));
			SingleAttributeObjectBlock composerUnbinded=this.rmngr.getSingleAttributeObjectBlockFromContentObject(this.rmngr.unbind(composerId));
			SingleAttributeObjectBlock compositionBlock=this.rmngr.getSingleAttributeObjectBlockFromContentObject(reportCompositionCo);
			compositionBlock.getAttributeValues().add(new AttributeValue("committal",auditInfoUnbinded));
			compositionBlock.getAttributeValues().add(new AttributeValue("composer",composerUnbinded));
			ReferenceModelObjectId saved=this.storeNewComposition(ehrId,compositionBlock);
			
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
	public ReturnValueUpdateReport updateReport(
			String requestId,
			II ehrId, //the id of the previous version
			II previousVersionId, 
			FunctionalRole composerId,
			String textTranscription,
			ReportStatus rstatus,
			II rootArchetypeId,
			String encodedConcepts
			) throws RejectException {
		if(ehrId==null || previousVersionId==null) {
			throw new RejectException(requestId,CSReason.REAS01);
		}
		if(textTranscription==null) {
			textTranscription="";
		}
		try {
			InputStream dadl=saprm.analyzeText(textTranscription, rootArchetypeId,encodedConcepts,true);
			ContentObject reportCompositionCo=dmngr.parseDADL(dadl);
			//We now need to extend the audit info to include the previous_version and version_set_id
			//Note that the previous composition might already be an update, so we need to get the version_set_id from it
			ReferenceModelObjectId previousCompositionId=pmngr.getReferenceModelObjectIdFromUniqueId(Long.parseLong(previousVersionId.getExtension()));
			ContentObject previousCompositionObject=pmngr.selectFromReferenceModelObjectId(previousCompositionId,false);
			Composition previousComposition=(Composition)this.rmngr.bind(previousCompositionObject);
			II versionSetId=previousComposition.getCommittal().getVersionSetId();
			//Now create the audit info
			AuditInfo auditInfo=createAuditInfo(rstatus,previousVersionId,versionSetId);
			SingleAttributeObjectBlock auditInfoUnbinded=this.rmngr.getSingleAttributeObjectBlockFromContentObject(this.rmngr.unbind(auditInfo));
			SingleAttributeObjectBlock composerUnbinded=this.rmngr.getSingleAttributeObjectBlockFromContentObject(this.rmngr.unbind(composerId));
			SingleAttributeObjectBlock compositionBlock=this.rmngr.getSingleAttributeObjectBlockFromContentObject(reportCompositionCo);
			compositionBlock.getAttributeValues().add(new AttributeValue("committal",auditInfoUnbinded));
			compositionBlock.getAttributeValues().add(new AttributeValue("composer",composerUnbinded));
			//We need to update the information archetype to set the correct state from the reportStatus
			//SEQLResultSet rs=this.query("", "SELECT e/items[at0008] WITH DESCENDANTS FROM EHR CONTAINS COMPOSITION c CONTAINS ENTRY e[CEN-EN13606-ENTRY.Informacion.v1] WHERE c/rc_id/extension=\""+reportId.getExtension()+"\";");
			updateRawReportState(compositionBlock,rstatus);
			//Now we are ready to store the composition as usual
			ReferenceModelObjectId saved=this.storeNewComposition(ehrId,compositionBlock);
			//Set as the new latest version
			//WARNING: possible inconsistency during this time
			this.pmngr.setAsNextVersionOf(saved, previousCompositionId);
			//Bind the versions in the database. 
			ContentObject savedObject=pmngr.selectFromReferenceModelObjectId(saved,false);
			return new ReturnValueUpdateReport(requestId,dmngr.serialize(savedObject,false));

			
			//It goes in all_compositions but when selecting, we only use the last version
			//Now, 
		} catch(Exception e) { 
			e.printStackTrace();
			throw new RejectException(requestId,CSReason.REAS02);
		}
	}

 
}
