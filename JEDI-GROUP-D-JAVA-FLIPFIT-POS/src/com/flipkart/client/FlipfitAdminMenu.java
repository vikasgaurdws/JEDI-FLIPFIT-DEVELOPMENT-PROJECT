package com.flipkart.client;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.business.FlipFitAdminOperations;

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
	public void approveGymOwnerRequests() {
		System.out.println("Enter GymOwnerId: ");
		flipfitAdminOperations.approveGymOwnerRequests(Integer.parseInt(sc.next()));
	}
	public void approveGymRequests() {
		System.out.println("Enter Gym Id: ");
		flipfitAdminOperations.approveGymRequests(Integer.parseInt(sc.next()));
	}

	public void viewGymUsers() {
		System.out.println("Enter gym Id: ");
		flipfitAdminOperations.viewGymUsers(Integer.parseInt(sc.next()));
	}



	public void adminOptions() throws Exception {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
		while (true) {
			System.out.println("1. View pending Gym Owner Requests");
			System.out.println("2. View pending Gym Requests");
			System.out.println("3. Approve Gym Owner Request");
			System.out.println("4. Approve Gym Request");
//			System.out.println("5. view gym users");
//			System.out.println("5. view all gym owners");
//			System.out.println("6. view gym details");
			System.out.println("5. Exit");

			System.out.println("Enter your choice: ");
			int choice = sc.nextInt();
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
//					viewGymUsers();
//					break;
//				case 8:
					System.out.println("Exiting...");
					return;


				default:
					System.out.println("Please check the option you have entered");
			}
		}
	}



}
