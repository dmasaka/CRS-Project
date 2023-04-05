/**
 * 
 */
package com.incedo.service;

import java.util.Scanner;

import com.incedo.bean.Employee;

/**
 * @author David Masaka
 *
 */
public class EmployeeService {
	
//	Employee emp = new Employee();
//	Employee employees[1] = new Employee();
//	Employee employees[] = {new Employee(), new Employee(), new Employee()};
	Employee employees[] = new Employee[3];
	//create, list, update, delete
	

	public void createEmployee() {
		for (int i = 0; i < employees.length; i++) {
			employees[i] = new Employee();
		}
		System.out.println("create employee");
		employees[0].setEmployeeId(101);
		employees[0].setEmployeeName("David");
		employees[0].setEmployeeAddress("New Jersey");
		System.out.println("create second employee");
		employees[1].setEmployeeId(102);
		employees[1].setEmployeeName("Amit");
		employees[1].setEmployeeAddress("New Delhi");
		System.out.println("create third employee");
		employees[2].setEmployeeId(103);
		employees[2].setEmployeeName("John");
		employees[2].setEmployeeAddress("China");
	}
	
	public boolean deleteEmployee(int id) {
		System.out.println("Employee " + id + " was deleted");
		boolean exist = false;
		for (Employee employee: employees) {
			if(employee.getEmployeeId() == id) {
				exist = true;
			}
		}
		if(!exist) {
			return false;
		}
		Employee temp[] = new Employee[employees.length - 1];
		int index = 0;
		for (Employee employee: employees) {
			if(employee.getEmployeeId() != id) {
				temp[index] = employee;
				index ++;
			}
		}
		employees = temp;
		return true;
	}
	
	public void listEmployees() {
		System.out.println("List all the employees");
		for(Employee employee : employees) {
			System.out.println("Employee: " + employee.getEmployeeId() + ", Name: " + employee.getEmployeeName() + ", Address: " + employee.getEmployeeAddress());
		}
	}
	
	public boolean updateEmployee(int id) {
		boolean exist = false;
		Scanner in = new Scanner(System.in);
		int index = 0;
		for(int i = 0; i < employees.length; i ++) {
			if(employees[i].getEmployeeId() == id) {
				System.out.println("Enter new name: ");
				String name = in.nextLine();
				System.out.println("Enter new address: ");
				String address = in.nextLine();
				employees[i].setEmployeeName(name);
				employees[i].setEmployeeAddress(address);
				exist = true;
			}
		}
		System.out.println("Update employee " + id  + " with the id");
		return exist;
	}
	
	public boolean switchquestion(int oper, int id) {
		switch(oper) {
		case 1:
			createEmployee();
			return true;
		case 2:
			listEmployees();
			return true;
		case 3:
			return deleteEmployee(id);
		case 4:
			return updateEmployee(id);
		default:
			return false;
		}
	}
}
