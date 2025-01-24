package com.flipkart.client;
import java.text.*;
import java.util.Scanner;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.business.FlipFitCustomerOperations;


public class FlipfitCustomerMenu {

    private final Scanner sc;
    private final FlipFitCustomer flipfitCustomer;  // Using FlipfitCustomer
    private final FlipFitCustomerOperations flipfitCustomerOperations;
    private String email;
    private int userId;

    // Constructor initializes scanner and customer operations
    public FlipfitCustomerMenu(Scanner sc) {
    	this.sc=sc;
        this.flipfitCustomer = new FlipFitCustomer();  // Initialize FlipfitCustomer
        this.flipfitCustomerOperations = new FlipFitCustomerOperations();  // Initialize FlipfitCustomerOperations
    }

    // Register a new customer
    public void registerCustomer() {
        System.out.println("Enter Name: ");
        flipfitCustomer.setUserName(sc.next());

        System.out.println("Enter email: ");
        email=sc.next();
        flipfitCustomer.setUserEmail(email);

        System.out.println("Enter password: ");
        flipfitCustomer.setUserPassword(sc.next());

        System.out.println("Enter Phone Number: ");
        flipfitCustomer.setUserMobile(sc.next());

        flipfitCustomer.setUserRole(3);

        System.out.println("Enter Age: ");
        flipfitCustomer.setCustomerAge(Integer.valueOf(sc.next()));


        flipfitCustomerOperations.registerCustomer(flipfitCustomer);

        System.out.println("Customer registered successfully!");
    }

    public void login(String email,String password) {  

        // Validate credentials
    	userId=flipfitCustomerOperations.validateCreds(email, password);
        if (userId!=-1) {
            System.out.println("Login successful!");
            flipfitCustomer.setUserId(userId);
            flipfitCustomerMenu();
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    public void editProfile() {
        System.out.println("Enter password: ");
        flipfitCustomer.setUserPassword(sc.next());

        System.out.println("Enter Name: ");
        flipfitCustomer.setUserName(sc.next());

        System.out.println("Enter Phone Number: ");
        flipfitCustomer.setUserMobile(sc.next());

        System.out.println("Enter Age: ");
        flipfitCustomer.setCustomerAge(Integer.valueOf(sc.next()));


        // Here you would likely want to update the customer using an operation method
        flipfitCustomerOperations.updateCustomerProfile(flipfitCustomer);

        System.out.println("Successfully edited your profile");
    }

    public void viewProfile() {
        FlipFitCustomer customerProfile = flipfitCustomerOperations.getCustomerProfile(userId);
        if (customerProfile != null) {
            System.out.println("Customer Profile: ");
            System.out.println("Name: " + customerProfile.getUserName());
            System.out.println("Email: " + customerProfile.getUserEmail());
            System.out.println("Phone Number: " + customerProfile.getUserMobile());
            System.out.println("Age: " + customerProfile.getCustomerAge());
        } 
        else {
            System.out.println("No profile found for the given user.");
        }
    }

    public void getGyms() {
        flipfitCustomerOperations.getAvailableGyms().forEach(System.out::println);
        System.out.println("Enter gym Id to view slots: ");
        int gymId=sc.nextInt();
        flipfitCustomerOperations.getSlots(gymId).forEach(System.out::println);
        bookSlot(gymId);
    }

    public void searchByLocation() {
        System.out.println("Enter location to search for gyms: ");
        sc.nextLine();
        String location = sc.nextLine();
        flipfitCustomerOperations.searchGymsByLocation(location).forEach(System.out::println);
        System.out.println("Enter gym Id to view slots: ");
        int gymId=sc.nextInt();
        flipfitCustomerOperations.getSlots(gymId).forEach(System.out::println);
        bookSlot(gymId);
    }

    public void bookSlot(int gymId) {
        System.out.println("Enter slot Id: ");
        int slotId = sc.nextInt();
        System.out.println("Enter date: (yyyy-mm-dd)");
        sc.nextLine();
        String date=sc.nextLine();
//        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        int success=flipfitCustomerOperations.bookGymSlot(userId,slotId, gymId,java.sql.Date.valueOf(date));
        if (success!=-1) {
            System.out.println("Slot booked successfully.");
            System.out.println("Enter payment type: ");
            String type=sc.nextLine();
            flipfitCustomerOperations.makePayment(gymId, type, success);;
        } else {
            System.out.println("Failed to book the slot.");
        }
    }

    // Cancel a booking
    public void cancelBooking() {
        System.out.println("Enter booking ID to cancel: ");
        int bookingId = sc.nextInt();
        boolean success = flipfitCustomerOperations.cancelBooking(userId, bookingId);
        if (success) {
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Failed to cancel the booking.");
        }
    }

    // View customer's bookings
    public void viewBookings() {
        flipfitCustomerOperations.getCustomerBookings(userId).forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Do you want to cancel a booking? Enter 1 for yes 0 for no");
        if(sc.nextInt()==1)
            cancelBooking();
    }
    
    public void flipfitCustomerMenu()
    {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        while (true) {
            System.out.println("1. Edit profile");
            System.out.println("2. View profile");
            System.out.println("3. View gyms");
            System.out.println("4. Search gyms by location");
            System.out.println("5. View Bookings");
            System.out.println("6. Cancel Bookings");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {


                case 1:
                    editProfile();
                    break;

                case 2:
                    viewProfile();
                    break;

                case 3:
                    getGyms();
                    break;
                case 4:
                    searchByLocation();
                    break;
                case 5:
                    viewBookings();
                    break;
                case 6:
                	cancelBooking();
                	break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Please check the option you have entered");
            }
        }
    }
}
