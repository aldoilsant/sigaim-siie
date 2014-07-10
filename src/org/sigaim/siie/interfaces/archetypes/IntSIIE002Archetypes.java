package org.sigaim.siie.interfaces.archetypes;

import java.util.Set;

import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.iso13606.rm.II;
import org.sigaim.siie.rm.exceptions.RejectException;

public interface IntSIIE002Archetypes {
	public ReturnValueArchetypes requestArchetypes(
			String requestId,
			Set<II> archetypeIds,
			CDCV concept,
			II specializations,
			II parentOf,
			String terminologyAvailable,
			String languageAvailable) throws RejectException;
			
}
