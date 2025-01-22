package com.flipkart.bean;

public class Notification {
	private Integer bookingId;
	private Integer slotId;
	private String bookingStatus;
	public Notification()
	{
//		this.bookingId = bookingId;
//		this.slotId = slotId;
//		this.bookingStatus = bookingStatus;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getSlotId() {
		return slotId;
	}
	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}
}
