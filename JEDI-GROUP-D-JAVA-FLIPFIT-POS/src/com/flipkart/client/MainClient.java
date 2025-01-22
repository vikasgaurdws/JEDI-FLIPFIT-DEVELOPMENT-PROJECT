/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

/**
 * 
 */
//public class MainClient {
	
	
	/**
	 * 
	 */
	public class MainClient {
		// TODO Auto-generated constructor stub
		
	public static void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Email\n");
		String email = sc.nextLine();
		System.out.println("Enter your Password\n");
		String password = sc.nextLine();
		System.out.println("Enter your Role\n");
		System.out.println("1.Admin\n");	
		System.out.println("2.Gym Owner\n");
		System.out.println("3.User\n");
		int role = sc.nextInt();
		switch(role) {
		case 1:
			String ad = "admin@flipkart.com";
			String ps = "admin";
			if(!email.equals(ad) && !password.equals(ps)) {
				//We have to throw and Error
				System.out.println("We have to throw an error\n");
			}else {
				System.out.println("Successfully Logged In");
			}
			
			
		case 2:
			FlipfitGymOwnerClient FlipfitGymOwnerClient = new FlipfitGymOwnerClient(sc);
			//flipfitGymOwnerClient.       this is to login for user
		case 3:
			  FlipfitCustomerClient flipfitCustomerClient = new FlipfitCustomerClient(sc);
			  //flipfitCustomerClient.     this is to login for gymOwner
			
		}
	}
	public static void display()
	{
		Scanner sc = new Scanner(System.in);
		boolean menu = true;
		while(menu) {
			System.out.println("Choose One of the Below");
			System.out.println("1.Login");
			System.out.println("2.Change Password");
			System.out.println("3.User Registration");
			System.out.println("4.GymOwner Registration");
			System.out.println("5.Exit");
			
			System.out.println("Enter Your Choice\n");
			
			int n = sc.nextInt();
			
			switch(n) {
				case 1: login();
				case 2: // It will be implemented later
				case 3: FlipfitCustomerClient flipfitCustomerClient = new FlipfitCustomerClient(sc);
						flipfitCustomerClient.registerCustomer();
						login();
						break;
					
				case 4: FlipfitGymOwnerClient flipfitGymOwnerClient = new FlipfitGymOwnerClient(sc);
						flipfitGymOwnerClient.gymOwnerRegistration(sc);
						login();
						break;
				case 5: System.out.println("Thank you for visiting\n");
					System.exit(0);
					break;
				default: System.out.println("Wrong Choice\n");
					
			}
		}
			
			
		
		
	}

	
	
	/**
	 * @param args
	 */
	public static void main throws InvalidInputException(String[] args) {
		
			display();
		
			System.out.println("Welcome to flipfit");
			
	}

	
	
	

}
