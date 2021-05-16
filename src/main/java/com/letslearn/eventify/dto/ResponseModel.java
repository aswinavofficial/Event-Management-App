package com.letslearn.eventify.dto;

import java.util.ArrayList;
import java.util.List;

import com.letslearn.eventify.exception.ApiError;

public class ResponseModel {
	
	private int responseCode;
	private BaseDTO data;
	private List<ApiError> errors;
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public BaseDTO getData() {
		return data;
	}
	public void setData(BaseDTO data) {
		this.data = data;
	}
	public List<ApiError> getErrors() {
		return errors;
	}
	public void setErrors(List<ApiError> errors) {
		this.errors = errors;
	}
	public ResponseModel(int responseCode, BaseDTO data, List<ApiError> errors) {
		super();
		this.responseCode = responseCode;
		this.data = data;
		this.errors = errors;
	}
	public ResponseModel() {
		super();
		
		errors = new ArrayList<ApiError>();
	}
	
	
	

}
