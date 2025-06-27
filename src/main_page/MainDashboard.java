package main_page;

import java.util.Scanner;
import auth_ems.Login;
import auth_ems.Registration;
import ems_db.EmsDataManipulation;

public class MainDashboard {
	
	Scanner sc = new Scanner(System.in);

	public MainDashboard(String username) {
		System.out.println("\t\t\t********* Welcome to EMS Dashboard *********\n\n");
		System.out.println(String.format("\t\t\t********* Hello %s *********\n\n", username));
	}
	
	public void mainDashboard() {		
		int choice;
		EmsDataManipulation emDataManipulation = new EmsDataManipulation();
	
		System.out.println("\t\t\t\t 1. Add Employee");
		System.out.println("\t\t\t\t 2. Update Employee");
		System.out.println("\t\t\t\t 3. Show Employees");
		System.out.println("\t\t\t\t 4. Delete Employee");
		System.out.println("\t\t\t\t 5. Exit\n");
		
		System.out.print("\t\t\tEnter your choice: ");
		choice = sc.nextInt();
		sc.nextLine(); // consume newline

		switch(choice) {
			case 1:
				System.out.print("Enter Employee ID: ");
				int emp_id = sc.nextInt();
				sc.nextLine(); // consume newline

				System.out.print("Enter Employee Name: ");
				String emp_name = sc.nextLine();

				System.out.print("Enter Employee Salary: ");
				float emp_sal = sc.nextFloat();
				sc.nextLine();

				System.out.print("Enter Employee Address: ");
				String emp_address = sc.nextLine();

				System.out.print("Enter Employee Phone: ");
				String emp_phone = sc.nextLine();

				System.out.print("Enter Employee Designation: ");
				String emp_designation = sc.nextLine();

				System.out.print("Enter Employee DOB (yyyy-mm-dd): ");
				String emp_dob = sc.nextLine();

				System.out.print("Enter Employee DOJ (yyyy-mm-dd): ");
				String emp_doj = sc.nextLine();

				System.out.print("Enter Employee DOL (yyyy-mm-dd): ");
				String emp_dol = sc.nextLine();

				boolean flag = emDataManipulation.addEmployee(emp_id, emp_name, emp_sal, emp_address,
						emp_phone, emp_designation, emp_dob, emp_doj, emp_dol);
				
				if(flag) {
					System.out.println("Employee added successfully.");
				} else {
					System.out.println("Failed to add employee.");
				}
				break;
			
			case 2:
				// TODO: Update employee code
				break;
			
			case 3:
				// TODO: Show employee code
				break;

			case 4:
				// TODO: Delete employee code
				break;

			case 5:
				System.out.println("\n\n\t\t********************** Exited **********************\n\n");
				System.exit(0);
				break;
			
			default:
				System.out.println("Invalid choice");
		}
	}
}
