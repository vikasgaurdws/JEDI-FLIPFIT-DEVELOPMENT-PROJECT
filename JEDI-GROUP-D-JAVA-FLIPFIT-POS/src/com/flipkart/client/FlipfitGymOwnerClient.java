/**
 * 
 */
package com.flipkart.client;

import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.business.FlipFitGymOwnerOperations;
import com.flipkart.bean.FlipFit;
/**
 * 
 */
public class FlipfitGymOwnerClient {
	FlipFitGymOwner gymOwner;
	FlipFitGymOwnerOperations flipfitGymOwnerOperations;
	Scanner in = new Scanner(System.in);
	FlipFit flipFit = new FlipFit("Gym1","Bellandur",5,500,false,1);
	FlipfitGymOwnerClient(){
	 gymOwner = new FlipFitGymOwner();
	 flipfitGymOwnerOperations = new FlipFitGymOwnerOperations();
	}
	public void gymOwnerRegistration(Scanner in) {
		System.out.println("\nEnter GymOwner Details: \n");
		System.out.print("Enter Email: ");
		gymOwner.setUserEmail(in.next());
		System.out.print("Enter Password: ");
		gymOwner.setUserPassword(in.next());
		gymOwner.setUserRole("GymOwner");
		System.out.print("Enter Name: ");
		gymOwner.setUserName(in.next());
		System.out.print("Enter Phone Number: ");
		gymOwner.setUserMobile(in.next());
		System.out.print("Enter PAN: ");
		gymOwner.setPanNumber(in.next());
		System.out.print("Enter Aadhaar: ");
		gymOwner.setAdharNumber(in.next());

	 //need to add it to db and get the confirmation
	}
    public void login() {
        in.nextLine();  

        System.out.print("Enter email: ");
        String email = in.nextLine();

        System.out.print("Enter password: ");
        String password = in.nextLine();

        // Validate credentials
        if (flipfitGymOwnerOperations.validateCreds(email, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password.");
        }
    }

	public void editProfile(String email) {
		System.out.println("Enter Details: ");
		System.out.print("Enter Email: ");
		gymOwner.setUserEmail(in.next());
		System.out.print("Enter Password: ");
		gymOwner.setUserPassword(in.next());
		gymOwner.setUserRole("GymOwner");
		System.out.print("Enter Name: ");
		gymOwner.setUserName(in.next());
		System.out.print("Enter Phone Number: ");
		gymOwner.setUserMobile(in.next());
		System.out.print("Enter PAN: ");
		gymOwner.setPanNumber(in.next());
		System.out.print("Enter Aadhaar: ");
		gymOwner.setAdharNumber(in.next());

		flipfitGymOwnerOperations.editProfile();
	}

	public void viewProfile( String email) {
		flipfitGymOwnerOperations.viewProfile();
	}

	public void addGym( String email) {
		flipfitGymOwnerOperations.addGym(flipFit);
	}

	public void editGym(String email) {
		flipfitGymOwnerOperations.editGym(flipFit);
	}

	public void getGymDetails(String email) {
		List<FlipFit> gymDetails = flipfitGymOwnerOperations.getGymDetail(email);
		for (FlipFit gym : gymDetails) {
			System.out.println(gym.toString());
		}
	}

	public void addSlot() {
		Slot slot = new Slot();
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

			System.out.print("Enter Your Choice: " );

			System.out.println("______________________________________________________________\n");
			int choice=in.nextInt();
			switch (choice) {
			case 1:
				viewProfile( email);
				break;
			case 2:
				editProfile(email);
				break;
			case 3:
				addGym(email);
				break;
			case 4:
				editGym(email);
				break;
			case 5:
				addSlot();
				break;
			case 6:
				getGymDetails(email);
				break;
			case 7:
				recur = false;
				break;
			default:
				System.out.println("Invalid Choice!");
			}
			if (!recur) {
				gymOwner = new FlipFitGymOwner();
				//boolean logOutSuccess = userBusiness.logout(gymOwner);
//				if (logOutSuccess)
//					System.out.println("Logged Out Successfully!");
//				else
					System.out.println("Logged Out Successfully!");
			}
		}

	}
}
