package org.sigaim.siie.interfaces.saprm;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.sigaim.saprm.SAPRM;
import org.sigaim.saprm.exception.UnhandledSAPRMException;
import org.sigaim.siie.interfaces.reportmanagement.sigaim.SigaimIntSIIE004ReportManagement;
import org.sigaim.siie.iso13606.rm.II;

public class DelegatingSigaimIntSIIE004SAPRM implements INT004SIIESAPRMProxy {
	private static org.apache.log4j.Logger log = Logger.getLogger(DelegatingSigaimIntSIIE004SAPRM.class);

	public DelegatingSigaimIntSIIE004SAPRM() {

	}
	@Override
	public InputStream analyzeText(String text, II rootArchetypeId, String updateConcepts) throws UnhandledSAPRMException {
			
		String result=new SAPRM().ANALYZE_TEXT("InformeClinicoNotaSOIP", text);
			if(result!=null) {
				log.debug("SAPRM RETURNS: "+result);
				return new ByteArrayInputStream(result.getBytes());
			} else {
				log.debug("SAPRM RETURNS NULL");
				return null;
			}
	}
}
