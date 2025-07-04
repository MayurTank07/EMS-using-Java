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
	
		do {
	
	
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
				 System.out.print("Enter Employee ID to update: ");
				    int empIdToUpdate = sc.nextInt();
				    sc.nextLine(); // consume newline

				    int updateChoice;

				    do {
				        System.out.println("\nWhat do you want to update?");
				        System.out.println("1. Name");
				        System.out.println("2. Salary");
				        System.out.println("3. Address");
				        System.out.println("4. Phone");
				        System.out.println("5. Designation");
				        System.out.println("6. DOB");
				        System.out.println("7. DOJ");
				        System.out.println("8. DOL");
				        System.out.println("9. Exit Update Menu");
				        System.out.print("Enter your choice: ");
				        updateChoice = sc.nextInt();
				        sc.nextLine(); // consume newline

				        String newValue, column = "";
				        boolean success, isNumOrDate = false;

				        switch (updateChoice) {
				            case 1:
				                column = "emp_name";
				                System.out.print("Enter new Name: ");
				                newValue = sc.nextLine();
				                success = emDataManipulation.updateSingleField(empIdToUpdate, column, newValue, false);
				                break;
				            case 2:
				                column = "emp_sal";
				                System.out.print("Enter new Salary: ");
				                newValue = sc.nextLine();
				                isNumOrDate = true;
				                success = emDataManipulation.updateSingleField(empIdToUpdate, column, newValue, isNumOrDate);
				                break;
				            case 3:
				                column = "emp_address";
				                System.out.print("Enter new Address: ");
				                newValue = sc.nextLine();
				                success = emDataManipulation.updateSingleField(empIdToUpdate, column, newValue, false);
				                break;
				            case 4:
				                column = "emp_phone";
				                System.out.print("Enter new Phone: ");
				                newValue = sc.nextLine();
				                success = emDataManipulation.updateSingleField(empIdToUpdate, column, newValue, false);
				                break;
				            case 5:
				                column = "emp_designation";
				                System.out.print("Enter new Designation: ");
				                newValue = sc.nextLine();
				                success = emDataManipulation.updateSingleField(empIdToUpdate, column, newValue, false);
				                break;
				            case 6:
				                column = "emp_dob";
				                System.out.print("Enter new DOB (yyyy-mm-dd): ");
				                newValue = sc.nextLine();
				                isNumOrDate = true;
				                success = emDataManipulation.updateSingleField(empIdToUpdate, column, newValue, isNumOrDate);
				                break;
				            case 7:
				                column = "emp_doj";
				                System.out.print("Enter new DOJ (yyyy-mm-dd): ");
				                newValue = sc.nextLine();
				                isNumOrDate = true;
				                success = emDataManipulation.updateSingleField(empIdToUpdate, column, newValue, isNumOrDate);
				                break;
				            case 8:
				                column = "emp_dol";
				                System.out.print("Enter new DOL (yyyy-mm-dd): ");
				                newValue = sc.nextLine();
				                isNumOrDate = true;
				                success = emDataManipulation.updateSingleField(empIdToUpdate, column, newValue, isNumOrDate);
				                break;
				            case 9:
				                System.out.println("Exiting update menu...");
				                continue;
				            default:
				                System.out.println("Invalid option. Try again.");
				                continue;
				        }

				        if (updateChoice >= 1 && updateChoice <= 8) {
				            if (success) {
				                System.out.println("Updated successfully.");
				            } 
				            else {
				                System.out.println("Failed to update. Check Employee ID or input.");
				            }
				        }

				    } while (updateChoice != 9);
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
		}while(true);
		
	}
	
	
}
