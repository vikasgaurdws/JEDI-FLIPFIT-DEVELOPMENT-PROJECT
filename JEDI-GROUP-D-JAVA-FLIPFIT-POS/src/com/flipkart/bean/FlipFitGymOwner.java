package com.flipkart.bean;

public class FlipFitGymOwner extends FlipFitGymUser{
	private String panNumber;
	private Long adharNumber;
	private Boolean flagVerified;
	private Integer gymId;
	public FlipFitGymOwner()
	{
		super();
//		 this.panNumber = panNumber;
//		this.adharNumber = adharNumber;
//		 this.flagVerified=flagVerified;
//		 this.gymId = gymId;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public Long getAdharNumber() {
		return adharNumber;
	}
	public void setAdharNumber(Long adharNumber) {
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

}
