package com.ssafy.happyhouse.model.dto;

public class UserInfo implements Comparable<UserInfo>{
	private String userId;
	private String userPassword;
	private String userName;
	private String userAddress;
	private String userPhoneNumber;
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public UserInfo(String userId, String userPassword, String userName, String userAddress, String userPhoneNumber) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName
				+ ", userAddress=" + userAddress + ", userPhoneNumber=" + userPhoneNumber + "]";
	}

	@Override
	public int compareTo(UserInfo o) {
		return (this.userId.compareToIgnoreCase(o.userId));
	}
	
}
