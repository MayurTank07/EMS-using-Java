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
	
	
	// ADD EMPLOYEE
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

	// UPDATE EMPLOYEE
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
	            } 
	            else {
	                pstmt.setDate(1, java.sql.Date.valueOf(newValue));  // for dob, doj, dol
	            }
	        } 
	        else {
	            pstmt.setString(1, newValue);
	        }

	        pstmt.setInt(2, emp_id);

	        int rows = pstmt.executeUpdate();  // rows --> 1
	        isUpdated = rows > 0;  // True

	    } 
	    catch (Exception e) {
	        System.out.println("Error updating field: " + e.getMessage());
	    } 
	    finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } 
	        catch (SQLException se) {
	            System.out.println("Error closing resources: " + se.getMessage());
	        }
	    }

	    return isUpdated;
	}

	
	// SHOW EMPLOYESS --> ALL, SEARCH BY ID, FILTER BY DESIGNATION
	public void showAllEmployees() {
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_DB?user=root&password=root");

	        String query = "SELECT * FROM employee";
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(query);

	        System.out.println("\n==================================================== Employee Records ====================================================");
	        System.out.printf("%-5s %-20s %-10s %-15s %-15s %-15s %-12s %-12s %-12s\n",
	                "ID", "Name", "Salary", "Address", "Phone", "Designation", "DOB", "DOJ", "DOL");
	        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

	        while (rs.next()) {
	            int id = rs.getInt("emp_id");
	            String name = rs.getString("emp_name");
	            float sal = rs.getFloat("emp_sal");
	            String address = rs.getString("emp_address");
	            String phone = rs.getString("emp_phone");
	            String desg = rs.getString("emp_designation");
	            Date dob = rs.getDate("emp_dob");
	            Date doj = rs.getDate("emp_doj");
	            Date dol = rs.getDate("emp_dol");

	            System.out.printf("%-5d %-20s %-10.2f %-15s %-15s %-15s %-12s %-12s %-12s\n",
	                    id, name, sal, address, phone, desg, dob, doj, dol);
	        }
	        
	        

	        System.out.println("==========================================================================================================================\n");

	    } catch (Exception e) {
	        System.out.println("Error retrieving employees: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException se) {
	            System.out.println("Error closing resources: " + se.getMessage());
	        }
	    }
	}

	public void searchEmployeeById(int emp_id) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_DB?user=root&password=root");

	        String query = "SELECT * FROM employee WHERE emp_id = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, emp_id);

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            System.out.println("\n=========== Employee Details ===========");
	            System.out.println("ID: " + rs.getInt("emp_id"));
	            System.out.println("Name: " + rs.getString("emp_name"));
	            System.out.println("Salary: " + rs.getFloat("emp_sal"));
	            System.out.println("Address: " + rs.getString("emp_address"));
	            System.out.println("Phone: " + rs.getString("emp_phone"));
	            System.out.println("Designation: " + rs.getString("emp_designation"));
	            System.out.println("DOB: " + rs.getDate("emp_dob"));
	            System.out.println("DOJ: " + rs.getDate("emp_doj"));
	            System.out.println("DOL: " + rs.getDate("emp_dol"));
	            System.out.println("========================================\n");
	        } else {
	            System.out.println("No employee found with ID " + emp_id);
	        }

	    } catch (Exception e) {
	        System.out.println("Error searching employee: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException se) {
	            System.out.println("Error closing resources: " + se.getMessage());
	        }
	    }
	}
	
	public void filterEmployeesByDesignation(String designation) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_DB?user=root&password=root");

	        String query = "SELECT * FROM employee WHERE emp_designation = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, designation);

	        rs = pstmt.executeQuery();

	        System.out.println("\n=========== Employees with Designation: " + designation + " ===========");
	        System.out.printf("%-5s %-20s %-10s %-15s %-15s\n",
	                "ID", "Name", "Salary", "Phone", "Address");
	        System.out.println("--------------------------------------------------------------");

	        boolean found = false;

	        while (rs.next()) {
	            found = true;
	            System.out.printf("%-5d %-20s %-10.2f %-15s %-15s\n",
	                    rs.getInt("emp_id"),
	                    rs.getString("emp_name"),
	                    rs.getFloat("emp_sal"),
	                    rs.getString("emp_phone"),
	                    rs.getString("emp_address"));
	        }

	        if (!found) {
	            System.out.println("No employees found with this designation.");
	        }
	        System.out.println("=====================================================\n");

	    } catch (Exception e) {
	        System.out.println("Error filtering employees: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException se) {
	            System.out.println("Error closing resources: " + se.getMessage());
	        }
	    }
	}



}
