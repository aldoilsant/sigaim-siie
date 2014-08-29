package org.sigaim.siie.interfaces.terminologies;

 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.sigaim.siie.interfaces.IdentifiedReturnValue;

public class ReturnValueSynonyms extends IdentifiedReturnValue {
	private  Map<String,Set<String>> synonyms;
	
	public ReturnValueSynonyms() {
		
	}
	public ReturnValueSynonyms(String requestId,  Map<String,Set<String>>  synonyms) {
		super(requestId);
		this.synonyms=synonyms;
	}
	public Map<String,Set<String>> getSynonyms( ) {
		return synonyms;
	}
	public void setSynonyms(Map<String,Set<String>> synonyms) {
		this.synonyms = synonyms;
	}
}
