/**
 * 
 */
package com.flipkart.business;

/**
 * 
 */
public class UserOperations {
	private String userID;
    private String email;
    private String phoneNumber;
    private String role;

    public void editProfile() {
        // Logic to edit user profile
        System.out.println("Profile updated successfully for user: " + userID);
    }

    public void viewProfile() {
        // Logic to view user profile
        System.out.println("Viewing profile for user: " + userID);
    }
}
