package com.letslearn.eventify.exception;

import java.util.UUID;

public class UserNotFoundException  extends RuntimeException{
	
	private UUID id;

	private static final long serialVersionUID = -6854530906923435532L;

	public UserNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(UUID id) {
		super();
		this.id = id;
	}

	
}
