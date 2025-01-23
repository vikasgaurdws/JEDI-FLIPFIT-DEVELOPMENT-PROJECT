package com.flipkart.bean;

import java.time.*;
public class DebitCard extends Payment{
	private String name;
	private LocalDate expiryDate;
	private Integer cvv;
	private Long cardNumber;

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

	@Override
	public String toString() {
		return "DebitCard{" +
				"name='" + name + '\'' +
				", expiryDate=" + expiryDate +
				", cvv=" + cvv +
				", cardNumber=" + cardNumber +
				'}';
	}

	public DebitCard() {
	}

	public DebitCard(String name, LocalDate expiryDate, Integer cvv, Long cardNumber) {
		this.name = name;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.cardNumber = cardNumber;
	}
}
