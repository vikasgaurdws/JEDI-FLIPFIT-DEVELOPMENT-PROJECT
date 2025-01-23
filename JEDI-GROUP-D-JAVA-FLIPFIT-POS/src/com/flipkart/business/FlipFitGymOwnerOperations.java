package com.flipkart.business;

import com.flipkart.bean.FlipFit;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.Slot;

import java.util.ArrayList;
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
	
	public boolean addGym(FlipFit gym) {
		System.out.println("Added");
		return true;
	}
	
	public void addSlot(Slot slot) {
		
	}
	
	public void editGym(FlipFit gym) {
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

	public List<FlipFit> getGymDetail(String gymOwnerEmail) {
		FlipFit flipFit = new FlipFit("Gym1","Bellandur",5,500,false,1);
		List<FlipFit> list = new ArrayList<>();
		list.add(flipFit);
		return list;
	}

	public boolean validateCreds(String email, String password) {
		return true;
	}
}