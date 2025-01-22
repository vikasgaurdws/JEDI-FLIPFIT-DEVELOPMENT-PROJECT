package com.flipkart.bean;

import java.time.*;
public class DebitCard extends Payment{
	private String name;
	private LocalDate expiryDate;
	private Integer cvv;
	private Long cardNumber;
	DebitCard{
		this.name = name;
		 this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.cardNumber = cardNumber;
		
	}
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	
	
}
