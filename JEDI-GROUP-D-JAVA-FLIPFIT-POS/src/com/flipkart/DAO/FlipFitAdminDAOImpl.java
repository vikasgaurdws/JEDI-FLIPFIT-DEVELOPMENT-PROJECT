package com.flipkart.DAO;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.utils.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminDAOImpl implements FlipFitAdminDAO {

    private final Connection connection;

    public FlipFitAdminDAOImpl() {
        this.connection = DBconnection.getConnection();
        if (connection == null) {
            System.out.println("Database connection failed. Please check your DBconnection class.");
        }
    }

    @Override
    public void createAdmin(String userName) {
        String query = "INSERT INTO FlipFitAdmin (username) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            statement.executeUpdate();
            System.out.println("Admin created with username: " + userName);
        } catch (SQLException e) {
            System.err.println("Error creating admin: " + e.getMessage());
        }
    }
    
    @Override
    public boolean verifyCred(String email, String pwd) {
        String query = "SELECT * FROM FlipFitUser WHERE email = ? AND passwordHash = ? AND roleID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, pwd);
            statement.setString(3, "1"); // Assuming "role" stores roleId as a string
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                // A match is found
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error verifying credentials: " + e.getMessage());
        }
        // No match found or an error occurred
        return false;
    }


    @Override
    public List<FlipFitGymOwner> viewAllGymOwners() {
        String query = "SELECT * FROM FlipFitGymOwners";
        List<FlipFitGymOwner> gymOwners = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                gymOwners.add(owner);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving gym owners: " + e.getMessage());
        }
        return gymOwners;
    }

    @Override
    public List<FlipFitGym> viewGymDetails() {
        String query = "SELECT * FROM FlipFitGym";
        List<FlipFitGym> gyms = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                FlipFitGym gym = new FlipFitGym();
                gyms.add(gym);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving gym details: " + e.getMessage());
        }
        return gyms;
    }

    @Override
    public boolean approveGymOwnerRequests(int ownerId) {
        String query = "UPDATE FlipFitGymOwner SET flagVerified = true WHERE gymOwnerId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ownerId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error approving Gym Owner request: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean approveGymRequests(int gymId) {
        String query = "UPDATE FlipFitGym SET flagVerified = true WHERE gymId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, gymId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error approving Gym request: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<FlipFitGym> viewPendingGymRequests() {
        String query = "SELECT * FROM FlipFitGym WHERE flagVerified = 0";
        List<FlipFitGym> pendingGyms = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
            	
                FlipFitGym gym = new FlipFitGym();
            	gym.setGymId(rs.getInt("gymID"));
            	gym.setGymName(rs.getString("gymName"));
            	gym.setGymLocation(rs.getString("gymLocation"));
            	gym.setPrice(rs.getInt("price"));
            	if(rs.getInt("flagVerified")==1)
            		gym.setFlagVerified(true);
            	else
            		gym.setFlagVerified(false);
                pendingGyms.add(gym);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving pending gym requests: " + e.getMessage());
        }
        return pendingGyms;
    }

    @Override
    public List<FlipFitGymOwner> viewPendingGymOwnerRequests() {
        String query = "SELECT * FROM FlipFitGymOwner Where flagVerified = 0";
        List<FlipFitGymOwner> pendingOwners = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setGymOwnerId(rs.getInt("gymOwnerID"));
                owner.setPanNumber(rs.getString("gymOwnerPAN"));
                owner.setAdharNumber(rs.getString("gymOwnerAadharNumber"));
                pendingOwners.add(owner);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving pending gym owner requests: " + e.getMessage());
        }
        return pendingOwners;
    }

    @Override
    public List<FlipFitGymOwner> viewGymUsers(int gymId) {
        String query = "SELECT * FROM GymUsers WHERE gymId = ?";
        List<FlipFitGymOwner> gymUsers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, gymId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FlipFitGymOwner user = new FlipFitGymOwner();
                gymUsers.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving gym users: " + e.getMessage());
        }
        return gymUsers;
    }
}
