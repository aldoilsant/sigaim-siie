package org.sigaim.siie.seql.engine.exceptions;

import org.sigaim.siie.seql.model.SEQLException;

public class SEQLInternalEngineError extends SEQLException {
	private static final long serialVersionUID = 1L;
	public SEQLInternalEngineError(String message) {
        super(message);
	}
}
