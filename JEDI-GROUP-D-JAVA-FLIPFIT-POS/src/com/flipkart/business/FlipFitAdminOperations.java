/**
 *
 */
package com.flipkart.business;

import com.flipkart.bean.FlipFit;
import com.flipkart.bean.FlipFitGymOwner;

import java.util.List;

/**
 *
 */
public class FlipFitAdminOperations{

	public void createAdmin(String UserName)
	{
		System.out.println("Admin is Created");
	}


	public List<FlipFitGymOwner> viewAllGymOwners()
	{
		//if() GymOwnerslist is empty
		System.out.println("No Gym owner found!!");
//		else
		System.out.println("All Gym Owners");

		// for Gymowner in GymOwners
		System.out.println("Gym owner");
        return null;

	}

	public List<FlipFit> viewGymDetails()
	{
		//if() Gymslist is empty
		System.out.println("No Gym found!!");
//		else
		System.out.println("All Gyms");

		// for Gym in Gyms
		System.out.println("Gym");
        return null;

	}

	public void approveGymOwnerRequests(int ownerId) {
//        boolean res=adminDAO.approveGymOwnerRequests(ownerId);
//        if (res){
		System.out.println("Gym Owner request with ID " + ownerId + " has been approved.");
//        }
//        else{
		System.out.println("Gym Owner request with ID " + ownerId + " could not be found!!");
//        }
	}

	/**
	 * Method to approve gym requests
	 * @param gymId
	 */
//    @Override
	public void approveGymRequests(int gymId) {
//        boolean res=adminDAO.approveGymRequests(gymId);
//        if (res){
		System.out.println("Gym request with ID " + gymId + " has been approved.");
//        }
//        else{
		System.out.println("Gym request with ID " + gymId + " could not be found!!");
//        }
	}

	public void viewPendingGymRequests()
	{
//    	GymReqList take it from DAO
//    	if(GymReqList not empty)
		System.out.println("All pending Requests");
//    	for GymReq in List print GymReq
	}
	public void viewPendingGymOwnerRequests()
	{
//    	GymOwnerReqList take it from DAO
//    	if(GymOwnerReqList not empty)
		System.out.println("All pending Requests");
//    	for GymOwnerReq in List print GymReq
	}

	public void viewGymUsers(int gymid)
	{
//    	GymUserList take it from DAO
//    	if(GymUserList not empty)
//    	{
//    		User id list = GymUserList[gymid]
//    	    for User in Userlist:
//    	    System.out.println("User details")
//    	}
//    	else
//    	{
//        System.out.println("Gym has no users");
//    	}
	}


}