package com.flipkart.business;

import com.flipkart.DAO.FlipFitGymOwnerDAOImpl;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.Slot;

import java.util.ArrayList;
import java.util.List;

public class FlipFitGymOwnerOperations extends FlipFitUserOperations{
    private final FlipFitGymOwnerDAOImpl  flipFitGymOwnerDAO = new FlipFitGymOwnerDAOImpl();

    public void requestForProfileVerification(){
        System.out.println("requestForProfileVerification");
    }
    public void requestGymVerification(){
        System.out.println("requestGV");
    }

	public void getProfile(FlipFitGym gymOwner) {
		System.out.println("gym owner details are : ");
		FlipFitGymOwner gymOwnerProfile = flipFitGymOwnerDAO.getFlipFitGymOwnerDetails(gymOwner);
		System.out.println(gymOwnerProfile.toString());
    }

	public void editProfile(FlipFitGym gymOwner) {
       boolean chk = flipFitGymOwnerDAO.editFlipFitGymOwnerDetails(gymOwner);

		if(chk){
			System.out.println("Gym is edited successfully\n"  + gym.toString());
		}
		else{
			System.out.println("Try Again");
		}
	}
	
	public void addGym(FlipFitGym GymOwner,FlipFitGym gym) {
	    boolean chk = flipFitGymOwnerDAO.addGym(GymOwner,gym);
		if(chk){
			System.out.println("Gym is added successfully\n"  + gym.toString());
		}
		else{
			System.out.println("Try Again");
		}

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

	public void editGym(FlipFitGym GymOwner, FlipFitGym gym) {

		boolean chk = flipFitGymOwnerDAO.editGym(GymOwner,gym);
		if(chk){
			System.out.println("Gym is edited successfully\n"  + gym.toString());
		}
		else{
			System.out.println("Try Again");
		}
	}

	public boolean isGymVerified(String gymId) {
		return true;
	}
	public boolean isGymOwnerVerified(String email) {
		return true;
	}

	public List<FlipFitGym> getGymDetail(String gymOwnerEmail) {gymOwnerEmail
//		FlipFitGym flipFitGym = new FlipFitGym("Gym1","Bellandur",5,500,false,1);
//		List<FlipFitGym> list = new ArrayList<>();
//		list.add(flipFitGym);
//		return list;

	}

	public boolean validateCreds(String email, String password) {
		return true;
	}
}