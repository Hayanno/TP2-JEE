package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import services.ResultSetToBean.ResultSetToBean;

public class JdbcTools {
	private String url      = "jdbc:mysql://localhost/TP2-JEE";
	private String user     = "root";
	private String password = "22itt6";
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void init() {
		if(this.url == null || this.user == null || this.password == null)
			throw new NullPointerException();
	}
	
	public void close() {
		
	}
	
	public Connection newConnection() throws SQLException {
	    Connection conn = DriverManager.getConnection(url, user, password);
	    
	    return conn;
	}
	
	public void quietClose(Connection conn) throws SQLException {
		conn.close();
	}
	
	public int executeUpdate(String sql, Object... parameters) 
		throws SQLException {
		int columnCounter = 1;
		
		try (Connection conn = newConnection()) {
			PreparedStatement st = conn.prepareStatement(sql);
			
			for(Object parameter : parameters) {
				st.setObject(columnCounter, parameter);
				columnCounter++;
			}
			
			int nb = st.executeUpdate();
				
			return nb;
		}
	}
	
	public <T> Collection<T> findBeans(String sql, ResultSetToBean<T> mapper)
		throws SQLException {
			return null;
		
	}
}
