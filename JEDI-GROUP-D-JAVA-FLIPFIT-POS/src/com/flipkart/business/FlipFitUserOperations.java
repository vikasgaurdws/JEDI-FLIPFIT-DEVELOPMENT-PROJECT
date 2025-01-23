/**
 * 
 */
package com.flipkart.business;

import com.flipkart.DAO.FlipFitGymOwnerDAOImpl;

import com.flipkart.DAO.FlipFitUserDAOImpl;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitGymUser;

/**
 * 
 */
public class FlipFitUserOperations{
	
	GymOwnerDAOImpl gymOwnerDao = new GymOwnerDAOImpl();
	FlipFitUserDAOImpl userDao = new FlipFitUserDAOImpl();
	/**
	Registers a customer in the system.
	@param customer The Customer object representing the customer data
	*/
	public boolean registerCustomer(FlipFitCustomer customer) {
		boolean registerSuccess = false;
		registerSuccess = userDao.registerCustomer(customer);
		return registerSuccess;
	}
	/**
	Registers a gym owner in the system.
	@param gymOwner The gym owner object representing the gym owner data
	*/
	public boolean registerGymOwner(FlipFitGymOwner gymOwner) {
		boolean registerSuccess = false;
		registerSuccess = userDao.registerGymOwner(gymOwner);
		return registerSuccess;
	}
	/**
	Verifies a user's data.
	@param user The user object representing the user data
	@return true if the user's data are valid else returns false
	*/
	public boolean authenticateUser(FlipFitGymUser user) {
		boolean authenticateSuccess = false;
		authenticateSuccess = userDao.authenticateUser(user);
		return authenticateSuccess;
	}
	/**
	Logs out a user.
	@param user The User object representing the user data
	@return true if the user is successfully logged out else returns false
	*/
	public boolean logout(FlipFitGymUser user) {
		return true;
	}
}
