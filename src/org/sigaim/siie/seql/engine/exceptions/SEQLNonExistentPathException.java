package org.sigaim.siie.seql.engine.exceptions;

public class SEQLNonExistentPathException extends SEQLSemanticErrorException{
	private static final long serialVersionUID = 1L;
	
	public SEQLNonExistentPathException(String message) {
        super(message);
	}
}
