package org.sigaim.siie.interfaces.archetypes;

import java.util.Set;

import org.sigaim.siie.interfaces.IdentifiedReturnValue;

public class ReturnValueArchetypes extends IdentifiedReturnValue{
	private Set<String> archetypes;
 
	public ReturnValueArchetypes() {
		
	}
	public ReturnValueArchetypes(String requestId, Set<String> archetypes) {
		super(requestId);
		this.archetypes=archetypes;
	}
	public Set<String> getArchetypes() {
		return archetypes;
	}
	public void setArchetypes(Set<String> archetypes) {
		this.archetypes=archetypes;
	}
}
