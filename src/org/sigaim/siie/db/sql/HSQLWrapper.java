package org.sigaim.siie.db.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HSQLWrapper implements SQLWrapper {
	private String connectionString="sigaim";
	private String user;
	private String pass;
	private Connection conn;
	
	public HSQLWrapper()   {	

	}

	@Override
	public void start() throws SQLException {
		try {
			 Class.forName("org.hsqldb.jdbcDriver");
		} catch(Exception e){
				e.printStackTrace();;
		}
		conn = DriverManager.getConnection(connectionString,user,pass);
	}

	@Override
	public void stop() throws SQLException {
		this.update("SHUTDOWN");
		this.conn.close();
		
	}

	@Override
	public synchronized void update(String expression) throws SQLException {
        Statement st = null;

        st = conn.createStatement();    // statements

        int i = st.executeUpdate(expression);    // run the query

        if (i == -1) {
            System.out.println("db error : " + expression);
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

 
}
