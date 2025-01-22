/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

/**
 * 
 */
public class MainClient {
	

	/**
	 * 
	 */
	public MainClient() {
		// TODO Auto-generated constructor stub
	}


		
	private static void display() {
		
		  Scanner sc = new Scanner(System.in);  
		        
		   try {
		            System.out.println("Enter the role");
		            String role = sc.next();
		            
		            if (role.equals("Admin")) {
		                
		            	System.out.println("Welcome Admin");
		                FlipfitAdminClient flipfitAdminClient = new FlipfitAdminClient(sc);
		                flipfitAdminClient.adminOptions();
		            }
		            else if (role.equals("Customer")) {
		                System.out.println("Welcome to the customer Menu");
		                System.out.println();

		                FlipfitCustomerClient flipfitCustomerClient = new FlipfitCustomerClient(sc);
		                System.out.println("1. Register");
		                System.out.println("2. Login");
		                System.out.println("3. Exit");
		                System.out.print("Enter your choice: ");
		                int choice = sc.nextInt();

		                if (choice > 3) {
		                    throw new InvalidInputException("Choice entered for the customer menu must be 3 or less.");
		                }

		                switch (choice) {
		                    case 1:
		                        flipfitCustomerClient.registerFlipfitGymOwner();
		                        break;
		                    case 2:
		                        flipfitCustomerClient.login();
		                        break;
		                    case 3:
		                        System.out.println("Exiting...");
		                        break;
		                    default:
		                        System.out.println("Incorrect choice");
		                }
		            }
		            else if (role.equals("GymOwner")) {
		                System.out.println("Welcome to the flipfit GymOwner Menu");
		                System.out.println();

		                FlipfitGymOwner flipfitGymOwner = new FlipfitGymOwner(sc);
		                System.out.println("1. Register");
		                System.out.println("2. Login");
		                System.out.println("3. Exit");
		                System.out.print("Enter your choice: ");
		                int choice = sc.nextInt();

		                if (choice > 3) {
		                    throw new InvalidInputException("Choice entered for the gym owner menu must be 3 or less.");
		                }

		                switch (choice) {
		                    case 1:
		                        flipfitGymOwner.registerCustomer();
		                        break;
		                    case 2:
		                        flipfitGymOwner.login();
		                        break;
		                    case 3:
		                        System.out.println("Exiting...");
		                        break;
		                    default:
		                        System.out.println("Incorrect choice");
		                }
		            }
		            else {
		                System.out.println("Invalid role entered.");
		            }
		        } catch (InvalidInputException e) {
		            System.out.println(e.getMessage());
		        }
		        
}
	
	/**
	 * @param args
	 */
	public static void main throws InvalidInputException(String[] args) {
		
			System.out.println("Welcome to flipfit");
			
			display();
	}

	
	
	

}
