package org.sigaim.siie.db.sql.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.sigaim.siie.db.sql.SQLWrapper;

public class MySQLWrapper implements SQLWrapper{
	private String connectionString="sigaim";
	private String user;
	private String pass;
	private Connection conn;
	private static org.apache.log4j.Logger log = Logger.getLogger(MySQLWrapper.class);
	
	@Override
	public void start() throws SQLException {
		try {
			 Class.forName("org.hsqldb.jdbcDriver");
		} catch(Exception e){
				e.printStackTrace();;
		}
		if(conn==null) {
			conn = DriverManager.getConnection(connectionString,user,pass);
		}
	}

	@Override
	public void stop() throws SQLException {
		this.conn.close();		
	}

	@Override
	public synchronized void update(String expression) throws SQLException {
        Statement st = null;

        st = conn.createStatement();    // statements

        int i = st.executeUpdate(expression);    // run the query

        if (i == -1) {
        	log.error("db error : " + expression);
        }

        st.close();
	}
	@Override
	public ResultSet query(String query) throws SQLException {
        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();

        // repeated calls to execute but we
        // choose to make a new one each time
        rs = st.executeQuery(query); 
        return rs;
	}
	public ResultSet updateWithGeneratedKeys(String query) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
 
        st = conn.createStatement();

        // repeated calls to execute but we
        // choose to make a new one each time
        st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS); 
        return st.getGeneratedKeys();
	}

	@Override
	public void setConnection(String connectionString, String user, String pass) {
		this.connectionString=connectionString;
		this.user=user;
		this.pass=pass;
	}

	@Override
	public void clearDB() throws SQLException {
		String[] queries= {
				"DROP TABLE reference_model_objects;",
				"DROP TABLE reference_model_object_versions;"
		};
		for(String query : queries) {
			this.update(query);
		}		
	}

	@Override
	public void initializeDB() throws SQLException {
		String[] queries={
				"CREATE TABLE IF NOT EXISTS reference_model_objects ("+
				"id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,"+
				"reference_model_class_name VARCHAR(100) NOT NULL,"+
				"archetype_id VARCHAR(100),"+
				"node_id VARCHAR(100),"+
				"unique_id_path VARCHAR(1000),"+
				"reference_model_path VARCHAR(1000),"+
				"archetype_path VARCHAR(1000),"+
				"depth INTEGER,"+
				"serialized VARCHAR(10000)"+
			");",
			"CREATE TABLE IF NOT EXISTS reference_model_object_versions ("+
			"id INTEGER NOT NULL PRIMARY KEY,"+
			"next INTEGER DEFAULT NULL"+
			");",
			"INSERT INTO reference_model_objects VALUES(1,'ehrsystem',NULL,NULL,'/1','/','/',1,'identifier=<reference_model_class_name = <\"II\">root = <\"org.sigaim.siie\">>');",
			"INSERT INTO reference_model_object_versions VALUES(1,NULL);",

		};
		for(String query :queries) {
			try {
				this.update(query);
			} catch(Exception e) {
			}
		}		
	}

	@Override
	public void setConnection(Connection conn) {
		this.conn=conn;
	} 

}
