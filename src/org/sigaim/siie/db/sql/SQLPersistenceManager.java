package org.sigaim.siie.db.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.exceptions.SemanticDADLException;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.ReferenceModelObjectId;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.seql.engine.exceptions.SEQLException;
import org.sigaim.siie.seql.parser.model.SEQLPath;
import org.sigaim.siie.seql.parser.model.SEQLPathComponent;

public class SQLPersistenceManager implements PersistenceManager {
	private String dbName="sigaim";
	private String jdbcConnection;
	private String user;
	private String pass;
	private SQLWrapper wrapper;
	private ReferenceModelManager referenceModelManager;
	private DADLManager dadlManager;
	
	public SQLPersistenceManager () {
		jdbcConnection="jdbc:hsqldb:"+ dbName;
		this.user="sa";
		this.pass="";
	}
	@Override
	public ReferenceModelObjectId saveReferenceModelObject(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object solvePathFromObjectId(ReferenceModelObjectId oid) {
		return null;
	}
	protected ResultSet doQuery(String query) throws PersistenceException{
		try {
			return wrapper.query(query);
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	protected void doUpdate(String query) throws PersistenceException {
		try {
			wrapper.update(query);
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	protected ResultSet doUpdateWithGeneratedKeys(String query) throws PersistenceException {
		try {
			return wrapper.updateWithGeneratedKeys(query);
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	public ReferenceModelObjectId saveSingleAttributeObjectBlockForParent(SingleAttributeObjectBlock block, ReferenceModelObjectId parent) throws PersistenceException, SemanticDADLException, ReferenceModelException{
		if(!(parent instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException("Incompatible reference model object id: "+parent);
		} else{
			SQLReferenceModelObjectId sparent=(SQLReferenceModelObjectId) parent;
			SEQLPath path=sparent.getUniqueIdPath();
			String objectClass =this.referenceModelManager.getReferenceModelClassName(block);
			Map<SEQLPathComponent,SingleAttributeObjectBlock> childRMObjects=this.referenceModelManager.splitForRMObjectVsDataObject(block);
			//Now we can insert our object, child objects have been removed
			String serializedObject=this.dadlManager.serialize(block);
			String query="INSERT INTO reference_model_objects VALUES (NULL,'"+objectClass+"',NULL,NULL,NULL,NULL,\'"+serializedObject+"\');";
			System.out.println("Ready to run query: "+query);
			ResultSet rs=this.doUpdateWithGeneratedKeys(query);
			System.out.println("Query executed. Retrieving generated keys...");
			//Get the new object id
			try {
				rs.next();
				long oid=rs.getLong(1);
				SEQLPath newPath=(SEQLPath)path.clone();
				newPath.addPathComponent(oid+"");
				System.out.println("Unique object id is: "+oid+". Updating  absolute path to: "+newPath+" and reference model path to "+sparent.getReferenceModelPath());
				//Update
				query="UPDATE reference_model_objects SET unique_id_path='"+newPath.toString()+"', reference_model_path='"+sparent.getReferenceModelPath()+"' where id="+oid;
				System.out.println("Query: "+query);
				this.doUpdate(query);
				//Recurse for childs 
				for(SEQLPathComponent key : childRMObjects.keySet()) {
					SEQLPath newReferenceModelPath=(SEQLPath)sparent.getReferenceModelPath().clone();
					newReferenceModelPath.addPathComponent(key);
					this.saveSingleAttributeObjectBlockForParent(childRMObjects.get(key), new SQLReferenceModelObjectId(null,newPath,newReferenceModelPath,null));
				}
			} catch(SQLException e) {
				e.printStackTrace();
				throw new PersistenceException(e.getMessage());
			}
			return null;
		}
	}
	@Override
	public ReferenceModelObjectId saveReferenceModelObjectFromContentObject(
			ContentObject obj) throws PersistenceException, SemanticDADLException, ReferenceModelException {
		//It must be a SingleAttributeComplexObjectBlock
		if(obj.getAttributeValues()!=null) {
			throw new SemanticDADLException("Root of DADL reference model file must be an object block");
		} else {
			ComplexObjectBlock block=obj.getComplexObjectBlock();
			//Find the reference model class
			if(!(block instanceof SingleAttributeObjectBlock)) {
				throw new SemanticDADLException("Root of DADL reference model file must be a single attribute object block");
			} else {
				SingleAttributeObjectBlock sblock=(SingleAttributeObjectBlock) block;
				return this.saveSingleAttributeObjectBlockForParent(sblock,new SQLReferenceModelObjectId(null,new SEQLPath("/"), new SEQLPath("/"), new SEQLPath("/")));
			}
		}
	}

	@Override
	public void reset() throws PersistenceException {
		this.clearDB();
		this.initializeDB();
	}
	protected void clearDB() throws PersistenceException {
		String[] queries= {
				"DROP TABLE reference_model_objects;"
		};
		for(String query : queries) {
			this.doUpdate(query);
		}
	}
	protected void initializeDB() throws PersistenceException {
		String[] queries={
				"CREATE TABLE IF NOT EXISTS reference_model_objects ("+
				"id INTEGER NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
				"reference_model_class_name VARCHAR(100) NOT NULL,"+
				"archetype_id VARCHAR(100),"+
				"node_id CHAR(6),"+
				"unique_id_path VARCHAR(1000),"+
				"reference_model_path VARCHAR(1000),"+
				"serialized VARCHAR(10000)"+
			");",
		};
		for(String query :queries) {
			this.doUpdate(query);
		}
	}
	@Override
	public void start() throws PersistenceException {
		wrapper=new HSQLWrapper();
		wrapper.setConnection(this.jdbcConnection, this.user, this.pass);
		try {
			wrapper.start();
			this.initializeDB();
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		
	}
	@Override
	public void stop() throws PersistenceException {
		try {
			wrapper.stop();
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	public void setReferenceModelManager(ReferenceModelManager manager) {
		this.referenceModelManager=manager;
	}
	public void setDADLManager(DADLManager manager) {
		this.dadlManager=manager;
	}
}
