package ems_db;

import java.sql.*;

public class EmsDataManipulation {

//	crud operation 
	
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
		
	public boolean logDataInsert(String username, String password) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    boolean loginSuccess = false;

	    try {
	        // Load driver and connect to EMS_DB
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_DB?user=root&password=root");

	        // Check if username and password match in registration table
	        String loginQuery = "SELECT * FROM registration WHERE username = ? AND password = ?";
	        pstmt = conn.prepareStatement(loginQuery);
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            loginSuccess = true;
	        }

	    } 
	    catch (Exception e) 
	    {
	        System.out.println("Error during login check: " + e.getMessage());
	    } 
	    finally 
	    {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException se) {
	            System.out.println("Error closing resources: " + se.getMessage());
	        }
	    }

	    return loginSuccess;
	}
	
	public boolean addEmployee(int emp_id, String emp_name, float emp_sal, String emp_address,
            String emp_phone, String emp_designation, String emp_dob,
            String emp_doj, String emp_dol) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			boolean isInserted = false;
			
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_DB?user=root&password=root");
			
			String query = "INSERT INTO employee (emp_id, emp_name, emp_sal, emp_address, emp_phone, emp_designation, emp_dob, emp_doj, emp_dol) "
			      + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, emp_id);
			pstmt.setString(2, emp_name);
			pstmt.setFloat(3, emp_sal);
			pstmt.setString(4, emp_address);
			pstmt.setString(5, emp_phone);
			pstmt.setString(6, emp_designation);
			pstmt.setDate(7, java.sql.Date.valueOf(emp_dob));  // yyyy-MM-dd
			pstmt.setDate(8, java.sql.Date.valueOf(emp_doj));  // yyyy-MM-dd
			pstmt.setDate(9, java.sql.Date.valueOf(emp_dol));  // yyyy-MM-dd
			
			// Execute update
			int rows = pstmt.executeUpdate();  
			isInserted = rows > 0;

			} 
			catch (Exception e) 
			{
				System.out.println("Error inserting employee: " + e.getMessage());
			} 
			finally 
			{
				try 
				{
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} 
				catch (SQLException se) 
				{
					System.out.println("Error closing resources: " + se.getMessage());
				}
			}
			
			return isInserted;
	}

	public boolean updateSingleField(int emp_id, String columnName, String newValue, boolean isNumericOrDate) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    boolean isUpdated = false;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_DB?user=root&password=root");

	        String query = "UPDATE employee SET " + columnName + " = ? WHERE emp_id = ?";
	        pstmt = conn.prepareStatement(query);

	        if (isNumericOrDate) {
	            if (columnName.equals("emp_sal")) {
	                pstmt.setFloat(1, Float.parseFloat(newValue));
	            } else {
	                pstmt.setDate(1, java.sql.Date.valueOf(newValue));  // for dob, doj, dol
	            }
	        } else {
	            pstmt.setString(1, newValue);
	        }

	        pstmt.setInt(2, emp_id);

	        int rows = pstmt.executeUpdate();
	        isUpdated = rows > 0;

	    } catch (Exception e) {
	        System.out.println("Error updating field: " + e.getMessage());
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException se) {
	            System.out.println("Error closing resources: " + se.getMessage());
	        }
	    }

	    return isUpdated;
	}


}
