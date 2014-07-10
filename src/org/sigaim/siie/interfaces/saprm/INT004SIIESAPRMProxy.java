package org.sigaim.siie.interfaces.saprm;

import java.io.InputStream;

import org.sigaim.siie.iso13606.rm.II;

public interface INT004SIIESAPRMProxy {
	InputStream analyzeText(String text, II rootArchetypeId);
}
