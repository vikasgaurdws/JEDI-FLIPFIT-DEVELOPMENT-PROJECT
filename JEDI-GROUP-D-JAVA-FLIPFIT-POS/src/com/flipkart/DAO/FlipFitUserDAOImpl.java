package com.flipkart.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.*;
import com.flipkart.constants.SQLConstants;
import com.flipkart.utils.DBconnection;

public abstract class FlipFitUserDAOImpl implements FlipFitUserDAO {

	public boolean authenticateUser(FlipFitGymUser user) {
		// to run without authentication, make isUserValid = true
		Connection connection = null;
		
		boolean isUserValid = false;
		try {connection = DBconnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_USER_LOGIN_CREDENTIAL); 

			preparedStatement.setString(1, user.getUserEmail());

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if (user.getUserPassword().equals(rs.getString("password"))
						&& user.getUserRole().equalsIgnoreCase(rs.getString("role"))) {
					System.out.println(
							rs.getString("email") + " " + rs.getString("password") + " " + rs.getString("role"));
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
		String query = "INSERT INTO customer VALUES (?,?,?,?,?)";
		String queryUser = "INSERT INTO user VALUES (?,?,?)";
		try {connection = DBconnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				PreparedStatement preparedStatementUser = connection.prepareStatement(queryUser);

			preparedStatement.setString(1, customer.getUserEmail());
			preparedStatement.setString(2, customer.getUserName());
//			preparedStatement.setString(3, customer.getPanNumber());
			preparedStatement.setInt(4, customer.getCustomerAge());
//			preparedStatement.setString(1, customer.getAddress());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected != 0)
				registerSuccess = true;

			preparedStatementUser.setString(1, customer.getUserEmail());
			preparedStatementUser.setString(2, customer.getUserPassword());
			preparedStatementUser.setString(3, "Customer");

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
		String query = "INSERT INTO gymOwner VALUES (?,?,?,?,?,?)";
		String queryOwner = "INSERT INTO user VALUES (?,?,?)";
		try {connection = DBconnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				PreparedStatement preparedStatementOwner = connection.prepareStatement(queryOwner);

			preparedStatement.setString(1, gymOwner.getUserEmail());
			preparedStatement.setString(2, gymOwner.getUserName());
			preparedStatement.setString(3, gymOwner.getUserMobile());
			preparedStatement.setString(4, gymOwner.getAdharNumber());
			preparedStatement.setString(5, gymOwner.getPanNumber());
			preparedStatement.setBoolean(6, gymOwner.getFlagVerified());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected != 0)
				registerSuccess = true;

			preparedStatementOwner.setString(1, gymOwner.getUserEmail());
			preparedStatementOwner.setString(2, gymOwner.getUserPassword());
			preparedStatementOwner.setString(3, "GymOwner");

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
