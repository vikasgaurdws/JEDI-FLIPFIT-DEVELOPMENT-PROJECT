package com.flipkart.utils;

import java.sql.*;

public class DBconnection {
	private static Connection connection = null;
	private static String url = "jdbc:mysql://localhost:3306/FlipFit_Schema";
	private static String username = "root";
	private static String password = "mysql@123";

	
	public static Connection getConnection() {
		
        if (connection != null)
            return connection;
        else {
        	
        	 try {

 	        	Class.forName("com.mysql.cj.jdbc.Driver");

 	        	connection = DriverManager.getConnection(url, username, password);
// 	            System.out.println("Connection successful!");

 	           } catch (ClassNotFoundException e) {
 	            System.err.println("MySQL JDBC Driver not found.");
 	            e.printStackTrace();
 	        } catch (SQLException e) {
 	            System.err.println("Connection failed!");
 	            e.printStackTrace();
 	        } 
            
        }
        
        return connection;
        
        
        }
	
            
 }