/**
 * 
 */
package com.flipkart.business;

/**
 * 
 */
public class UpiOperations extends PaymentOperations {
    private String upiID;

    public void pay() {
        // Logic to process UPI payment
        System.out.println("UPI payment processed: " + upiID);
    }

    public void verify() {
        // Logic to verify UPI ID
        System.out.println("UPI ID verified: " + upiID);
    }
}
