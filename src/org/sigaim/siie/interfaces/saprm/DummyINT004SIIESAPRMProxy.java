package org.sigaim.siie.interfaces.saprm;

import java.io.InputStream;

import org.sigaim.siie.iso13606.rm.II;
import org.sigaim.siie.seql.monitor.SEQLMonitor;

public class DummyINT004SIIESAPRMProxy implements INT004SIIESAPRMProxy{

	@Override
	public InputStream analyzeText(String text, II rootArchetypeId) {
		InputStream is=DummyINT004SIIESAPRMProxy.class.getResourceAsStream("/org/sigaim/siie/data/dadl/nota19_013.dadl");
	    return is;
	    /*
	    //Stupid scanner trick
		java.util.Scanner s = new java.util.Scanner(is);
	    s.useDelimiter("\\A");
	    String result= s.hasNext() ? s.next() : "";
	    s.close();
	    try {
	    	is.close();
	    } catch (Exception e) {}
	    return result;*/
	}

}
