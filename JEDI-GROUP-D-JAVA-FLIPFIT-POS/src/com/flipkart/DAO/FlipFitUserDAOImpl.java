package com.flipkart.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.flipkart.bean.*;
import com.flipkart.utils.DBconnection;

public class FlipFitUserDAOImpl implements FlipFitUserDAO {

	public boolean authenticateUser(FlipFitGymUser user) {
		// to run without authentication, make isUserValid = true
		Connection connection = null;
		
		boolean isUserValid = false;
		try {connection = DBconnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SQLConstants.SQL_SELECT_USER_LOGIN_CREDENTIAL"); 

			preparedStatement.setString(1, user.getUserEmail());

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if (user.getUserPassword().equals(rs.getString("passwordHash"))
						&& user.getUserRole().equals(rs.getInt("roleID"))) {
					System.out.println(
							rs.getString("email") + " " + rs.getString("passwordHash") + " " + rs.getString("roleID"));
					isUserValid = true;
				}
			}

		} catch (SQLException e) {
			printSQLException(e);
		}

		return isUserValid;
	}

	public boolean registerCustomer(FlipFitCustomer customer) {
		Connection connection = null;
		boolean registerSuccess = false;
		String query = "INSERT INTO customer VALUES (?,?)";
		String queryUser = "INSERT INTO user VALUES (?,?,?)";
		try {connection = DBconnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				PreparedStatement preparedStatementUser = connection.prepareStatement(queryUser);

			preparedStatement.setInt(1, customer.getUserId());
			preparedStatement.setInt(2, customer.getCustomerAge());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected != 0)
				registerSuccess = true;
			
			preparedStatementUser.setInt(1, customer.getUserId());
			preparedStatementUser.setString(2, customer.getUserEmail());
			preparedStatementUser.setString(3, customer.getUserMobile());
			preparedStatementUser.setInt(4, customer.getUserRole());
			preparedStatementUser.setString(5, customer.getUserPassword());

			rowsAffected = preparedStatementUser.executeUpdate();
			if (rowsAffected != 0)
				registerSuccess = true;

		} catch (SQLException e) {
			printSQLException(e);
		}

		return registerSuccess;
	}

//	public boolean registerGymOwner(FlipFitGymOwner gymOwner) {
//		Connection connection = null;
//		boolean registerSuccess = false;
////		String query = "INSERT INTO gymOwner VALUES (?,?,?,?)";
//		String queryOwner = "INSERT INTO FlipFitGymOwner (gymOwnerPAN,gymOwnerAadharNumber) VALUES (?,?)";
//		
////		gymOwnerID INT PRIMARY KEY,
////	    gymOwnerPAN VARCHAR(50),
////	    gymOwnerAadharNumber VARCHAR(50),
////	    flagVerified BOOLEAN DEFAULT FALSE,
//		
//		String queryUser = "INSERT INTO FlipFitUser (userName, email, passwordHash, phoneNumber, roleID) VALUES (?, ?, ?, ?, ?)";
//
//		try {connection = DBconnection.getConnection();
//				PreparedStatement statementUser = connection.prepareStatement(queryUser);
//				PreparedStatement preparedStatementOwner = connection.prepareStatement(queryOwner);
//				
////			preparedStatementOwner.setInt(1, gymOwner.getUserId());
////			preparedStatementOwner.setString(2, gymOwner.getUserMobile());
//			preparedStatementOwner.setString(1, gymOwner.getAdharNumber());
//			preparedStatementOwner.setString(2, gymOwner.getPanNumber());
//
//			int rowsAffected = statementUser.executeUpdate();
//			if (rowsAffected != 0)
//				registerSuccess = true;
//			
////			preparedStatement.setInt(1, gymOwner.getUserId());
////			preparedStatement.setString(2, gymOwner.getUserEmail());
////			preparedStatement.setString(3, gymOwner.getUserMobile());
////			preparedStatement.setString(4, gymOwner.getUserRole());
////			preparedStatement.setString(5, gymOwner.getUserPassword());
//			
//			 // Insert into FlipFitUser table
//	        statementUser = connection.prepareStatement(queryUser, Statement.RETURN_GENERATED_KEYS);
//	        statementUser.setString(1, gymOwner.getUserName());
//	        statementUser.setString(2, gymOwner.getUserEmail());
//	        statementUser.setString(3, gymOwner.getUserPassword()); 
//	        statementUser.setString(4, gymOwner.getUserMobile());
//	        statementUser.setInt(5, 3);
//
//
//			rowsAffected = preparedStatementOwner.executeUpdate();
//			if (rowsAffected != 0)
//				registerSuccess = true;
//
//		} catch (SQLException e) {
//			printSQLException(e);
//		}
//
//		return registerSuccess;
//	}
	
	public boolean registerGymOwner(FlipFitGymOwner gymOwner) {
	    Connection connection = null;
	    boolean registerSuccess = false;
	    String queryUser = "INSERT INTO FlipFitUser (userName, email, passwordHash, phoneNumber, roleID) VALUES (?, ?, ?, ?, ?)";
	    String queryOwner = "INSERT INTO FlipFitGymOwner (gymOwnerID, gymOwnerPAN, gymOwnerAadharNumber, flagVerified) VALUES (?, ?, ?, DEFAULT)";

	    try {
	        connection = DBconnection.getConnection();
	        connection.setAutoCommit(false); // Start transaction

	        // Step 1: Insert into FlipFitUser table
	        try (PreparedStatement statementUser = connection.prepareStatement(queryUser, Statement.RETURN_GENERATED_KEYS)) {
	            statementUser.setString(1, gymOwner.getUserName());
	            statementUser.setString(2, gymOwner.getUserEmail());
	            statementUser.setString(3, gymOwner.getUserPassword());
	            statementUser.setString(4, gymOwner.getUserMobile());
	            statementUser.setInt(5, gymOwner.getUserRole()); // Assuming roleID 3 is for Gym Owners

	            int userRowsInserted = statementUser.executeUpdate();
	            if (userRowsInserted == 0) {
	                throw new SQLException("Failed to insert into FlipFitUser. No rows affected.");
	            }

	            // Retrieve the generated userID
	            try (ResultSet generatedKeys = statementUser.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    int gymOwnerID = generatedKeys.getInt(1);

	                    // Step 2: Insert into FlipFitGymOwner table
	                    try (PreparedStatement preparedStatementOwner = connection.prepareStatement(queryOwner)) {
	                        preparedStatementOwner.setInt(1, gymOwnerID);
	                        preparedStatementOwner.setString(2, gymOwner.getPanNumber());
	                        preparedStatementOwner.setString(3, gymOwner.getAdharNumber());

	                        int ownerRowsInserted = preparedStatementOwner.executeUpdate();
	                        if (ownerRowsInserted > 0) {
	                            registerSuccess = true;
	                            gymOwner.setGymOwnerId(gymOwnerID); // Update the gymOwner object with the generated ID
	                        } else {
	                            throw new SQLException("Failed to insert into FlipFitGymOwner. No rows affected.");
	                        }
	                    }
	                } else {
	                    throw new SQLException("Failed to retrieve generated userID.");
	                }
	            }
	        }

	        connection.commit(); // Commit transaction
	    } catch (SQLException e) {
	        if (connection != null) {
	            try {
	                connection.rollback(); // Rollback transaction in case of failure
	            } catch (SQLException rollbackEx) {
	                rollbackEx.printStackTrace();
	            }
	        }
	        printSQLException(e);
	    } finally {
//	        if (connection != null) {
//	            try {
//	                connection.setAutoCommit(true); // Reset auto-commit to default
////	                connection.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
	    }

	    return registerSuccess;
	}




	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
