/**
 * 
 */
package com.flipkart.client;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.flipkart.DAO.FlipFitAdminDAOImpl;
import com.flipkart.exception.InvalidInputException;

/**
 * 
 */
//public class MainClient {
	
	
	/**
	 * 
	 */
	public class FlipfitMenu {
		// TODO Auto-generated constructor stub
		public static Scanner sc = new Scanner(System.in);
		
	public static void login() throws Exception {
//		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Email");
		String email = sc.next();
		System.out.println("Enter your Password");
		String password = sc.next();
		System.out.println("Enter your Role");
		System.out.println("1.Admin");	
		System.out.println("2.Gym Owner");
		System.out.println("3.Customer");
		int role =-1;
		try {
				role=sc.nextInt();
		}catch(Exception e){
			throw new InvalidInputException(e.getMessage());
		}
		switch(role) {
		case 1:
			FlipFitAdminDAOImpl ob = new FlipFitAdminDAOImpl();
			if(ob.verifyCred(email, password))
			{
				System.out.println("Successfully Logged in as Admin "+ ", Current time: "+LocalDateTime.now());
				FlipfitAdminMenu flipfitAdminClient = new FlipfitAdminMenu(sc);
				flipfitAdminClient.adminOptions();
			}
			else {
				//We have to throw and Error
				System.out.println("Invalid credentials.\n");
			}
			break;
		case 2:
			FlipfitGymOwnerMenu flipfitGymOwnerClient = new FlipfitGymOwnerMenu(sc);
			flipfitGymOwnerClient.login(email,password);
			break;
		case 3:
			  FlipfitCustomerMenu flipfitCustomerClient = new FlipfitCustomerMenu(sc);
			  flipfitCustomerClient.login(email,password);
			break;
			
		}
	}
	public static void display() throws Exception {
		boolean menu = true;
		while(menu) {
			System.out.println("Choose One of the Below");
			System.out.println("1.Login");
			System.out.println("2.Change Password");
			System.out.println("3.Customer Registration");
			System.out.println("4.GymOwner Registration");
			System.out.println("5.Exit");
			
			System.out.println("Enter Your Choice");
			int n=-1;
			try {
				n=sc.nextInt();
			}catch(Exception e) {
				throw new InvalidInputException(e.getMessage());
			}
			switch(n) {
				case 1:
						login();
						break;
				case 2: // It will be implemented later
				case 3: FlipfitCustomerMenu flipfitCustomerClient = new FlipfitCustomerMenu(sc);
						flipfitCustomerClient.registerCustomer();
						login();
						break;
					
				case 4: FlipfitGymOwnerMenu flipfitGymOwnerClient = new FlipfitGymOwnerMenu(sc);
						flipfitGymOwnerClient.gymOwnerRegistration();
						login();
						break;
				case 5: System.out.println("Thank you for visiting\n");
					System.exit(0);
				default: System.out.println("Wrong Choice\n");
					
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to flipfit");
		try {
			display();
		}catch(InvalidInputException e) {
			System.out.println("\n++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Enter a valid choice!");
			System.out.println("++++++++++++++++++++++++++++++++++++++++\n");
			sc.next();
			main(args);
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
