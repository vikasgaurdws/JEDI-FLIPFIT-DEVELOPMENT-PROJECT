package com.flipkart.bean;
import java.time.*;
import java.time.LocalDate;

public class WaitListed {
	private Integer userId;
	private Integer slotId;
	private LocalDate date;
	private LocalTime time;
	private Integer waitlistId;
	public Integer getWaitlistId() {
		return waitlistId;
	}
	public void setWaitlistId(Integer waitlistId) {
		this.waitlistId = waitlistId;
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	
	
}
