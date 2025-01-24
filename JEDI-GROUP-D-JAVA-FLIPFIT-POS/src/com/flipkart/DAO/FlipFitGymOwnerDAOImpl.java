package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.*;
import com.flipkart.utils.DBconnection;

public class FlipFitGymOwnerDAOImpl implements FlipFitGymOwnerDAO {
	
	Connection connection = DBconnection.getConnection();

    @Override
    public FlipFitGymOwner getFlipFitGymOwnerDetails(FlipFitGymOwner gymOwner) {
        FlipFitGymOwner ownerDetails = null;
        try{
            String query = "SELECT * FROM FlipFitGymOwner JOIN FlipFitUser ON FlipFitGymOwner.gymOwnerID = FlipFitUser.userID WHERE FlipFitUser.email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gymOwner.getUserEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ownerDetails = new FlipFitGymOwner();
                ownerDetails.setGymOwnerId(resultSet.getInt("gymOwnerID"));
                ownerDetails.setPanNumber(resultSet.getString("gymOwnerPAN"));
                ownerDetails.setAdharNumber(resultSet.getString("gymOwnerAadharNumber"));
                ownerDetails.setFlagVerified(resultSet.getBoolean("flagVerified"));
                ownerDetails.setUserEmail(resultSet.getString("email"));
                ownerDetails.setUserName(resultSet.getString("userName"));
                ownerDetails.setUserMobile(resultSet.getString("phoneNumber"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ownerDetails;
    }


    @Override
    public boolean editFlipFitGymOwnerDetails(FlipFitGymOwner gymOwner) {
        try{
            String query = "UPDATE FlipFitUser JOIN FlipFitGymOwner ON FlipFitUser.userID = FlipFitGymOwner.gymOwnerID SET userName = ?, phoneNumber = ? WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gymOwner.getUserName());
            preparedStatement.setString(2, gymOwner.getUserMobile());
            preparedStatement.setString(3, gymOwner.getUserEmail());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    @Override
    public boolean addGym(FlipFitGymOwner gymOwner, FlipFitGym gym) {
        try{
            String query = "INSERT INTO FlipFitGym (gymName, gymLocation, ownerID, price, availableSlot) SELECT ?, ?, gymOwnerID, ?, ? FROM FlipFitGymOwner JOIN FlipFitUser ON FlipFitGymOwner.gymOwnerID = FlipFitUser.userID WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gym.getGymName());
            preparedStatement.setString(2, gym.getGymLocation());
            preparedStatement.setInt(3, gym.getPrice());
            preparedStatement.setInt(4, gym.getAvailableSlot());
            preparedStatement.setString(5, gymOwner.getUserEmail());
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    @Override
    public boolean editGym(FlipFitGymOwner gymOwner, FlipFitGym gym) {
        try{
            String query = "UPDATE FlipFitGym SET gymName = ?, gymLocation = ?, price = ?, availableSlot = ? WHERE gymID = ? AND ownerID = (SELECT gymOwnerID FROM FlipFitGymOwner JOIN FlipFitUser ON FlipFitGymOwner.gymOwnerID = FlipFitUser.userID WHERE email = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gym.getGymName());
            preparedStatement.setString(2, gym.getGymLocation());
            preparedStatement.setInt(3, gym.getPrice());
            preparedStatement.setInt(4, gym.getAvailableSlot());
            preparedStatement.setInt(5, gym.getGymId());
            preparedStatement.setString(6, gymOwner.getUserEmail());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }


    @Override
    public List<FlipFitGym> getGymsOfFlipFitGymOwner(FlipFitGymOwner gymOwner) {
        List<FlipFitGym> gyms = new ArrayList<>();
        try{
            String query = "SELECT * FROM FlipFitGym WHERE ownerID = (SELECT gymOwnerID FROM FlipFitGymOwner JOIN FlipFitUser ON FlipFitGymOwner.gymOwnerID = FlipFitUser.userID WHERE email = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gymOwner.getUserEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FlipFitGym gym = new FlipFitGym();
                gym.setGymId(resultSet.getInt("gymID"));
                gym.setGymName(resultSet.getString("gymName"));
                gym.setGymLocation(resultSet.getString("gymLocation"));
                gym.setPrice(resultSet.getInt("price"));
                gym.setAvailableSlot(resultSet.getInt("availableSlot"));
                gyms.add(gym);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return gyms;
    }


    @Override
    public boolean addSlot(Slot slot) {
        try{
            String query = "INSERT INTO FlipFitGymSlot (gymID, startTime, capacity, availableSeats) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, slot.getGymId());
            preparedStatement.setTime(2, java.sql.Time.valueOf(slot.getStartTime()));
            preparedStatement.setInt(3, slot.getCapacity());
            preparedStatement.setInt(4, slot.getAvailableSeats());
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
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


	@Override
    public FlipFitGymOwner login(String email, String pass) {
        FlipFitGymOwner gymOwner = null;
        try{
            String query = "SELECT * FROM FlipFitUser JOIN FlipFitGymOwner ON FlipFitUser.userID = FlipFitGymOwner.gymOwnerID WHERE email = ? AND passwordHash = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                gymOwner = new FlipFitGymOwner();
                gymOwner.setGymOwnerId(resultSet.getInt("gymOwnerID"));
                gymOwner.setPanNumber(resultSet.getString("gymOwnerPAN"));
                gymOwner.setAdharNumber(resultSet.getString("gymOwnerAadharNumber"));
                gymOwner.setFlagVerified(resultSet.getBoolean("flagVerified"));
                gymOwner.setUserEmail(resultSet.getString("email"));
                gymOwner.setUserName(resultSet.getString("userName"));
                gymOwner.setUserMobile(resultSet.getString("phoneNumber"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return gymOwner;
    }

	@Override
    public FlipFitGymOwner register(FlipFitGymOwner gymOwner) {
        try{
            String userInsertQuery = "INSERT INTO FlipFitUser (email, phoneNumber, roleID, passwordHash, userName) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement userStatement = connection.prepareStatement(userInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            userStatement.setString(1, gymOwner.getUserEmail());
            userStatement.setString(2, gymOwner.getUserMobile());
            userStatement.setInt(3, 2); // Assuming roleID 2 is for Gym Owners
            userStatement.setString(4, gymOwner.getUserPassword());
            userStatement.setString(5, gymOwner.getUserName());
            int userRowsInserted = userStatement.executeUpdate();
            if (userRowsInserted > 0) {
                ResultSet generatedKeys = userStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    String gymOwnerInsertQuery = "INSERT INTO FlipFitGymOwner (gymOwnerID, gymOwnerPAN, gymOwnerAadharNumber, flagVerified) VALUES (?, ?, ?, ?)";
                    PreparedStatement gymOwnerStatement = connection.prepareStatement(gymOwnerInsertQuery);
                    gymOwnerStatement.setInt(1, userId);
                    gymOwnerStatement.setString(2, gymOwner.getPanNumber());
                    gymOwnerStatement.setString(3, gymOwner.getAdharNumber());
                    gymOwnerStatement.setBoolean(4, gymOwner.getFlagVerified());
                    int gymOwnerRowsInserted = gymOwnerStatement.executeUpdate();
                    if (gymOwnerRowsInserted > 0) {
                        gymOwner.setGymOwnerId(userId);
                        return gymOwner;
                    }
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }
}
