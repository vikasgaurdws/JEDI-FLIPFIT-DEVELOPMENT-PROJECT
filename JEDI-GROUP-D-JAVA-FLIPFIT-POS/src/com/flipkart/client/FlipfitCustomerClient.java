package com.flipkart.client;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.Slot;
import com.flipkart.business.FlipFitCustomerOperations;


public class FlipfitCustomerClient {

    private final Scanner sc = new Scanner(System.in);
    private final FlipFitCustomer flipfitCustomer;  // Using FlipfitCustomer
    private final FlipFitCustomerOperations flipfitCustomerOperations;
    private String email;
    private int userId; //need to carry this forward for further methods

    // Constructor initializes scanner and customer operations
    public FlipfitCustomerClient() {
        this.flipfitCustomer = new FlipFitCustomer();  // Initialize FlipfitCustomer
        this.flipfitCustomerOperations = new FlipFitCustomerOperations();  // Initialize FlipfitCustomerOperations
    }

    // Register a new customer 
    // TODO:and get the userID
    public void registerCustomer() {
        System.out.print("Enter Name: ");
        flipfitCustomer.setUserName(sc.next());

        System.out.print("Enter email: ");
        this.email=sc.next();
        flipfitCustomer.setUserEmail(email);

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

    public void login(String email, String password) {
        sc.nextLine();  
        this.email=email;

        // Validate credentials
        if (flipfitCustomerOperations.validateCreds(email, password)) {
            System.out.println("Login successful!");
            flipfitCustomerMenu();
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
        flipfitCustomerOperations.getGyms()
                                 .forEach(gym -> System.out.println(gym)); 
        
        System.out.print("Would you like to see the slots for any gym? (yes/no): ");
        String response = sc.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {

         
        	bookSlot(this.email);

        } 
        else {
        	flipfitCustomerMenu();
        	
        }

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
        
    	
        System.out.print("/n Please enter the Gym ID to view available slots: ");
        int gymId = sc.nextInt();
        
        System.out.print("Enter Date (yyyy-mm-dd): ");
		String dateStr = sc.next();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(dateStr);
        
        List<Slot> slots = flipfitCustomerOperations.getSlots(gymId);

         for (Slot slot : slots) {
             System.out.println(slot);
         }
         

        System.out.println("Enter the slot id that you want to book: ");
		String slotId = sc.next();
        System.out.println("Enter details for payment");
	      
        //TODO:check the payment from client to DAO
	        makePayment(email);
	        
        int bookingResponse = flipfitCustomerOperations.bookSlot(gymId,slotId, email, date);
		
        switch (bookingResponse) {
		case 0:
			System.out.println("You have already booked this time. Cancelling the previous one and booking this slot");
			break;
		case 1:
			System.out.println("Slot is already booked, added to the waiting list");
			break;
		case 2:
			System.out.println("Successfully booked the slot");
			break;
		case 3:
			System.out.println("Slot not found");
			break;
		default:
			System.out.println("Booking failed");
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
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Do you want to cancel a booking? Enter 1 for yes 0 for no");
        if(sc.nextInt()==1)
            cancelBooking(email);
    }

    // Make payment for a booking
    public void makePayment(String email) {
       
    }
    
    
    public void flipfitCustomerMenu()
    {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        while (true) {
            System.out.println("1. Edit profile");
            System.out.println("2. View profile");
            System.out.println("3. View gyms");
            System.out.println("4. Search gyms by location");
            System.out.println("5. Search gyms by time");
            System.out.println("6. Book slots");
            System.out.println("7. View Bookings");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {


                case 1:
                    editProfile(email);
                    break;

                case 2:
                    viewProfile(email);
                    break;

                case 3:
                    getGyms();
                    break;

                case 4:
                    searchByLocation();
                    break;
                case 5:
                    searchByTime();
                    break;
                case 6:
                    bookSlot(email);
                    break;
                case 7:
                    viewBookings(email);
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Please check the option you have entered");
            }
        }
    }
}
