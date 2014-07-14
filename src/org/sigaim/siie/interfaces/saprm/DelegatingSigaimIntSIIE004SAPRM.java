package org.sigaim.siie.interfaces.saprm;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.sigaim.siie.iso13606.rm.II;

import com.coremain.sigaim.saprm.SAPRM;

public class DelegatingSigaimIntSIIE004SAPRM implements INT004SIIESAPRMProxy {
	
	public DelegatingSigaimIntSIIE004SAPRM() {

	}
	@Override
	public InputStream analyzeText(String text, II rootArchetypeId) {
		String result=new SAPRM().ANALYZE_TEXT("InformeClinicoNotaSOIP", text);
		if(result!=null) {
			return new ByteArrayInputStream(result.getBytes());
		} else return null;
		
	}
}
