package com.flipkart.business;

public class SlotOperations {
    private String slotID;
    private String gymID;
    private String startTime;
    private int capacity;
    private int availableSeats;

    public boolean checkAvailability() {
        // Logic to check availability
        return availableSeats > 0;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getSlotID() {
        return slotID;
    }
}
