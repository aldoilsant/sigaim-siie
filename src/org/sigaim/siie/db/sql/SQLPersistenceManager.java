package org.sigaim.siie.db.sql;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import org.sigaim.siie.utils.Utils;

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
			String objectClass =Utils.toUppercaseNotation(this.referenceModelManager.getReferenceModelClassName(block)).toLowerCase();
			String archetypeId=this.referenceModelManager.getArchetypeIdForRMObject(block);
			if(archetypeId==null) {
				archetypeId="NULL";
			} else {
				archetypeId="'"+archetypeId+"'";
			}
			String nodeId=this.referenceModelManager.getArchetypeNodeIdForRMObject(block);
			if(nodeId==null) {
				nodeId="NULL";
			} else {
				nodeId="'"+nodeId+"'";
			}
			Map<SEQLPathComponent,SingleAttributeObjectBlock> childRMObjects=this.referenceModelManager.splitForRMObjectVsDataObject(block);
			//Now we can insert our object, child objects have been removed
			String serializedObject=this.dadlManager.serialize(block);
			String query="INSERT INTO reference_model_objects VALUES (NULL,'"+objectClass+"',"+archetypeId+","+nodeId+",NULL,NULL,NULL,NULL,\'"+serializedObject+"\');";
			System.out.println("Ready to run query: "+query);
			ResultSet rs=this.doUpdateWithGeneratedKeys(query);
			System.out.println("Query executed. Retrieving generated keys...");
			//Get the new object id
			try {
				rs.next();
				long oid=rs.getLong(1);
				SEQLPath newPath=(SEQLPath)path.clone();
				newPath.addPathComponent(oid+"");
				System.out.println("Unique object id is: "+oid+". Updating  absolute path to: "+newPath+" and reference model path to "+sparent.getReferenceModelPath()+" and archetype path to "+sparent.getArchetypePath());
				//Update
				query="UPDATE reference_model_objects SET unique_id_path='"+newPath.toString()+"', reference_model_path='"+sparent.getReferenceModelPath()+"', archetype_path='"+sparent.getArchetypePath()+"', depth="+newPath.getPathComponents().size()+" where id="+oid;
				System.out.println("Query: "+query);
				this.doUpdate(query);
				//Recurse for childs 
				for(SEQLPathComponent key : childRMObjects.keySet()) {
					SEQLPath newReferenceModelPath=(SEQLPath)sparent.getReferenceModelPath().clone();
					newReferenceModelPath.addPathComponent(key);
					SEQLPath archetypePath=(SEQLPath)sparent.getArchetypePath().clone();
					String className=this.referenceModelManager.getReferenceModelClassName(childRMObjects.get(key));
					Class<?> rmClass=this.referenceModelManager.referenceModelClassFromString(className);
					String archetype_id=null;
					String node_id=null;
					if(this.referenceModelManager.isArchetypedClass(rmClass)) {
						archetype_id=this.referenceModelManager.getArchetypeIdForRMObject(childRMObjects.get(key));
						node_id=this.referenceModelManager.getArchetypeNodeIdForRMObject(childRMObjects.get(key));
					}
					if(node_id==null) { //Not assigned to an archetype meaning
						archetypePath.addPathComponent(key.getPathIdentifier());
					} else {
						archetypePath.addPathComponent(key.getPathIdentifier()+"["+node_id+"]");
					}
					//Save the archetype model path, that is, the archetype id and the archetype node id
					//Note that in the reference model objects that are not rmobjets do not have these values. Sorry
					this.saveSingleAttributeObjectBlockForParent(childRMObjects.get(key), new SQLReferenceModelObjectId(null,newPath,newReferenceModelPath,archetypePath));
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
				"node_id VARCHAR(100),"+
				"unique_id_path VARCHAR(1000),"+
				"reference_model_path VARCHAR(1000),"+
				"archetype_path VARCHAR(1000),"+
				"depth INTEGER,"+
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
	public List<ReferenceModelObjectId> getDeepestRMObjectsForParentAndPath(ReferenceModelObjectId parent, List<SEQLPathComponent> components) throws PersistenceException{
		//Find the deepest object that is independently serialized to test in-memory
		List<ReferenceModelObjectId> ret=new ArrayList<ReferenceModelObjectId>();
		List<SEQLPathComponent> toRemove=new ArrayList<SEQLPathComponent>();

		if(!(parent instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException("Incompatible reference model object id: "+parent);
		} else {
			SEQLPath buildingPath=new SEQLPath((String)null);
			Class<?> ptype;
			SQLReferenceModelObjectId sparent=(SQLReferenceModelObjectId) parent;
			for(SEQLPathComponent component : components) {
				buildingPath.addPathComponent(component);
				ptype=this.referenceModelManager.getPathType(sparent.getObjectClass().getSimpleName().toLowerCase(), buildingPath);
				//Else we need to solve the path components. The first test is if the goal path is a real object or not
				if(this.referenceModelManager.isRMObjectClass(ptype)) {
					toRemove.add(component);
				}   else {
					buildingPath=buildingPath.removeLastPathComponent();
					break;
				}
			}
			//For the identified variable
			buildingPath=buildingPath.removeFirstPathComponent();
			if(buildingPath.getPathComponents()==null || buildingPath.getPathComponents().size()==0)  {
				ret.add(sparent);
			} else {
				//Use the building path to retrieve a, for certain, rmobject
				ret=this.selectObjectsMatchingPathFromParent(parent,buildingPath);
			}
		}
		components.removeAll(toRemove);
		return ret;
	}
	public List<ReferenceModelObjectId> selectObjectsMatchingPathFromParent(ReferenceModelObjectId parent,SEQLPath subpath) throws PersistenceException{
		//Get the parent reference model path
		List<ReferenceModelObjectId> toRet=new ArrayList<ReferenceModelObjectId>();
		if(!(parent instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException("Incompatible reference model object id: "+parent);
		} else {
			SQLReferenceModelObjectId sparent=(SQLReferenceModelObjectId) parent;
			SEQLPath referenceModelPath=(SEQLPath)sparent.getReferenceModelPath().clone();
			SEQLPath archetypePath=(SEQLPath)sparent.getArchetypePath().clone();

			for(SEQLPathComponent subComponent : subpath.getPathComponents()) {
				referenceModelPath.addPathComponent(subComponent.toReferenceModelPathComponent());
				archetypePath.addPathComponent(subComponent.toNodeIDPath());
			}
			StringBuilder pathBuilder=new StringBuilder(referenceModelPath.getFullPath());
			pathBuilder.deleteCharAt(pathBuilder.length()-1);
			String query=null;
			query="SELECT unique_id_path, reference_model_path, archetype_path, reference_model_class_name FROM reference_model_objects WHERE reference_model_path LIKE'"+pathBuilder.toString()+"%' AND archetype_path='"+archetypePath.toString()+"' AND depth="+(referenceModelPath.getPathComponents().size()+1);
			query+=";";
			System.out.println(query);
			ResultSet rs=this.doQuery(query);
			try {
				while(rs.next()) {
					SQLReferenceModelObjectId newObjectId=new SQLReferenceModelObjectId();
					newObjectId.setUniqueIdPath(new SEQLPath(rs.getString(1)));
					newObjectId.setReferenceModelPath(new SEQLPath(rs.getString(2)));
					newObjectId.setArchetypePath(new SEQLPath(rs.getString(3)));
					newObjectId.setObjectClass(this.referenceModelManager.referenceModelClassFromString(Utils.toUppercaseNotation(rs.getString(4))));
					toRet.add(newObjectId);
				}
			} catch(SQLException e) {
				e.printStackTrace();
				throw new PersistenceException(e.getMessage());
			}
		}
		return toRet;
	}
	public ContentObject selectFromReferenceModelObjectId(ReferenceModelObjectId id) throws PersistenceException {
		if(!(id instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException("Incompatible reference model object id: "+id);
		} else {
			SQLReferenceModelObjectId sid=(SQLReferenceModelObjectId)id;
			String query="SELECT serialized  FROM reference_model_objects WHERE unique_id_path='"+sid.getUniqueIdPath()+"'";
			query+=";";
			ResultSet rs=this.doQuery(query);
			try {
				while(rs.next()) {
					String dadl="<"+rs.getString(1)+">";
					return this.dadlManager.parseDADL(new ByteArrayInputStream(dadl.getBytes()));
				}
			} catch(SQLException e) {
				e.printStackTrace();
				throw new PersistenceException(e.getMessage());
			}
		}
		return null;
	}
	@Override
	public List<ReferenceModelObjectId> selectMatchingObjectsForComponentAndParent(
			String referenceModelClass, String archetypeId,
			ReferenceModelObjectId parent) throws PersistenceException {
		List<ReferenceModelObjectId> toRet=new ArrayList<ReferenceModelObjectId>();
		String query="SELECT unique_id_path, reference_model_path, archetype_path, reference_model_class_name FROM reference_model_objects WHERE reference_model_class_name='"+Utils.toUppercaseNotation(referenceModelClass).toLowerCase()+"'";
		if(archetypeId!=null) {
			query+=" AND archetype_id='"+archetypeId+"' ";
		}
		if(parent!=null) {
			//Use the unique id path to test the parent... must match the whole string but the last component
			SQLReferenceModelObjectId sparent=(SQLReferenceModelObjectId) parent;
			query+=" AND unique_id_path LIKE '"+sparent.getUniqueIdPath()+"%' AND unique_id_path != '"+sparent.getUniqueIdPath()+"'";
		}
		query+=";";
		ResultSet rs=this.doQuery(query);
		try {
			while(rs.next()) {
				SQLReferenceModelObjectId newObjectId=new SQLReferenceModelObjectId();
				newObjectId.setUniqueIdPath(new SEQLPath(rs.getString(1)));
				newObjectId.setReferenceModelPath(new SEQLPath(rs.getString(2)));
				newObjectId.setArchetypePath(new SEQLPath(rs.getString(3)));
				newObjectId.setObjectClass(this.referenceModelManager.referenceModelClassFromString(Utils.toUppercaseNotation(rs.getString(4))));
				toRet.add(newObjectId);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		}
		return toRet;
	}
	@Override
	public ReferenceModelObjectId getReferenceModelRoot() {
		return new SQLReferenceModelObjectId(null,new SEQLPath("/"),new SEQLPath("/"),new SEQLPath("/"));
	}
 
}
