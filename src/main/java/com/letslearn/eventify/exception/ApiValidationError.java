package com.letslearn.eventify.exception;

public class ApiValidationError extends ApiSubError{
	
	   private String object;
	   private String field;
	   private Object rejectedValue;
	   private String message;
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Object getRejectedValue() {
		return rejectedValue;
	}
	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ApiValidationError(String object, String field, Object rejectedValue, String message) {
		super();
		this.object = object;
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.message = message;
	}
	public ApiValidationError() {
		super();
		// TODO Auto-generated constructor stub
	}
	   
	   

}
