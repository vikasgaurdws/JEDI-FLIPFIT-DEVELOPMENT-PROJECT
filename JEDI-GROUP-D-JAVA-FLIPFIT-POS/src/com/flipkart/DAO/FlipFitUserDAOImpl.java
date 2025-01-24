package com.flipkart.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
						&& user.getUserRole().equalsIgnoreCase(rs.getString("roleID"))) {
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
			preparedStatementUser.setString(4, customer.getUserRole());
			preparedStatementUser.setString(5, customer.getUserPassword());

			rowsAffected = preparedStatementUser.executeUpdate();
			if (rowsAffected != 0)
				registerSuccess = true;

		} catch (SQLException e) {
			printSQLException(e);
		}

		return registerSuccess;
	}

	public boolean registerGymOwner(FlipFitGymOwner gymOwner) {
		Connection connection = null;
		boolean registerSuccess = false;
		String query = "INSERT INTO gymOwner VALUES (?,?,?,?)";
		String queryOwner = "INSERT INTO user VALUES (?,?,?,?,?)";
		try {connection = DBconnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				PreparedStatement preparedStatementOwner = connection.prepareStatement(queryOwner);

			preparedStatement.setInt(1, gymOwner.getUserId());
			preparedStatement.setString(2, gymOwner.getUserMobile());
			preparedStatement.setString(3, gymOwner.getAdharNumber());
			preparedStatement.setBoolean(4, gymOwner.getFlagVerified());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected != 0)
				registerSuccess = true;
			
			preparedStatementOwner.setInt(1, gymOwner.getUserId());
			preparedStatementOwner.setString(2, gymOwner.getUserEmail());
			preparedStatementOwner.setString(3, gymOwner.getUserMobile());
			preparedStatementOwner.setString(4, gymOwner.getUserRole());
			preparedStatementOwner.setString(5, gymOwner.getUserPassword());


			rowsAffected = preparedStatementOwner.executeUpdate();
			if (rowsAffected != 0)
				registerSuccess = true;

		} catch (SQLException e) {
			printSQLException(e);
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
