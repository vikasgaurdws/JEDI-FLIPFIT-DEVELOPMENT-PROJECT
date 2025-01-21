/**
 * 
 */
package com.flipkart.business;

/**
 * 
 */
public class CustomerOperations extends UserOperations {
	private int age;

    public void getGymList() {
        // Logic to retrieve list of gyms
        System.out.println("Fetching gym list...");
    }

    public void searchByLocation() {
        // Logic to search gyms by location
        System.out.println("Searching gyms by location...");
    }

    public void searchByTime() {
        // Logic to search gyms by time
        System.out.println("Searching gyms by time...");
    }

    public void cancelBooking(BookingsOperations booking) {
        // Logic to cancel a booking
        booking.deleteBooking();
        System.out.println("Booking cancelled: " + booking.getBookingID());
    }

    public void bookSlot(SlotOperations slot) {
        // Logic to book a slot
        if (slot.checkAvailability()) {
            System.out.println("Slot booked: " + slot.getSlotID());
        } else {
            System.out.println("Slot not available: " + slot.getSlotID());
        }
    }

    public void makePayment(PaymentOperations payment) {
        // Logic to process payment
        payment.status();
        System.out.println("Payment made successfully: " + payment.getPaymentID());
    }
}
