package auth_ems;

import java.util.Scanner;

import auth_validations.Login_validator;

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
			// db code check login values matches with db values
			System.out.println("login succesfull");
		}
		else {
			System.out.println("login unsucessfull");
		}
		
	}	
	
}
