package com.flipkart.dao;

import java.sql.*;
import com.flipkart.utils.*;

public class FlipfitCustomer {

    public static void main(String[] args) {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Get the connection from DBconnection class
            connection = DBconnection.getConnection();
            
            // Create a statement object from the connection
            statement = connection.createStatement();
            
            // SQL query to fetch all users from the table
            String sql = "SELECT * FROM FlipfitUser";
            
            // Execute the query and get a result set
            resultSet = statement.executeQuery(sql);
            
            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id"); // Assuming 'id' is a column
                String username = resultSet.getString("username"); // Assuming 'username' is a column
                String email = resultSet.getString("email"); // Assuming 'email' is a column

                // Output the data
                System.out.println("ID: " + id + ", Username: " + username + ", Email: " + email);
            }

        } catch (SQLException e) {
            System.err.println("SQL error occurred.");
            e.printStackTrace();
        } 
        
    }
}
