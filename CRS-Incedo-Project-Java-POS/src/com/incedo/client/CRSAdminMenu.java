package com.incedo.client;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.incedo.service.AdminInterface;
import com.incedo.service.AdminService;

public class CRSAdminMenu {

	/**
	 * runs the client for the admin menu
	 * @param username
	 * @param password
	 * @param scan
	 */
	public void run(String username, String password, Scanner scan) {
		AdminInterface adm = new AdminService();
		int adminid = adm.findAdminId(username, password);
		if (adminid == 0) {
			System.out.println("Invalid Username or Password");
			return;
		}
		System.out.println("Hello " + username + ", good to see you!");
		System.out.println("Options: ");
		System.out.println(
				"1: Generate Report Card, \n2: Add Professor, \n3: Approve Student Registration, \n4: Add Course, \n5: Remove Course, \n6: Exit");
		System.out.println("Enter Action Number: ");
		int action = scan.nextInt();
		int studentId;
		String courseCode;
//		int professorId;
		switch (action) {
		case 1:
			System.out.println("Enter Student Id: ");
			studentId = scan.nextInt();
			break;
		case 2:
			scan.nextLine();
			System.out.println("Enter Professor Information");
			System.out.println("Name: ");
			String name = scan.nextLine();
			System.out.println("Username: ");
			String userp = scan.nextLine();
			System.out.println("Password: ");
			String passp = scan.nextLine();
			System.out.println("Address: ");
			String addressp = scan.nextLine();
			System.out.println("Department: ");
			String depp = scan.nextLine();
			System.out.println("Designation: ");
			String desp = scan.nextLine();
			System.out.println("Date of Joining (dd/mm/yyyy include 0): ");
			String datestrp = scan.nextLine();
			SimpleDateFormat format= new SimpleDateFormat("dd/mm/yyyy");
			try {
				Date datep = format.parse(datestrp);
				adm.addProfessor(name, userp, passp, addressp, depp, desp, datep);
			} catch (ParseException e) {
				adm.addProfessor(name, username, password, addressp, depp, desp, null);
				e.printStackTrace();
			}
			break;
		case 3:
			List<String> studs = adm.listOfUnapproved();
			for (String stud : studs) {
				System.out.println(stud);
			}
			System.out.println("Enter 1: to approve 1 student or 2: to approve all students");
			int onlyone = scan.nextInt();
			if (onlyone == 1) {
				System.out.println("Enter Student Id: ");
				studentId = scan.nextInt();
				adm.approveStudentRegistration(studentId);
			} else {
				adm.approveAll();
			}
			break;
		case 4:
			scan.nextLine();
			System.out.println("Enter Course Code: ");
			String coursec = scan.nextLine();
			System.out.println("Enter Course Name");
			String coursen = scan.nextLine();
			System.out.println("Enter 1: for course offered or 2: for course not offered");
			boolean courseb = (scan.nextInt()) == 1 ? true : false;
			System.out.println("Enter Course Professor Id (0 for none): ");
			int profid = scan.nextInt();
			adm.addCourse(coursec, coursen, courseb, profid);
			break;
		case 5:
			System.out.println("Enter Course Id: ");
			courseCode = scan.nextLine();
			adm.removeCourse(courseCode);
			break;
		case 6:
			return;
		default:
			System.out.println("Invalid Option");
		}
	}

}
