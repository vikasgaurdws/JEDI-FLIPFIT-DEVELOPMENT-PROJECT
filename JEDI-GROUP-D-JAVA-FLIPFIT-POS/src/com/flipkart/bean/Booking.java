package com.flipkart.bean;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {

    private Integer userId;
    private Integer slotId;
    private Integer gymId;
    private LocalTime time;
    private String bookingStatus;
    private LocalDate bookingDate;
    private Integer bookingId;

    // Constructor
    public Booking() {
        // Empty constructor if needed for initialization
    }

    // Getters and Setters
    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Integer getGymId() {
        return gymId;
    }

    public void setGymId(Integer gymId) {
        this.gymId = gymId;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    // toString method for printing details
    @Override
    public String toString() {
        return "Booking ID: " + bookingId + "\n" +
               "User ID: " + userId + "\n" +
               "Slot ID: " + slotId + "\n" +
               "Gym ID: " + gymId + "\n" +
               "Booking Status: " + bookingStatus + "\n" +
               "Booking Date: " + bookingDate + "\n" +
               "Time: " + time + "\n";
    }

	
}
