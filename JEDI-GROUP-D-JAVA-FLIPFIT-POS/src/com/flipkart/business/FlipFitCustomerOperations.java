package com.flipkart.business;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;
import com.flipkart.DAO.FlipFitCustomerDAO;
import com.flipkart.DAO.FlipFitCustomerDAOImpl;


public class FlipFitCustomerOperations {
	FlipFitCustomerDAO flipfitcustomerdao=new FlipFitCustomerDAOImpl();

    public boolean cancelBooking(int userId, int bookingId) {
      
    	System.out.println("Canceled Booking");

    	return flipfitcustomerdao.cancelBooking(userId,bookingId);
        
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
    
    public boolean makePayment(String paymentType,int bookingId, int price) {
    	return flipfitcustomerdao.makePayment(paymentType,bookingId, price);
    }

    public List<Booking> getCustomerBookings(int userId) {
        return flipfitcustomerdao.viewBookings(userId);
    }
    public FlipFitCustomer getCustomerProfile(int userId) {
    	return flipfitcustomerdao.getCustomerProfile(userId);
    }
    // call from user DAO
    public int registerCustomer(FlipFitCustomer flipfitCustomer) {
        System.out.println("Customer added successfully");

    	return flipfitcustomerdao.registerCustomer(flipfitCustomer);
    }
    public int validateCreds(String email, String password) {
    	int id=flipfitcustomerdao.authenticateUser(email,password);// 1 is the int for role of user
        return id;
    }

	public int bookedSeats(int sid, LocalDate locdate) {
		return flipfitcustomerdao.bookedSeats(sid,locdate);
		
	}

	public int joinWaitlist(int userId, int slotId, int gymId, Date valueOf) {
		return flipfitcustomerdao.waitlist(userId,slotId,gymId,valueOf);
	}

	public String getUserName(String email, String password) {
		return flipfitcustomerdao.getUserName(email,password);
	}

	public List<Booking> schedule(int userId, Date date) {
		  return getCustomerBookings(userId).stream()
			        .filter(booking -> booking.getBookingDate().equals(date.toLocalDate()))
			        .collect(Collectors.toList());		
	}
}

