package com.flipkart.business;

import java.util.ArrayList;
import java.util.List;

public class GymOperations {
    private String gymID;
    private String location;
    private List<SlotOperations> availableSlots;
    private double price;
    private boolean flagVerified;

    public String getGymID() {
        return gymID;
    }

    public List<SlotOperations> getSlots() {
        // Logic to retrieve slots
        return availableSlots;
    }

    public boolean isVerified() {
        return flagVerified;
    }

}
