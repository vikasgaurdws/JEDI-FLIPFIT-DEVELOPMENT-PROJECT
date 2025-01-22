package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.FlipFitGym;

public class FlipFitGymOwnerOperations extends FlipFitUserOperations{
    public void requestForProfileVerfication(){
        System.out.println("requestForProfileVerfication");
    }
    public void requestGymVerification(){
        System.out.println("request");
    }
    public void addGym(){
        System.out.println("add");
    }
    public void viewGym(){
        System.out.println("view");
    }
    public void isVerified(){
        System.out.println("isVerified");
    }
	public boolean validateCreds(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<FlipFitGym> getGymDetail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}

