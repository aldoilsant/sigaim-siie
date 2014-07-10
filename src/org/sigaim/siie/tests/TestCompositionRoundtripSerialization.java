package org.sigaim.siie.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.iso13606.rm.Composition;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.monitor.SEQLMonitor;

public class TestCompositionRoundtripSerialization {

	@Test
	public void test() throws Exception{
		DADLManager dmng=new OpenEHRDADLManager();
		ReflectorReferenceModelManager mng=new ReflectorReferenceModelManager(dmng);
		InputStream is=SEQLMonitor.class.getResourceAsStream("/org/sigaim/siie/data/dadl/nota19_007.dadl");
		ContentObject unbinded=dmng.parseDADL(is);
		String reserialized=dmng.serialize(unbinded, false);
		unbinded=dmng.parseDADL(new ByteArrayInputStream(reserialized.getBytes()));
		Composition comp=(Composition)mng.bind(unbinded);
	}

}
