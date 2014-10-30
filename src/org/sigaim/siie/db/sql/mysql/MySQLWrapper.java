package org.sigaim.siie.db.sql.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.db.sql.SQLWrapper;

public class MySQLWrapper implements SQLWrapper{
	private static org.apache.log4j.Logger log = Logger.getLogger(MySQLWrapper.class);
	
	@Override
	public void update(String expression, Connection conn) throws SQLException {
        Statement st = null;

        st = conn.createStatement();    // statements

        int i = st.executeUpdate(expression);    // run the query

        if (i == -1) {
        	log.error("db error : " + expression);
        }

        st.close();
	}
	@Override
	public ResultSet query(String query, Connection conn) throws SQLException {
        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();

        // repeated calls to execute but we
        // choose to make a new one each time
        rs = st.executeQuery(query); 
        return rs;
	}
	public ResultSet updateWithGeneratedKeys(String query, Connection conn) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
 
        st = conn.createStatement();

        // repeated calls to execute but we
        // choose to make a new one each time
        st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS); 
        return st.getGeneratedKeys();
	}

	@Override
	public void clearDB(Connection conn) throws SQLException {
		String[] queries= {
				"DROP TABLE reference_model_objects;",
				"DROP TABLE reference_model_object_versions;",
				"DROP TABLE indexes;"
		};
		for(String query : queries) {
			try {
				this.update(query,conn);
			} catch(Exception e) {
				e.printStackTrace();
			}		
		}		
	}

	@Override
	public void initializeDB(Connection conn) throws SQLException {
		String[] queries={
				"CREATE TABLE IF NOT EXISTS reference_model_objects ("+
				"id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,"+
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
			"id  BIGINT UNSIGNED NOT NULL PRIMARY KEY,"+
			"next  BIGINT UNSIGNED DEFAULT NULL"+
			");",
			"CREATE TABLE IF NOT EXISTS indexes ("+
			"index_id  BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,"+
			"index_value  BIGINT UNSIGNED  DEFAULT 0,"+
			"index_name VARCHAR(100) NOT NULL"+
			");",
			"INSERT INTO reference_model_objects VALUES(1,'ehrsystem',NULL,NULL,'/1','/','/',1,'identifier=<reference_model_class_name = <\"II\">root = <\"org.sigaim.siie\">>');",
			"INSERT INTO reference_model_object_versions VALUES(1,NULL);",
			"INSERT INTO indexes VALUES(1,0,'all_ehrs');",
			"INSERT INTO indexes VALUES(2,0,'all_subjects_of_care');",
			"INSERT INTO indexes VALUES(3,0,'all_performers');",
			"INSERT INTO indexes VALUES(4,0,'all_healthcare_facilities');",
			"CREATE INDEX unique_id_path_index ON reference_model_objects(unique_id_path(767)) USING BTREE;",
			"CREATE INDEX class_name_index ON reference_model_objects(reference_model_class_name) USING BTREE"
		};
		for(String query :queries) {
			try {
				this.update(query,conn);
			} catch(Exception e) {
				//e.printStackTrace();
			}
		}		
	}
	@Override
	public ResultSet updatePreparedStatementWithGeneratedKeys(
			PreparedStatement statement) throws SQLException {
		statement.executeUpdate();
		return statement.getGeneratedKeys();
	}
	@Override
	public void updatePreparedStatement(PreparedStatement statement)
			throws SQLException {
		statement.executeUpdate();
	}

}
