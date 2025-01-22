package com.flipkart.bean;
import java.time.*;
public class CreditCard {
	private LocalDate expiryDate;
	private Integer cvv;
	private Long cardNumber;
	private String name;
	CreditCard{
		this.expiryDate = expityDate;
		this.cvv = cvv;
		this.cardNumber = cardNumber;
		this.name= name;
		
		
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
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	}
