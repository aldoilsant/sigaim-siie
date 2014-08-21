package org.sigaim.siie.interfaces.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.sql.SQLPersistenceManager;
import org.sigaim.siie.interfaces.eql.IntSIIE001EQL;
import org.sigaim.siie.interfaces.eql.ReturnValueEQL;
import org.sigaim.siie.interfaces.eql.sigaim.SigaimIntSIIE001EQL;
import org.sigaim.siie.interfaces.reportmanagement.IntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateHealthcareFacility;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreatePerformer;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateReport;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateSubjectOfCare;
import org.sigaim.siie.interfaces.reportmanagement.sigaim.SigaimIntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.saprm.DummyINT004SIIESAPRMProxy;
import org.sigaim.siie.interfaces.saprm.INT004SIIESAPRMProxy;
import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.iso13606.rm.FunctionalRole;
import org.sigaim.siie.iso13606.rm.HealthcareFacility;
import org.sigaim.siie.iso13606.rm.Performer;
import org.sigaim.siie.iso13606.rm.ReportStatus;
import org.sigaim.siie.iso13606.rm.SubjectOfCare;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.engine.SEQLEngine;
import org.sigaim.siie.seql.engine.SEQLPipeEngine;
import org.sigaim.siie.seql.engine.execution.SEQLExecutionMemorySolverStage;
import org.sigaim.siie.seql.engine.preprocessing.SEQLPreprocessingValidateIdentifiedVariablesStage;
import org.sigaim.siie.seql.model.SEQLResultSet;

public class EQLTests {
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

	
	@Test
	public void testSEQLService() throws Exception {
		//Create a new query. All queries must end with ;
		String query="SELECT e/items[at0008]/parts[at0009] WITH DESCENDANTS FROM EHR CONTAINS COMPOSITION c CONTAINS ENTRY e[CEN-EN13606-ENTRY.Informacion.v1] WHERE c/rc_id/extension=\"6\";";
		ReturnValueEQL result=this.eqlService.query("1", query);
		assert(result!=null);
		String sres=result.getSerialized();
		System.out.println("Result: "+sres);
	}

}
