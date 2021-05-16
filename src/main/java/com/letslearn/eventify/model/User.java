package com.letslearn.eventify.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import javax.persistence.Index;


@Entity
@Table(name = "User",
indexes = {@Index(name = "email_index",  columnList="email", unique = true),
        @Index(name = "mobile_index", columnList="mobileNumber", unique = true),
        @Index(name = "username_index", columnList="userName", unique = true)})
public class User {
	
	@Id
	@Column(name="id",unique=true)
	@Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
	
	@Column(name="email",unique=true)
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	@Column(name="mobileNumber",unique=true)
	private String mobileNumber;
	
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

}
