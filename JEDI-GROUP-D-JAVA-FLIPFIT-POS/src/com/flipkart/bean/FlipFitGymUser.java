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
	 private Long userMobile;
	 private String userRole;
	 private Integer userId;
	FlipFitGymUser()
	{
		this.userName = userName;
		this.userEmail =  userEmail;
		this.userPassword = userPassword;
		this.userMobile = userMobile;
		this.userRole = userRole;
		this.userId = userId;
	}
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
public Long getUserMobile() {
	return userMobile;
}
public void setUserMobile(Long userMobile) {
	this.userMobile = userMobile;
}
public String getUserRole() {
	return userRole;
}
public void setUserRole(String userRole) {
	this.userRole = userRole;
}

 
}
