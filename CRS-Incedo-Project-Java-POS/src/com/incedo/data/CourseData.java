package com.incedo.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.incedo.bean.Course;

public class CourseData {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static List<Course> courses = new ArrayList<Course>();
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/crsdata";
	static final String USER = "root";
	static final String PASS = "root";
	
	public static List<Course> listOfCourses() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "select coursecode, name, isoffered, professorid from courses";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			//String courseCode, String name, boolean isOffered, String professorId
			while(rs.next()){
				courses.add(new Course(rs.getString("coursecode"), rs.getString("name"), rs.getBoolean("isoffered"), rs.getInt("professorid")));
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
		return courses;
	}
	
//	public static List<Course> listOfCourses(){
//		Course first = new Course("CMP101", "Computer Introduction", true, "192934");
//		Course second = new Course("ART101", "Art Introduction", true, "24224");
//		Course third = new Course("CMP244", "Computer Modeling", false, "444934");
//		courses.add(first);
//		courses.add(second);
//		courses.add(third);
//		return courses;
//	}
}
