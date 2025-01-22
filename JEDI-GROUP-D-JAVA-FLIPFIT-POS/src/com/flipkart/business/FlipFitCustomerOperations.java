package com.flipkart.business;

import com.flipkart.bean.FlipFitCustomer;


public class FlipFitCustomerOperations {


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
        System.out.println("Customer added successfully");
    }

    public boolean validateCreds(String email, String password) {
        System.out.println("Credentials verified");
        return true;
    }
}

