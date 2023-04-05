/**
 * 
 */
package com.incedo.client;

import java.util.Scanner;

import com.incedo.service.EmployeeService;

/**
 * @author David Masaka
 *
 */
public class EmployeeClientApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//call all the employee operations here
		
		Scanner scan = new Scanner(System.in);
		
		EmployeeService service = new EmployeeService();
		while(true) {
			System.out.println("1: create, 2: list, 3:delete, 4:update");
			int input = scan.nextInt();
			if (input > 2) {
				System.out.println("What is the id?: ");
				int id = scan.nextInt();
				service.switchquestion(input, id);
			} else {
				service.switchquestion(input, input);
			}
		}
	}

}
