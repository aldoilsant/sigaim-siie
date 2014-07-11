package org.sigaim.siie.db.sql;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;
import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.IntegerValue;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.openehr.am.parser.StringValue;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.exceptions.SemanticDADLException;
import org.sigaim.siie.db.DBSerializer;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.ReferenceModelObjectId;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.db.sql.mysql.MySQLWrapper;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.seql.model.SEQLException;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;
import org.sigaim.siie.utils.Utils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class SQLPersistenceManager implements PersistenceManager {
	private SQLWrapper wrapper;
	private ReferenceModelManager referenceModelManager;
	private DADLManager dadlManager;
	private DataSource source;

	private static org.apache.log4j.Logger log = Logger
			.getLogger(SQLPersistenceManager.class);

	protected Connection getConnection() {
		try {
			return source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected Connection getConnection(boolean autocommit) {
		try {
			Connection conn = source.getConnection();
			if (!autocommit) {
				conn.setAutoCommit(false);
			}
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void closeConnection(Connection conn) {
		try {
			conn.setAutoCommit(true);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public SQLPersistenceManager() throws PersistenceException {
		MysqlDataSource basicDataSource = new MysqlDataSource();
		basicDataSource.setUser("root");
		basicDataSource.setPassword("root");
		basicDataSource.setServerName("localhost");
		basicDataSource.setPort(8889);
		basicDataSource.setDatabaseName("sigaimsiie");
		this.source = basicDataSource;
		this.start();
	}

	public SQLPersistenceManager(DataSource source) throws PersistenceException {
		this.source = source;
		this.start();
	}

	@Override
	public ReferenceModelObjectId saveReferenceModelObject(Object object) {
		throw new NotImplementedException();
	}

	@Override
	public Object solvePathFromObjectId(ReferenceModelObjectId oid) {
		throw new NotImplementedException();
	}

	protected ResultSet doQuery(String query, Connection conn)
			throws PersistenceException {
		try {
			return wrapper.query(query, conn);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	protected void doUpdate(String query, Connection conn)
			throws PersistenceException {
		try {
			wrapper.update(query, conn);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	protected ResultSet doUpdateWithGeneratedKeys(String query, Connection conn)
			throws PersistenceException {
		try {
			return wrapper.updateWithGeneratedKeys(query, conn);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	protected SEQLPath nextArchetypePath(SQLReferenceModelObjectId sparent,
			SingleAttributeObjectBlock rmObject, SEQLPathComponent component)
			throws ReferenceModelException {

		String className = this.referenceModelManager
				.getReferenceModelClassName(rmObject);
		Class<?> rmClass = this.referenceModelManager
				.referenceModelClassFromString(className);
		String archetype_id = null;
		String node_id = null;
		SEQLPath archetypePath = (SEQLPath) sparent.getArchetypePath().clone();
		if (this.referenceModelManager.isArchetypedClass(rmClass)) {
			archetype_id = this.referenceModelManager
					.getArchetypeIdForRMObject(rmObject);
			node_id = this.referenceModelManager
					.getArchetypeNodeIdForRMObject(rmObject);
		}
		if (node_id == null) { // Not assigned to an archetype meaning
			archetypePath.addPathComponent(component.getPathIdentifier());
		} else {
			archetypePath.addPathComponent(component.getPathIdentifier() + "["
					+ node_id + "]");
		}
		return archetypePath;

	}

	@Override
	public void reset() throws PersistenceException {
		this.clearDB();
		this.initializeDB();
	}

	protected void clearDB() throws PersistenceException {
		try {
			Connection conn = source.getConnection();
			this.wrapper.clearDB(conn);
			conn.close();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	protected void initializeDB() throws PersistenceException {
		try {
			Connection conn = source.getConnection();
			this.wrapper.initializeDB(conn);
			conn.close();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	@Override
	public void start() throws PersistenceException {
		wrapper = new MySQLWrapper();
		this.initializeDB();
	}

	@Override
	public void stop() throws PersistenceException {

	}

	public void setReferenceModelManager(ReferenceModelManager manager) {
		this.referenceModelManager = manager;
	}

	public void setDADLManager(DADLManager manager) {
		this.dadlManager = manager;
	}

	public List<ReferenceModelObjectId> getDeepestRMObjectsForParentAndPath(
			ReferenceModelObjectId parent, List<SEQLPathComponent> components)
			throws PersistenceException {
		// Find the deepest object that is independently serialized to test
		// in-memory
		List<ReferenceModelObjectId> ret = new ArrayList<ReferenceModelObjectId>();
		List<SEQLPathComponent> toRemove = new ArrayList<SEQLPathComponent>();

		if (!(parent instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException(
					"Incompatible reference model object id: " + parent);
		} else {
			SEQLPath buildingPath = new SEQLPath((String) null);
			Class<?> ptype;
			SQLReferenceModelObjectId sparent = (SQLReferenceModelObjectId) parent;
			for (SEQLPathComponent component : components) {
				buildingPath.addPathComponent(component);
				ptype = this.referenceModelManager.getPathType(sparent
						.getObjectClass().getSimpleName().toLowerCase(),
						buildingPath);
				// Else we need to solve the path components. The first test is
				// if the goal path is a real object or not
				if (this.referenceModelManager.isRMObjectClass(ptype)) {
					toRemove.add(component);
				} else {
					buildingPath = buildingPath.removeLastPathComponent();
					break;
				}
			}
			// For the identified variable
			buildingPath = buildingPath.removeFirstPathComponent();
			if (buildingPath.getPathComponents() == null
					|| buildingPath.getPathComponents().size() == 0) {
				ret.add(sparent);
			} else {
				// Use the building path to retrieve a, for certain, rmobject
				ret = this.selectObjectsMatchingPathFromParent(parent,
						buildingPath);
			}
		}
		components.removeAll(toRemove);
		return ret;
	}

	public List<ReferenceModelObjectId> selectObjectsMatchingPathFromParent(
			ReferenceModelObjectId parent, SEQLPath subpath)
			throws PersistenceException {
		// Get the parent reference model path
		List<ReferenceModelObjectId> toRet = new ArrayList<ReferenceModelObjectId>();
		if (!(parent instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException(
					"Incompatible reference model object id: " + parent);
		} else {
			SQLReferenceModelObjectId sparent = (SQLReferenceModelObjectId) parent;
			SEQLPath referenceModelPath = (SEQLPath) sparent
					.getReferenceModelPath().clone();
			int depth = sparent.getUniqueIdPath().getPathComponents().size();
			SEQLPath archetypePath = (SEQLPath) sparent.getArchetypePath()
					.clone();

			StringBuilder referenceModelPathBuilder = new StringBuilder(
					referenceModelPath.toString().replace("[", "\\\\[")
							.replace("]", "\\\\]"));
			StringBuilder archetypePathBuilder = new StringBuilder(
					archetypePath.toString().replace("[", "\\\\[")
							.replace("]", "\\\\]"));
			for (SEQLPathComponent subComponent : subpath.getPathComponents()) {
				SEQLPathComponent referenceModelPathComponent = subComponent
						.toReferenceModelPathComponent();
				if (!referenceModelPathComponent.hasPredicate()) {
					referenceModelPathComponent = new SEQLPathComponent(
							referenceModelPathComponent.getPathIdentifier()
									+ "(\\\\[[0-9]+\\\\])?", null);
					referenceModelPathBuilder
							.append(referenceModelPathComponent.toString()
									+ "/");
				} else {
					referenceModelPathBuilder
							.append(referenceModelPathComponent.toString()
									.replace("[", "\\\\[")
									.replace("]", "\\\\]")
									+ "/");
				}
				SEQLPathComponent archetypePathComponent = subComponent
						.toNodeIDPath();
				if (!archetypePathComponent.hasPredicate()) {
					archetypePathComponent = new SEQLPathComponent(
							archetypePathComponent.getPathIdentifier()
									+ "(\\\\[at[0-9]+\\\\])?", null);
					archetypePathBuilder.append(archetypePathComponent
							.toString() + "/");
				} else {
					archetypePathBuilder.append(archetypePathComponent
							.toString().replace("[", "\\\\[")
							.replace("]", "\\\\]")
							+ "/");
				}
				depth++;
			}
			referenceModelPathBuilder.deleteCharAt(referenceModelPathBuilder
					.length() - 1);
			referenceModelPathBuilder.append("(.+)?");
			archetypePathBuilder
					.deleteCharAt(archetypePathBuilder.length() - 1);
			archetypePathBuilder.append("(.+)?");
			String query = null;
			query = "SELECT unique_id_path, reference_model_path, archetype_path, reference_model_class_name FROM reference_model_objects WHERE reference_model_path RLIKE'"
					+ referenceModelPathBuilder.toString()
					+ "' AND archetype_path RLIKE '"
					+ archetypePathBuilder.toString() + "' AND depth=" + depth;
			query += ";";
			log.debug(query);
			Connection conn = getConnection();
			ResultSet rs = this.doQuery(query, conn);
			try {
				while (rs.next()) {
					SQLReferenceModelObjectId newObjectId = new SQLReferenceModelObjectId();
					newObjectId.setUniqueIdPath(new SEQLPath(rs.getString(1)));
					newObjectId.setReferenceModelPath(new SEQLPath(rs
							.getString(2)));
					newObjectId.setArchetypePath(new SEQLPath(rs.getString(3)));
					newObjectId.setObjectClass(this.referenceModelManager
							.referenceModelClassFromString(Utils
									.toUppercaseNotation(rs.getString(4))));
					toRet.add(newObjectId);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PersistenceException(e.getMessage());
			} finally {
				closeConnection(conn);
			}
		}
		return toRet;
	}

	protected void appendChildToParent(SQLReferenceModelObjectId parentId,
			ContentObject parsedParent, Boolean deep, Connection conn)
			throws PersistenceException {
		int childDepth = parentId.getUniqueIdPath().getPathComponents().size() + 1;
		String query = "SELECT unique_id_path, reference_model_path, archetype_path, reference_model_class_name, serialized FROM reference_model_objects WHERE unique_id_path LIKE '"
				+ parentId.getUniqueIdPath()
				+ "%' and depth="
				+ childDepth
				+ " ORDER BY reference_model_path;";
		log.debug("Child query: " + query);
		ResultSet rs = this.doQuery(query, conn);
		try {
			while (rs.next()) {
				log.debug("Child: " + rs.getString(2));
				SEQLPath referenceModelPath = new SEQLPath(rs.getString(2));
				String dadl = "<" + rs.getString(5) + ">";
				ContentObject parsedChild = this.dadlManager
						.parseDADL(new ByteArrayInputStream(dadl.getBytes()));
				if (deep) {
					// recurse
					SQLReferenceModelObjectId newObjectId = new SQLReferenceModelObjectId();
					newObjectId.setUniqueIdPath(new SEQLPath(rs.getString(1)));
					newObjectId.setReferenceModelPath(referenceModelPath);
					newObjectId.setArchetypePath(new SEQLPath(rs.getString(3)));
					newObjectId.setObjectClass(this.referenceModelManager
							.referenceModelClassFromString(Utils
									.toUppercaseNotation(rs.getString(4))));
					this.appendChildToParent(newObjectId, parsedChild, deep,
							conn);
				}
				// Append the ContentObject to the construct
				SEQLPathComponent childComponent = referenceModelPath
						.getLastPathComponent();
				if (childComponent.getPathPredicate() != null) { // Collection
																	// item
					// Get Key 1 as collection key
					String collectionKey = childComponent.getPathPredicate()
							.getKey1();
					// Create a new keyedObject and append it
					SingleAttributeObjectBlock childBlock = this.referenceModelManager
							.getSingleAttributeObjectBlockFromContentObject(parsedChild);
					// FIXME improve type detection
					SimpleValue<?> key;
					if (Utils.isInteger(collectionKey)) {
						key = new IntegerValue(Integer.parseInt(collectionKey));
					} else {
						key = new StringValue(collectionKey);
					}
					log.debug("Appending colletion item to parent");
					KeyedObject childKeyedObject = new KeyedObject(key,
							childBlock);
					SingleAttributeObjectBlock parentBlock = this.referenceModelManager
							.getSingleAttributeObjectBlockFromContentObject(parsedParent);
					boolean exists = false;
					MultipleAttributeObjectBlock mblock = null;
					for (AttributeValue at : parentBlock.getAttributeValues()) {
						if (at.getId().equals(
								childComponent.getPathIdentifier())) {
							exists = true;
							mblock = (MultipleAttributeObjectBlock) at
									.getValue();
							break;
						}
					}
					if (!exists) {
						ArrayList<KeyedObject> keyedObjects = new ArrayList<KeyedObject>();
						// Create a multiple attribute object block
						mblock = new MultipleAttributeObjectBlock(null,
								keyedObjects);
						parentBlock.getAttributeValues().add(
								new AttributeValue(childComponent
										.getPathIdentifier(), mblock));
					}
					// Now add the keyed object. In case of lists, keep order
					// for better readability
					if (key instanceof IntegerValue) {
						Utils.ensureSize(
								(ArrayList<KeyedObject>) mblock.getKeyObjects(),
								(Integer) key.getValue());
						mblock.getKeyObjects().set(
								((Integer) key.getValue()) - 1,
								childKeyedObject);
					} else {
						mblock.getKeyObjects().add(childKeyedObject);
					}
				} else { // Single attribute
					log.debug("Appending single item to parent");
					SingleAttributeObjectBlock childBlock = this.referenceModelManager
							.getSingleAttributeObjectBlockFromContentObject(parsedChild);
					SingleAttributeObjectBlock parentBlock = this.referenceModelManager
							.getSingleAttributeObjectBlockFromContentObject(parsedParent);
					parentBlock.getAttributeValues().add(
							new AttributeValue(childComponent
									.getPathIdentifier(), childBlock));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		}
	}

	@Override
	public ContentObject selectFromReferenceModelObjectId(
			ReferenceModelObjectId id, Boolean deep)
			throws PersistenceException {
		if (!(id instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException(
					"Incompatible reference model object id: " + id);
		} else {
			SQLReferenceModelObjectId sid = (SQLReferenceModelObjectId) id;
			String query = "SELECT serialized  FROM reference_model_objects WHERE unique_id_path='"
					+ sid.getUniqueIdPath() + "'";
			query += ";";
			Connection conn = getConnection();
			ResultSet rs = this.doQuery(query, conn);
			try {
				while (rs.next()) {
					String dadl = "<" + rs.getString(1) + ">";
					ContentObject parsed = this.dadlManager
							.parseDADL(new ByteArrayInputStream(dadl.getBytes()));
					if (deep) {
						this.appendChildToParent(sid, parsed, deep, conn);
					}
					return parsed;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PersistenceException(e.getMessage());
			} finally {
				closeConnection(conn);
			}
		}
		return null;
	}

	@Override
	public List<ReferenceModelObjectId> selectMatchingObjectsForComponentAndParent(
			String referenceModelClass, String archetypeId,
			ReferenceModelObjectId parent, Boolean useAllVersions) throws PersistenceException {
		
		//Search for all subclasses of the given referenceModelclass... to improve the query
		List<String> subClassesOrSelf=this.referenceModelManager.getSubclassesOrSelf(referenceModelClass);
		String inCondition="";
		int i=0;
		for(i=0;i<subClassesOrSelf.size();i++) {
			inCondition+="'"+subClassesOrSelf.get(i).toLowerCase()+"'";
			if(i<subClassesOrSelf.size()-1) {
				inCondition+=",";
			}
		}
		List<ReferenceModelObjectId> toRet=new ArrayList<ReferenceModelObjectId>();
		String query="SELECT unique_id_path, reference_model_path, archetype_path, reference_model_class_name FROM reference_model_objects INNER JOIN reference_model_object_versions ON reference_model_objects.id=reference_model_object_versions.id WHERE reference_model_class_name IN("+inCondition+")";
		if(archetypeId!=null) {
			query+=" AND archetype_id='"+archetypeId+"' ";
		}
		if(parent!=null) {
			//Use the unique id path to test the parent... must match the whole string but the last component
			SQLReferenceModelObjectId sparent=(SQLReferenceModelObjectId) parent;
			query+=" AND unique_id_path LIKE '"+sparent.getUniqueIdPath()+"%' AND unique_id_path != '"+sparent.getUniqueIdPath()+"'";
		}
		if(!useAllVersions) {
			query+=" AND reference_model_object_versions.next IS NULL";
		}
		query+=";";
		Connection conn=getConnection();
		ResultSet rs=this.doQuery(query,conn);
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
		} finally {
			closeConnection(conn);
		}
		return toRet;
	}

	@Override
	public ReferenceModelObjectId getReferenceModelRoot() {
		return new SQLReferenceModelObjectId(
				this.referenceModelManager.getRootClass(), new SEQLPath("/1"),
				new SEQLPath("/"), new SEQLPath("/"));
	}

	public Class<?> getClassFromRMID(ReferenceModelObjectId id)
			throws PersistenceException {
		if (!(id instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException(
					"Incompatible reference model object id: " + id);
		} else {
			return ((SQLReferenceModelObjectId) id).getObjectClass();
		}
	}

	@Override
	public int countObjectsMatchingPathFromParent(
			ReferenceModelObjectId parent, SEQLPath subpath)
			throws PersistenceException {
		return this.selectObjectsMatchingPathFromParent(parent, subpath).size();
	}

	@Override
	public ReferenceModelObjectId saveObjectToPathFromParentWithSerializer(
			SingleAttributeObjectBlock block, ReferenceModelObjectId parent,
			SEQLPathComponent component, DBSerializer serializer)
			throws PersistenceException, SemanticDADLException,
			ReferenceModelException {
		if (!(parent instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException(
					"Incompatible reference model object id: " + parent);
		} else {
			SQLReferenceModelObjectId sparent = (SQLReferenceModelObjectId) parent;
			// Adapt to saveSingleAttributeObjectBlock and recurse
			SEQLPath newReferenceModelPath = (SEQLPath) sparent
					.getReferenceModelPath().clone();
			newReferenceModelPath.addPathComponent(component);
			SEQLPath newArchetypePath = this.nextArchetypePath(sparent, block,
					component);
			sparent.setArchetypePath(newArchetypePath);
			sparent.setReferenceModelPath(newReferenceModelPath);
			return this.saveSingleAttributeObjectBlockForParentWithSerializer(
					block, sparent, serializer);
		}
	}

	public ReferenceModelObjectId saveSingleAttributeObjectBlockForParentWithSerializer(
			SingleAttributeObjectBlock block, ReferenceModelObjectId parent,
			DBSerializer serializer) throws PersistenceException,
			SemanticDADLException, ReferenceModelException {
		Connection conn = getConnection(false);
		Exception e = null;
		ReferenceModelObjectId ret = null;
		try {
			//Set the prepared statements
			PreparedStatement insertRMObjectStatement=conn.prepareStatement("INSERT INTO reference_model_objects VALUES(NULL,?,?,?,NULL,NULL,NULL,NULL,'');",Statement.RETURN_GENERATED_KEYS);
			PreparedStatement updateRMObjectStatement=conn.prepareStatement("UPDATE reference_model_objects SET unique_id_path=?, reference_model_path=?, archetype_path=?, depth=?, serialized=? where id=?;");;
			PreparedStatement insertVersionsStatement=conn.prepareStatement("INSERT INTO reference_model_object_versions VALUES(?,NULL);");
			ret = this.saveSingleAttributeObjectBlockForParentWithSerializer(
					block, parent, serializer, conn,insertRMObjectStatement,updateRMObjectStatement,insertVersionsStatement);
		} catch (Exception re) {
			e = re;
		} finally {
			if (e != null) {
				try {
					conn.rollback();
					closeConnection(conn);
				} catch (Exception e2) {
				}
				throw new PersistenceException(e.getMessage());
			} else {
				try {
					conn.commit();
					closeConnection(conn);
				} catch (Exception e2) {
				}
			}
		}
		return ret;
	}

	public ReferenceModelObjectId saveSingleAttributeObjectBlockForParentWithSerializer(
			SingleAttributeObjectBlock block, ReferenceModelObjectId parent,
			DBSerializer serializer, Connection conn,PreparedStatement insertRMObjectStatement,PreparedStatement updateRMObjectStatement,PreparedStatement insertVersionsStatement)
			throws PersistenceException, SemanticDADLException,
			ReferenceModelException {
		if (!(parent instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException(
					"Incompatible reference model object id: " + parent);
		} else {
			String query=null;
			SQLReferenceModelObjectId sparent = (SQLReferenceModelObjectId) parent;
			SQLReferenceModelObjectId ret = null;
			SEQLPath path = sparent.getUniqueIdPath();
			String objectClass = Utils.toUppercaseNotation(
					this.referenceModelManager
							.getReferenceModelClassName(block)).toLowerCase();
			String archetypeId = this.referenceModelManager
					.getArchetypeIdForRMObject(block);
			if (archetypeId == null) {
				archetypeId = "NULL";
			} else {
				archetypeId = "'" + archetypeId + "'";
			}
			String nodeId = this.referenceModelManager
					.getArchetypeNodeIdForRMObject(block);
			if (nodeId == null) {
				nodeId = "NULL";
			} else {
				nodeId = "'" + nodeId + "'";
			}
			Map<SEQLPathComponent, SingleAttributeObjectBlock> childRMObjects = this.referenceModelManager
					.splitForRMObjectVsDataObject(block);
			// Now we can insert our object, child objects have been removed
			try {
				insertRMObjectStatement.setString(1, objectClass);
				insertRMObjectStatement.setString(2, archetypeId);
				insertRMObjectStatement.setString(3, nodeId);
			} catch(SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
			/*query= "INSERT INTO reference_model_objects VALUES (NULL,'"
					+ objectClass + "'," + archetypeId + "," + nodeId
					+ ",NULL,NULL,NULL,NULL,'');";*/
			//log.debug("Ready to run query: " + this.insertRMObjectStatement.toString());
			ResultSet rs=null;
			try {
				rs= wrapper.updatePreparedStatementWithGeneratedKeys(insertRMObjectStatement);
			} catch(SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
			//log.debug("Query executed. Retrieving generated keys...");
			// Get the new object id
			try {
				rs.next();
				long oid = rs.getLong(1);
				SEQLPath newPath = (SEQLPath) path.clone();
				newPath.addPathComponent(oid + "");
				/*log.debug("Unique object id is: " + oid
						+ ". Updating  absolute path to: " + newPath
						+ " and reference model path to "
						+ sparent.getReferenceModelPath()
						+ " and archetype path to "
						+ sparent.getArchetypePath());*/
				// Update
				String serializedObject = null;
				if (serializer == null) {
					serializedObject = this.dadlManager.serialize(block);
				} else {
					serializedObject = serializer.extendSerialization(block,
							oid);
				}
				try {
					updateRMObjectStatement.setString(1, newPath.toString());
					updateRMObjectStatement.setString(2, sparent.getReferenceModelPath().toString());
					updateRMObjectStatement.setString(3, sparent.getArchetypePath().toString());
					updateRMObjectStatement.setInt(4,newPath.getPathComponents().size());
					updateRMObjectStatement.setString(5,serializedObject);
					updateRMObjectStatement.setLong(6,oid);
					this.wrapper.updatePreparedStatement(updateRMObjectStatement);
				} catch(SQLException e) {
					throw new ReferenceModelException(e.getMessage());
				}
				/*query = "UPDATE reference_model_objects SET unique_id_path='"
						+ newPath.toString() + "', reference_model_path='"
						+ sparent.getReferenceModelPath()
						+ "', archetype_path='" + sparent.getArchetypePath()
						+ "', depth=" + newPath.getPathComponents().size()
						+ ", serialized=\'" + serializedObject + "\' where id="
						+ oid;*/
				//log.debug("Query: " + query);
				//this.doUpdate(query, conn);
				// Insert a vanilla entry in the versions table
				insertVersionsStatement.setLong(1, oid);
				wrapper.updatePreparedStatement(insertVersionsStatement);
				/*query = "INSERT INTO reference_model_object_versions VALUES("
						+ oid + ",NULL);";*/
				//log.debug("Version query: " + query);
				//this.doUpdate(query, conn);
				ret = new SQLReferenceModelObjectId();
				ret.setArchetypePath(sparent.getArchetypePath());
				ret.setReferenceModelPath(sparent.getReferenceModelPath());
				ret.setUniqueIdPath(newPath);
				// Recurse for childs
				for (SEQLPathComponent key : childRMObjects.keySet()) {
					SEQLPath newReferenceModelPath = (SEQLPath) sparent
							.getReferenceModelPath().clone();
					newReferenceModelPath.addPathComponent(key);
					SEQLPath archetypePath = (SEQLPath) sparent
							.getArchetypePath().clone();
					String className = this.referenceModelManager
							.getReferenceModelClassName(childRMObjects.get(key));
					Class<?> rmClass = this.referenceModelManager
							.referenceModelClassFromString(className);
					String archetype_id = null;
					String node_id = null;
					if (this.referenceModelManager.isArchetypedClass(rmClass)) {
						archetype_id = this.referenceModelManager
								.getArchetypeIdForRMObject(childRMObjects
										.get(key));
						node_id = this.referenceModelManager
								.getArchetypeNodeIdForRMObject(childRMObjects
										.get(key));
					}
					if (node_id == null) { // Not assigned to an archetype
											// meaning
						archetypePath.addPathComponent(key.getPathIdentifier());
					} else {
						archetypePath.addPathComponent(key.getPathIdentifier()
								+ "[" + node_id + "]");
					}
					// Save the archetype model path, that is, the archetype id
					// and the archetype node id
					// Note that in the reference model objects that are not
					// rmobjets do not have these values. Sorry
					this.saveSingleAttributeObjectBlockForParentWithSerializer(
							childRMObjects.get(key),
							new SQLReferenceModelObjectId(null, newPath,
									newReferenceModelPath, archetypePath),
							serializer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PersistenceException(e.getMessage());
			}
			return ret;
		}
	}

	@Override
	public ReferenceModelObjectId saveReferenceModelObjectFromContentObjectWithSerializer(
			ContentObject obj, DBSerializer serializer)
			throws PersistenceException, SemanticDADLException,
			ReferenceModelException {
		// It must be a SingleAttributeComplexObjectBlock
		if (obj.getAttributeValues() != null) {
			throw new SemanticDADLException(
					"Root of DADL reference model file must be an object block");
		} else {
			ComplexObjectBlock block = obj.getComplexObjectBlock();
			// Find the reference model class
			if (!(block instanceof SingleAttributeObjectBlock)) {
				throw new SemanticDADLException(
						"Root of DADL reference model file must be a single attribute object block");
			} else {
				SingleAttributeObjectBlock sblock = (SingleAttributeObjectBlock) block;
				return this
						.saveSingleAttributeObjectBlockForParentWithSerializer(
								sblock, this.getReferenceModelRoot(),
								serializer);
			}
		}
	}

	@Override
	public ReferenceModelObjectId saveReferenceModelObjectWithSerializer(
			Object object, DBSerializer serializer) throws PersistenceException {
		throw new NotImplementedException();
	}

	@Override
	public ReferenceModelObjectId saveReferenceModelObjectFromContentObject(
			ContentObject cobj) throws PersistenceException,
			SemanticDADLException, ReferenceModelException {
		return this.saveReferenceModelObjectFromContentObjectWithSerializer(
				cobj, null);
	}

	@Override
	public ReferenceModelObjectId saveObjectToPathFromParent(
			SingleAttributeObjectBlock block, ReferenceModelObjectId parent,
			SEQLPathComponent component) throws PersistenceException,
			SemanticDADLException, ReferenceModelException {
		return this.saveObjectToPathFromParentWithSerializer(block, parent,
				component, null);
	}

	@Override
	public void setAsNextVersionOf(ReferenceModelObjectId newVersion,
			ReferenceModelObjectId lastVersion) throws PersistenceException {
		if (!(newVersion instanceof SQLReferenceModelObjectId)
				|| !(lastVersion instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException(
					"Incompatible reference model object id. One of: "
							+ newVersion + ", " + lastVersion);
		}
		SQLReferenceModelObjectId sNewVersion = (SQLReferenceModelObjectId) newVersion;
		SQLReferenceModelObjectId sLastVersion = (SQLReferenceModelObjectId) lastVersion;
		long noid = sNewVersion.getUniqueId();
		long loid = sLastVersion.getUniqueId();
		String query = "UPDATE reference_model_object_versions SET next="
				+ noid + " WHERE id=" + loid + ";";
		log.debug("Update version query: " + query);
		log.debug("Query: " + query);
		Connection conn = getConnection();
		this.doUpdate(query, conn);
		closeConnection(conn);
	}

	@Override
	public ReferenceModelObjectId getReferenceModelObjectIdFromUniqueId(long oid)
			throws PersistenceException {
		SQLReferenceModelObjectId toRet = null;
		String query = "SELECT unique_id_path, reference_model_path, archetype_path, reference_model_class_name FROM reference_model_objects WHERE id="
				+ oid + ";";
		Connection conn = getConnection();
		ResultSet rs = this.doQuery(query, conn);
		try {
			while (rs.next()) {
				toRet = new SQLReferenceModelObjectId();
				toRet.setUniqueIdPath(new SEQLPath(rs.getString(1)));
				toRet.setReferenceModelPath(new SEQLPath(rs.getString(2)));
				toRet.setArchetypePath(new SEQLPath(rs.getString(3)));
				toRet.setObjectClass(this.referenceModelManager
						.referenceModelClassFromString(Utils
								.toUppercaseNotation(rs.getString(4))));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		}
		closeConnection(conn);
		return toRet;
	}

	@Override
	public SEQLPath getReferenceModelPathFoRMObject(ReferenceModelObjectId id)
			throws PersistenceException {
		// TODO Auto-generated method stub
		if (!(id instanceof SQLReferenceModelObjectId)) {
			throw new PersistenceException(
					"Incompatible reference model object id: " + id);
		} else {
			SQLReferenceModelObjectId sid = (SQLReferenceModelObjectId) id;
			return sid.getReferenceModelPath();
		}
	}

	@Override
	public long readAtomicIndex(String indexName) throws PersistenceException {
		// Begin transaction
		long toRet = 0;
		String query = "SELECT index_value FROM indexes WHERE index_name='"
				+ indexName + "' FOR UPDATE;";
		Connection conn = getConnection();
		ResultSet rs = this.doQuery(query, conn);
		try {
			while (rs.next()) {
				toRet = rs.getLong(1);
				toRet++;
				query = "UPDATE indexes SET index_value=" + toRet
						+ " WHERE index_name='" + indexName + "';";
				this.doUpdate(query, conn);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		}
		closeConnection(conn);
		return toRet;
	}

	@Override
	public void declareAtomicIndex(String indexName)
			throws PersistenceException {
		Connection conn = getConnection();
		this.doUpdate(
				"INSERT INTO indexes VALUES(NULL,0,'" + indexName + "');", conn);
		closeConnection(conn);
	}
}
