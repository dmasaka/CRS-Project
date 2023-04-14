package com.incedo.client;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.incedo.service.ProfessorInterface;
import com.incedo.service.ProfessorService;

public class CRSProfessorMenu {

	/**
	 * runs the menu for the professor client
	 * @param username
	 * @param password
	 * @param scan
	 */
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
				List<List<String>> courses = prof.viewStudents(professorid);
				System.out.println("Choose Sorting Field 1: by grade 2: by id");
				int sm = scan.nextInt();
				System.out.printf("%-9s%-12s%-9s\n", "COURSE", "STUDENT ID", "GRADE");
				if (sm == 1) {
					courses.stream()
					.sorted(Comparator.comparing(e -> e.get(2)))
					.sorted(Comparator.comparing(e -> e.get(0)))
					.forEach(e -> System.out.printf("%-9s%-12s%-9s\n", e.get(1), e.get(0), e.get(2)));
				} else {
					courses.stream()
					.sorted(Comparator.comparing(e -> e.get(1)))
					.sorted(Comparator.comparing(e -> e.get(0)))
					.forEach(e -> System.out.printf("%-9s%-12s%-9s\n", e.get(1), e.get(0), e.get(2)));
				}
				System.out.printf("\n");
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid Option");
			}
		}
	}
}
