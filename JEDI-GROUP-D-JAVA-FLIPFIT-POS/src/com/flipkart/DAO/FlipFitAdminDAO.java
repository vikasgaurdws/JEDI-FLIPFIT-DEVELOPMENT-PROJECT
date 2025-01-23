package com.flipkart.DAO;

import com.flipkart.bean.*;
import java.util.List;

public interface FlipFitAdminDAO {

    void createAdmin(String userName);

    List<FlipFitGymOwner> viewAllGymOwners();

    List<FlipFitGym> viewGymDetails();

    boolean approveGymOwnerRequests(int ownerId);

    boolean approveGymRequests(int gymId);

    List<FlipFitGym> viewPendingGymRequests();

    List<FlipFitGymOwner> viewPendingGymOwnerRequests();

    List<FlipFitGymOwner> viewGymUsers(int gymId);
}


