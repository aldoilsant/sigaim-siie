package org.sigaim.siie.seql.monitor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.DADLParser;
import org.openehr.am.parser.ObjectBlock;
import org.openehr.am.parser.ParseException;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.archetypes.FileArchetypeManager;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.db.sql.SQLPersistenceManager;
import org.sigaim.siie.iso13606.rm.BL;
import org.sigaim.siie.iso13606.rm.Composition;
import org.sigaim.siie.iso13606.rm.EHRExtract;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.engine.SEQLPipeEngine;
import org.sigaim.siie.seql.engine.execution.SEQLExecutionMemorySolverStage;
import org.sigaim.siie.seql.engine.preprocessing.SEQLPreprocessingValidateIdentifiedVariablesStage;
import org.sigaim.siie.seql.model.SEQLException;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLQuery;
import org.sigaim.siie.seql.model.SEQLResultSet;
import org.sigaim.siie.seql.parser.SEQLErrorListener;
import org.sigaim.siie.seql.parser.SEQLModelListener;
import org.sigaim.siie.seql.parser.generated.SEQLLexer;
import org.sigaim.siie.seql.parser.generated.SEQLParser;


/* SENSIBLE QUERIES
 * RETRIEVE ALL SYMPTOMS FOR EACH SUBJECT OF CARE BY NAME
 * SELECT h/ehr_id/extension, e/value/display_name/value FROM EHR h CONTAINS ELEMENT e[CEN-EN13606-CLUSTER.Sintoma.v1] WHERE EXISTS e/value/display_name/value;
 */

public class SEQLMonitor {

	public static void main(String[] args) throws Exception {
		boolean done=false;
		System.out.println("Welcome to SEQLMON 0.1");
		DADLManager dmng=new OpenEHRDADLManager();
		ReflectorReferenceModelManager mng=new ReflectorReferenceModelManager(dmng);
		InputStream is;
		SQLPersistenceManager pmngr=new SQLPersistenceManager();
		pmngr.setDADLManager(dmng);
		pmngr.setReferenceModelManager(mng);
		SEQLExecutionMemorySolverStage stage=new SEQLExecutionMemorySolverStage(pmngr,mng,dmng);
		SEQLPipeEngine engine=new SEQLPipeEngine();
		engine.addPreprocessStage(new SEQLPreprocessingValidateIdentifiedVariablesStage());
		engine.addExecutionStage(stage);
		
	    System.out.println();
	    System.out.println();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	   
		String command="";
		while(!done) {
			System.out.print("SEQLMON> ");
			command+=input.readLine();
			if(command.equals("exit;")) {
				System.out.println("Bye");
				pmngr.stop();
				done=true;
			} else if(command.endsWith(";")){
				is=new ByteArrayInputStream(command.getBytes(StandardCharsets.UTF_8));
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
			    try {
			    	parser.query();
			    } catch(Exception e) {
			    	e.printStackTrace();
			    	areErrors=true;
			    }
			    areErrors |= errorListener.hasErrors();
			    if(areErrors) {
			    	System.err.println("INVALID QUERY");
			    } else {
			    	SEQLQuery query=listener.getQuery();
			    	System.out.println("Sintactically valid query accepted:"+query.toString());
			    	try {
			    		SEQLResultSet rs=engine.runQuery(query);
			    		if(rs!=null) {
			    			int nrow=0;
							while(rs.nextRow()) {
								for(int i=0;i<rs.getNumberOfColumns();i++) {
									ContentObject cellObject=rs.getColumn(i);
									System.out.println("Row "+(nrow)+", Column "+i+": "+dmng.serialize(cellObject, false));
								}
								nrow++;
							}
			    		}
			    	} catch(SEQLException e) {
			    		System.out.println("ERROR: "+e.getMessage());
			    	}
			    }
			    command="";
			} else {
				//Just move on
			}
		}
	}
}
