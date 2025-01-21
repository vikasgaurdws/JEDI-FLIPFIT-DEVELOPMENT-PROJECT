/**
 * 
 */
package com.flipkart.business;

import java.util.Date;

/**
 * 
 */
public class CreditCardOperations extends PaymentOperations {
	
	private String cardNumber;
    private String name;
    private String expiry;
    private String CVV;

    public void pay() {
        // Logic to process credit card payment
        System.out.println("Credit card payment processed: " + cardNumber);
    }

    public void verify() {
        // Logic to verify credit card
        System.out.println("Credit card verified: " + cardNumber);
    }
	
	
	
}
