package com.flipkart.DAO;

import com.flipkart.bean.*;
import java.util.List;

public interface FlipFitGymOwnerDAO {

    /**
     * Retrieves the details of a FlipFit Gym Owner based on their email ID.
     * @param FlipFitGymOwnerEmailId the email ID of the gym owner
     * @return a FlipFitGymOwner object containing the owner's details
     */
    FlipFitGymOwner getFlipFitGymOwnerDetails(String FlipFitGymOwnerEmailId);

    /**
     * Adds a new FlipFit Gym Owner's details to the database.
     * @param FlipFitGymOwnerDetails the FlipFitGymOwner object containing the details to be added
     */
//    void addFlipFitGymOwnerDetails(FlipFitGymOwner FlipFitGymOwnerDetails);

    /**
     * Edits the details of an existing FlipFit Gym Owner in the database.
     * @param FlipFitGymOwnerDetails the FlipFitGymOwner object containing the updated details
     */
    void editFlipFitGymOwnerDetails(String FlipFitGymOwnerEmailId );

    /**
     * Retrieves the details of a gym based on its ID.
     * @param gymId the ID of the gym
     * @return a FlipFitGym object containing the gym's details
     */


    /**
     * Adds a new gym's details to the database.
     * @param gymDetails the FlipFitGym object containing the details to be added
     */
    void addGym(String FlipFitGymOwnerEmailId);

    /**
     * Edits the details of an existing gym in the database.
     * @param gymDetails the FlipFitGym object containing the updated details
     */
    void editGym(String FlipFitGymOwnerEmailId);

    /**
     * Retrieves a list of gyms owned by a specific FlipFit Gym Owner.
     * @param FlipFitGymOwnerId the email ID of the gym owner
     * @return a list of FlipFitGym objects containing the details of the gyms
     */
    List<FlipFitGym> getGymsOfFlipFitGymOwner(String FlipFitGymOwnerEmailId);

    /**
     * Retrieves a list of possible slots for a specific gym.
     * @param gymId the ID of the gym
     * @return a list of Slot objects containing the details of the slots
     */
//    List<Slot> getPossibleSlots(Integer gymId);

    /**
     * Adds a new slot's details to the database.
     * @param slot the Slot object containing the details to be added
     */
    void addSlot();

    /**
     * Checks whether a FlipFit Gym Owner is approved.
     * @param email the email ID of the gym owner
     * @return true if the owner is approved, false otherwise
     */
//    boolean checkOwnerApproval(String email);

    /**
     * Checks whether a gym is approved.
     * @param gymId the ID of the gym
     * @return true if the gym is approved, false otherwise
     */
//    boolean checkGymApproval(String gymId);
}
