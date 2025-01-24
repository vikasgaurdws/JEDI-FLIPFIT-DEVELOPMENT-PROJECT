package com.flipkart.business;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.DAO.FlipFitAdminDAO;
import com.flipkart.DAO.FlipFitAdminDAOImpl;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;

public class FlipFitAdminOperations {

    // Simulating DAO object (replace with actual DAO implementation)
    private final FlipFitAdminDAO adminDAO=new FlipFitAdminDAOImpl();

    // Constructor for dependency injection

    public void createAdmin(String userName) {
        adminDAO.createAdmin(userName);
    }

    public List<FlipFitGymOwner> viewAllGymOwners() {
        List<FlipFitGymOwner> gymOwners = adminDAO.viewAllGymOwners();
        if (gymOwners.isEmpty()) {
            System.out.println("No Gym Owners found!");
            return new ArrayList<>();
        }
        return gymOwners;
    }

    public List<FlipFitGym> viewGymDetails() {
        List<FlipFitGym> gyms = adminDAO.viewGymDetails();
        if (gyms.isEmpty()) {
            System.out.println("No Gyms found!");
            return new ArrayList<>();
        }
        return gyms;
    }

    public void approveGymOwnerRequests(int ownerId) {
        boolean result = adminDAO.approveGymOwnerRequests(ownerId);
        if (result) {
            System.out.println("Gym Owner request with ID " + ownerId + " has been approved.");
        } else {
            System.out.println("Gym Owner request with ID " + ownerId + " could not be found.");
        }
    }

    public void approveGymRequests(int gymId) {
        boolean result = adminDAO.approveGymRequests(gymId);
        if (result) {
            System.out.println("Gym request with ID " + gymId + " has been approved.");
        } else {
            System.out.println("Gym request with ID " + gymId + " could not be found.");
        }
    }

    public List<FlipFitGym> viewPendingGymRequests() {
        return adminDAO.viewPendingGymRequests();
    }

    public List<FlipFitGymOwner> viewPendingGymOwnerRequests() {
        return adminDAO.viewPendingGymOwnerRequests();
    }

    public List<FlipFitGymOwner> viewGymUsers(int gymId) {
        return adminDAO.viewGymUsers(gymId);
    }
}
