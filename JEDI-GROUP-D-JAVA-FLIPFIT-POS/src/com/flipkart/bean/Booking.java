package com.flipkart.bean;

import java.time.LocalDateTime;
import java.time.*;
public class Booking {

	private Integer userId;
	private Integer slotId;
	private Integer gymId;
	private LocalTime time;
	private Enum bookingStatus;
	private LocalDate bookingDate;
	private Integer bookingId;
	Booking(){
		 this.userId = userId;
		 this.slotId = slotId;
		 this.gymId = gymId;
		 this.time= time;
		 this.bookingDate = bookingDate;
		this.bookingId = bookingId;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSlotId() {
		return slotId;
	}
	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}
	public Integer getGymId() {
		return gymId;
	}
	public void setGymId(Integer gymId) {
		this.gymId = gymId;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public Enum getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(Enum bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	
	
	
	
}
