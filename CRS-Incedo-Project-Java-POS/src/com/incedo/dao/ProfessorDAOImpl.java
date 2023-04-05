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

import com.incedo.bean.Professor;
import com.incedo.constants.SQLConstants;
import com.incedo.exception.UserNotFoundException;
import com.incedo.utils.DBUtils;

/**
 * @author David Masaka
 *
 */
public class ProfessorDAOImpl implements ProfessorDAOInterface {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static List<Professor> profs = new ArrayList<Professor>();
	

	@Override
	public List<Professor> listOfProfessors() {
		// TODO Auto-generated method stub
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.LIST_PROFESSORS);
			ResultSet rs = stmt.executeQuery();
			//int userId, String name, String username, String password, String address, String department, String designation, Date doj
			while(rs.next()){
				profs.add(new Professor(rs.getInt("professorid"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("address"), rs.getString("department"),
						rs.getString("designation"), rs.getDate("doj")));
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
		return profs;
	}
	
	//professorid, roleid, name, username, password, address, departemnt, designation, doj
	
	public void add(Professor prof) {
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.INSERT_PROFESSOR);
			// int userId, String name, String username, String password, String address,
			// professorid, roleid, name, username, password, address, department, designation, doj
			stmt.setInt(1, 0);
			stmt.setInt(2, 0);
			stmt.setString(3, prof.getName());
			stmt.setString(4, prof.getUsername());
			stmt.setString(5, prof.getPassword());
			stmt.setString(6, prof.getAddress());
			stmt.setString(7, prof.getDepartment());
			stmt.setString(8, prof.getDesignation());
			stmt.setDate(9, prof.getDoj());
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
	public Professor login(String username, String password) {
		Professor prof = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.LOGIN_PROFESSOR);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			//int userId, String name, String username, String password, String address, String department, String designation, Date doj
			while(rs.next()){
				prof = new Professor(rs.getInt("professorid"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("address"), rs.getString("department"),
						rs.getString("designation"), rs.getDate("doj"));
			}
			if(prof == null) throw new UserNotFoundException();
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
		return prof;
	}
}
