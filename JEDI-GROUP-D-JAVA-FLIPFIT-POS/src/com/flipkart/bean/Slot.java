package com.flipkart.bean;
import java.time.*;
public class Slot {
	private Integer gymId;
	private LocalTime startTime;
	private Integer capacity;
	private Integer availableSeats;
	private Integer slotId;
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
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	
	
	
	
}
