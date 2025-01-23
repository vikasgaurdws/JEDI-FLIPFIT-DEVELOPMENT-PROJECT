/**
 * 
 */
package com.flipkart.bean;

/**
 * 
 */
public class FlipFitCustomer extends FlipFitGymUser {
	private Integer customerAge;

	public Integer getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(Integer customerAge) {
		this.customerAge = customerAge;
	}


	public FlipFitCustomer(Integer customerAge) {
		this.customerAge = customerAge;
	}

	public FlipFitCustomer() {
	}

	@Override
	public String toString() {
		return "FlipFitCustomer{" +
				"customerAge=" + customerAge +
				'}';
	}
}
