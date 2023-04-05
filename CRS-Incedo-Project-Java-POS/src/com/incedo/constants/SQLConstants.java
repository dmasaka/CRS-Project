/**
 * 
 */
package com.incedo.constants;

/**
 * @author David Masaka
 *
 */
public class SQLConstants {
	public static final String 	LIST_STUDENTS = "select studentid, roleid, name, username, password, address, semesterid, isapproved from students";
	public static final String LOGIN_STUDENT = "select studentid, roleid, name, username, password, address, semesterid, isapproved from students where LOWER(username) = LOWER(?) and password=?";
	public static final String INSERT_STUDENT = "insert into students values (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UNAPPROVED_STUDENTS = "select studentid, roleid, name, username, password, address, semesterid, isapproved from students where isapproved=0";
	public static final String APPROVE_STUDENTS = "update students set isapproved=1 where true";
	public static final String LIST_PROFESSORS = "select professorid, roleid, name, username, password, address, department, designation, doj from professors";
	public static final String INSERT_PROFESSOR = "insert into professors values(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	public static final String LOGIN_PROFESSOR = "select professorid, roleid, name, username, password, address, department, designation, doj from professors where LOWER(username) = LOWER(?) and password=?";
	public static final String LIST_ADMINS = "select adminid, roleid, name, username, password, address, doj from admins";
	public static final String APPROVE_STUDENT = "update students set isapproved=1 where studentid=? ";
	public static final String LOGIN_ADMIN = "select adminid, roleid, name, username, password, address, doj from admins where LOWER(username) = LOWER(?) and password=?";
	public static final String LIST_COURSES = "select coursecode, name, isoffered, professorid from courses";
	public static final String INSERT_COURSE = "insert into courses values (?, ?, ?, ?) ";
	public static final String DELETE_COURSE = "delete from courses where courseCode=?";
	public static final String ADD_PROFESSOR = "update courses set professorid=? where courseCode=?";
	public static final String CHECK_PROFESSOR = "select professorid from courses where courseCode=?";
//	public static final String 
//	public static final String 
//	public static final String 
}
