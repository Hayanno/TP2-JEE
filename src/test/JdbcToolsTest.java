package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import app.JdbcTools;

public class JdbcToolsTest extends JdbcTools {
	private JdbcTools jdbcTools;
	private Connection conn;
	
	@Test(expected=NullPointerException.class)
	public void initPasswordNullTest() {
		jdbcTools = new JdbcTools();
		jdbcTools.setPassword(null);
		jdbcTools.init();
	}
	
	@Test(expected=NullPointerException.class)
	public void initUserNullTest() {
		jdbcTools = new JdbcTools();
		jdbcTools.setUser(null);
		jdbcTools.init();
	}
	
	@Test(expected=NullPointerException.class)
	public void initUrlNullTest() {
		jdbcTools = new JdbcTools();
		jdbcTools.setUrl(null);
		jdbcTools.init();
	}
	
	@Test
	public void testNewConnection() throws SQLException {
		jdbcTools = new JdbcTools();
		jdbcTools.init();
		conn = jdbcTools.newConnection();
		assertEquals(false, conn.isClosed());
	}
	
	@Test
	public void testQuietClose() throws SQLException {
		jdbcTools = new JdbcTools();
		jdbcTools.init();
		conn = jdbcTools.newConnection();
		jdbcTools.quietClose(conn);
		assertEquals(true, conn.isClosed());
	}
	
	@Test
	public void testExecuteUpdate() throws SQLException {
		final String sql = "INSERT INTO person"
				+ "(name, age) VALUES"
				+ "(?, ?)";
		final String name = "Victor";
		final int age = 23;
		
		jdbcTools = new JdbcTools();
		jdbcTools.init();
		conn = jdbcTools.newConnection();
		int nb = jdbcTools.executeUpdate(sql, name, age);
		assertEquals(1, nb);
	}
}
