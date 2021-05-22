package com.letslearn.eventify.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Event")
public class Event {

	@Id
	@Column(name="id",unique=true)
	@Type(type="org.hibernate.type.UUIDCharType")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", columnDefinition = "VARCHAR(2000)", nullable = false)
	private String description;
	
	@CreationTimestamp
	@Column(name = "createdTimeStamp",nullable = false)
    private LocalDateTime createdTimeStamp;
 
    @UpdateTimestamp
	@Column(name = "updatedTimeStamp",nullable = false)
    private LocalDateTime updateDateTime;
    
    
	@Column(name = "createdUser", nullable = false)
	@Type(type="org.hibernate.type.UUIDCharType")
    private UUID createdUser;
	
	@Column(name = "registrationEndDateTime", nullable = false)
	private LocalDateTime registrationEndDateTime;
	
	@Column(name = "startDateTime", nullable = false)
	private LocalDateTime startDateTime;
	
	@Column(name = "endDateTime", nullable = false)
	private LocalDateTime endDateTime;
	
	@Column(name = "venue", nullable = false)
	private String venue;
	
	@Column(name = "isPublic", columnDefinition="tinyint(1) default 0")
	private boolean isPublic;
	
	@Column(name = "isOnline", columnDefinition="tinyint(1) default 0")
	private boolean isOnline;
	
	@Column(name = "capacity", nullable = false)
	private int capacity;
	
	@Column(name = "price", nullable = false)
	private int price;
	
	@Column(name = "status", nullable = false)
	private String status;


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}



	public LocalDateTime getRegistrationEndDateTime() {
		return registrationEndDateTime;
	}

	public void setRegistrationEndDateTime(LocalDateTime registrationEndDateTime) {
		this.registrationEndDateTime = registrationEndDateTime;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UUID getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(UUID createdUser) {
		this.createdUser = createdUser;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", createdTimeStamp="
				+ createdTimeStamp + ", updateDateTime=" + updateDateTime + ", createdUser=" + createdUser
				+ ", registrationEndDateTime=" + registrationEndDateTime + ", startDateTime=" + startDateTime
				+ ", endDateTime=" + endDateTime + ", venue=" + venue + ", isPublic=" + isPublic + ", isOnline="
				+ isOnline + ", capacity=" + capacity + ", price=" + price + ", status=" + status + "]";
	}
	
	
	
}
