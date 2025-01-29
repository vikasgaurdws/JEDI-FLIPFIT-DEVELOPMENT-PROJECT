package com.flipkart.DAO;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.flipkart.bean.*;

public interface FlipFitCustomerDAO {
	
	public int registerCustomer(FlipFitCustomer flipFitCustomer );
	 
	public int authenticateUser(String email,String password);
	public List<FlipFitGym> getAvailableGyms();
	
	public List<FlipFitGym> getGymsByLocation(String location);


	public List<Slot> getSlots(int gymId);
	public int bookedSeats(int slotId, LocalDate date);

	
	public int bookSlots( int userId, int slotId, int gymId, Date date);

	
	public List<Booking> viewBookings(int userid);
	
	public boolean cancelBooking(int userid,int bookingId);

	public void updateCustomerProfile(FlipFitCustomer flipFitCustomer );
	
	public FlipFitCustomer getCustomerProfile(int userId);

	
	boolean makePayment(String paymentType, int bookingId, int price);

	public int waitlist(int userId, int slotId, int gymId, Date valueOf);


	public String getUserName(String email, String password);


	


}