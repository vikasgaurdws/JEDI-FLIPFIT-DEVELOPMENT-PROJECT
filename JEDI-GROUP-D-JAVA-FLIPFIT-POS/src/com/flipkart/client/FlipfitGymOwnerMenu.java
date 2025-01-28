/**
 *
 */
package com.flipkart.client;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.business.FlipFitGymOwnerOperations;
import com.flipkart.exception.InvalidInputException;
import com.flipkart.bean.FlipFitGym;

import static java.sql.Types.NULL;

public class FlipfitGymOwnerMenu {


    FlipFitGymOwner gymOwner;

    FlipFitGymOwnerOperations flipfitGymOwnerOperations;
    Scanner in;


    FlipfitGymOwnerMenu(Scanner in) {
        this.in = in;
        gymOwner = new FlipFitGymOwner();
        flipfitGymOwnerOperations = new FlipFitGymOwnerOperations();
    }

    public void gymOwnerRegistration() {
        FlipFitGymOwner gymOwner = new FlipFitGymOwner();

        System.out.println("\nEnter GymOwner Details: \n");

        System.out.println("Enter Email: ");
        gymOwner.setUserEmail(in.next());

        System.out.println("Enter Password: ");
        gymOwner.setUserPassword(in.next());
        gymOwner.setUserRole(3);

        System.out.println("Enter Name: ");
        gymOwner.setUserName(in.next());

        System.out.println("Enter Phone Number: ");
        gymOwner.setUserMobile(in.next());

        System.out.println("Enter PAN: ");
        gymOwner.setPanNumber(in.next());

        System.out.println("Enter Aadhaar: ");
        gymOwner.setAdharNumber(in.next());
        
        
        gymOwner.setFlagVerified(false);
        FlipFitGymOwner done = flipfitGymOwnerOperations.registerGymOwner(gymOwner);
        System.out.println(done);
        this.gymOwner = done;
        
        if(gymOwner!=null)
        {
        	System.out.println("Registration sucessful");
        	return;
        }


    }

    public void login(String email, String password) {

        // Validate credentials
        this.gymOwner = flipfitGymOwnerOperations.login(email, password);
        if (gymOwner != null) {
            System.out.println("Login successful!");
            System.out.println("Welcome "+gymOwner.getUserName()+"! "+", Current time: "+LocalDateTime.now());

            System.out.println(gymOwner);
            gymOwnerMenu(email);
        } else {
            System.out.println("Invalid email or password.");
        }
    }


 public void editProfile() {
        FlipFitGymOwner gymOwner = new FlipFitGymOwner();
        System.out.println("\nEnter GymOwner Details: \n");

        System.out.println("Enter Email: ");
        gymOwner.setUserEmail(in.next());


        System.out.println("Enter Password: ");
        gymOwner.setUserPassword(in.next());
        gymOwner.setUserRole(2);

        System.out.println("Enter Name: ");
        gymOwner.setUserName(in.next());

        System.out.println("Enter Phone Number: ");
        gymOwner.setUserMobile(in.next());

        System.out.println("Enter PAN: ");
        gymOwner.setPanNumber(in.next());

        System.out.println("Enter Aadhaar: ");
        gymOwner.setAdharNumber(in.next());

        flipfitGymOwnerOperations.editProfile(gymOwner);
        this.gymOwner =gymOwner;
    }


    public void addGym() throws InvalidInputException{
    	
    	int id=gymOwner.getGymOwnerId();
    	boolean flag= flipfitGymOwnerOperations.isGymOwnerVerified(id);
    	
    	if(!flag)
    		{
    			System.out.println("you are not yet verified please retry later");
    			return;
    		
    		}
        FlipFitGym flipFitGym = new FlipFitGym();
        System.out.println("\nEnter Gym Details:");
        System.out.println("Enter gym name");
        in.nextLine(); // Consume leftover newline
        flipFitGym.setGymName(in.next());
        System.out.println("Enter gym location");
        flipFitGym.setGymLocation(in.next());
        System.out.println("Enter available slot");
        try {
        flipFitGym.setAvailableSlot(in.nextInt());
        System.out.println("Enter price");
        flipFitGym.setPrice(in.nextInt());
        }catch(Exception e) {
        	throw new InvalidInputException(e.getMessage());
        }
        flipFitGym.setFlagVerified(false);
        
        flipfitGymOwnerOperations.addGym(gymOwner, flipFitGym);
    	}

    public void editGym() throws InvalidInputException {
        FlipFitGym flipFitGym = new FlipFitGym();
        System.out.println("\nEnter Gym Details: \n");
        System.out.println("Enter gym name");
        flipFitGym.setGymName(in.next());
        System.out.println("Enter gym location");
        flipFitGym.setGymLocation(in.next());
        System.out.println("Enter available slot");
        try {
            flipFitGym.setAvailableSlot(in.nextInt());
            System.out.println("Enter price");
            flipFitGym.setPrice(in.nextInt());
            }catch(Exception e) {
            	throw new InvalidInputException(e.getMessage());
            }
        
        flipFitGym.setFlagVerified(false);
        System.out.println("Enter gym id");
        flipFitGym.setGymId(in.nextInt());

        flipfitGymOwnerOperations.editGym(gymOwner, flipFitGym);

    }

    public void getGymDetails() {
        List<FlipFitGym> gymDetails = flipfitGymOwnerOperations.getGymDetail(gymOwner);
        for (FlipFitGym gym : gymDetails) {
            System.out.println(gym.toString());
        }
    }
  
public void addSlot() throws InvalidInputException {
	try {
		Slot slot = new Slot();
		
		System.out.println("Enter Gym id");
		int gymid = Integer.valueOf(in.next());
		boolean flag = flipfitGymOwnerOperations.isGymVerified(gymid);
		if(!flag)
		{
			System.out.println("Sorry your gym is not yet verified");
			return ;
		}
		slot.setGymId(gymid);
		
		System.out.println("Enter Start time (HH:MM):");
		String timeInput = in.next(); // User inputs time like "09:30"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime startTime = LocalTime.parse(timeInput, formatter);
		slot.setStartTime(startTime);
		
		System.out.println("Enter no of seats for each slot");
		slot.setCapacity(Integer.valueOf(in.next()));

		slot.setAvailableSeats(0);
		flipfitGymOwnerOperations.addSlot(slot);
		}catch(Exception e) {
			throw new InvalidInputException(e.getMessage());
		}
	}


    public void gymOwnerMenu(String email) {
        boolean recur = true;
        while (recur) {
            System.out.println("\nHere are the actions you can perform!");

            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Add Gym");
            System.out.println("4. Add Slot");
            System.out.println("5. View All Gym Details");
            System.out.println("6. LogOut\n");

            System.out.println("Enter Your Choice: ");

            System.out.println("______________________________________________________________\n");
            int choice = in.nextInt();
            try {
            switch (choice) {
                case 1:
                    flipfitGymOwnerOperations.getProfile(gymOwner);
                    break;
                case 2:
                    editProfile();
                    break;
                case 3:
                    addGym();
                    break;
                
                case 4:
                    addSlot();
                    break;
                case 5:
                    getGymDetails();
                    break;
                case 6:
                	System.out.println("Thankyou for visiting!!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice!");
            }
            if (!recur) {
                gymOwner = new FlipFitGymOwner();
                System.out.println("Logged Out Successfully!");
            }
        }catch(InvalidInputException e) {
        	System.out.println("\n++++++++++++++++++++++++++++++++++++++++");
        	System.out.println("Enter a valid input!");
        	System.out.println("++++++++++++++++++++++++++++++++++++++++\n");

        	gymOwnerMenu(email);
        }
       }

    }

}
