package com.incedo.service;

import java.util.List;

public interface ProfessorInterface {
	public int findProfessorId(String username, String password);
	public boolean addGrade(int professorId, String courseId, int studentid, String grade);
	public List<List<String>> viewStudents(int professorId);
}
