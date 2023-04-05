package com.incedo.service;

import java.util.List;
import java.util.TreeMap;

import com.incedo.bean.CourseRegistration;
import com.incedo.bean.Student;
import com.incedo.dao.CourseRegistrationDAOImpl;
import com.incedo.dao.StudentDAOImpl;
import com.incedo.dao.StudentDAOInterface;
import com.incedo.exception.ApprovalNotDone;
import com.incedo.exception.UserNotFoundException;

public class StudentService implements StudentInterface {

	public int findStudentId(String username, String password) {
		StudentDAOInterface sdao = new StudentDAOImpl();
		try {
			Student stud = sdao.login(username, password);
			if (stud == null) throw new UserNotFoundException();
			if (stud.getStudentId() == -1) throw new ApprovalNotDone();
			return stud.getStudentId();
		} catch (UserNotFoundException ex) {
			System.out.println(ex.getMessage());
			return 0;
		} catch (ApprovalNotDone ex) {
			System.out.println(ex.getMessage());
			return -1;
		}
	}

	public void addStudent(String name, String username, String password, String address) {
		StudentDAOInterface sdao = new StudentDAOImpl();
		Student stud = new Student(0, name, username, password, address, 0, 0, "");
		sdao.add(stud);
	}

	// probably change the input to a student id and course
	public void registerForCourse(int studentId, String courseCode) {
		CourseRegistrationDAOImpl courdata = new CourseRegistrationDAOImpl();
		courdata.addCourseRegistration(studentId, courseCode);
	}

	// probably change the input to a student id and course
	public void addCourse(int studentId, String courseCode) {
		CourseRegistrationDAOImpl courdata = new CourseRegistrationDAOImpl();
		courdata.addCourseRegistration(studentId, courseCode);
	}

	// probably change the input to a student id and course
	public void deleteCourse(int studentId, String courseCode) {
		CourseRegistrationDAOImpl courdata = new CourseRegistrationDAOImpl();
		courdata.deleteCourseRegistration(studentId, courseCode);
	}

	// probably change the input to a student id
	public TreeMap<String, String> viewGrades(int studentId) {
		TreeMap<String, String> grades = new TreeMap<String, String>();
		CourseRegistrationDAOImpl courdata = new CourseRegistrationDAOImpl();
		List<CourseRegistration> courses = courdata.listOfCoursesByStudentId(studentId);
//		List<CourseRegistration> courses = courdata.listOfCourses();
		for (CourseRegistration coureg : courses) {
			grades.put(coureg.getCoursecode(), coureg.getGrade());
		}
		return grades;
	}
}
