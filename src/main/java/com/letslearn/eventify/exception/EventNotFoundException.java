package com.letslearn.eventify.exception;

import java.util.UUID;

public class EventNotFoundException extends RuntimeException{
	

	private static final long serialVersionUID = 1395593948248098828L;
	private UUID id;
	
	public EventNotFoundException(UUID id) {
		super();
		this.id = id;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "EventNotFoundException [id=" + id + "]";
	}

	
	

}
