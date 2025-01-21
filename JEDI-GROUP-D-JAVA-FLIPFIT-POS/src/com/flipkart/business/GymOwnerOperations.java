/**
 * 
 */
package com.flipkart.business;

import java.util.List;

/**
 * 
 */
public class GymOwnerOperations extends UserOperations {
    private String PAN;
    private String aadharNumber;
    private boolean flagVerified;
    private List<GymOperations> gyms;

    public void requestForVerification() {
        // Logic to request verification
        System.out.println("Verification requested for owner: " + PAN);
    }

    public void addGym(GymOperations gym) {
        // Logic to add a gym
        gyms.add(gym);
        System.out.println("Gym added successfully: " + gym.getGymID());
    }

    public void viewGym() {
        // Logic to view gyms
        gyms.forEach(gym -> System.out.println("Gym: " + gym.getGymID()));
    }

    public boolean isVerified() {
        return flagVerified;
    }
}
