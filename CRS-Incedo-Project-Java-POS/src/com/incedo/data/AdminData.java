package com.incedo.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.incedo.bean.Admin;

public class AdminData {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static List<Admin> admins = new ArrayList<Admin>();
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/crsdata";
	static final String USER = "root";
	static final String PASS = "root";

	public static List<Admin> listOfAdmins() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "select adminid, roleid, name, username, password, address, doj from admins";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			//int userId, String name, String username, String password, String address, Date doj
			while(rs.next()){
				admins.add(new Admin(rs.getInt("adminid"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("address"), rs.getDate("doj")));
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}
		return admins;
	}
	
//	public static void add(Student stud) {
//		students.add(stud);
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("Connecting to database...");
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			String sql = "insert into students values(?, ?, ?, ?, ?, ?, ?, ?) ";
//			stmt = conn.prepareStatement(sql);
//			//int userId, String name, String username, String password, String address, int studentId, int semesterId, String branch
//			//studentid, roleid, name, username, password, address, semesterid, isapproved
//			stmt.setInt(1, stud.getUserId());
//			stmt.setInt(2, 0);
//			stmt.setString(3, stud.getName());
//			stmt.setString(4, stud.getUsername());
//			stmt.setString(5, stud.getPassword());
//			stmt.setString(6, stud.getAddress());
//			stmt.setInt(7, stud.getSemesterId());
//			stmt.setInt(8, 1);
//			stmt.executeUpdate();
//		} catch (SQLException se) {
//			// Handle errors for JDBC
//			se.printStackTrace();
//		} catch (Exception e) {
//			// Handle errors for Class.forName
//			e.printStackTrace();
//		} finally {
//			// finally block used to close resources
//			try {
//				if (stmt != null)
//					stmt.close();
//			} catch (SQLException se2) {
//			} // nothing we can do
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			} // end finally try
//		}
//	}
//	public static List<Admin> listOfAdmins(){
//		Admin first = new Admin(8484, "Eric Penn", "EricP", "Eric12", "102 Soup St. Philadelphia", new Date(84848484));
//		Admin second = new Admin(8485, "Bald Better", "BaldB", "Bald12", "87 Night Ln. New York", new Date(184848484));
//		Admin third = new Admin(8486, "Alex Soup", "AlexS", "Alex12", "64 Soul Rd. New Delhi", new Date(284848484));
//		admins.add(first);
//		admins.add(second);
//		admins.add(third);
//		return admins;
//	}
}
