package org.sigaim.siie.seql.monitor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.DADLParser;
import org.openehr.am.parser.ParseException;
import org.sigaim.siie.archetypes.FileArchetypeManager;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.db.sql.SQLPersistenceManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.parser.SEQLErrorListener;
import org.sigaim.siie.seql.parser.SEQLModelListener;
import org.sigaim.siie.seql.parser.generated.SEQLLexer;
import org.sigaim.siie.seql.parser.generated.SEQLParser;
import org.sigaim.siie.seql.parser.model.SEQLPath;
import org.sigaim.siie.seql.parser.model.SEQLQuery;


public class SEQLMonitor {

	public static void main(String[] args) throws Exception {
		boolean done=false;
		System.out.println("Welcome to SEQLMON 0.1");
		DADLManager dmng=new OpenEHRDADLManager();
		ReflectorReferenceModelManager mng=new ReflectorReferenceModelManager(dmng);
		Class<?> type=mng.getPathType("composition", new SEQLPath("c/content/items/structure_type"));
		InputStream is;
		FileArchetypeManager mgr=new FileArchetypeManager();
		Archetype arq=mgr.getArquetypeById("CEN-EN13606-COMPOSITION.InformeClinicoNotaSOIP.v1");

		is=SEQLMonitor.class.getResourceAsStream("/org/sigaim/siie/data/dadl/ehr_001.dadl");
		ContentObject unbinded=dmng.parseDADL(is);
		SQLPersistenceManager pmngr=new SQLPersistenceManager();
		pmngr.setDADLManager(dmng);
		pmngr.setReferenceModelManager(mng);
		System.out.print("Starting persistence manager...");
		pmngr.start();
		System.out.println("Done");
		System.out.print("Ressetting database....");
		pmngr.reset();
		System.out.println("Done");
		
		pmngr.saveReferenceModelObjectFromContentObject(unbinded);
		/*String serialized=dmng.serialize(dmng.parseDADL(is),false);
		serialized=dmng.serialize(dmng.parseDADL(new ByteArrayInputStream(serialized.getBytes())),false);
		System.out.println(serialized);
		ContentObject obj=dmng.parseDADL(new ByteArrayInputStream(serialized.getBytes()));
		Object binded=mng.bind(obj);
		obj=mng.unbind(binded);
		System.out.println(dmng.serialize(obj,false));*/
		//DADLParser dadlParser=new DADLParser(is);
		//ContentObject obj=dadlParser.parse();
		//String serialized=obj.toString();
		//System.out.println(serialized);
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
			    }
			    command="";
			} else {
				//Just move on
			}
		}
	}
}
