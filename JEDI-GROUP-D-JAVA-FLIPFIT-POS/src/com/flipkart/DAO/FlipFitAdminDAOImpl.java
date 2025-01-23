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
        String query = "INSERT INTO Admin (username) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            statement.executeUpdate();
            System.out.println("Admin created with username: " + userName);
        } catch (SQLException e) {
            System.err.println("Error creating admin: " + e.getMessage());
        }
    }

    @Override
    public List<FlipFitGymOwner> viewAllGymOwners() {
        String query = "SELECT * FROM GymOwners";
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
        String query = "SELECT * FROM Gyms";
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
        String query = "UPDATE GymOwners SET approved = 1 WHERE ownerId = ?";
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
        String query = "UPDATE Gyms SET approved = 1 WHERE gymId = ?";
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
        String query = "SELECT * FROM Gyms WHERE approved = 0";
        List<FlipFitGym> pendingGyms = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                FlipFitGym gym = new FlipFitGym();
                pendingGyms.add(gym);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving pending gym requests: " + e.getMessage());
        }
        return pendingGyms;
    }

    @Override
    public List<FlipFitGymOwner> viewPendingGymOwnerRequests() {
        String query = "SELECT * FROM GymOwners WHERE approved = 0";
        List<FlipFitGymOwner> pendingOwners = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
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
