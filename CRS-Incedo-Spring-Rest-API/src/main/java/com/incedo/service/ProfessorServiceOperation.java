package com.incedo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.incedo.bean.CourseRegistration;
import com.incedo.bean.Professor;
import com.incedo.dao.CourseRegistrationDAOImpl;
import com.incedo.dao.CourseRegistrationDAOInterface;
import com.incedo.dao.ProfessorDAOImpl;
import com.incedo.dao.ProfessorDAOInterface;
import com.incedo.exception.IncorrectGradeException;
import com.incedo.exception.UserNotFoundException;


@Service
public class ProfessorServiceOperation implements ProfessorInterface {
	public Professor findProfessorId(String username, String password) {
		ProfessorDAOInterface pdao = new ProfessorDAOImpl();
		try {
			Professor prof = pdao.login(username, password);
			if (prof == null)
				throw new UserNotFoundException();
			return prof;
		} catch (UserNotFoundException ex) {
			return null;
		}
	}

	// probably take in an id, course, and Professor id
	public boolean addGrade(int professorId, String courseId, int studentid, String grade) {
		try {
			if (grade.length() > 5)
				throw new IncorrectGradeException();
		} catch (IncorrectGradeException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		CourseRegistrationDAOInterface crdao = new CourseRegistrationDAOImpl();
		crdao.changeGrade(courseId, studentid, grade);
		return true;
	}

	// probably take in an id, course, and Professor id
	// also probably return some collection of Professors and grades
	public List<CourseRegistration> viewStudents(int professorId) {
		CourseRegistrationDAOInterface crdao = new CourseRegistrationDAOImpl();
		return crdao.listOfCoursesByProfessorId(professorId);
	}
}
