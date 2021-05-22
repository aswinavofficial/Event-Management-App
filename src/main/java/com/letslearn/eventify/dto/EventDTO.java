package com.letslearn.eventify.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventDTO {
	
	private UUID id;
	
	private String name;
	
	private String description;
	
	private LocalDateTime registrationEndDateTime;
	
	private LocalDateTime startDateTime;
	
	private LocalDateTime endDateTime;
	
	private String venue;
	
	private boolean isPublic;
	
	private boolean isOnline;
	
	private int capacity;

	private int price;

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

	@Override
	public String toString() {
		return "EventDTO [id=" + id + ", name=" + name + ", description=" + description + ", registrationEndDateTime="
				+ registrationEndDateTime + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime
				+ ", venue=" + venue + ", isPublic=" + isPublic + ", isOnline=" + isOnline + ", capacity=" + capacity
				+ ", price=" + price + ", status=" + status + "]";
	}
	
	

}
