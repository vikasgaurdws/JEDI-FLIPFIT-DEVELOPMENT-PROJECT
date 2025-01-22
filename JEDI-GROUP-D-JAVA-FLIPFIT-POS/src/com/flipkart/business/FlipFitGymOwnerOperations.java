package com.flipkart.business;

public class FlipFitGymOwnerOperations extends FlipFitUserOperations{
	
    public void requestForProfileVerfication(){
        System.out.println("requestForProfileVerfication");
    }
    
    public void requestGymVerification(){
        System.out.println("requestGV");
    }

	public GymOwner getProfile(String email) {
		System.out.println("request");
	}

	public void editProfile(String gymId) {
		System.out.println("Edited your profile Successfully!");
	}
	
	public boolean addGym(Gym gym) {
		System.out.println("Added");
		return true;
	}
	
	public void addSlot(Slot slot) {
		
	}
	
	public void editGym(Gym gym) {
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

	public FlipFitGym getGymDetail(String gymOwnerEmail, String gymId) {
		
	}
	
}