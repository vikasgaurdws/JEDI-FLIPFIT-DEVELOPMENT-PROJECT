/**
 * 
 */
package com.flipkart.business;

import java.util.List;

/**
 * 
 */
public class AdminOperations extends UserOperations {
    private List<GymOwnerOperations> gymOwnerList;
    private List<GymOperations> gyms;

    public void handleGymOwnerVerification() {
        // Logic to verify gym owners
        gymOwnerList.forEach(owner -> {
            owner.requestForVerification();
            System.out.println("Verified owner: " + owner);
        });
    }

    public void handleGymVerification() {
        // Logic to verify gyms
        gyms.forEach(gym -> {
            gym.isVerified();
            System.out.println("Verified gym: " + gym.getGymID());
        });
    }
}
