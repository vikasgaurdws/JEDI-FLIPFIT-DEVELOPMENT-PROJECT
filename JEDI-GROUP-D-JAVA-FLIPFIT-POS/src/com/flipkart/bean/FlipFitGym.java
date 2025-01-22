package com.flipkart.bean;

public class FlipFitGym{
	private String gymName;
	private String gymLocation;
	private Integer availableSlot;
	private Integer price;
	private Boolean flagVerified;
	private Integer gymId;

        FlipFitGym()
	{
		this.gymNmae = gymName;
		this.gymLocation = gymLocation;
		this.availableSlot = avaiableSlot;
		this.price = price;
		this.flagVerified = flagVerified;
		this.gymId = gymId;
	}
	
	public Integer getGymId() {
		return gymId;
	}
	public void setGymId(Integer gymId) {
		this.gymId = gymId;
	}
	public String getGymName() {
		return gymName;
	}
	public void setGymName(String gymName) {
		this.gymName = gymName;
	}
	public String getGymLocation() {
		return gymLocation;
	}
	public void setGymLocation(String gymLocation) {
		this.gymLocation = gymLocation;
	}
	public Integer getAvailableSlot() {
		return availableSlot;
	}
	public void setAvailableSlot(Integer availableSlot) {
		this.availableSlot = availableSlot;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Boolean getFlagVerified() {
		return flagVerified;
	}
	public void setFlagVerified(Boolean flagVerified) {
		this.flagVerified = flagVerified;
	}
	
}
