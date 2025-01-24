/**
 * 
 */
package com.flipkart.bean;

/**
 * 
 */
public class FlipFitGymUser {
	private String userName;
	 private String userEmail;
	 private String userPassword;
	 private String userMobile;
	 private String userRole;
	 public Integer userId;

 public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserEmail() {
	return userEmail;
}
public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}
public String getUserPassword() {
	return userPassword;
}
public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
}
public String getUserMobile() {
	return userMobile;
}
public void setUserMobile(String userMobile) {
	this.userMobile = userMobile;
}
public String getUserRole() {
	return userRole;
}
public void setUserRole(String userRole) {
	this.userRole = userRole;
}

	public FlipFitGymUser() {
	}

	public FlipFitGymUser(String userName, String userEmail, String userPassword, String userMobile, String userRole, Integer userId) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userMobile = userMobile;
		this.userRole = userRole;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "FlipFitGymUser{" +
				"userName='" + userName + '\'' +
				", userEmail='" + userEmail + '\'' +
				", userPassword='" + userPassword + '\'' +
				", userMobile='" + userMobile + '\'' +
				", userRole='" + userRole + '\'' +
				", userId=" + userId +
				'}';
	}
}
