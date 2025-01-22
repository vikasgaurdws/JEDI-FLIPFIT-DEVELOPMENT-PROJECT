package com.flipkart.business;

import com.flipkart.bean.FlipFitCustomer;

import java.util.Iterator;

public class FlipFitCustomerOperations {
    
    public void getGyms(){
        System.out.println("getGyms");
    }
    public void getSlots(){
        System.out.println("getSlots");
    }
    public void searchByLocation(){
        System.out.println("searchLocation");
    }
    public void searchByTime(){
        System.out.println("searchByTime");
    }
    public void cancelBooking(){
        System.out.println("cancelBooking");
    }
    public void viewBookings(){
        System.out.println("viewBookings");
    }
    public void bookSlot(){
        System.out.println("bookSlot");
    }
    public void makePayment(){
        System.out.println("makePayment");
    }

    public boolean cancelBooking(String email, String bookingId) {
        System.out.println("Canceled Booking");
        return true;
    }

    public boolean bookGymSlot(String email, String gymName, String time) {
        System.out.println("Booked");
        return true;
    }

    public void updateCustomerProfile(FlipFitCustomer flipfitCustomer) {
    }

    public FlipFitCustomer getCustomerProfile(String email) {
        return new FlipFitCustomer();
    }

    public Iterable<Object> getAvailableGyms() {
        return null;
    }

    public Iterable<Object> searchGymsByLocation(String location) {
        return null;
    }

    public Iterable<Object> searchGymsByTime(String time) {
        return null;
    }

    public Iterable<Object> getCustomerBookings(String email) {
        return null;
    }

    public void registerCustomer(FlipFitCustomer flipfitCustomer) {

    }

    public boolean validateCreds(String email, String password) {
        System.out.println("Credentials verified");
        return true;
    }
}

