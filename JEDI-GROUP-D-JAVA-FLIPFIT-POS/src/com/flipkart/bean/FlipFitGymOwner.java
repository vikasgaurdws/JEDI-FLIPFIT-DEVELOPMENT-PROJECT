package com.flipkart.bean;

public class FlipFitGymOwner extends FlipFitGymUser{
	private String panNumber;
	private String adharNumber;
	private Boolean flagVerified;
	private Integer gymId;

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
	public Integer getGymId() {
		return gymId;
	}
	public void setGymId(Integer gymId) {
		this.gymId = gymId;
	}


	public FlipFitGymOwner() {
	}

	public FlipFitGymOwner(String panNumber, String adharNumber, Boolean flagVerified, Integer gymId) {
		this.panNumber = panNumber;
		this.adharNumber = adharNumber;
		this.flagVerified = flagVerified;
		this.gymId = gymId;
	}


	@Override
	public String toString() {
		return "FlipFitGymOwner{" +
				"panNumber='" + panNumber + '\'' +
				", adharNumber='" + adharNumber + '\'' +
				", flagVerified=" + flagVerified +
				", gymId=" + gymId +
				'}';
	}
}
