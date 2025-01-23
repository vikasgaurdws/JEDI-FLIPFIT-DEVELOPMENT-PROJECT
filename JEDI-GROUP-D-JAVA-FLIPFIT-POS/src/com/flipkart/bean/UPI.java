package com.flipkart.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class UPI extends Payment{
	private String upiId;

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public UPI(String upiId) {
		this.upiId = upiId;
	}

	public UPI(LocalDate paymentDate, LocalTime paymentTime, Integer amount, String type, Integer paymentId, String upiId) {
		super(paymentDate, paymentTime, amount, type, paymentId);
		this.upiId = upiId;
	}

	@Override
	public String toString() {
		return "UPI{" +
				"upiId='" + upiId + '\'' +
				'}';
	}
}
