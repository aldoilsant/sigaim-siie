package org.sigaim.siie.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sigaim.sgm.GlossaryQuery;
import org.sigaim.sgm.glosario.bo.Concept;
import org.sigaim.siie.utils.pool.SingleConnectionDataSource;
import org.sigaim.utils.accesoBD.AccesoSIGAIM;


public class TestSGM {
	GlossaryQuery q;
	List<String> archetype_ids;

	@Before
	public void setUp() throws Exception {
		SingleConnectionDataSource basicDataSource= new SingleConnectionDataSource();
		basicDataSource.setUser("root");
		basicDataSource.setPassword("root");
		basicDataSource.setServerName("localhost");
		basicDataSource.setPort(8889);
		basicDataSource.setDatabaseName("saprm");
		AccesoSIGAIM.setDataSource(basicDataSource);
		q = new GlossaryQuery();
		archetype_ids = new ArrayList<String>();
		archetype_ids.add("CEN-EN13606-CLUSTER.Sintoma.v1");
		archetype_ids.add("CEN-EN13606-COMPOSITION.InformeClinicoNotaSOIP.v1");
	}
	
	@Test
	public void test() {
		System.out.println(" -- REQUEST_SYNONYMS -- ");
		List<String> conceptos = new ArrayList<String>();
		conceptos.add("394715003");
		conceptos.add("S0000001");
		List<String> terms = new ArrayList<String>();
		terms.add("SNOMED-CT");
		terms.add("SIGAIM");
		HashMap<Concept,List<Concept>> res = q.REQUEST_SYNONYMS(conceptos, terms);
		System.out.println("res:" + res.size());
		for (Entry<Concept,List<Concept>> e:res.entrySet() ) {
			Concept c = e.getKey(); 
			System.out.println("Concepto " + c.getCodigo() + " " + c.getTerminology() + " " + c.getDescripcion());
			for (Concept c2:e.getValue()) {
				System.out.println("  SY: " + c2.getCodigo() + " " + c2.getTerminology() + " " + c2.getDescripcion());				
			}
		}
	}

}
