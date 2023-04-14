/**
 * 
 */
package com.incedo.data;

import java.util.ArrayList;
import java.util.List;

import com.incedo.bean.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author David Masaka
 *
 */
public class StudentData {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static List<Student> students = new ArrayList<Student>();
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/crsdata";
	static final String USER = "root";
	static final String PASS = "root";

	public static List<Student> listOfStudents() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "select studentid, roleid, name, username, password, address, semesterid, isapproved from students";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			//int userId, String name, String username, String password, String address, int studentId, int semesterId, String branch
			while(rs.next()){
				students.add(new Student(rs.getInt("studentid"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("address"), rs.getInt("studentid"), rs.getInt("semesterid"), ""));
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
		return students;
	}
	
	public static void add(Student stud) {
		students.add(stud);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "insert into students values(?, ?, ?, ?, ?, ?, ?, ?) ";
			stmt = conn.prepareStatement(sql);
			//int userId, String name, String username, String password, String address, int studentId, int semesterId, String branch
			//studentid, roleid, name, username, password, address, semesterid, isapproved
			stmt.setInt(1, stud.getUserId());
			stmt.setInt(2, 0);
			stmt.setString(3, stud.getName());
			stmt.setString(4, stud.getUsername());
			stmt.setString(5, stud.getPassword());
			stmt.setString(6, stud.getAddress());
			stmt.setInt(7, stud.getSemesterId());
			stmt.setInt(8, 1);
			stmt.executeUpdate();
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
	}

//	public static List<Student> listOfStudents() {
//		Student first = new Student(101, "David", "thedavid", "David12", "101 Main St. New York", 1010, 1, "computers");
//		Student second = new Student(102, "Amit", "theamit", "Amit12", "1 First St. New Jersey", 1011, 1, "IT");
//		Student third = new Student(103, "Pat", "thepat", "Pat12", "42 Second St. Texas", 1012, 1, "Art");
//		students.add(first);
//		students.add(second);
//		students.add(third);
//		return students;
//	}
}
