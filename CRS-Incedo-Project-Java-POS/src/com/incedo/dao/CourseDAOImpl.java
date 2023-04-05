/**
 * 
 */
package com.incedo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.incedo.bean.Course;
import com.incedo.constants.SQLConstants;
import com.incedo.utils.DBUtils;

/**
 * @author David Masaka
 *
 */
public class CourseDAOImpl implements CourseDAOInterface {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static List<Course> courses = new ArrayList<Course>();

	@Override
	public List<Course> listOfCourses() {
		// TODO Auto-generated method stub
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.LIST_COURSES);
			ResultSet rs = stmt.executeQuery();
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
	
	public void addCourse(Course course) {
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.INSERT_COURSE);
			// int userId, String name, String username, String password, String address,
			// int studentId, int semesterId, String branch
			// studentid, roleid, name, username, password, address, semesterid, isapproved
			stmt.setString(1, course.getCourseCode());
			stmt.setString(2, course.getName());
			stmt.setBoolean(3, course.isOffered());
			stmt.setInt(4, course.getProfessorId());
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
	
	public void deleteCourse(String courseCode) {
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.DELETE_COURSE);
			// int userId, String name, String username, String password, String address,
			// int studentId, int semesterId, String branch
			// studentid, roleid, name, username, password, address, semesterid, isapproved
			stmt.setString(1, courseCode);
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

	@Override
	public int addProfessor(String courseCode, int professorid) {
		if (checkProfessor(courseCode) != 0) return -1;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.ADD_PROFESSOR);
			stmt.setInt(1, professorid);
			stmt.setString(2, courseCode);
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
		return 0;
	}
	
	public int checkProfessor(String courseCode) {
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.CHECK_PROFESSOR);
			stmt.setString(1, courseCode);
			ResultSet rs = stmt.executeQuery();
			//String courseCode, String name, boolean isOffered, String professorId
			while(rs.next()){
				return 0;
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
		return -1;
	}

}
