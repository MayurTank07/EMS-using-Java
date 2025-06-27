package ems_db;


import java.sql.*;

public class Ems_Db {

	 
       
    public void dbConnection() {
    	  Connection conn = null;
          Statement stmt = null;
        try {
            
//        	System.out.println("Connecting to MySQL Database...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=root");
//            System.out.println("Connected to MySQL Database successfully.");

            stmt = conn.createStatement();

            String query = "CREATE DATABASE IF NOT EXISTS EMS_DB";
            stmt.executeUpdate(query);
//            System.out.println("Database EMS_DB is ready.");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_DB?user=root&password=root");
//            System.out.println("Connected to EMS_DB database.");

        } 
        catch (Exception e) 
        {
            System.out.println("Error during DB connection: " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException se) 
            {
                System.out.println("Error closing resources: " + se.getMessage());
            }
        }
    }

   
    public void regTable() {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Load the driver and connect to EMS_DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_DB?user=root&password=root");

            stmt = conn.createStatement();

            String createTableQuery = "CREATE TABLE IF NOT EXISTS registration ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "email VARCHAR(100) NOT NULL,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "password VARCHAR(100) NOT NULL,"
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";

            stmt.executeUpdate(createTableQuery);

            System.out.println("Table 'registration' created or already exists.");

        } 
        catch (Exception e) 
        {
            System.out.println("Error creating table: " + e.getMessage());
        } 
        finally {
            try 
            {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException se) {
                System.out.println("Error closing resources: " + se.getMessage());
            }
        }
    }

    public void empTable() {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Load driver and connect to EMS_DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_DB?user=root&password=root");

            stmt = conn.createStatement();

            String createEmpTableQuery = "CREATE TABLE IF NOT EXISTS employee ("
                    + "emp_id INT PRIMARY KEY,"
                    + "emp_name VARCHAR(100) NOT NULL,"
                    + "emp_sal FLOAT NOT NULL,"
                    + "emp_address VARCHAR(200),"
                    + "emp_phone VARCHAR(15),"
                    + "emp_designation VARCHAR(100),"
                    + "emp_doj DATE,"
                    + "emp_dob DATE,"
                    + "emp_dol DATE"
                    + ")";

            stmt.executeUpdate(createEmpTableQuery);

//            System.out.println("Table 'employee' created or already exists.");

        } catch (Exception e) {
            System.out.println("Error creating employee table: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                System.out.println("Error closing resources: " + se.getMessage());
            }
        }
    }

    
}
