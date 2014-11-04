package org.sigaim.siie.interfaces.terminologies.sigaim;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.axis2.AxisFault;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.interfaces.terminologies.IntSIIE003Terminologies;
import org.sigaim.siie.interfaces.terminologies.ReturnValueConcepts;
import org.sigaim.siie.interfaces.terminologies.ReturnValueSynonyms;
import org.sigaim.siie.interfaces.terminologies.ReturnValueTerminologies;
import org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub;
import org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.Concept;
import org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws.INT003SGMImplStub.ConceptResult;
import org.sigaim.siie.iso13606.rm.CD;
import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.iso13606.rm.ST;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.exceptions.CSReason;
import org.sigaim.siie.rm.exceptions.RejectException;

public class SIGAIMIntSIIE003Terminologies implements IntSIIE003Terminologies {
	private ReferenceModelManager rmngr;
	private DADLManager dmngr;
	private String sgm_endpoint;
	private INT003SGMImplStub proxy;

	public SIGAIMIntSIIE003Terminologies(String endpoint, ReferenceModelManager rmngr, DADLManager dmngr) throws AxisFault {
		this.sgm_endpoint=endpoint;
		this.rmngr=rmngr;
		this.dmngr=dmngr;
		this.proxy=new INT003SGMImplStub(endpoint);
	}
	@Override
	public ReturnValueTerminologies requestTerminologies(String requestId)
			throws RejectException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnValueConcepts requestConcepts(String requestId,
			Set<CDCV> conceptIds, Set<CD> terminologiIds)
			throws RejectException {
		// TODO Auto-generated method stub
		return null;
	}
	protected CDCV codedValueFromConcept(Concept concept) {
		CDCV cv=new CDCV();
		cv.setCode(concept.getCodigo());
		cv.setCodeSystemVersion(concept.getIdTerminologyVersion());
		cv.setCodeSystemName(concept.getTerminology());
		ST displayName= new ST();
		displayName.setValue(concept.getDescripcion());
		cv.setDisplayName(displayName);
		return cv;
	}
	//Concepts passed as CDVC so we have all the necessary info
	@Override
	public ReturnValueSynonyms requestSynonyms(String requestId,
			List<String> conceptIds)
			throws RejectException {
		INT003SGMImplStub.REQUEST_SYNONYMS req=new INT003SGMImplStub.REQUEST_SYNONYMS();

		//Note that we are receiving a set of serialized concepts
		//We unserialize the CDCV values and use the correct stuff
		try {
			List<String> conceptCodes=new ArrayList<String>();
			List<String> terminologyCodes=new ArrayList<String>();
			List<String> versionCodes=new ArrayList<String>();
			for(String serializedCode : conceptIds) {
				CDCV cv=(CDCV)this.rmngr.bind(this.dmngr.parseDADL(new ByteArrayInputStream(serializedCode.getBytes())));
				conceptCodes.add(cv.getCode());
				terminologyCodes.add(cv.getCodeSystemName());
				versionCodes.add(cv.getCodeSystemVersion());
			}
			req.setConcept_codes(conceptCodes.toArray(new String[conceptCodes.size()]));
			req.setTerminology_names(terminologyCodes.toArray(new String[terminologyCodes.size()]));
			req.setTerminology_version_names(versionCodes.toArray(new String[versionCodes.size()]));
			INT003SGMImplStub.REQUEST_SYNONYMSResponse resp=proxy.rEQUEST_SYNONYMS(req);
			ConceptResult[] result=resp.get_return();
			//Call the SMG
			//Translate back into the expected norm format
			HashMap<String,Set<String>> retMap=new HashMap<String,Set<String>>();
			int i,j;
			for(ConceptResult entry : result) {
				HashSet<String> seralizedSynonyms= new HashSet<String>();
				for(Concept syn : entry.getLista()) {
					seralizedSynonyms.add(this.dmngr.serialize(this.rmngr.unbind(this.codedValueFromConcept(syn)),true));
				}
				retMap.put(this.dmngr.serialize(this.rmngr.unbind(this.codedValueFromConcept(entry.getConcepto())),true), seralizedSynonyms);
			}
			return new ReturnValueSynonyms(requestId,retMap);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RejectException(requestId,CSReason.REAS02);
		}
	}


}
