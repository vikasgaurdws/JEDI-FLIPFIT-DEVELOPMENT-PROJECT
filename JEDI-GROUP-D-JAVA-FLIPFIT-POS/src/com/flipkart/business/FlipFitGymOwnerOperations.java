package com.flipkart.business;

import com.flipkart.DAO.FlipFitGymOwnerDAOImpl;
import com.flipkart.DAO.FlipFitUserDAO;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.Slot;

import java.util.ArrayList;
import java.util.List;

public class FlipFitGymOwnerOperations extends FlipFitUserOperations{

	private final FlipFitGymOwnerDAOImpl flipFitGymOwnerDAO = new FlipFitGymOwnerDAOImpl();
	
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
	
	public void addGym(String email) {

	}
	
	public void addSlot(Slot slot) {
		boolean resp = flipFitGymOwnerDAO.addSlot(slot);
		if(resp){
			System.out.println("Slot added successfully \n"+slot.toString());
		}else{
			System.out.println("Failed to add slot.");
		}
	}
	
	public void editGym(FlipFitGym gym) {
		System.out.println("Edited Gym Details Successfully! ");
	}
	

	public boolean isGymVerified(String gymId) {
		return true;
	}
	public boolean isGymOwnerVerified(String email) {
		return true;
	}

	public List<FlipFitGym> getGymDetail(FlipFitGymOwner gymOwner) {

//		FlipFitGym flipFitGym = new FlipFitGym("Gym1","Bellandur",5,500,false,1);
//		List<FlipFitGym> list = new ArrayList<>();
//		list.add(flipFitGym);
//		return list;

		return flipFitGymOwnerDAO.getGymsOfFlipFitGymOwner(gymOwner);
	}

	public FlipFitGymOwner login(String email, String password) {
        return flipFitGymOwnerDAO.login(email,password);
	}
}