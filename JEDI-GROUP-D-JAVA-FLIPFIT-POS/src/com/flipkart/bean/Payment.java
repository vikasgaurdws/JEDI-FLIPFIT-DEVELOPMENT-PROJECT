package com.flipkart.bean;
import java.time.*;


public class Payment {
	private LocalDate paymentDate;
	private LocalTime paymentTime;
	private Integer amount;
	private String type;
	private Integer paymentId;
	Payment ()
	{
		this .amount = amount;
		this.type =type;
		this.paymentId = paymentId;
		this.paymentTime = paymentTime;
		this.paymentDate= paymentDate;
		
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public LocalTime getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(LocalTime paymentTime) {
		this.paymentTime = paymentTime;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
