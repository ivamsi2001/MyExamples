package com.venkat.jdbcexamples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCRun {
	
	public static void displayEmpData() {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = DataService.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from employee");
			while (rs.next()) {
				System.out.println(rs.getString("id") + "," + rs.getString("name") + "," + rs.getString("department_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataService.closeSQLResoureces(rs, stmt, connection);
		}
	}
	
	public static void insertEmp(Emp... empObjs) {
		Connection connection = null;
		PreparedStatement psmt = null;
		try {
			connection = DataService.getConnection();
			psmt = connection.prepareStatement("insert into employee values (?,?,?)");
			for (Emp empObj : empObjs) {
				psmt.setInt(1, empObj.getId());
				psmt.setString(2, empObj.getName());
				psmt.setInt(3, empObj.getDept());
				psmt.addBatch();
				psmt.clearParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataService.closeSQLResoureces(psmt, connection);
		}
	}
	
	public static void main(String[] args) {
		Emp empObj = new Emp();
		empObj.setId(5);
		empObj.setName("ABC");
		empObj.setDept(1);
		//
		insertEmp(empObj);
		//
		displayEmpData();
	}

}
