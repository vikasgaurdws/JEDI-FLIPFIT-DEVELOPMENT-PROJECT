/**
 * 
 */
package com.flipkart.business;

import java.util.Date;

/**
 * 
 */
public class PaymentOperations {
	
	private String paymentID;
    private Date paymentDate;
    private String paymentTime;
    private double paymentAmount;
    private String paymentType;
    private String paymentDetails;

    public String getPaymentID() {
        return paymentID;
    }

    public void getInfo() {
        // Logic to retrieve payment info
        System.out.println("Payment info: " + paymentID);
    }

    public void update() {
        // Logic to update payment details
        System.out.println("Payment updated: " + paymentID);
    }

    public void status() {
        // Logic to check payment status
        System.out.println("Payment status checked for: " + paymentID);
    }
	
	
	
}
