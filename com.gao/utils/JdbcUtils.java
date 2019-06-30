package com.gao.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	static {
		try {
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			driver = prop.getProperty("jdbc.driver");
			url = prop.getProperty("jdbc.url");
			username = prop.getProperty("jdbc.username");
			password = prop.getProperty("jdbc.password");
			
			Class.forName(driver);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,username,password);
	}
	
	public static void free(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(rs != null) rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if(conn != null) conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
