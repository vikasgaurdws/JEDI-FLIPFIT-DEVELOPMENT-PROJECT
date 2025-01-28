package com.flipkart.client;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;
import com.flipkart.business.FlipFitCustomerOperations;
import com.flipkart.exception.InvalidDateException;
import com.flipkart.exception.InvalidInputException;
import com.flipkart.exception.InvalidLocationException;


public class FlipfitCustomerMenu {

    private  Scanner sc;
    private  FlipFitCustomer flipfitCustomer; 
    private  FlipFitCustomerOperations flipfitCustomerOperations;
    private String email;
    private int userId;
    private String userName;

    // Constructor initializes scanner and customer operations
    public FlipfitCustomerMenu(Scanner sc) {
    	this.sc=sc;
        this.flipfitCustomer = new FlipFitCustomer();  
        this.flipfitCustomerOperations = new FlipFitCustomerOperations();  
        }	

    // Register a new customer
    public void registerCustomer() throws Exception {
        System.out.println("Enter Name: ");
        flipfitCustomer.setUserName(sc.next());

        
        System.out.println("Enter email: ");
        email=sc.next();
        flipfitCustomer.setUserEmail(email);

        System.out.println("Enter password: ");
        flipfitCustomer.setUserPassword(sc.next());
        
        System.out.println("Enter userName: ");
        this.userName=sc.next();
        flipfitCustomer.setUserName(this.userName);


        System.out.println("Enter Phone Number: ");
        flipfitCustomer.setUserMobile(sc.next());

        flipfitCustomer.setUserRole(3);

        System.out.println("Enter Age: ");
        try {
        flipfitCustomer.setCustomerAge(Integer.valueOf(sc.next()));
        }catch(Exception e) {
        	throw new InvalidInputException(e.getMessage());
        }


        this.userId = flipfitCustomerOperations.registerCustomer(flipfitCustomer);
        
        
       

    }

    public void login(String email,String password) {  

        // Validate credentials
    	this.userId=flipfitCustomerOperations.validateCreds(email, password);
    	this.userName=flipfitCustomerOperations.getUserName(email,password);
        if (userId!=-1) {
            System.out.println("Login successful!");
            flipfitCustomer.setUserId(userId);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Welcome "+userName+" current time: "+LocalDateTime.now());
            flipfitCustomerMenu();
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    public void editProfile() throws Exception {
        System.out.println("Enter password: ");
        flipfitCustomer.setUserPassword(sc.next());

        System.out.println("Enter Name: ");
        flipfitCustomer.setUserName(sc.next());

        System.out.println("Enter Phone Number: ");
        flipfitCustomer.setUserMobile(sc.next());

        System.out.println("Enter Age: ");
        try {
            flipfitCustomer.setCustomerAge(Integer.valueOf(sc.next()));
            }catch(Exception e) {
            	throw new InvalidInputException(e.getMessage());
            }


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

    public void getGyms() throws Exception{

        List<FlipFitGym> gyms = flipfitCustomerOperations.getAvailableGyms();
        gyms.forEach(System.out::println);
        
        System.out.println("Enter gym Id to view slots: ");
        int gymId = sc.nextInt();
        
        FlipFitGym selectedGym = gyms.stream()
                .filter(gym -> gym.getGymId() == gymId)
                .findFirst()
                .orElse(null);

		if (selectedGym == null) {
		System.out.println("Invalid gym ID! Please try again.");
		return;  
		}

        
		List<Slot> slots= flipfitCustomerOperations.getSlots(gymId);
        slots.forEach(System.out::println);

        boolean flag = true;
        
        while (flag) {

            System.out.println("Enter slot Id to see number of booked seats: ");
            int sid = sc.nextInt();

            System.out.println("Enter date that you want to see the booked seats (yyyy-mm-dd): ");
            String dateString = sc.next(); 
            LocalDate locdate;
            try {
            	locdate = LocalDate.parse(dateString); 
            }catch(Exception e) {
            	throw new InvalidDateException(e.getMessage());
            }
            if(locdate.compareTo(LocalDate.now())<0)
            	throw new InvalidDateException("");
            int bookedSeats = flipfitCustomerOperations.bookedSeats(sid, locdate);
            //from the slots list above get the capacity 
            
            Slot selectedSlot = slots.stream()
                    .filter(slot -> slot.getSlotId() == sid)
                    .findFirst()
                    .orElse(null);
            
            if (selectedSlot == null) {
                System.out.println("Invalid slot ID! Please try again.");
                continue;
            }

            int capacity = selectedSlot.getCapacity();
            int remainingSeats = capacity - bookedSeats;
            
            System.out.println("The remaining no of seats for Slot ID: " + sid + " on " + locdate + " is " + remainingSeats);

            System.out.println("Enter 0 to exit, 1 to view more slots, or 2 to book this slot: ");
            int customerChoice = sc.nextInt();

            switch (customerChoice) {
                case 0:
                    System.out.println("Exiting slot viewing...");
                    flag = false;
                    break;
                case 1:
                    System.out.println("Viewing more slots...");
                    break;
                case 2:
                    int price = selectedGym.getPrice();
                    if(remainingSeats!=0)bookSlot(gymId, sid, dateString, price);
                    else
                    {
                    	System.out.println("Would you like to join the waitlist for this slot(yes/no)? ");
                    	sc.nextLine();
                    	String ip = sc.nextLine().toLowerCase();                     	
                    	
                    	if (ip.equals("yes")) {
                    	    
                    		waitlist(gymId, sid, dateString, price);
                    	}
                    	
                    }
                    flag = false; 
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 0, 1, or 2.");
            }
        }
    }

    public void searchByLocation() throws Exception {
        System.out.println("Enter location to search for gyms: ");
        sc.nextLine();
        String location = sc.nextLine();
        
        List<FlipFitGym> gyms= flipfitCustomerOperations.searchGymsByLocation(location);
        if(gyms.size()==0)
        	throw new InvalidLocationException(location);
        gyms.forEach(System.out::println);
        
        System.out.println("Enter gym Id to view slots: ");
        int gymId = sc.nextInt();
        
        FlipFitGym selectedGym = gyms.stream()
                .filter(gym -> gym.getGymId() == gymId)
                .findFirst()
                .orElse(null);

		if (selectedGym == null) {
		System.out.println("Invalid gym ID! Please try again.");
		return;  
		}
        
        flipfitCustomerOperations.getSlots(gymId).forEach(System.out::println);

        boolean flag = true;
        
        while (flag) {

            System.out.println("Enter slot Id to see number of booked seats: ");
            int sid = sc.nextInt();

            System.out.println("Enter date that you want to see the booked seats (yyyy-mm-dd): ");
            String dateString = sc.next(); 
            LocalDate locdate;
            try {
            	locdate = LocalDate.parse(dateString); 
            }catch(Exception e) {
            	throw new InvalidDateException(e.getMessage());
            }
            if(locdate.compareTo(LocalDate.now())<0)
            	throw new InvalidDateException("");
            int bookedSeats = flipfitCustomerOperations.bookedSeats(sid, locdate);
            System.out.println("No. of Bookings for Slot ID: " + sid + " on " + locdate + " is " + bookedSeats);

            // Ask user for next action
            System.out.println("Enter 0 to exit, 1 to view more slots, or 2 to book this slot: ");
            int customerChoice = sc.nextInt();

            switch (customerChoice) {
                case 0:
                    System.out.println("Exiting slot viewing...");
                    flag = false;
                    break;
                case 1:
                    System.out.println("Viewing more slots...");
                    break;
                case 2:
                    int price = selectedGym.getPrice();
                    bookSlot(gymId, sid, dateString, price);
                    flag = false; 
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 0, 1, or 2.");
            }
        }
    }
    
    public void waitlist(int gymId, int slotId, String date, int price) {
        
    	
        
        
        int success=flipfitCustomerOperations.joinWaitlist(userId,slotId, gymId,java.sql.Date.valueOf(date));
        System.out.println(success);
        if (success!=-1 ) {
            System.out.println("Enter payment type: ");
            String type=sc.nextLine();
            
            
            boolean paymentStatus=flipfitCustomerOperations.makePayment(type, success,price);
            if(paymentStatus)System.out.println("Payment of "+ price+" recieved and Slot booked with bookingID: "+success);
            else
            {
            	System.out.println("Payment Failed Booking not successful");
            	flipfitCustomerOperations.cancelBooking(userId, success);
            }
        }
        
        else if(success==-1) {
            System.out.println("You have a booking already during this slot and if you want to join waitlist please cancel that first ");
        }
    }

    
    public void bookSlot(int gymId, int slotId, String date, int price) {
        
    	
       
        int success=flipfitCustomerOperations.bookGymSlot(userId,slotId, gymId,java.sql.Date.valueOf(date));
        
        if (success!=-1 && success!=Integer.MIN_VALUE) {
            System.out.println("Enter payment type: ");
            
            sc.nextLine();  
            String type=sc.nextLine();
            
            boolean paymentStatus=flipfitCustomerOperations.makePayment(type, success,price);
            if(paymentStatus)System.out.println("Payment of "+ price+" recieved and Slot booked with bookingID: "+success);
            else
            {
            	System.out.println("Payment Failed Booking not successful");
            	flipfitCustomerOperations.cancelBooking(userId, success);
            }
        }
        else if(success==Integer.MIN_VALUE)
        {
        	System.out.println("You are rebooking the slot again in the same gym!!!");
        }
        else {
            System.out.println("Failed to book the slot.");
        }
    }

   

	// Cancel a booking
    public void cancelBooking()throws Exception {
        System.out.println("Enter booking ID to cancel: ");
        int bookingId = -1;
        try {
        bookingId=sc.nextInt();
        }catch(Exception e) {
        	throw new InvalidInputException(e.getMessage());
        }
        boolean success = flipfitCustomerOperations.cancelBooking(userId, bookingId);
        if (success) {
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Failed to cancel the booking.");
        }
    }

    // View customer's bookings
    public void viewBookings() throws Exception{
    	 List<Booking> bookings =flipfitCustomerOperations.getCustomerBookings(userId);

    	 if (bookings.isEmpty()) {
             System.out.println("No bookings found for the selected date.");
         } else {
             bookings.forEach(System.out::println);
         }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Do you want to cancel a booking? Enter 1 for yes 0 for no");
        if(sc.nextInt()==1)
            cancelBooking();
    }
    
    
    private void viewDailySchedule() {
    	System.out.println("Enter the date that you want to see your schedule(yyyy-mm--dd): ");

        String dateInput = sc.next(); 
        Date date = Date.valueOf(dateInput); 

        List<Booking> bookings = flipfitCustomerOperations.schedule(userId, date);

        if (bookings.isEmpty()) {
            System.out.println("No bookings found for the selected date.");
        } else {
            bookings.forEach(System.out::println);
        }
		
	}
    
    
    public void flipfitCustomerMenu()
    {
    	System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        while (true) {
            System.out.println("1. Edit profile");
            System.out.println("2. View profile");
            System.out.println("3. View gyms");
            System.out.println("4. Search gyms by location");
            System.out.println("5. View Bookings");
            System.out.println("6. Cancel Bookings");
            System.out.println("7. View your Daily Schedule ");
            System.out.println("8. Exit");


            System.out.print("Enter your choice: ");
            int choice=-1;
            try {
            choice= sc.nextInt();
            }catch(Exception e) {
            	System.out.println("\n++++++++++++++++++++++++++++++++++++++++");
    			System.out.println("Enter a valid choice!");
    			System.out.println("++++++++++++++++++++++++++++++++++++++++\n");
    			sc.next();
    			flipfitCustomerMenu();
            }try {
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
                    viewDailySchedule();
                    return;	
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Please check the option you have entered");
            }
            }catch(InvalidLocationException e) {
            	System.out.println("\n++++++++++++++++++++++++++++++++++++++++");
    			System.out.println("Enter a valid Location!");
    			System.out.println("++++++++++++++++++++++++++++++++++++++++\n");
    			flipfitCustomerMenu();
            }catch(InvalidDateException e) {
            	System.out.println("\n++++++++++++++++++++++++++++++++++++++++");
    			System.out.println("Enter a valid date in yyyy-mm-dd format!");
    			System.out.println("++++++++++++++++++++++++++++++++++++++++\n");
    			flipfitCustomerMenu();
            }
            catch(Exception e) {
            	System.out.println("\n++++++++++++++++++++++++++++++++++++++++");
    			System.out.println("Enter a valid choice!");
    			System.out.println("++++++++++++++++++++++++++++++++++++++++\n");
    			sc.next();
    			flipfitCustomerMenu();
            }
        }
    }

	
}
