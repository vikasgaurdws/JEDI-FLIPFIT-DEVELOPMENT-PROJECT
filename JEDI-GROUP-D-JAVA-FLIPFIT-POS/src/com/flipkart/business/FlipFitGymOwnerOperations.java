package com.flipkart.business;

import com.flipkart.DAO.FlipFitGymOwnerDAOImpl;

import com.flipkart.DAO.FlipFitUserDAO;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.Slot;

import java.util.ArrayList;
import java.util.List;

public class FlipFitGymOwnerOperations extends FlipFitUserOperations{

	private  FlipFitGymOwnerDAOImpl flipFitGymOwnerDAO = new FlipFitGymOwnerDAOImpl();
	
  
    

	public void getProfile(FlipFitGymOwner gymOwner) {
		System.out.println("gym owner details are : ");
		FlipFitGymOwner gymOwnerProfile = flipFitGymOwnerDAO.getFlipFitGymOwnerDetails(gymOwner);
		System.out.println(gymOwnerProfile.toString());
    }

	public void editProfile(FlipFitGymOwner gymOwner) {
       boolean chk = flipFitGymOwnerDAO.editFlipFitGymOwnerDetails(gymOwner);

		if(chk){
			System.out.println("Gym is edited successfully\n"  + gymOwner.toString());
		}
		else{
			System.out.println("Try Again");
		}
	}
	

	public void addGym(FlipFitGymOwner GymOwner,FlipFitGym gym) {
//		boolean chk = false;
	    boolean chk = flipFitGymOwnerDAO.addGym(GymOwner,gym);
		if(chk){
			System.out.println("Gym is added successfully!\n");
		}
		else{
			System.out.println("Try Again");
		}
	}
	
	public boolean isGymOwnerVerified(Integer GymOwnerid)
	{
		boolean flag = flipFitGymOwnerDAO.getVerifiedGymowner(GymOwnerid);
		return flag;
	}
	
	public boolean isGymVerified(Integer Gymid)
	{
		boolean flag = flipFitGymOwnerDAO.getVerifiedGym(Gymid);
		return flag;
	}
	
	public void addSlot(Slot slot) {

		boolean chk = flipFitGymOwnerDAO.addSlot(slot);
		if(chk){
			System.out.println("Slot added successfully\n"  + slot.toString());
		}
		else{
			System.out.println("Try Again");
		}

	}

	public void editGym(FlipFitGymOwner GymOwner, FlipFitGym gym) {

		boolean chk = flipFitGymOwnerDAO.editGym(GymOwner,gym);
		if(chk){
			System.out.println("Gym is edited successfully\n"  + gym.toString());
		}
		else{
			System.out.println("Try Again");
		}
	}


	
	public FlipFitGymOwner registerGymOwner(FlipFitGymOwner gymOwner)
	{
		FlipFitGymOwner chk = flipFitGymOwnerDAO.register(gymOwner);

		return chk;
	}
	
	public FlipFitGymOwner login(String email,String Password)
	{
		FlipFitGymOwner chk = flipFitGymOwnerDAO.login(email,Password);
		return chk;
	}

	public List<FlipFitGym> getGymDetail(FlipFitGymOwner gymOwner) {



		return flipFitGymOwnerDAO.getGymsOfFlipFitGymOwner(gymOwner);

	}

	
}