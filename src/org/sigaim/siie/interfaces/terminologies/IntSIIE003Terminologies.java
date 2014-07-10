package org.sigaim.siie.interfaces.terminologies;

import java.util.Set;

import org.sigaim.siie.iso13606.rm.CD;
import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.rm.exceptions.RejectException;

public interface IntSIIE003Terminologies {

	public ReturnValueTerminologies requestTerminologies(String requestId) throws RejectException;
	public ReturnValueConcepts requestConcepts(
			String requestId,
			Set<CDCV> conceptIds,
			Set<CD> terminologiIds
			) throws RejectException;
	public ReturnValueSynonyms requestSynonyms(
			String requestId,
			CDCV conceptIds,
			Set<CD> terminologiIds
			) throws RejectException;
}
