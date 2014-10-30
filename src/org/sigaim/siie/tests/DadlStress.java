package org.sigaim.siie.tests;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.monitor.SEQLMonitor;

public class DadlStress {
	public long millisecondsFromInterval(long start, long end) {
		return (end-start)/(1000*1000);
	}
	@Test
	public void test() {
		DADLManager dmng=new OpenEHRDADLManager();
//		dmng.parseDADL(null);
		ReflectorReferenceModelManager mng=new ReflectorReferenceModelManager(dmng);
		InputStream is;
		is=new BufferedInputStream(SEQLMonitor.class.getResourceAsStream("/org/sigaim/siie/data/dadl/big_dadl.dadl"));
		long start=System.nanoTime();
		ContentObject unbinded=dmng.parseDADL(is);
		long end=System.nanoTime();
		System.out.println("Parse dadl time: "+this.millisecondsFromInterval(start, end));
		String res=dmng.serialize(unbinded,true);
		end=System.nanoTime();
		System.out.println("Serialize dadl time: "+this.millisecondsFromInterval(start, end));
		System.out.println(res);

	}

}
