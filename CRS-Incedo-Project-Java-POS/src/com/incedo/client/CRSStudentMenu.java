package com.incedo.client;

import java.util.Scanner;
import java.util.TreeMap;

import com.incedo.service.PaymentInterface;
import com.incedo.service.PaymentService;
import com.incedo.service.StudentInterface;
import com.incedo.service.StudentService;

public class CRSStudentMenu {
	
	public void run(String username, String password, Scanner scan) {
		StudentInterface stud = new StudentService();
		int studentId = stud.findStudentId(username, password);
		if(studentId == 0 || studentId == -1) return;
		System.out.println("Hello " + username + ", good to see you!");
		System.out.println("Student Id: " + studentId);
		System.out.println("Options: ");
		System.out.println("1: Register for Semester, \n2: Add Course, \n3: Drop Course, \n4: View Grades, \n5: Pay Fee \n6: Exit");
		System.out.println("Enter Action Number: ");
		int action = scan.nextInt();
		String courseId;
		scan.nextLine();
		switch(action) {
		case 1:
			System.out.println("Enter Semester ID: ");
			courseId = scan.nextLine();
			System.out.println("ID: " + courseId);
			break;
		case 2:
			System.out.println("Enter Course ID: ");
			courseId = scan.nextLine();
			stud.addCourse(studentId, courseId);
			break;
		case 3:
			System.out.println("Enter Course ID: ");
			courseId = scan.nextLine();
			stud.deleteCourse(studentId, courseId);
			break;
		case 4:
			TreeMap<String, String> grades = stud.viewGrades(studentId);
			for (String courseCode: grades.keySet()) {
				System.out.println(courseCode + ": " + grades.get(courseCode));
			}
			break;
		case 5:
			System.out.println("Choose 1: Offline Payment or 2: Online Payment");
			int ofOrOn = scan.nextInt();
			PaymentInterface pay = new PaymentService();
			if(ofOrOn == 1) {
				System.out.println("Enter Bank Name: ");
				String bankname = scan.nextLine();
				System.out.println("Enter Check Number: ");
				int checkname = scan.nextInt();
				pay.offline(studentId, bankname, checkname);
			} else {
				System.out.println("Enter Card Number: ");
				int cardNumber = scan.nextInt();
				System.out.println("Enter Card Type: ");
				String cardType = scan.nextLine();
				pay.onlineCard(studentId, cardNumber, cardType);
			}
			break;
		case 6:
			return;
		default:
			System.out.println("Invalid Option");
		}
	}
	
	public void add(Scanner scan) {
		//int userId, String name, String username, String password, String address, int studentId, int semesterId, String branch
		scan.nextLine();
		System.out.println("Enter Name:");
		String name = scan.nextLine();
		System.out.println("Enter Username: ");
		String username = scan.nextLine();
		System.out.println("Enter Password: ");
		String password = scan.nextLine();
		System.out.println("Enter Address: ");
		String address = scan.nextLine();
		StudentInterface stud = new StudentService();
		stud.addStudent(name, username, password, address);
	}

}
