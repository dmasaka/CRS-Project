package com.incedo.client;

import java.util.List;
import java.util.Scanner;

import com.incedo.service.ProfessorInterface;
import com.incedo.service.ProfessorService;

public class CRSProfessorMenu {

	public void run(String username, String password, Scanner scan) {
		ProfessorInterface prof = new ProfessorService();
		int professorid = prof.findProfessorId(username, password);
		if (professorid == 0) {
			System.out.println("Invalid Username or Password");
			return;
		}
		System.out.println("Hello " + username + ", good to see you!");
		while (true) {
			System.out.println("Options: ");
			System.out.println("1: Add Grades, \n2: View Enrolled Students, \n3: Exit");
			System.out.println("Enter Action Number: ");
			int action = scan.nextInt();
			switch (action) {
			case 1:
				String courseCode;
				int studentid;
				String grade;
				scan.nextLine();
				System.out.println("Enter Course Code: ");
				courseCode = scan.nextLine();
				System.out.println("Enter Student ID: ");
				studentid = scan.nextInt();
				scan.nextLine();
				System.out.println("Enter Grade");
				grade = scan.nextLine();
				prof.addGrade(professorid, courseCode, studentid, grade);
				System.out.println();
				break;
			case 2:
				List<String> courses = prof.viewStudents(professorid);
				for (String cour : courses) {
					System.out.println(cour);
				}
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid Option");
			}
		}
	}
}
