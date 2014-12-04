package org.sigaim.siie.tests;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import org.junit.Test;
import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.iso13606.rm.Composition;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.monitor.SEQLMonitor;

public class TestCompositionRoundtripSerialization {

	public long millisecondsFromInterval(long start, long end) {
		return (end-start)/(1000*1000);
	}
	String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	public void printMap(Map<String,String> map) {
		for(String key : map.keySet()) {
			String value=map.get(key);
			System.out.println(key+" = "+value);
		}
	}
	@Test
	public void testParse() throws Exception{
		long start;
		long end;
		DADLManager dmng=new OpenEHRDADLManager();
//		dmng.parseDADL(null);
		ReflectorReferenceModelManager mng=new ReflectorReferenceModelManager(dmng);
		InputStream is;
		is=new BufferedInputStream(SEQLMonitor.class.getResourceAsStream("/org/sigaim/siie/data/dadl/nota19_013_packed.dadl"));
		start=System.nanoTime();
		ContentObject unbinded=dmng.parseDADL(is);
		end=System.nanoTime();
		System.out.println("Parse dadl time: "+this.millisecondsFromInterval(start, end));
		Map<String,Object> pathMap=mng.createPathMap(unbinded, true,true,null,null);
		//this.printMap(pathMap);
		is=new BufferedInputStream(SEQLMonitor.class.getResourceAsStream("/org/sigaim/siie/data/dadl/nota19_013_packed.dadl"));
		is=new ByteArrayInputStream(this.convertStreamToString(is).getBytes());
		start=System.nanoTime();
		unbinded=dmng.parseDADL(is);
		end=System.nanoTime();
		System.out.println("Parse dadl time (2): "+this.millisecondsFromInterval(start, end));
		start=System.nanoTime();
		String reserialized=dmng.serialize(unbinded, true);
		end=System.nanoTime();
		System.out.println("Serialize dadl time: "+this.millisecondsFromInterval(start, end));
		unbinded=dmng.parseDADL(new ByteArrayInputStream(reserialized.getBytes()));
		start=System.nanoTime();
		Composition comp=(Composition)mng.bind(unbinded);
		end=System.nanoTime();
		System.out.println("Bind dadl time: "+this.millisecondsFromInterval(start, end));
		start=System.nanoTime();
		unbinded=mng.unbind(comp);
		end=System.nanoTime();
		System.out.println("Unbind dadl time: "+this.millisecondsFromInterval(start, end));
		reserialized=dmng.serialize(unbinded,true);
		start=System.nanoTime();
		unbinded=dmng.parseDADL(new ByteArrayInputStream(reserialized.getBytes()));
		end=System.nanoTime();
		System.out.println("Parse dadl time (3): "+this.millisecondsFromInterval(start, end));
		comp=(Composition)mng.bind(unbinded);
		//PathMap tests
	}

}
