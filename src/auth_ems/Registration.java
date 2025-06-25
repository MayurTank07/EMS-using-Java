package auth_ems;

import java.util.Scanner;

import auth_validations.Registration_validator;
import ems_db.*;

public class Registration {
	
	Scanner sc = new Scanner(System.in);
	String uname = null, username = null, email = null, password = null;
	Ems_Db db = new Ems_Db();
	EmsDataManipulation emsDbManipulation = new EmsDataManipulation();

	
	public Registration() {
		System.out.println("\n\n\t\t\t********* Registration Page *********\n\n");
	}
	
	
	public void enterResgDetails() {
		System.out.print("\t\t\t Enter your name : ");
        uname = sc.nextLine();

        System.out.print("\t\t\t Enter your email : ");
        email = sc.nextLine();  // FIXED

        System.out.print("\t\t\t Enter your username : ");
        username = sc.nextLine();  // FIXED

        System.out.print("\t\t\t Enter your password : ");
        password = sc.nextLine();  // FIXED
        
        auth_validation();
	}
	
	public void auth_validation() {
		System.out.println("\t\t\t\t Authentication begins.... \n");
		
		Registration_validator reg_validator = new Registration_validator(uname, username, email, password);	
		
		Boolean flag = reg_validator.validation();
		
		if(flag) {
			System.out.println("\n\n\t\t\t********* Authentication Successfull *********");
			dbResgDataUpload();
			reg_success();
		}
		else {
			System.out.println("\n\n\t\t\t********* Authentication Failed *********\n\n");
		}
	}
	
	
	public void dbResgDataUpload()
	{
		db.dbConnection();
		db.regTable();
		emsDbManipulation.resDataInsert(uname, email, username, password);
	
		
	}
	
	public void reg_success() {
		System.out.println("\n\n\t\t\t********* Registration Successfull *********\n\n");
		
	}
}
