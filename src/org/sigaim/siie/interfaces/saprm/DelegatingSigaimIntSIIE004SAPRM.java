package org.sigaim.siie.interfaces.saprm;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.sigaim.siie.iso13606.rm.II;

import com.coremain.sigaim.saprm.SAPRM;

public class DelegatingSigaimIntSIIE004SAPRM implements INT004SIIESAPRMProxy {
	private SAPRM saprm;
	private static org.apache.log4j.Logger log = Logger
			.getLogger(DelegatingSigaimIntSIIE004SAPRM.class);
	
	public DelegatingSigaimIntSIIE004SAPRM() {
		saprm=new SAPRM();

	}
	@Override
	public InputStream analyzeText(String text, II rootArchetypeId) {
		String result=saprm.ANALYZE_TEXT("InformeClinicoNotaSOIP", text);
		log.debug("SAPRM returns: "+result);
		return new ByteArrayInputStream(result.getBytes());
		
	}
}
