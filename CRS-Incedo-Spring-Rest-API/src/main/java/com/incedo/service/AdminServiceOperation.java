package com.incedo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.incedo.bean.Admin;
import com.incedo.bean.Course;
import com.incedo.bean.Professor;
import com.incedo.bean.Student;
import com.incedo.dao.AdminDAOImpl;
import com.incedo.dao.AdminDAOInterface;
import com.incedo.dao.CourseDAOImpl;
import com.incedo.dao.CourseDAOInterface;
import com.incedo.dao.ProfessorDAOImpl;
import com.incedo.dao.ProfessorDAOInterface;
import com.incedo.dao.StudentDAOImpl;
import com.incedo.dao.StudentDAOInterface;
import com.incedo.exception.UserNotFoundException;

@Service
public class AdminServiceOperation implements AdminInterface {

	public Admin findAdminId(String username, String password) {
		AdminDAOInterface adao = new AdminDAOImpl();
		try {
			Admin admin = adao.login(username, password);
			if (admin == null) throw new UserNotFoundException();
			return admin;
		} catch (UserNotFoundException ex) {
			return null;
		}
	}

	// probably take in a id, Admin id
	// probably return a collection of courses and grades
	public void generateReportCard(int userId, int studentId, String grades) {

	}

	// probably take in an id and professor information
	public void addProfessor(String name, String username, String password, String address, String department,
			String designation, Date datep) {
		java.sql.Date date = new java.sql.Date(datep.getTime());
		Professor prof = new Professor(0, name, username, password, address, department, designation, date);
		ProfessorDAOInterface pdao = new ProfessorDAOImpl();
		pdao.add(prof);
	}

	// probably take in id, Admin id, course
	public boolean approveAdminRegistration() {
		return true;
	}

	// take in id, course
	public boolean addCourse(String courseCode, String name, boolean isOffered, int professorid) {
		Course course = new Course(courseCode, name, isOffered, professorid);
		CourseDAOInterface cdao = new CourseDAOImpl();
		cdao.addCourse(course);
		return true;
	}

	public boolean removeCourse(String courseCode) {
		CourseDAOInterface cdao = new CourseDAOImpl();
		cdao.deleteCourse(courseCode);
		return true;
	}

	public boolean passwordChange(int userId, String password) {
		return true;
	}

	public void approveStudentRegistration(int studentid) {
		AdminDAOInterface adao = new AdminDAOImpl();
		adao.approveStudent(studentid);
	}

	public void registerStudent() {
	}

	public List<Student> listOfUnapproved() {
		StudentDAOInterface sdao = new StudentDAOImpl();
		return sdao.listOfUnapprovedStudents();
	}

	public void approveAll() {
		StudentDAOInterface sdao = new StudentDAOImpl();
		sdao.approveAll();
	}

	@Override
	public int addProfessorToCourse(String courseCode, int professorid) {
		// TODO Auto-generated method stub
		return 0;
	}
}
