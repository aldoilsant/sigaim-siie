package org.sigaim.siie.interfaces.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.sql.SQLPersistenceManager;
import org.sigaim.siie.interfaces.eql.IntSIIE001EQL;
import org.sigaim.siie.interfaces.eql.ReturnValueEQL;
import org.sigaim.siie.interfaces.eql.sigaim.SigaimIntSIIE001EQL;
import org.sigaim.siie.interfaces.reportmanagement.IntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.reportmanagement.ReportStatus;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateHealthcareFacility;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreatePerformer;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateReport;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateSubjectOfCare;
import org.sigaim.siie.interfaces.reportmanagement.sigaim.SigaimIntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.saprm.DummyINT004SIIESAPRMProxy;
import org.sigaim.siie.interfaces.saprm.INT004SIIESAPRMProxy;
import org.sigaim.siie.iso13606.rm.BL;
import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.iso13606.rm.Cluster;
import org.sigaim.siie.iso13606.rm.EHRExtract;
import org.sigaim.siie.iso13606.rm.Element;
import org.sigaim.siie.iso13606.rm.FunctionalRole;
import org.sigaim.siie.iso13606.rm.HealthcareFacility;
import org.sigaim.siie.iso13606.rm.II;
import org.sigaim.siie.iso13606.rm.Item;
import org.sigaim.siie.iso13606.rm.Performer;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.engine.SEQLEngine;
import org.sigaim.siie.seql.engine.SEQLPipeEngine;
import org.sigaim.siie.seql.engine.execution.SEQLExecutionMemorySolverStage;
import org.sigaim.siie.seql.engine.preprocessing.SEQLPreprocessingValidateIdentifiedVariablesStage;
import org.sigaim.siie.seql.model.SEQLException;
import org.sigaim.siie.seql.model.SEQLResultSet;

public class TestUpdateReport {
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
		this.saprm=new DummyINT004SIIESAPRMProxy();
		this.eqlService= new SigaimIntSIIE001EQL(engine,dadlManager);
		this.reportManagementService=new SigaimIntSIIE004ReportManagement(persistenceManager, referenceModelManager, dadlManager, saprm, seqlEngine);
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
	@Test
	public void testUpdateReport() throws Exception {
		//The first report always has an id of 4. 
		//Use EQL to retrieve the only composition (in this case) in the latest version
		ReturnValueEQL ret=this.eqlService.query("", "SELECT e/ehr_id, c/rc_id, c/composer FROM EHR e CONTAINS COMPOSITION c WHERE e/ehr_id/extension=\"4\";");
		String serialized=ret.getSerialized();
		SEQLResultSet rs=createResultSet(serialized);
		if(rs.getNumberOfRows()!=1 ) {
			throw new Exception("Unexpected number of rows: "+rs.getNumberOfRows());
		}
		//Bind the IIs from the columns
		rs.nextRow();
		II ehrId=(II)this.referenceModelManager.bind(rs.getColumn(0));
		II previousVersionId=(II)this.referenceModelManager.bind(rs.getColumn(1));
		FunctionalRole previousComposerId=(FunctionalRole)this.referenceModelManager.bind(rs.getColumn(2));
		II fakePerformerId=new II();
		fakePerformerId.setExtension("an invalid extension value");
		previousComposerId.setPerformer(fakePerformerId);
		ReportStatus rstatus=new ReportStatus();
		rstatus.setConfirmed(true);
		rstatus.setSigned(true);
		rstatus.setDictated(true);
		this.reportManagementService.updateReport("1", ehrId, previousVersionId, previousComposerId, "", rstatus, null, null);
		//Now retrieve the performer id and the report state,  see if the update has been successful
		ret=this.eqlService.query("", "SELECT c/composer, r/items[at0020] WITH DESCENDANTS FROM EHR e CONTAINS COMPOSITION c CONTAINS ENTRY r[CEN-EN13606-ENTRY.Informacion.v1] WHERE e/ehr_id/extension=\"4\";");
		serialized=ret.getSerialized();
		rs=createResultSet(serialized);
		if(rs.getNumberOfRows()!=1 ) {
			throw new Exception("Unexpected number of rows: "+rs.getNumberOfRows());
		}
		//Bind the IIs from the columns
		rs.nextRow();
		FunctionalRole newComposerId=(FunctionalRole)this.referenceModelManager.bind(rs.getColumn(0));
		II newPerformerId=newComposerId.getPerformer();
		if(!newPerformerId.getExtension().equals(fakePerformerId.getExtension())) {
			throw new Exception("Unexpected extension for performer: "+newPerformerId.getExtension());
		}
		Cluster reportState=(Cluster)this.referenceModelManager.bind(rs.getColumn(1));
		for(Item part : reportState.getParts()) {
			if(part instanceof Element) {
				Element epart=(Element)part;
				BL bValue=(BL)epart.getValue();
				if(part.getArchetypeId().getExtension().equals("at0021") && !bValue.isValue()) {
					throw new Exception("Unexpected dictated boolean value (should be true)");
				}
				if(part.getArchetypeId().getExtension().equals("at0022") && !bValue.isValue()) {
					throw new Exception("Unexpected signed boolean value (should be true)");
				}
				if(part.getArchetypeId().getExtension().equals("at0025") && !bValue.isValue()) {
					throw new Exception("Unexpected confirmed boolean value (should be true)");
				}
			}
		}
	}

}
