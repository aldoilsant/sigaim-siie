package org.sigaim.siie.db.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLWrapper {
	public void setConnection(String connectionString, String user, String pass);
	public void setConnection(Connection conn);
	public void start() throws SQLException;
	public void stop() throws SQLException;
	public void clearDB() throws SQLException;
	public void initializeDB() throws SQLException;
	public void update(String query)  throws SQLException;
	public ResultSet query(String query) throws SQLException;
	public ResultSet updateWithGeneratedKeys(String query) throws SQLException;
}
