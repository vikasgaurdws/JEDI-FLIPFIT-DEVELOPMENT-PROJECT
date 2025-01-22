package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.business.FlipFitCustomerOperations;


public class FlipfitCustomerClient {

    private final Scanner sc;
    private final FlipFitCustomer flipfitCustomer;  // Using FlipfitCustomer
    private final FlipFitCustomerOperations flipfitCustomerOperations;

    // Constructor initializes scanner and customer operations
    public FlipfitCustomerClient(Scanner sc) {
        this.sc = sc;
        this.flipfitCustomer = new FlipFitCustomer();  // Initialize FlipfitCustomer
        this.flipfitCustomerOperations = new FlipFitCustomerOperations();  // Initialize FlipfitCustomerOperations
    }

    // Register a new customer
    public void registerCustomer() {
        System.out.print("Enter Name: ");
        flipfitCustomer.setUserName(sc.next());

        System.out.print("Enter email: ");
        flipfitCustomer.setUserEmail(sc.next());

        System.out.print("Enter password: ");
        flipfitCustomer.setUserPassword(sc.next());

        System.out.print("Enter Phone Number: ");
        flipfitCustomer.setUserMobile(sc.next());

        flipfitCustomer.setUserRole("Customer");

        System.out.print("Enter Age: ");
        flipfitCustomer.setCustomerAge(Integer.valueOf(sc.next()));


        flipfitCustomerOperations.registerCustomer(flipfitCustomer);

        System.out.println("Customer registered successfully!");
    }

    public void login() {
        sc.nextLine();  

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        // Validate credentials
        if (flipfitCustomerOperations.validateCreds(email, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    public void editProfile(String email) {
        System.out.print("Enter password: ");
        flipfitCustomer.setUserPassword(sc.next());

        System.out.print("Enter Name: ");
        flipfitCustomer.setUserName(sc.next());

        System.out.print("Enter Phone Number: ");
        flipfitCustomer.setUserMobile(sc.next());

        System.out.print("Enter Age: ");
        flipfitCustomer.setCustomerAge(Integer.valueOf(sc.next()));


        // Here you would likely want to update the customer using an operation method
        flipfitCustomerOperations.updateCustomerProfile(flipfitCustomer);

        System.out.println("Successfully edited your profile");
    }

    public void viewProfile(String email) {
        FlipFitCustomer customerProfile = flipfitCustomerOperations.getCustomerProfile(email);
        if (customerProfile != null) {
            System.out.println("Customer Profile: ");
            System.out.println("Name: " + customerProfile.getUserName());
            System.out.println("Email: " + customerProfile.getUserEmail());
            System.out.println("Phone Number: " + customerProfile.getUserMobile());
            System.out.println("Age: " + customerProfile.getCustomerAge());
        } 
        else {
            System.out.println("No profile found for the given email.");
        }
    }

    public void getGyms() {
        flipfitCustomerOperations.getAvailableGyms().forEach(System.out::println);
    }

    public void searchByLocation() {
        System.out.print("Enter location to search for gyms: ");
        String location = sc.nextLine();
        flipfitCustomerOperations.searchGymsByLocation(location).forEach(System.out::println);
    }

    // Search for gyms by available time
    public void searchByTime() {
        System.out.print("Enter time to search for available gyms: ");
        String time = sc.nextLine();
        flipfitCustomerOperations.searchGymsByTime(time).forEach(System.out::println);
    }

    public void bookSlot(String email) {
        System.out.print("Enter gym name to book: ");
        String gymName = sc.nextLine();
        System.out.print("Enter desired time: ");
        String time = sc.nextLine();
        boolean success = flipfitCustomerOperations.bookGymSlot(email, gymName, time);
        if (success) {
            System.out.println("Slot booked successfully.");
        } else {
            System.out.println("Failed to book the slot.");
        }
    }

    // Cancel a booking
    public void cancelBooking(String email) {
        System.out.print("Enter booking ID to cancel: ");
        String bookingId = sc.nextLine();
        boolean success = flipfitCustomerOperations.cancelBooking(email, bookingId);
        if (success) {
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Failed to cancel the booking.");
        }
    }

    // View customer's bookings
    public void viewBookings(String email) {
        flipfitCustomerOperations.getCustomerBookings(email).forEach(System.out::println);
    }

    // Make payment for a booking
    public void makePayment(String email) {
       
    }
    
    
    public void flipfitCustomerMenu()
    {
    	
    }
}
