package org.sigaim.siie.interfaces.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import org.sigaim.siie.interfaces.reportmanagement.sigaim.SigaimIntSIIE004ReportManagement;
import org.sigaim.siie.interfaces.saprm.DummyINT004SIIESAPRMProxy;
import org.sigaim.siie.interfaces.saprm.INT004SIIESAPRMProxy;
import org.sigaim.siie.interfaces.terminologies.IntSIIE003Terminologies;
import org.sigaim.siie.interfaces.terminologies.ReturnValueSynonyms;
import org.sigaim.siie.interfaces.terminologies.sigaim.SIGAIMIntSIIE003Terminologies;
import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.rm.exceptions.CSReason;
import org.sigaim.siie.rm.exceptions.RejectException;
import org.sigaim.siie.seql.engine.SEQLEngine;
import org.sigaim.siie.seql.engine.SEQLPipeEngine;
import org.sigaim.siie.seql.engine.execution.SEQLExecutionMemorySolverStage;
import org.sigaim.siie.seql.engine.preprocessing.SEQLPreprocessingValidateIdentifiedVariablesStage;

public class TerminologiesTests {
	IntSIIE001EQL eqlService;
	IntSIIE004ReportManagement reportManagementService;
	private DADLManager dadlManager;
	private PersistenceManager persistenceManager;
	private ReferenceModelManager referenceModelManager;
	private INT004SIIESAPRMProxy saprm;
	private SEQLEngine seqlEngine;
	private IntSIIE003Terminologies terminologiesService;
	
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
		this.terminologiesService=new SIGAIMIntSIIE003Terminologies("http://sigaim.saprm.cesga.es:8080/SIGAIM-SGM-WS/services/INT003SGMImpl",this.referenceModelManager,this.dadlManager);
	}
	@Test
	public void test() throws Exception {
		List<String> concepts=new ArrayList<String>();
		CDCV concept= new CDCV();
		concept.setCode("394715003");
		concept.setCodeSystemName("SNOMED-CT");
		concept.setCodeSystemVersion("20140430");
		concepts.add(this.dadlManager.serialize(this.referenceModelManager.unbind(concept),false));
		concept= new CDCV();
		concept.setCode("S0000001");
		concept.setCodeSystemName("SIGAIM");
		concept.setCodeSystemVersion("2014");
		concepts.add(this.dadlManager.serialize(this.referenceModelManager.unbind(concept),false));	
		ReturnValueSynonyms ret=this.terminologiesService.requestSynonyms("1",concepts);
		if(ret.getReasonCode()!=null) {
			throw new RejectException("",CSReason.REAS02);
		}
		Map<String,Set<String>> retMap=ret.getSynonyms();
		for(Entry<String,Set<String>> entry : retMap.entrySet()) {
			System.out.println("Concept: "+entry.getKey());
			for(String syn : entry.getValue()) {
				System.out.println("  Synonym: "+syn);
			}
		}
	}

}
