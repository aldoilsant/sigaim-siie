package org.sigaim.siie.interfaces.terminologies;

import java.util.Set;

import org.sigaim.siie.interfaces.IdentifiedReturnValue;
import org.sigaim.siie.iso13606.rm.CDCV;

public class ReturnValueSynonyms extends IdentifiedReturnValue {
	private Set<CDCV> synonyms;
	
	public ReturnValueSynonyms() {
		
	}
	public ReturnValueSynonyms(String requestId, Set<CDCV> synonyms) {
		super(requestId);
		this.synonyms=synonyms;
	}
	public Set<CDCV> getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(Set<CDCV> synonyms) {
		this.synonyms = synonyms;
	}
}
