package org.sigaim.siie.interfaces.eql.sigaim;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.log4j.Logger;
import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.interfaces.eql.IntSIIE001EQL;
import org.sigaim.siie.interfaces.eql.ReturnValueEQL;
import org.sigaim.siie.rm.exceptions.CSReason;
import org.sigaim.siie.rm.exceptions.RejectException;
import org.sigaim.siie.seql.engine.SEQLEngine;
import org.sigaim.siie.seql.model.SEQLException;
import org.sigaim.siie.seql.model.SEQLQuery;
import org.sigaim.siie.seql.model.SEQLResultSet;
import org.sigaim.siie.seql.parser.SEQLErrorListener;
import org.sigaim.siie.seql.parser.SEQLModelListener;
import org.sigaim.siie.seql.parser.generated.SEQLLexer;
import org.sigaim.siie.seql.parser.generated.SEQLParser;

@WebService
public class SigaimIntSIIE001EQL implements IntSIIE001EQL {
	private static org.apache.log4j.Logger log = Logger.getLogger(SigaimIntSIIE001EQL.class);
	private SEQLEngine engine;
	private DADLManager dadlManager;
	
	public SigaimIntSIIE001EQL(SEQLEngine engine, DADLManager dadlManager) {
		this.engine=engine;
		this.dadlManager=dadlManager;
	}
	
	@Override @WebMethod
	public ReturnValueEQL query(String requestId, String eqlQuery)
			throws RejectException {
		try {
			//Prepare the query parser
			InputStream is=new ByteArrayInputStream(eqlQuery.getBytes(StandardCharsets.UTF_8));
			CharStream in = new ANTLRInputStream(is);
		    SEQLLexer lexer = new SEQLLexer(in);
		    CommonTokenStream tokens = new CommonTokenStream(lexer);
		    SEQLParser parser=new SEQLParser(tokens);
		    SEQLModelListener listener=new SEQLModelListener();
		    SEQLErrorListener errorListener=new SEQLErrorListener();
		    parser.setBuildParseTree(true);
		    parser.addParseListener(listener);
		   // parser.removeErrorListeners();
		    parser.addErrorListener(errorListener);
		    boolean areErrors=false;
		    parser.query();
		    areErrors |= errorListener.hasErrors();
		    if(areErrors) {
				throw new RejectException(requestId,CSReason.REAS04);
		    } else {
		    	SEQLQuery query=listener.getQuery();
		    	log.info("Sintactically valid query accepted:"+query.toString());
		    	SEQLResultSet rs=engine.runQuery(query);
		    	if(rs==null) throw new RejectException(requestId,CSReason.REAS04);
		    	else {
		    		int nrow=0;
		    		String result="<";
		    		while(rs.nextRow()) {
		    			int i;
		    			//Create the DADL
		    			result+="["+nrow+"]=<";
		    			for(i=0;i<rs.getNumberOfColumns();i++) {
		    				result+="["+i+"]="+this.dadlManager.serialize(rs.getColumn(i),false);
		    			}
		    			result+=">";
		    			nrow++;
		    		}
		    		result+=">";
			    	return new ReturnValueEQL(requestId,result);
		    	}
		    }
		}
		catch(RejectException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RejectException(requestId,CSReason.REAS02);
		}
	}

}