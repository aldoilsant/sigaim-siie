package org.sigaim.siie.db.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLWrapper {
	public void clearDB(Connection con) throws SQLException;
	public void initializeDB(Connection con) throws SQLException;
	public void update(String query, Connection con)  throws SQLException;
	public ResultSet query(String query, Connection con) throws SQLException;
	public ResultSet updateWithGeneratedKeys(String query, Connection con) throws SQLException;
	public ResultSet updatePreparedStatementWithGeneratedKeys(PreparedStatement statement) throws SQLException;
	public void updatePreparedStatement(PreparedStatement statement) throws SQLException;
}
