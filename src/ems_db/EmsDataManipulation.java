package ems_db;

import java.sql.*;

public class EmsDataManipulation {

	public void resDataInsert(String name, String email, String username, String password) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        // Load driver and connect to EMS_DB
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_DB?user=root&password=root");

	        String insertQuery = "INSERT INTO registration (name, email, username, password) VALUES (?, ?, ?, ?)";

	        pstmt = conn.prepareStatement(insertQuery);
	        pstmt.setString(1, name);
	        pstmt.setString(2, email);
	        pstmt.setString(3, username);
	        pstmt.setString(4, password);

	        int rowsInserted = pstmt.executeUpdate(); //it will return how many rows inserted in table

	        if (rowsInserted > 0) 
	        {
	            System.out.println("Registration data inserted successfully.");
	        } 
	        else {
	            System.out.println("Failed to insert registration data.");
	        }

	    } 
	    catch (Exception e) 
	    {
	        System.out.println("Error inserting data: " + e.getMessage());
	    } 
	    finally 
	    {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException se) {
	            System.out.println("Error closing resources: " + se.getMessage());
	        }
	    }
	}

		
	public void logDataInsert(String username, String password)
	{
		//code
	}
}
