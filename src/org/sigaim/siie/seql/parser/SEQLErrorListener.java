package org.sigaim.siie.seql.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class SEQLErrorListener extends BaseErrorListener {
	boolean errors=false;
	
	@Override public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
		errors=true;
	}
	public boolean hasErrors() {
		return errors;
	}
}
