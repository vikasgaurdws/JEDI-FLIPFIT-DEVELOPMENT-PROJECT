package com.flipkart.client;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.business.FlipFitAdminOperations;
import com.flipkart.exception.InvalidInputException;

import java.util.*;

public class FlipfitAdminMenu {

	FlipFitAdminOperations flipfitAdminOperations = new FlipFitAdminOperations();

	List<FlipFitGymOwner> gymOwnerList;
	List<FlipFitGym> gymList;

	Scanner sc;

	public FlipfitAdminMenu(Scanner sc)
	{
		this.sc=sc;
//		gymOwnerList = flipfitAdminOperations.viewAllGymOwners();
//		gymList = flipfitAdminOperations.viewGymDetails();
	}
	public void createAdmin() {
		System.out.println("Enter Admin Name: ");
		flipfitAdminOperations.createAdmin(sc.next());

	}

	public void viewPendingGymOwnerRequests() {
		flipfitAdminOperations.viewPendingGymOwnerRequests().forEach(System.out::println);
	}
	public void viewPendingGymRequests() {
		flipfitAdminOperations.viewPendingGymRequests().forEach(System.out::println);
	}
	public void approveGymOwnerRequests() throws InvalidInputException {
		System.out.println("Enter GymOwnerId: ");
		try {
		flipfitAdminOperations.approveGymOwnerRequests(Integer.parseInt(sc.next()));
		}catch(Exception e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	public void approveGymRequests() throws Exception{
		System.out.println("Enter Gym Id: ");
		try {
		flipfitAdminOperations.approveGymRequests(Integer.parseInt(sc.next()));
		}catch(Exception e) {
			throw new InvalidInputException(e.getMessage());
		}
	}



	public void adminOptions() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
		while (true) {
			System.out.println("1. View pending Gym Owner Requests");
			System.out.println("2. View pending Gym Requests");
			System.out.println("3. Approve Gym Owner Request");
			System.out.println("4. Approve Gym Request");
			System.out.println("5. Exit");

			System.out.println("Enter your choice: ");
			int choice = sc.nextInt();
			try {
			switch (choice) {


				case 1:
					viewPendingGymOwnerRequests();
					break;

				case 2:
					viewPendingGymRequests();
					break;

				case 3:
					approveGymOwnerRequests();
					break;

				case 4:
					approveGymRequests();
					break;
				case 5:
					System.out.println("Exiting...");
				    sc.close(); // Close scanner

					System.exit(0);
				default:
					System.out.println("Please check the option you have entered");
			}
			}catch(Exception e) {
				System.out.println("\n++++++++++++++++++++++++++++++++++++++++");
				System.out.println("Enter a valid Id!");
				System.out.println("++++++++++++++++++++++++++++++++++++++++\n");
				adminOptions();
			}
		}
	}



}
