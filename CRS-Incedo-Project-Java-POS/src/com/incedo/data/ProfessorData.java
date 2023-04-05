/**
 * 
 */
package com.incedo.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.incedo.bean.Professor;

/**
 * @author David Masaka
 *
 */
//int userId, String name, String username, String password, String address, String department, String designation, Date doj
public class ProfessorData {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static List<Professor> profs = new ArrayList<Professor>();
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/crsdata";
	static final String USER = "root";
	static final String PASS = "root";
	
	public static List<Professor> listOfProfessors() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "select professorid, roleid, name, username, password, address, department, designation, doj from professors";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			//int userId, String name, String username, String password, String address, String department, String designation, Date doj
			while(rs.next()){
				profs.add(new Professor(rs.getInt("userid"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("address"), rs.getString("department"),
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
//	public static List<Professor> listOfProfessors() {
//		Professor first = new Professor(1919, "Albert Ein", "AlbertE", "Albert12", "202 Hamburg", "Physics", "Associate", new Date(202084834));
//		Professor second = new Professor(1920, "Soul Man", "SoulM", "Soul12", "33 Outer Space", "Astronomy", "Chair", new Date(204084834));
//		Professor third = new Professor(1921, "Yolo Right", "YoloR", "Yolo12", "988 Texas", "Cowboys", "Associate", new Date(205084834));
//		professors.add(first);
//		professors.add(second);
//		professors.add(third);
//		return professors;
//	}
}
