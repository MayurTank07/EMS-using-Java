package ems_dash;

import java.util.*;

import auth_ems.*;
import ems_db.Ems_Db;


public class Ems_Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int choice;
		
		System.out.println("\n\n\t\t********************** Welcome **********************\n\n");
		System.out.println("\t\t\t\t 1. Login \n");
		System.out.println("\t\t\t\t 2. Registration \n");
				
		
		System.out.print("\t\t\tEnter your choice : ");
		choice = sc.nextInt();

		switch(choice)
		{
			case 1 :
				Login lg = new Login();
				lg.enterLoginDetails();
			break;
			
			case 2:
				Registration rs = new Registration();
				rs.enterResgDetails();
			break;
			
			default:
				System.out.println("Invalid choice");
		}
		
		System.out.println("\n\n\t\t********************** Exited **********************\n\n");
	}

}
