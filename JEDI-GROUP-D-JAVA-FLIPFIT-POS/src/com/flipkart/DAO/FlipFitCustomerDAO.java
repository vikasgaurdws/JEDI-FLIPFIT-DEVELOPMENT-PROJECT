package com.flipkart.DAO;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.flipkart.bean.*;

public interface FlipFitCustomerDAO {
	
	public int registerCustomer(FlipFitCustomer flipFitCustomer );
	 
	public int authenticateUser(String email,String password);
	public List<FlipFitGym> getAvailableGyms();
	
	public List<FlipFitGym> getGymsByLocation(String location);


	public List<Slot> getSlots(int gymId);
	public int bookedSeats(int slotId, LocalDate date);

	
	public int bookSlots( int userId, int slotId, int gymId, Date date);

	public void makePayment(int gymId, String paymentType, int bookingId);
	
	public List<Booking> viewBookings(int userid);
	
	public void cancelBooking(int userid,int bookingId);

	public void updateCustomerProfile(FlipFitCustomer flipFitCustomer );
	
	public FlipFitCustomer getCustomerProfile(int userId);


}
