/**
 * 
 */
package com.flipkart.business;

import java.util.Date;

/**
 * 
 */
public class BookingsOperations {
	private String bookingID;
    private String userID;
    private String slotID;
    private String gymID;
    private String startTime;
    private String bookingStatus;
    private Date bookingDate;

    public boolean checkValidity() {
        // Logic to check booking validity
        return true;
    }

    public void deleteBooking() {
        // Logic to delete booking
        System.out.println("Booking deleted: " + bookingID);
    }

    public void book() {
        // Logic to create a booking
        System.out.println("Booking created: " + bookingID);
    }

    public void waitlist() {
        // Logic to add booking to waitlist
        System.out.println("Added to waitlist: " + bookingID);
    }

    public String getBookingID() {
        return bookingID;
    }

}
