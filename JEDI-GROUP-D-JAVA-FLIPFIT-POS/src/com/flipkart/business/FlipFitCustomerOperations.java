package com.flipkart.business;

import java.sql.Date;
import java.util.List;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;
import com.flipkart.DAO.FlipFitCustomerDAO;
import com.flipkart.DAO.FlipFitCustomerDAOImpl;


public class FlipFitCustomerOperations {
	FlipFitCustomerDAO flipfitcustomerdao=new FlipFitCustomerDAOImpl();
//	FlipFitUserDAO flipfituserdao=new FlipFitUserDAOImpli();

    public boolean cancelBooking(int userId, int bookingId) {
    	flipfitcustomerdao.cancelBooking(userId,bookingId);
//        System.out.println("Canceled Booking");
        return true;
    }

    public int bookGymSlot(int userId,int slotId,int gymId,Date date) {
        return flipfitcustomerdao.bookSlots(userId,slotId,gymId,date);
    }

    public void updateCustomerProfile(FlipFitCustomer flipfitCustomer) {
    	flipfitcustomerdao.updateCustomerProfile(flipfitCustomer);
    }

    public List<FlipFitGym> getAvailableGyms() {
        return flipfitcustomerdao.getAvailableGyms();
    }

    public List<FlipFitGym> searchGymsByLocation(String location) {
        return flipfitcustomerdao.getGymsByLocation(location);
    }
    public List<Slot> getSlots(int gymId){
    	return flipfitcustomerdao.getSlots(gymId);
    }
    
    public void makePayment(int gymId,String paymentType,int bookingId) {
    	flipfitcustomerdao.makePayment(gymId,paymentType,bookingId);
    }

    public List<Booking> getCustomerBookings(int userId) {
        return flipfitcustomerdao.viewBookings(userId);
    }
    public FlipFitCustomer getCustomerProfile(int userId) {
    	return flipfitcustomerdao.getCustomerProfile(userId);
    }
    // call from user DAO
    public void registerCustomer(FlipFitCustomer flipfitCustomer) {
    	flipfitcustomerdao.registerCustomer(flipfitCustomer);
        System.out.println("Customer added successfully");
    }
    public int validateCreds(String email, String password) {
    	int id=flipfitcustomerdao.authenticateUser(email,password);// 1 is the int for role of user
        System.out.println("Credentials verified");
        return id;
    }
}

