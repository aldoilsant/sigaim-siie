package org.sigaim.siie.interfaces.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.sql.SQLPersistenceManager;
import org.sigaim.siie.interfaces.eql.IntSIIE001EQL;
import org.sigaim.siie.interfaces.eql.sigaim.SigaimIntSIIE001EQL;
import org.sigaim.siie.interfaces.reportmanagement.IntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateHealthcareFacility;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreatePerformer;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateReport;
import org.sigaim.siie.interfaces.reportmanagement.ReturnValueCreateSubjectOfCare;
import org.sigaim.siie.interfaces.reportmanagement.sigaim.SigaimIntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.saprm.DummyINT004SIIESAPRMProxy;
import org.sigaim.siie.interfaces.saprm.INT004SIIESAPRMProxy;
import org.sigaim.siie.iso13606.rm.EHRExtract;
import org.sigaim.siie.iso13606.rm.FunctionalRole;
import org.sigaim.siie.iso13606.rm.HealthcareFacility;
import org.sigaim.siie.iso13606.rm.Performer;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.engine.SEQLEngine;
import org.sigaim.siie.seql.engine.SEQLPipeEngine;
import org.sigaim.siie.seql.engine.execution.SEQLExecutionMemorySolverStage;
import org.sigaim.siie.seql.engine.preprocessing.SEQLPreprocessingValidateIdentifiedVariablesStage;

public class PopulateDBForStress {
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
	
	public void testCreateReport() throws Exception {
		//Create a new performer, a new subject of care and a new healthcare facility
		ReturnValueCreateHealthcareFacility rvhf=this.reportManagementService.createHealthcareFacility("1");
		HealthcareFacility newFacility=(HealthcareFacility)
				referenceModelManager.bind(
				dadlManager.parseDADL(new ByteArrayInputStream(rvhf.getSerialized().getBytes())));
		ReturnValueCreateSubjectOfCare rvsc=this.reportManagementService.createSubjectOfCare("1");
		EHRExtract newEHR=(EHRExtract)
				referenceModelManager.bind(
				dadlManager.parseDADL(new ByteArrayInputStream(rvsc.getSerialized().getBytes())));		
		ReturnValueCreatePerformer rvp=this.reportManagementService.createPerformer("1");
		Performer newPerformer=(Performer)
				referenceModelManager.bind(
				dadlManager.parseDADL(new ByteArrayInputStream(rvp.getSerialized().getBytes())));
		FunctionalRole composer=new FunctionalRole();
		composer.setHealthcareFacility(newFacility.getIdentifier());
		composer.setPerformer(newPerformer.getIdentifier());

		ReturnValueCreateReport result=this.reportManagementService.createReport("1", newEHR.getEhrId(), composer, "", true, null);
				
	}
	public void testCreateReports(int nreports) throws Exception {
		for(int i=0;i<nreports;i++) {
			System.out.println("CREATING REPORT "+i);
			long start=System.currentTimeMillis();
			testCreateReport();
			long end=System.currentTimeMillis();
			System.out.println("TIME TAKEN: "+(end-start));
		}
	}
	@Test
	public void doTest() throws Exception {
		testCreateReports(9000);
	}

}
