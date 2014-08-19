package org.sigaim.siie.tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.sql.SQLPersistenceManager;
import org.sigaim.siie.interfaces.eql.IntSIIE001EQL;
import org.sigaim.siie.interfaces.eql.sigaim.SigaimIntSIIE001EQL;
import org.sigaim.siie.interfaces.reportmanagement.IntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.saprm.INT004SIIESAPRMProxy;
import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.iso13606.rm.II;
import org.sigaim.siie.iso13606.rm.IVLTS;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.engine.SEQLEngine;
import org.sigaim.siie.seql.engine.SEQLPipeEngine;
import org.sigaim.siie.seql.engine.execution.SEQLExecutionMemorySolverStage;
import org.sigaim.siie.seql.engine.preprocessing.SEQLPreprocessingValidateIdentifiedVariablesStage;

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
 
	public void requestEhrExtract(
			String requestId,  
			II subjectOfCareId, //Mandatory
			CDCV purpose, //Optional, purpopse of the EHR extract, probably ignored
			Set<II> rc_ids, //Explicits rc_ids for components to be included.
			IVLTS time_period, //Date or time interval for data
			int max_sensitivity, //Max_sensitivity, ignored
			boolean all_versions, //Latest version if false
			boolean multimedia_included, //Include multimedia, probably ignored
			Set<II> archetype_ids, //record components matching archetype ids
			Set<CDCV> meanings //meaning attribute match
			) {
		/* The big question here is this one: 
		 * The resulting EHR_EXTRACT might contain such additional RECORD_COMPONENTS as are required to conform to ISO 13606-1, such as the COMPOSITIONS that contain the requested RECORD_COMPONENTS.
		 * */ 
		/* *
		 * Note that, if that is the case, we would need to perform a query to retrieve the compositions but only with the desired "branch" of the objects
		 * */
	}
	@Test
	public void test() {
 
	}

}
