/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

/**
 * 
 */
public class FlipfitGymOwnerClient {
	FlipfitGymOwner gymOwner;
	FlipfitGymOwnerOperations flipfitGymOwnerOperations;
	Scanner in;
	
	FlipfitGymOwnerClient(Scanner sc){
	 gymOwner = new GymOwner();
	 gymOwnerBusiness = new GymOwnerBusiness();
	 this.in=sc;
	}
	
	public void gymOwnerRegistration(Scanner in) {
		System.out.println("\nEnter GymOwner Details: \n");
		System.out.print("Enter Email: ");
		gymOwner.setEmail(in.next());
		System.out.print("Enter Password: ");
		gymOwner.setPassword(in.next());
		gymOwner.setRoleId("GymOwner");
		System.out.print("Enter Name: ");
		gymOwner.setName(in.next());
		System.out.print("Enter Phone Number: ");
		gymOwner.setPhoneNumber(in.next());
		System.out.print("Enter PAN: ");
		gymOwner.setPanNumber(in.next());
		System.out.print("Enter Aadhaar: ");
		gymOwner.setAadharNumber(in.next());

	 //need to add it to db and get the confirmation
	}

	public void editProfile(String email) {
		System.out.println("Enter Details: ");
		System.out.print("Enter Email: ");
		gymOwner.setEmail(in.next());
		System.out.print("Enter Password: ");
		gymOwner.setPassword(in.next());
		gymOwner.setRoleId("GymOwner");
		System.out.print("Enter Name: ");
		gymOwner.setName(in.next());
		System.out.print("Enter Phone Number: ");
		gymOwner.setPhoneNumber(in.next());
		System.out.print("Enter PAN: ");
		gymOwner.setPanNumber(in.next());
		System.out.print("Enter Aadhaar: ");
		gymOwner.setAadharNumber(in.next());

		gymOwnerBusiness.editProfile(gymOwner);
	}

	public void viewProfile( String email) {
		
	}

	public void addGym( String email) {
		
	}

	public void editGym(Scanner in, String email) {
		
	}

	public void getGymDetails(Scanner in, String email) {
		List<Gym> gymDetails = gymOwnerBusiness.getGymDetail(email);
		for (Gym gym : gymDetails) {
			System.out.println(gym);
		}
	}

	public void addSlot(Scanner in) {
		
	}

	public void gymOwnerMenu(Scanner in, String email) {
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
				System.out.println(ColorConstants.RED + "Invalid Choice!" + ColorConstants.RESET);
			}
			if (!recur) {
				gymOwner = new GymOwner();
				boolean logOutSuccess = userBusiness.logout(gymOwner);
				if (logOutSuccess)
					System.out.println(ColorConstants.GREEN + "Logged Out Successfully!" + ColorConstants.RESET);
				else
					System.out.println(ColorConstants.RED + "Logged Out Successfully!" + ColorConstants.RESET);
			}
		}

	}
}
