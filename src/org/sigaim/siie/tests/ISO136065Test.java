package org.sigaim.siie.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.PrimitiveObjectBlock;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.openehr.am.parser.StringValue;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.sql.SQLPersistenceManager;
import org.sigaim.siie.interfaces.eql.IntSIIE001EQL;
import org.sigaim.siie.interfaces.eql.ReturnValueEQL;
import org.sigaim.siie.interfaces.eql.sigaim.SigaimIntSIIE001EQL;
import org.sigaim.siie.interfaces.reportmanagement.IntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.saprm.INT004SIIESAPRMProxy;
import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.iso13606.rm.ExtractCriteria;
import org.sigaim.siie.iso13606.rm.II;
import org.sigaim.siie.iso13606.rm.IVLTS;
import org.sigaim.siie.iso13606.rm.TS;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.rm.exceptions.CSReason;
import org.sigaim.siie.rm.exceptions.RejectException;
import org.sigaim.siie.seql.engine.SEQLEngine;
import org.sigaim.siie.seql.engine.SEQLPipeEngine;
import org.sigaim.siie.seql.engine.execution.SEQLExecutionMemorySolverStage;
import org.sigaim.siie.seql.engine.preprocessing.SEQLPreprocessingValidateIdentifiedVariablesStage;
import org.sigaim.siie.seql.model.SEQLException;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLResultSet;

public class ISO136065Test {
	IntSIIE001EQL eqlService;
	IntSIIE004ReportManagement reportManagementService;
	private DADLManager dadlManager;
	private PersistenceManager persistenceManager;
	private ReferenceModelManager referenceModelManager;
	private INT004SIIESAPRMProxy saprm;
	private SEQLEngine seqlEngine;
	
	@Before
	public void setUp() throws Exception {
		this.dadlManager=new OpenEHRDADLManager();
		this.referenceModelManager=new ReflectorReferenceModelManager(this.dadlManager);
		SQLPersistenceManager sqlManager=new SQLPersistenceManager();
		sqlManager.setDADLManager(this.dadlManager);
		sqlManager.setReferenceModelManager(this.referenceModelManager);
		this.persistenceManager=sqlManager;
		SEQLExecutionMemorySolverStage stage=new SEQLExecutionMemorySolverStage(persistenceManager,referenceModelManager,dadlManager);
		SEQLPipeEngine engine=new SEQLPipeEngine();
		engine.addPreprocessStage(new SEQLPreprocessingValidateIdentifiedVariablesStage());
		engine.addExecutionStage(stage);
		this.seqlEngine=engine;
		this.eqlService= new SigaimIntSIIE001EQL(engine,dadlManager);
	}
	
	protected SEQLResultSet createResultSet(String input) throws SEQLException{
		SEQLResultSet rs=new SEQLResultSet();
		ContentObject serializedResultSet=this.dadlManager.parseDADL(new ByteArrayInputStream(input.getBytes()));
		//The first multipleAttributeObjectBlock are the rows. 
		if(serializedResultSet.getComplexObjectBlock() instanceof SingleAttributeObjectBlock) {
			//Empty result set
			rs.setNumberOfColumns(0);
			rs.compile();
			return rs;
		}
		MultipleAttributeObjectBlock rowsBlock=(MultipleAttributeObjectBlock)serializedResultSet.getComplexObjectBlock();
		for(KeyedObject row : rowsBlock.getKeyObjects()) {
			rs.addRow();
			//Each column is, itself, a multipleAttributeobjectblock
			MultipleAttributeObjectBlock columnBlock=(MultipleAttributeObjectBlock)row.getObject();
			int columns=0;
			for(KeyedObject column : columnBlock.getKeyObjects()) {
				rs.appendToRow(new ContentObject(null,(ComplexObjectBlock)column.getObject()));
				columns++;
			}
			rs.setNumberOfColumns(columns);
		}
		rs.compile();
		return rs;
	}
	
	public String getDateComparisonFromTS(TS time, boolean closed, boolean high) {
		String operator=null;
		if(closed && high) {
			operator="<=";
		} else if(closed && !high) {
			operator=">=";
		} else if(!closed && high) {
			operator="<";
		} else {
			operator=">";
		}
		return operator + "'"+time.getValue()+"'";
	}
	//If multiple constraints are specified, the provided artefacts shall conform to all of the constraints (i.e. to their intersection).
	//Include criteria as well (new object, unbind)
	public ContentObject requestEhrExtract(
			String requestId,  
			II subjectOfCareId, //Mandatory
			CDCV purpose, //Optional, purpopse of the EHR extract, IGNORED
			Set<II> rc_ids, //Explicits rc_ids for components to be included.
			IVLTS time_period, //Date or time interval for data
			int max_sensitivity, //Max_sensitivity, IGNORED
			boolean all_versions, //Latest version if false
			boolean multimedia_included, //Include multimedia, IGNORED
			Set<II> archetype_ids, //record components matching archetype ids
			Set<CDCV> meanings //meaning attribute match
			) throws RejectException{
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT MERGED AS m e,r FROM EHR e CONTAINS ");
		if(all_versions) {
			queryBuilder.append("ALL VERSIONS OF ");
		}
		queryBuilder.append("RECORD_COMPONENT r WHERE e/subject_of_care/extension=\"");
		queryBuilder.append(subjectOfCareId.getExtension());
		queryBuilder.append("\" AND e/subject_of_care/root=\"");
		queryBuilder.append(subjectOfCareId.getRoot());
		queryBuilder.append("\"");
		boolean areRcIds=rc_ids != null && rc_ids.size()!=0;
		boolean areArchetypeIds=archetype_ids != null && archetype_ids.size()!=0;
		boolean areMeanings=meanings != null && meanings.size()!=0;
		boolean areLimits=areRcIds | areArchetypeIds || areMeanings;
 
 
		if(areRcIds) {
			boolean nfirst=false;
			queryBuilder.append(" AND (");
			for(II id : rc_ids) {
				if(nfirst) {
					queryBuilder.append(" OR ");
				}
				nfirst=true;
				queryBuilder.append(" (r/rc_id/root=\"");
				queryBuilder.append(id.getRoot());
				queryBuilder.append("\" ");
				queryBuilder.append(" AND r/rc_id/extension=\"");
				queryBuilder.append(id.getExtension());
				queryBuilder.append("\")");
			}
			queryBuilder.append(" )  ");
		}
		if(areArchetypeIds) {
			boolean nfirst=false;
			queryBuilder.append(" AND (");
			for(II id : archetype_ids) {
				if(nfirst) {
					queryBuilder.append(" OR ");
				}
				nfirst=true;
				queryBuilder.append(" (r/archetype_id/root=\"");
				queryBuilder.append(id.getRoot());
				queryBuilder.append("\" ");
				if(id.getExtension()!=null) {
					queryBuilder.append("AND r/archetype_id/extension=\"");
					queryBuilder.append(id.getExtension());
					queryBuilder.append("\"");
				}
				queryBuilder.append(")");
			}
			queryBuilder.append(" )  ");
		}
		if(areMeanings) {
			boolean nfirst=false;
			queryBuilder.append(" AND (");
			for(CDCV id : meanings) {
				if(nfirst) {
					queryBuilder.append(" OR ");
				}
				nfirst=true;
				queryBuilder.append(" AND (r/meaning/code_system_name=\"");
				queryBuilder.append(id.getCodeSystemName());
				queryBuilder.append("\" ");
				if(id.getCode()!=null) {
					queryBuilder.append(" AND r/meaning/code=\"");
					queryBuilder.append(id.getCode());
					queryBuilder.append("\"");
				}

				queryBuilder.append(")");
			}
			queryBuilder.append(" )  ");
		}
		if(time_period!=null && (time_period.getHigh()!=null || time_period.getLow() != null) ) {
			queryBuilder.append("HAVING ");
			String time=null;
			if(time_period.getHigh()!=null) {
				time=this.getDateComparisonFromTS(time_period.getHigh(), time_period.isHighClosed(), true);
				queryBuilder.append(" (m/reference_model_class_name!= \"Composition\" OR m/committal/time_committed");
				queryBuilder.append(time);
				queryBuilder.append(" ) ");
			}
			if(time_period.getLow()!=null) {
				if(time!=null) {
					queryBuilder.append(" AND ");
				}
				time=this.getDateComparisonFromTS(time_period.getLow(), time_period.isLowClosed(), false);
				queryBuilder.append(" (m/reference_model_class_name!= \"Composition\" OR m/committal/time_committed");
				queryBuilder.append(time);
				queryBuilder.append(" ) ");
			}
		}
		queryBuilder.append(";");
		System.out.println("Query: "+queryBuilder);
		ReturnValueEQL ret=this.eqlService.query(requestId, queryBuilder.toString());
		System.out.println("Query complete: "+queryBuilder);
		if(ret.getReasonCode()!=null) {
			throw new RejectException(requestId,CSReason.valueOf(ret.getReasonCode()));
		} else {
			try {
				SEQLResultSet rs=this.createResultSet(ret.getSerialized());
				if(rs.getNumberOfRows()==0) {
					return null;
				} else {
					rs.nextRow();
					ContentObject obj= rs.getColumn(0);
					//Modify time_created and add criteria
					GregorianCalendar gregorianCalendar = new GregorianCalendar();
				    DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
				    XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
				    SingleAttributeObjectBlock block=this.referenceModelManager.getSingleAttributeObjectBlockFromContentObject(obj);
				    AttributeValue timeCreatedAtt=null;
				    for(AttributeValue att : block.getAttributeValues()) {
				    	if(att.getId().equals("time_created")) {
				    		timeCreatedAtt=att;
				    		break;
				    	}
				    }
				    block.getAttributeValues().remove(timeCreatedAtt);
				    block.getAttributeValues().add(new AttributeValue("time_created",new PrimitiveObjectBlock(null,new StringValue(now.toString()),null,null,null,null)));
				    ExtractCriteria crit=new ExtractCriteria();
				    crit.setAllVersions(all_versions);
				    crit.setMaxSensitivity(BigInteger.valueOf(max_sensitivity));
				    crit.setMultimediaIncluded(multimedia_included);
				    crit.setTimePeriod(time_period);
				    if(archetype_ids!=null) {
				    	crit.getArchetypeIds().addAll(archetype_ids);
				    }
				    SingleAttributeObjectBlock critBlock=this.referenceModelManager.getSingleAttributeObjectBlockFromContentObject(this.referenceModelManager.unbind(crit));
				    KeyedObject kCrit=new KeyedObject(new StringValue("1"),critBlock);
				    List<KeyedObject> kCritList=new ArrayList<KeyedObject>();
				    kCritList.add(kCrit);
				    MultipleAttributeObjectBlock mblock=new MultipleAttributeObjectBlock(null, kCritList);
				    block.getAttributeValues().add(new AttributeValue("criteria", mblock));
				    return obj;
				}
			} catch(Exception e) {
				e.printStackTrace();
				throw new RejectException(requestId,CSReason.REAS02);
			}
			
		}
	}
	public void printMap(Map<String,Object> map) {
		for(String key : map.keySet()) {
			Object value=map.get(key);
			System.out.println(key+" : "+value.toString());
		}
	}
	@Test
	public void test() throws Exception {
		II subjectOfCareId= new II();
		subjectOfCareId.setRoot("org.sigaim");
		subjectOfCareId.setExtension("3");
		II testRcId=new II();
		testRcId.setRoot("org.sigaim");
		testRcId.setExtension("9");
		Set<II> rc_ids=new HashSet<II>();
		rc_ids.add(testRcId);
		testRcId=new II();
		testRcId.setRoot("org.sigaim");
		testRcId.setExtension("40");
		rc_ids.add(testRcId);
		
		Set<II> archetypeIds= new HashSet<II> ();
		testRcId=new II();
		testRcId.setRoot("CEN-EN13606-SECTION.ImpresionMedica.v1");
		archetypeIds.add(testRcId);
		
		Set<CDCV> meanings= new HashSet<CDCV>();
		CDCV testCode=new CDCV();
		testCode.setCode("at0009");
		testCode.setCodeSystemName("CEN-EN13606-ENTRY.Informacion.v1");
		meanings.add(testCode);
		
		IVLTS time_period= new IVLTS();
		TS low=new TS();
		low.setValue("2014-08-21T04:50:29.923+02:00");
		time_period.setLow(low);
		time_period.setLowClosed(true);
		
		
		ContentObject ret=this.requestEhrExtract("", subjectOfCareId, null, null, null, 0, true, false, null, null);
		System.out.println("Query completed");
		System.out.println(this.dadlManager.serialize(ret, false));
		List<String> startExclusions=new ArrayList<String>();
		startExclusions.add("/all_compositions[at0000]/content[at0012]/");
		startExclusions.add("/all_compositions/content[at0012]/");
		List<String> endExclusions=new ArrayList<String>();
		endExclusions.add("archetype_id");
		endExclusions.add("meaning");
		endExclusions.add("/reference_model_class_name");
		endExclusions.add("/name");
		Map<String,Object> retMap=this.referenceModelManager.createPathMap(ret, true,true,startExclusions,endExclusions);
		printMap(retMap);
		System.out.println("Path Count: "+retMap.size());
	}

}
