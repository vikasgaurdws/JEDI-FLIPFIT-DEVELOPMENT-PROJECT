package com.flipkart.bean;

public class FlipFitGymOwner extends FlipFitGymUser{
	private String panNumber;
	private String adharNumber;
	private Boolean flagVerified;
	private Integer userId;

	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getAdharNumber() {
		return adharNumber;
	}
	public void setAdharNumber(String adharNumber) {
		this.adharNumber = adharNumber;
	}
	public Boolean getFlagVerified() {
		return flagVerified;
	}
	public void setFlagVerified(Boolean flagVerified) {
		this.flagVerified = flagVerified;
	}
	public Integer getGymOwnerId() {
		return super.userId;
	}
	public void setGymOwnerId(Integer GymOwnerId) {
		this.userId = GymOwnerId;
	}


	public FlipFitGymOwner() {
	}

	public FlipFitGymOwner(String panNumber, String adharNumber, Boolean flagVerified, Integer GymOwnerId) {
		this.panNumber = panNumber;
		this.adharNumber = adharNumber;
		this.flagVerified = flagVerified;
		this.userId = GymOwnerId;
	}


	@Override
	public String toString() {
		return "FlipFitGymOwner{" +
				"GymOwnerId='" + userId + '\'' +
				", panNumber='" + panNumber + '\'' +
				", adharNumber='" + adharNumber + '\'' +
				", flagVerified=" + flagVerified + 
//				", userId=" + userId +
				'}';
	}
}
