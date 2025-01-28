package com.flipkart.bean;
import java.time.*;


public class CreditCard {
    private LocalDate expiryDate;
    private String cvv;
    private String cardNumber;
    private String name;

    // Constructor
    public CreditCard(LocalDate expiryDate, String cvv, String cardNumber, String name) {
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardNumber = cardNumber;
        this.name = name;
    }
    
    public CreditCard()

    {
    	
    }
    // Getters and Setters
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Overriding toString() method
    @Override
    public String toString() {
        return "CreditCard{" +
               "expiryDate=" + expiryDate +
               ", cvv='***'" +  
               ", cardNumber='XXXX-XXXX-XXXX-" + (cardNumber.length() > 4 ? cardNumber.substring(cardNumber.length() - 4) : "****") + "'" + // Mask card number
               ", name='" + name + "'" +
               '}';
    }
}
