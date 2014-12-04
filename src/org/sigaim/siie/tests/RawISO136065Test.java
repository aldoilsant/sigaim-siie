package org.sigaim.siie.tests;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.monitor.SEQLMonitor;

public class RawISO136065Test {
	public void printMap(Map<String,Object> map) {
		for(String key : map.keySet()) {
			Object value=map.get(key);
			System.out.println(key+" : "+value.toString());
		}
	}
	@Test
	public void testParse() throws Exception{
		DADLManager dmng=new OpenEHRDADLManager();
		ReflectorReferenceModelManager mng=new ReflectorReferenceModelManager(dmng);
		InputStream is;
		is=new BufferedInputStream(SEQLMonitor.class.getResourceAsStream("/org/sigaim/siie/data/dadl/nota19_tsdate.dadl"));
		ContentObject unbinded=dmng.parseDADL(is);
		List<String> startExclusions=new ArrayList<String>();
		startExclusions.add("/all_compositions[at0000]/content[at0012]/");
		startExclusions.add("/all_compositions/content[at0012]/");
		List<String> endExclusions=new ArrayList<String>();
		endExclusions.add("archetype_id");
		endExclusions.add("meaning");
		endExclusions.add("/reference_model_class_name");
		endExclusions.add("/name");
		Map<String,Object> retMap=mng.createPathMap(unbinded, true,true,startExclusions,endExclusions);
		printMap(retMap);
		System.out.println("Path Count: "+retMap.size());

	}
}
