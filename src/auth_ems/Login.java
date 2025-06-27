package auth_ems;

import java.util.Scanner;

import auth_validations.Login_validator;
import ems_db.*;
import main_page.MainDashboard;

public class Login {

	Scanner sc = new Scanner(System.in);
	String username = null, password = null;
	
	public Login() {
		System.out.println("\n\n\t\t\t********* Login Page *********\n\n");
	}
	
	public void enterLoginDetails() {
		
        System.out.print("\t\t\t Enter your username : ");
        username = sc.nextLine();  // FIXED

        System.out.print("\t\t\t Enter your password : ");
        password = sc.nextLine();  // FIXED
        
        auth_validation();
	}
	
	public void auth_validation() {
		
		Login_validator lg_validator = new Login_validator(username, password);
		
		
		Boolean flag = lg_validator.validation();
		
		if(flag)
		{

			EmsDataManipulation emDataManipulation = new EmsDataManipulation();
			Boolean login = emDataManipulation.logDataInsert(username, password);
			
			if (login) {
				System.out.println("\n\n\t\t\t********* Login Sucessfull *********\n\n");
				
				Ems_Db db = new Ems_Db();
				db.empTable();
								
				MainDashboard md = new MainDashboard(username);
				md.mainDashboard();
			}
			else {
				System.out.println("\n\n\t\t\t********* Invalid Crediantials *********\n\n");
			}
			
		}
		else {
			System.out.println("\n\n\t\t\t********* Login Failed *********\n\n");
		}
		
	}	
	
}
