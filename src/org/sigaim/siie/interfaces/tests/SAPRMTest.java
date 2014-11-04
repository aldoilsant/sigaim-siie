package org.sigaim.siie.interfaces.tests;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.sql.SQLPersistenceManager;
import org.sigaim.siie.interfaces.eql.IntSIIE001EQL;
import org.sigaim.siie.interfaces.eql.sigaim.SigaimIntSIIE001EQL;
import org.sigaim.siie.interfaces.reportmanagement.IntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.reportmanagement.sigaim.SigaimIntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.saprm.DummyINT004SIIESAPRMProxy;
import org.sigaim.siie.interfaces.saprm.INT004SIIESAPRMProxy;
import org.sigaim.siie.interfaces.saprm.WebServiceSigaimSIIE004SAPRM;
import org.sigaim.siie.interfaces.terminologies.IntSIIE003Terminologies;
import org.sigaim.siie.interfaces.terminologies.sigaim.SIGAIMIntSIIE003Terminologies;
import org.sigaim.siie.iso13606.rm.II;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.engine.SEQLEngine;
import org.sigaim.siie.seql.engine.SEQLPipeEngine;
import org.sigaim.siie.seql.engine.execution.SEQLExecutionMemorySolverStage;
import org.sigaim.siie.seql.engine.preprocessing.SEQLPreprocessingValidateIdentifiedVariablesStage;
import org.sigaim.siie.utils.pool.SingleConnectionDataSource;

public class SAPRMTest {
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
		this.saprm=new WebServiceSigaimSIIE004SAPRM("http://sigaim.saprm.cesga.es:8080/SIGAIM-SAPRM-WS/services/INT004SIIESAPRMImpl");
		this.eqlService= new SigaimIntSIIE001EQL(engine,dadlManager);
		this.reportManagementService=new SigaimIntSIIE004ReportManagement(persistenceManager, referenceModelManager, dadlManager, saprm, seqlEngine);
	}
	
	@Test
	public void test() throws Exception{
		II rootArchetypeId=new II();
		rootArchetypeId.setRoot("InformeClinicoNotaSOIP");
		InputStream dadl=DummyINT004SIIESAPRMProxy.class.getResourceAsStream("/org/sigaim/siie/data/dadl/saprm_input/sample_input.dadl");
		ContentObject input=dadlManager.parseDADL(dadl);
		String serialized=dadlManager.serialize(input,true);
		System.out.println("Calling with: "+serialized);
		dadl=saprm.analyzeText(serialized, rootArchetypeId, "", true);
		ContentObject reportCompositionCo=dadlManager.parseDADL(dadl);
		System.out.println(dadlManager.serialize(reportCompositionCo, false));
	}

}
