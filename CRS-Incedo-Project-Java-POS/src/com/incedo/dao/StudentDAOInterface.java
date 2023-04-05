package com.incedo.dao;

import java.util.List;

import com.incedo.bean.Student;
import com.incedo.exception.ApprovalNotDone;
import com.incedo.exception.UserNotFoundException;

public interface StudentDAOInterface {
	public List<Student> listOfStudents();
	public void add(Student stud);
	public Student login(String username, String password) throws UserNotFoundException, ApprovalNotDone;
	public List<Student> listOfUnapprovedStudents();
	public void approveAll();
}
