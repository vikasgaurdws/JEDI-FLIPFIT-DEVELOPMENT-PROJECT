/**
 * 
 */
package com.flipkart.business;

import java.util.Date;

/**
 * 
 */
public class DebitCardOperations extends PaymentOperations {
	
	private String cardNumber;
    private String name;
    private String expiry;
    private String CVV;

    public void pay() {
        // Logic to process debit card payment
        System.out.println("Debit card payment processed: " + cardNumber);
    }

    public void verify() {
        // Logic to verify debit card
        System.out.println("Debit card verified: " + cardNumber);
    }
}
