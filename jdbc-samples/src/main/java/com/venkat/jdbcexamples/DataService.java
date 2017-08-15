package com.venkat.jdbcexamples;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataService {

	private static String ConnectionString;
	private static String user;
	private static String pwd;

	static {
		InputStream in = null;
		try {
			//
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			//in = new FileInputStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			//
			String Driver = prop.getProperty("driver");
			ConnectionString = prop.getProperty("jdbcurl");
			user = prop.getProperty("dbusername");
			pwd = prop.getProperty("dbpassword");
			//
			Class.forName(Driver);
			//
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private DataService() {
		//
	}

	public static Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(ConnectionString, user, pwd);
		return con;
	}

	public static void closeSQLResoureces(ResultSet rs, Statement pstmt, Connection con) {
		closeSQLResoureces(rs, pstmt);
		closeSQLResoureces(con);
	}
	
	public static void closeSQLResoureces(Statement pstmt, Connection con) {
		closeSQLResoureces(pstmt);
		closeSQLResoureces(con);
	}

	public static void closeSQLResoureces(ResultSet rs, Statement pstmt) {
		closeSQLResoureces(rs);
		closeSQLResoureces(pstmt);
	}

	public static void closeSQLResoureces(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeSQLResoureces(Statement... stmts) {
		try {
			if (stmts != null) {
				for (Statement stmt : stmts) {
					stmt.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeSQLResoureces(ResultSet... rss) {
		try {
			if (rss != null) {
				for (ResultSet rs : rss) {
					rs.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}