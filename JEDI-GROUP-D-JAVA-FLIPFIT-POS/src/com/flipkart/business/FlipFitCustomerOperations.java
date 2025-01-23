package com.flipkart.business;

import com.flipkart.bean.*;

import java.util.*;


public class FlipFitCustomerOperations {
    FlipFitCustomerDAO flipFitCustomerDAO = new FlipFitCustomerDAO();
    FlipFitGymDAO flipFitGymDAO =new FlipFitGymDAO();
    GymSlotDAO gymSlotDAO=new GymSlotDAO();
    FlipFitAdminDAO flipFitAdminDAO = new FlipFitAdminDAO();
    BookingsDAO bookingsDAO=new BookingsDAO();
    List<FlipFitGym> gyms;
    
    int userId;
    String email;
    
    
    //register the customer and get the userId and store it in the 
    public void registerCustomer(FlipFitCustomer flipfitCustomer) {
    	
    	//search for a method that returns the userid after insertion
    	flipFitCustomerDAO.save(flipfitCustomer);
    }
    
    public boolean validateCreds(String email, String password) {
        String actualPass = flipFitCustomerDAO.getPassword(email);
        String role = flipFitCustomerDAO.getRole();
        return actualPass.equals(password) && role.equals("Customer");
        
    }
    
    public List<FlipFitGym> getGyms(){
    	 gyms = flipFitGymDAO.getGyms();
        return gyms;
    }
    
    public List<Slot> getSlots(int gymId){
         
         List<Slot> slots = flipFitGymDAO.getCustomerBookings(gymId);
         return slots;
        
    }
    
    public List<FlipFitGym> searchByLocation( String  location){
        List<FlipFitGym> gyms = flipFitGymDAO.getGymsByLocation(location);
        return gyms;

    }
    public List<FlipFitGym> searchByTime(LocalTime time){
        List<FlipFitGym> gyms = flipFitGymDAO.getGymsByTime(time);
        return gyms;
        
    }
    
    public boolean bookSlot(int userId, String email,Slot slot){
				
    }
    public void makePayment(){
        System.out.println("makePayment");
    }

    
    public void cancelBooking(int userId){
        
        
        System.out.println("cancel booking " + userId);
    }
    
    
    public List<Booking> viewBookings(Integer userId){
         

    }
    
    public boolean cancelBooking(String email, String bookingId) {
         Booking booking = flipFitCustomerDAO.getBookingById(bookingId);
         //isupdate method will handle -> to delete the booking id;
         System.out.println("cancel booking of "+email + "and booking id is : " + bookid);
    }

    public boolean bookGymSlot(String email, int gymId, String time) {
        System.out.println("Booked");
        return true;
    }

    public void updateCustomerProfile(FlipFitCustomer flipfitCustomer) {
    }

    public FlipFitCustomer getCustomerProfile(String email) {
        return new FlipFitCustomerDAO.getByEmail(email);
    }
    public List<Booking> getCustomerBookings(String email) {
    	
    	//get the userId or get it from the flipfitCustomerDAO
        
        List<Booking> bookings = bookingsDAO.getById(userId);
        return bookings;

    }

   

    
}