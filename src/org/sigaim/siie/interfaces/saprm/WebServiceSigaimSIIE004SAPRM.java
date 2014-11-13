package org.sigaim.siie.interfaces.saprm;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.axis2.AxisFault;
import org.sigaim.siie.interfaces.saprm.ws.INT004SIIESAPRMImplStub;
import org.sigaim.siie.iso13606.rm.II;



public class WebServiceSigaimSIIE004SAPRM implements INT004SIIESAPRMProxy{
	private String saprm_endpoint;
	private INT004SIIESAPRMImplStub proxy;

	public WebServiceSigaimSIIE004SAPRM(String endpoint) throws AxisFault{
		saprm_endpoint=endpoint;
		this.proxy=new INT004SIIESAPRMImplStub(saprm_endpoint);
	}
	
	
	@Override
	public InputStream analyzeText(String text, II rootArchetypeId,
			String updateConcepts, boolean execute) throws UnhandledSAPRMException {

		INT004SIIESAPRMImplStub.AnalyzeText atext= new INT004SIIESAPRMImplStub.AnalyzeText();
		atext.setAe("base");
		atext.setExecute(execute);
		atext.setInfo(updateConcepts);
		atext.setText(text);
		atext.setType("InformeClinicoNotaSOIP");

		try {
			INT004SIIESAPRMImplStub.AnalyzeTextResponse resp= proxy.analyzeText(atext);
			System.out.println(resp.get_return());
			return new ByteArrayInputStream(resp.get_return().getBytes());
		} catch(Exception e) {
			e.printStackTrace();
			throw new UnhandledSAPRMException(e.getMessage());
		}
	}

}
