package com.incedo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.incedo.bean.CourseRegistration;
import com.incedo.bean.Student;
import com.incedo.dao.CourseRegistrationDAOImpl;
import com.incedo.dao.StudentDAOImpl;
import com.incedo.dao.StudentDAOInterface;
import com.incedo.exception.ApprovalNotDone;
import com.incedo.exception.UserNotFoundException;

@Service
public class StudentServiceOperation implements StudentInterface {

	public Student findStudentId(String username, String password) {
		StudentDAOInterface sdao = new StudentDAOImpl();
		try {
			Student stud = sdao.login(username, password);
			if (stud == null) throw new UserNotFoundException();
			if (stud.getStudentId() == -1) throw new ApprovalNotDone();
			return stud;
		} catch (UserNotFoundException ex) {
			System.out.println(ex.getMessage());
			return null;
		} catch (ApprovalNotDone ex) {
			System.out.println(ex.getMessage());
			return null;
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
	public List<CourseRegistration> viewGrades(int studentId) {
		CourseRegistrationDAOImpl courdata = new CourseRegistrationDAOImpl();
		return courdata.listOfCoursesByStudentId(studentId);
	}
}
