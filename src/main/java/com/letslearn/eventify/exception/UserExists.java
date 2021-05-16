package com.letslearn.eventify.exception;

import java.util.UUID;

public class UserExists  extends RuntimeException{
	
	private String message;

	private static final long serialVersionUID = -6854530906923435532L;

	public UserExists() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserExists(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}




	
	
}
