package com.letslearn.eventify.exception;

public class UserAuthenticationException  extends RuntimeException {
	
	private String message;

	private static final long serialVersionUID = -6042259574826420008L;

	public UserAuthenticationException() {
		super();
	}

	public UserAuthenticationException(String message) {
		super(message);
		this.message = message;
	}
	
	
	
	

}
