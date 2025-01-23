package com.flipkart.DAO;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitGymUser;

public interface FlipFitUserDAO {
	public boolean authenticateUser(FlipFitGymUser user);

	public boolean registerCustomer(FlipFitCustomer customer);

	public boolean registerGymOwner(FlipFitGymOwner gymOwner);
}
