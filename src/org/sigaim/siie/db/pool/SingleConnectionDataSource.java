package org.sigaim.siie.db.pool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.lang.NotImplementedException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class SingleConnectionDataSource implements DataSource {
	Boolean connectionAvailable=true;
	private String user;
	private String password;
	private String serverName;
	private int port;
	private String databaseName;
	Connection conn=null;
	
	public SingleConnectionDataSource() {
		
	}
	
	public void returnConnection() {
		this.connectionAvailable=true;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		if(conn==null) {
			MysqlDataSource basicDataSource = new MysqlDataSource();
			basicDataSource.setUser(this.getUser());
			basicDataSource.setPassword(this.getPassword());
			basicDataSource.setServerName(this.getServerName());
			basicDataSource.setPort(this.getPort());
			basicDataSource.setDatabaseName(this.getDatabaseName());	
			conn=basicDataSource.getConnection();
			conn=new DelegatingConnection(conn,this);
		}
		if(!this.connectionAvailable) {
			throw new SQLException("Out of connections...");
		}
		this.connectionAvailable=false;
		return conn;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		throw new NotImplementedException();
	}


}
