package com.flipkart.DAO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import com.flipkart.bean.*;

/**
 * Interface for FlipFit Customer Data Access Object.
 * Provides methods for user registration, authentication, 
 * gym and slot management, booking, payments, and profile handling.
 */
public interface FlipFitCustomerDAO {
    
    /**
     * Registers a new customer.
     * @param flipFitCustomer The customer details to be registered.
     * @return The generated customer ID.
     */
    public int registerCustomer(FlipFitCustomer flipFitCustomer);

    /**
     * Authenticates a user based on email and password.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The user ID if authentication is successful, -1 otherwise.
     */
    public int authenticateUser(String email, String password);

    /**
     * Retrieves a list of available gyms.
     * @return List of FlipFitGym objects.
     */
    public List<FlipFitGym> getAvailableGyms();

    /**
     * Retrieves gyms filtered by location.
     * @param location The location to filter gyms.
     * @return List of gyms in the specified location.
     */
    public List<FlipFitGym> getGymsByLocation(String location);

    /**
     * Retrieves available slots for a given gym.
     * @param gymId The ID of the gym.
     * @return List of slots.
     */
    public List<Slot> getSlots(int gymId);

    /**
     * Retrieves the number of booked seats for a given slot on a specific date.
     * @param slotId The ID of the slot.
     * @param date The date for which booked seats are counted.
     * @return The number of booked seats.
     */
    public int bookedSeats(int slotId, LocalDate date);

    /**
     * Books a slot for a user in a gym.
     * @param userId The ID of the user.
     * @param slotId The ID of the slot.
     * @param gymId The ID of the gym.
     * @param date The date of booking.
     * @return Booking ID if successful, -1 otherwise.
     */
    public int bookSlots(int userId, int slotId, int gymId, Date date);

    /**
     * Retrieves the list of bookings for a user.
     * @param userId The ID of the user.
     * @return List of booking details.
     */
    public List<Booking> viewBookings(int userId);

    /**
     * Cancels a booking for a user.
     * @param userId The ID of the user.
     * @param bookingId The ID of the booking to cancel.
     * @return True if cancellation is successful, false otherwise.
     */
    public boolean cancelBooking(int userId, int bookingId);

    /**
     * Updates the profile details of a customer.
     * @param flipFitCustomer The customer details to be updated.
     */
    public void updateCustomerProfile(FlipFitCustomer flipFitCustomer);

    /**
     * Retrieves the profile details of a customer.
     * @param userId The ID of the user.
     * @return The customer's profile details.
     */
    public FlipFitCustomer getCustomerProfile(int userId);

    /**
     * Processes a payment for a booking.
     * @param paymentType The type of payment (e.g., credit card, UPI).
     * @param bookingId The ID of the booking.
     * @param price The amount to be paid.
     * @return True if payment is successful, false otherwise.
     */
    public boolean makePayment(String paymentType, int bookingId, int price);

    /**
     * Adds a user to the waitlist for a slot in a gym.
     * @param userId The ID of the user.
     * @param slotId The ID of the slot.
     * @param gymId The ID of the gym.
     * @param date The date for which the waitlist is being created.
     * @return Waitlist ID if successful, -1 otherwise.
     */
    public int waitlist(int userId, int slotId, int gymId, Date date);

    /**
     * Retrieves the username of a user based on email and password.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The username if found, null otherwise.
     */
    public String getUserName(String email, String password);
}
