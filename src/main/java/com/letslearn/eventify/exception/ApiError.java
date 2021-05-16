package com.letslearn.eventify.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

public class ApiError {
	
	private int responseCode;
	private HttpStatus status;
	private String message;
	private String debugMessage;
	private List<ApiSubError> subErrors;
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDebugMessage() {
		return debugMessage;
	}
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}
	public List<ApiSubError> getSubErrors() {
		return subErrors;
	}
	public void setSubErrors(List<ApiSubError> subErrors) {
		this.subErrors = subErrors;
	}
	public ApiError() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApiError(int responseCode, HttpStatus status, String message, String debugMessage,
			List<ApiSubError> subErrors) {
		super();
		this.responseCode = responseCode;
		this.status = status;
		this.message = message;
		this.debugMessage = debugMessage;
		this.subErrors = subErrors;
	}
	
	 public ApiError(HttpStatus status, String message, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = message;
	       this.debugMessage = ex.getLocalizedMessage();
	   }
	public ApiError(HttpStatus status) {
		
		this.status = status;
	}
	
	
	

}
