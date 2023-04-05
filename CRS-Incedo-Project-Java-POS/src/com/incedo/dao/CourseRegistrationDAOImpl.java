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

import com.incedo.bean.CourseRegistration;
import com.incedo.utils.DBUtils;

/**
 * @author David Masaka
 *
 */
public class CourseRegistrationDAOImpl implements CourseRegistrationDAOInterface {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	public List<CourseRegistration> listOfCourses() {
		List<CourseRegistration> couregs = new ArrayList<CourseRegistration>();
		try {
			conn = DBUtils.getConnection();
			String sql = "select courseregid, coursecode, studentid, grade from courseregisters";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			//int courseregid, String coursecode, int studentid, String grade
			while(rs.next()){
				couregs.add(new CourseRegistration(rs.getInt("courseregid"), rs.getString("coursecode"), rs.getInt("studentid"), rs.getString("grade")));
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
		return couregs;
	}
	
	public List<CourseRegistration> listOfCoursesByStudentId(int studentid) {
		List<CourseRegistration> couregs = new ArrayList<CourseRegistration>();
		try {
			conn = DBUtils.getConnection();
			String sql = "select courseregid, coursecode, studentid, grade from courseregisters where studentid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentid);
			ResultSet rs = stmt.executeQuery();
			//int courseregid, String coursecode, int studentid, String grade
			while(rs.next()){
				couregs.add(new CourseRegistration(rs.getInt("courseregid"), rs.getString("coursecode"), rs.getInt("studentid"), rs.getString("grade")));
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
		return couregs;
	}
	
	public List<CourseRegistration> listOfCoursesByProfessorId(int professorid) {
		List<CourseRegistration> couregs = new ArrayList<CourseRegistration>();
		try {
			conn = DBUtils.getConnection();
			String sql = "select courseregid, courses.coursecode, studentid, grade "
					+ "from courses inner join courseregisters "
					+ "on courses.coursecode=courseregisters.coursecode where professorid=? order by coursecode";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorid);
			ResultSet rs = stmt.executeQuery();
			//int courseregid, String coursecode, int studentid, String grade
			while(rs.next()){
				couregs.add(new CourseRegistration(rs.getInt("courseregid"), rs.getString("coursecode"), rs.getInt("studentid"), rs.getString("grade")));
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
		return couregs;
	}
	
	public void changeGrade(String courseCode, int studentid, String grade) {
		try {
			conn = DBUtils.getConnection();
//			String sql = "select courseregid, coursecode, studentid, grade from courseregisters where studentid=?";
			String sql = "update courseregisters set grade=? where coursecode=? and studentid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, grade);
			stmt.setNString(2, courseCode);
			stmt.setInt(3, studentid);
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
	
	public void addCourseRegistration(int studentid, String courseCode) {
		try {
			conn = DBUtils.getConnection();
			//courseregid, coursecode, studentid, grade
			String sql = "insert into courseregisters values (?, ?, ?, ?) ";
			stmt = conn.prepareStatement(sql);
			//int userId, String name, String username, String password, String address, int studentId, int semesterId, String branch
			//studentid, roleid, name, username, password, address, semesterid, isapproved
			stmt.setInt(1, 0);
			stmt.setString(2, courseCode);
			stmt.setInt(3, studentid);
			stmt.setString(4, "None");
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
	
	public void deleteCourseRegistration(int studentid, String courseCode) {
		try {
			conn = DBUtils.getConnection();
			//courseregid, coursecode, studentid, grade
//			String sql = "insert into courseregisters values (?, ?, ?, ?) ";
			String sql = "delete from courseregisters where studentid=? and coursecode=? ";
			stmt = conn.prepareStatement(sql);
			//int userId, String name, String username, String password, String address, int studentId, int semesterId, String branch
			//studentid, roleid, name, username, password, address, semesterid, isapproved
			stmt.setInt(1, studentid);
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
	}
}
