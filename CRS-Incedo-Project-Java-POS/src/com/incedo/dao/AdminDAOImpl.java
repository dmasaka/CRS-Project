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

import com.incedo.bean.Admin;
import com.incedo.constants.SQLConstants;
import com.incedo.exception.UserNotFoundException;
import com.incedo.utils.DBUtils;

/**
 * @author David Masaka
 *
 */
public class AdminDAOImpl implements AdminDAOInterface {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static List<Admin> admins = new ArrayList<Admin>();

	@Override
	public List<Admin> listOfAdmins() {
		// TODO Auto-generated method stub
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.LIST_ADMINS);
			ResultSet rs = stmt.executeQuery();
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
	
	public void approveStudent(int studentid) {
		// TODO Auto-generated method stub
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.APPROVE_STUDENT);
			stmt.setInt(1, studentid);
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
	public Admin login(String username, String password) throws UserNotFoundException {
		Admin admin = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstants.LOGIN_ADMIN);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			//int userId, String name, String username, String password, String address, Date doj
			while(rs.next()){
				admin = new Admin(rs.getInt("adminid"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("address"), rs.getDate("doj"));
			}
			if(admin == null) throw new UserNotFoundException();
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
		return admin;
	}

}
