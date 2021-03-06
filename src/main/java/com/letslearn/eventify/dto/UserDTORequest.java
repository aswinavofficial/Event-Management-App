package com.letslearn.eventify.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTORequest {
	
private UUID id;
	
    @NotBlank(message = "Email is mandatory")
	private String email;
	
    @NotBlank(message = "First Name is mandatory")
	private String firstName;
	
    @NotBlank(message = "Last Name is mandatory")
	private String lastName;

    @NotBlank(message = "User Name is mandatory")
	private String userName;
	
    @NotBlank(message = "Mobile Number is mandatory")
    @Size(min=10,max=10)
	private String mobileNumber;
	
//    @NotBlank(message = "Password is mandatory")
	private String password;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDTOInput [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", mobileNumber=" + mobileNumber + ", password=" + "**masked**" + "]";
	}
	
	

}
