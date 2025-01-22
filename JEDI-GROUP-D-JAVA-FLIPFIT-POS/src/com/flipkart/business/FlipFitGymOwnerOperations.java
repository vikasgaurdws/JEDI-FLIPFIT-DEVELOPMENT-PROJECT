package com.flipkart.business;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.Slot;

import java.util.List;

public class FlipFitGymOwnerOperations extends FlipFitUserOperations{
	
    public void requestForProfileVerfication(){
        System.out.println("requestForProfileVerfication");
    }
    
    public void requestGymVerification(){
        System.out.println("requestGV");
    }

	public FlipFitGymOwner getProfile(String email) {
		System.out.println("request");

        return null;
    }

	public void editProfile(String gymId) {
		System.out.println("Edited your profile Successfully!");
	}
	
	public boolean addGym(FlipFitGym gym) {
		System.out.println("Added");
		return true;
	}
	
	public void addSlot(Slot slot) {
		
	}
	
	public void editGym(FlipFitGym gym) {
		System.out.println("Edited Gym Details Successfully! ");
	}
	
	public void viewGym() {
		
	}
	
	public boolean isGymVerified(String gymId) {
		return true;
	}
	public boolean isGymOwnerVerified(String email) {
		return true;
	}

	public List<FlipFitGym> getGymDetail(String gymOwnerEmail) {
		return null;
	}

	public boolean validateCreds(String email, String password) {
		return true;
	}
}