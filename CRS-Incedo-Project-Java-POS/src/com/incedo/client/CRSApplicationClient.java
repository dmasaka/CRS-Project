package com.incedo.client;

import java.util.Scanner;

public class CRSApplicationClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		while(true) {
		System.out.println("Welcome to the CRS Application !");
		System.out.println("Press 1: login \nPress 2: -- Registration of the Student \nPress 3: Update Password");
			System.out.println("Press 4: -- Exit");
			int action = scan.nextInt();
			if(action == 4) {
				scan.close();
				return;
			}
			String username, password;
			int role;
			switch(action) {
			case 1:
				scan.nextLine();
				System.out.println("Enter Username: ");
				username = scan.nextLine();
				System.out.println("Enter Password: ");
				password = scan.nextLine();
				System.out.println("Enter Role (1: Student, 2: Professor, 3: Admin) : ");
				role = scan.nextInt();
				switch(role) {
					case 1:
						CRSStudentMenu menu1 = new CRSStudentMenu();
						menu1.run(username, password, scan);
						break;
					case 2:
						CRSProfessorMenu menu2 = new CRSProfessorMenu();
						menu2.run(username, password, scan);
						break;
					case 3:
						CRSAdminMenu menu3 = new CRSAdminMenu();
						menu3.run(username, password, scan);
						break;
					default:
						System.out.println("Not vaild Option");
						break;
				}
				break;
			case 2:
				CRSStudentMenu stme = new CRSStudentMenu();
				stme.add(scan);
				break;
			case 3:
				scan.nextLine();
				System.out.println("Enter Username: ");
				username = scan.nextLine();
				System.out.println("Enter Password: ");
				password = scan.nextLine();
				System.out.println("Enter Role (1: Student, 2: Professor, 3: Admin) : ");
				role = scan.nextInt();
				System.out.println("New Password: ");
				String npassword = scan.nextLine();
				System.out.println("Renter New Password");
				String cpassword = scan.nextLine();
				if(!npassword.equals(cpassword)) {
					System.out.println("Password Did Not Match");
				}
				switch(role) {
				case 1:
					CRSStudentMenu menu1 = new CRSStudentMenu();
					menu1.run(username, password, scan);
					break;
				case 2:
					CRSProfessorMenu menu2 = new CRSProfessorMenu();
					menu2.run(username, password, scan);
					break;
				case 3:
					CRSAdminMenu menu3 = new CRSAdminMenu();
					menu3.run(username, password, scan);
					break;
				default:
					System.out.println("Not vaild Option");
					break;
			}
				break;
			default:
				System.out.println("Invalid Option");
			}
		}
	}

}
