package org.sigaim.siie.interfaces.terminologies.sigaim;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.sigaim.sgm.GlossaryQuery;
import org.sigaim.sgm.glosario.bo.Concept;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.interfaces.terminologies.IntSIIE003Terminologies;
import org.sigaim.siie.interfaces.terminologies.ReturnValueConcepts;
import org.sigaim.siie.interfaces.terminologies.ReturnValueSynonyms;
import org.sigaim.siie.interfaces.terminologies.ReturnValueTerminologies;
import org.sigaim.siie.iso13606.rm.CD;
import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.iso13606.rm.ST;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.exceptions.CSReason;
import org.sigaim.siie.rm.exceptions.RejectException;

public class SIGAIMIntSIIE003Terminologies implements IntSIIE003Terminologies {
	private ReferenceModelManager rmngr;
	private DADLManager dmngr;
	private GlossaryQuery query;
	
	public SIGAIMIntSIIE003Terminologies(GlossaryQuery query, ReferenceModelManager rmngr, DADLManager dmngr) {
		this.query=query;
		this.rmngr=rmngr;
		this.dmngr=dmngr;
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
		cv.setCodeSystemVersion(concept.getIdVersionTerminology());
		cv.setCodeSystemName(concept.getTerminology());
		ST displayName= new ST();
		displayName.setValue(concept.getDescripcion());
		cv.setDisplayName(displayName);
		return cv;
	}
	@Override
	public ReturnValueSynonyms requestSynonyms(String requestId,
			List<String> conceptIds)
			throws RejectException {
		//Note that we are receiving a set of serialized concepts
		//We unserialize the CDCV values and use the correct stuff
		try {
			List<String> conceptCodes=new ArrayList<String>();
			List<String> terminologyCodes=new ArrayList<String>();
			for(String serializedCode : conceptIds) {
				CDCV cv=(CDCV)this.rmngr.bind(this.dmngr.parseDADL(new ByteArrayInputStream(serializedCode.getBytes())));
				conceptCodes.add(cv.getCode());
				terminologyCodes.add(cv.getCodeSystemName());
			}
			//Call the SMG
			HashMap<Concept,List<Concept>> res = query.REQUEST_SYNONYMS(conceptCodes, terminologyCodes);
			//Translate back into the expected norm format
			HashMap<String,Set<String>> retMap=new HashMap<String,Set<String>>();
			int i,j;
			for(Entry<Concept,List<Concept>> entry : res.entrySet()) {
				HashSet<String> seralizedSynonyms= new HashSet<String>();
				for(Concept syn : entry.getValue()) {
					seralizedSynonyms.add(this.dmngr.serialize(this.rmngr.unbind(this.codedValueFromConcept(syn)),false));
				}
				retMap.put(this.dmngr.serialize(this.rmngr.unbind(this.codedValueFromConcept(entry.getKey())),false), seralizedSynonyms);
			}
			return new ReturnValueSynonyms(requestId,retMap);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RejectException(requestId,CSReason.REAS02);
		}
	}


}
