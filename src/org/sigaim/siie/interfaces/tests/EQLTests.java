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
import org.sigaim.siie.seql.model.SEQLException;
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
		this.dadlManager = new OpenEHRDADLManager();
		this.referenceModelManager = new ReflectorReferenceModelManager(
				this.dadlManager);
		SQLPersistenceManager sqlManager = new SQLPersistenceManager();
		sqlManager.setDADLManager(this.dadlManager);
		sqlManager.setReferenceModelManager(this.referenceModelManager);
		this.persistenceManager = sqlManager;
		SEQLExecutionMemorySolverStage stage = new SEQLExecutionMemorySolverStage(
				persistenceManager, referenceModelManager, dadlManager);
		SEQLPipeEngine engine = new SEQLPipeEngine();
		engine.addPreprocessStage(new SEQLPreprocessingValidateIdentifiedVariablesStage());
		engine.addExecutionStage(stage);
		this.seqlEngine = engine;
		this.eqlService = new SigaimIntSIIE001EQL(engine, dadlManager);
	}

	protected SEQLResultSet createResultSet(String input) throws SEQLException {
		SEQLResultSet rs = new SEQLResultSet();
		ContentObject serializedResultSet = this.dadlManager
				.parseDADL(new ByteArrayInputStream(input.getBytes()));
		// The first multipleAttributeObjectBlock are the rows.
		if (serializedResultSet.getComplexObjectBlock() instanceof SingleAttributeObjectBlock) {
			// Empty result set
			rs.setNumberOfColumns(0);
			rs.compile();
			return rs;
		}
		MultipleAttributeObjectBlock rowsBlock = (MultipleAttributeObjectBlock) serializedResultSet
				.getComplexObjectBlock();
		for (KeyedObject row : rowsBlock.getKeyObjects()) {
			rs.addRow();
			// Each column is, itself, a multipleAttributeobjectblock
			MultipleAttributeObjectBlock columnBlock = (MultipleAttributeObjectBlock) row
					.getObject();
			int columns = 0;
			for (KeyedObject column : columnBlock.getKeyObjects()) {
				rs.appendToRow(new ContentObject(null,
						(ComplexObjectBlock) column.getObject()));
				columns++;
			}
			rs.setNumberOfColumns(columns);
		}
		rs.compile();
		return rs;
	}

	@Test
	public void testSEQLService() throws Exception {
		// All queries for the client
		String[] queries = {
		 "SELECT c/rc_id/extension FROM EHR e CONTAINS COMPOSITION c WHERE EXISTS c/rc_id/extension;",
		 "SELECT e/all_healthcare_facilities FROM EHR SYSTEM e;",
		 "SELECT e/all_performers FROM EHR SYSTEM e;",
		 "SELECT e/all_subjects_of_care FROM EHR SYSTEM e;",
		 "SELECT e/items[at0008] WITH DESCENDANTS FROM EHR CONTAINS COMPOSITION c CONTAINS ENTRY e[CEN-EN13606-ENTRY.Informacion.v1] WHERE c/rc_id/extension=\"6\";",
		 "SELECT e,  c, c/committal, c/composer FROM EHR e CONTAINS COMPOSITION c[CEN-EN13606-COMPOSITION.InformeClinicoNotaSOIP.v1];",
		 "SELECT p/identifier FROM EHR SYSTEM e CONTAINS PERFORMER p WHERE p/identifier/extension=\"6\";",
		 "SELECT  r/items[at0002]/parts[at0003], r/items[at0002]/parts[at0004], r/items[at0002]/parts[at0005], r/items[at0002]/parts[at0006] FROM EHR e CONTAINS COMPOSITION c[CEN-EN13606-COMPOSITION.InformeClinicoNotaSOIP.v1] CONTAINS ENTRY r[CEN-EN13606-ENTRY.Informacion.v1] WHERE c/rc_id/extension=\"6\";",
		 "SELECT e/items[at0008]/parts[at0009] FROM EHR CONTAINS COMPOSITION c CONTAINS ENTRY e[CEN-EN13606-ENTRY.Informacion.v1] WHERE c/rc_id/extension=\"6\";",
		 "SELECT e,  c, c/committal, c/composer FROM EHR e CONTAINS ALL VERSIONS OF COMPOSITION c[CEN-EN13606-COMPOSITION.InformeClinicoNotaSOIP.v1] WHERE c/committal/version_set_id/extension=\"6\";",

		};
		for (String query : queries) {
			// query=
			// "SELECT e/items[at0008] WITH DESCENDANTS FROM EHR CONTAINS COMPOSITION c CONTAINS ENTRY e[CEN-EN13606-ENTRY.Informacion.v1] WHERE c/rc_id/extension=\"479526\";";
			System.out.println("Running query: " + query);
			long start = System.currentTimeMillis();
			ReturnValueEQL result = this.eqlService.query("1", query);
			long end = System.currentTimeMillis();
			System.out.println("Time taken: " + (end - start));
			String sres = result.getSerialized();
			SEQLResultSet res = this.createResultSet(sres);
			System.out.println("Number of rows: " + res.getNumberOfRows());
			//System.out.println("Result: " + sres);
			// System.out.println("Rows: "+result.getSerialized().)
			assert (result != null);
		}

	}

}
