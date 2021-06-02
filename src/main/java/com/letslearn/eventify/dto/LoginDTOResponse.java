package com.letslearn.eventify.dto;

public class LoginDTOResponse {
	
	private String jwt;
	private UserDTO userDetails;
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public UserDTO getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}
	

}
