package org.sigaim.siie.seql.engine.exceptions;

import org.sigaim.siie.seql.model.SEQLException;

public class SEQLSemanticErrorException extends SEQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SEQLSemanticErrorException(String message) {
        super(message);
	}
}
