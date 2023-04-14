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

import com.incedo.bean.Student;
import com.incedo.constants.SQLConstants;
import com.incedo.utils.DBUtils;

/**
 * @author David Masaka
 *
 */
public class StudentDAOImpl implements StudentDAOInterface {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;

	public List<Student> listOfStudents() {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<Student>();
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.LIST_STUDENTS);
			ResultSet rs = stmt.executeQuery();
			// int userId, String name, String username, String password, String address,
			// int studentId, int semesterId, String branch
			while (rs.next()) {
				students.add(new Student(rs.getInt("studentid"), rs.getString("name"), rs.getString("username"),
						rs.getString("password"), rs.getString("address"), rs.getInt("studentid"),
						rs.getInt("semesterid"), ""));
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
	
	public Student login(String username, String password) {
		// TODO Auto-generated method stub
		Student student = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.LOGIN_STUDENT);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				student = new Student(rs.getInt("studentid"), rs.getString("name"), rs.getString("username"),
						rs.getString("password"), rs.getString("address"), rs.getInt("studentid"),
						rs.getInt("semesterid"), "");
				System.out.println(rs.getInt("studentid"));
//				if (rs.getInt("isapproved") == 0) {
//					student.setUserId(-1);
//					student.setStudentId(-1);
//				}
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
		return student;
	}

	public void add(Student stud) {
		// TODO Auto-generated method stub
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.INSERT_STUDENT);
			// int userId, String name, String username, String password, String address,
			// int studentId, int semesterId, String branch
			// studentid, roleid, name, username, password, address, semesterid, isapproved
			stmt.setInt(1, 0);
			stmt.setInt(2, 0);
			stmt.setString(3, stud.getName());
			stmt.setString(4, stud.getUsername());
			stmt.setString(5, stud.getPassword());
			stmt.setString(6, stud.getAddress());
			stmt.setInt(7, stud.getSemesterId());
			stmt.setInt(8, 0);
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
	public List<Student> listOfUnapprovedStudents() {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<Student>();
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.UNAPPROVED_STUDENTS);
			ResultSet rs = stmt.executeQuery();
			// int userId, String name, String username, String password, String address,
			// int studentId, int semesterId, String branch
			
			while (rs.next()) {
				students.add(new Student(rs.getInt("studentid"), rs.getString("name"), rs.getString("username"),
						rs.getString("password"), rs.getString("address"), rs.getInt("studentid"),
						rs.getInt("semesterid"), ""));
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
	
	public void approveAll() {
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.APPROVE_STUDENTS);
			// int userId, String name, String username, String password, String address,
			// int studentId, int semesterId, String branch
			// studentid, roleid, name, username, password, address, semesterid, isapproved
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
