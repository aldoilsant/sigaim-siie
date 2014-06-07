package org.sigaim.siie.db.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLWrapper {
	public void setConnection(String connectionString, String user, String pass);
	public void start() throws SQLException;
	public void stop() throws SQLException;
	public void update(String query)  throws SQLException;
	public ResultSet query(String query) throws SQLException;
	public ResultSet updateWithGeneratedKeys(String query) throws SQLException;
}
