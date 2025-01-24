/**
 *
 */
package com.flipkart.client;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.business.FlipFitGymOwnerOperations;
import com.flipkart.bean.FlipFitGym;

import static java.sql.Types.NULL;

public class FlipfitGymOwnerMenu {


    FlipFitGymOwner gymOwner;

    FlipFitGymOwnerOperations flipfitGymOwnerOperations;
    Scanner in;

//	FlipFitGym flipFitGym = new FlipFitGym("Gym1", "Bellandur", 5, 500, false, 1);

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
        gymOwner.setUserRole("GymOwner");

        System.out.println("Enter Name: ");
        gymOwner.setUserName(in.next());

        System.out.println("Enter Phone Number: ");
        gymOwner.setUserMobile(in.next());

        System.out.println("Enter PAN: ");
        gymOwner.setPanNumber(in.next());

        System.out.println("Enter Aadhaar: ");
        gymOwner.setAdharNumber(in.next());

        boolean done = flipfitGymOwnerOperations.registerGymOwner(gymOwner);
        if (done) {
            System.out.println("Added the user successfully \n" + gymOwner.toString());
            this.gymOwner = gymOwner;
        } else {
            System.out.println("Error adding the user");
        }
    }

    public void login(String email, String password) {

        // Validate credentials
        this.gymOwner = flipfitGymOwnerOperations.login(email, password);
        if (gymOwner != null) {
            System.out.println("Login successful!");
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
        gymOwner.setUserRole("GymOwner");

        System.out.println("Enter Name: ");
        gymOwner.setUserName(in.next());

        System.out.println("Enter Phone Number: ");
        gymOwner.setUserMobile(in.next());

        System.out.println("Enter PAN: ");
        gymOwner.setPanNumber(in.next());

        System.out.println("Enter Aadhaar: ");
        gymOwner.setAdharNumber(in.next());

        flipfitGymOwnerOperations.editProfile(gymOwner);
        this.gymOwner = gymOwner;
    }


    public void addGym() {
        FlipFitGym flipFitGym = new FlipFitGym();
        System.out.println("\nEnter Gym Details: \n");
        System.out.println("Enter gym name");
        flipFitGym.setGymName(in.next());
        System.out.println("Enter gym location");
        flipFitGym.setGymLocation(in.next());
        System.out.println("Enter available slot");
        flipFitGym.setAvailableSlot(in.nextInt());
        System.out.println("Enter price");

        flipFitGym.setFlagVerified(false);
        System.out.println("Enter gym id");
        flipFitGym.setGymId(in.nextInt());

        flipfitGymOwnerOperations.addGym(gymOwner, flipFitGym);
    }

    public void editGym() {
        FlipFitGym flipFitGym = new FlipFitGym();
        System.out.println("\nEnter Gym Details: \n");
        System.out.println("Enter gym name");
        flipFitGym.setGymName(in.next());
        System.out.println("Enter gym location");
        flipFitGym.setGymLocation(in.next());
        System.out.println("Enter available slot");
        flipFitGym.setAvailableSlot(in.nextInt());
        System.out.println("Enter price");

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
  
public void addSlot() {
		Slot slot = new Slot();
		System.out.println("Enter slot id");
		slot.setSlotId(Integer.valueOf(in.next()));
		System.out.println("Enter Gym id");
		slot.setGymId(Integer.valueOf(in.next()));
		System.out.println("Enter Start time");
		slot.setStartTime(LocalTime.ofSecondOfDay(Integer.parseInt(in.next())));
		System.out.println("Enter capacity");
		slot.setCapacity(Integer.valueOf(in.next()));
		System.out.println("Enter available seats");
		slot.setAvailableSeats(Integer.valueOf(in.next()));
		flipfitGymOwnerOperations.addSlot(slot);
	}


    public void gymOwnerMenu(String email) {
        boolean recur = true;
        while (recur) {
            System.out.println("\nHere are the actions you can perform!");

            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Add Gym");
            System.out.println("4. Edit Gym");
            System.out.println("5. Add Slot");
            System.out.println("6. View All Gym Details");
            System.out.println("7. LogOut\n");

            System.out.println("Enter Your Choice: ");

            System.out.println("______________________________________________________________\n");
            int choice = in.nextInt();
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
                    editGym();
                    break;
                case 5:
                    addSlot();
                    break;
                case 6:
                    getGymDetails();
                    break;
                case 7:
                    recur = false;
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
            if (!recur) {
                gymOwner = new FlipFitGymOwner();
                System.out.println("Logged Out Successfully!");
            }
        }

    }

}
