package com.letslearn.eventify.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.letslearn.eventify.controller.UserController;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExeptionController extends ResponseEntityExceptionHandler {
	
    Logger log = LoggerFactory.getLogger(ExeptionController.class);


	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		log.error("Exception ",ex);

		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(UserExists.class)
	protected ResponseEntity<Object> handleUserExists(UserExists ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		log.error("Exception ",ex);

		return buildResponseEntity(apiError);
	}
	
	
	  
	  @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	                                                                HttpHeaders headers, HttpStatus status,
	                                                                WebRequest request) {
		  
	    String errorMessage = ex.getBindingResult().getFieldErrors().stream()
	                 .map(DefaultMessageSourceResolvable::getDefaultMessage)
	                 .findFirst()
	                 .orElse(ex.getMessage());
	    
	    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(errorMessage);
		
		log.error("Validation error ",ex);

	    
		  return buildResponseEntity(apiError); 

	  }
	 
	
	@Order(Ordered.LOWEST_PRECEDENCE)
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAllExceptions(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage(ex.getMessage());
		
		log.error("Exception ",ex);
		return buildResponseEntity(apiError);
	}
	
	
	


	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		log.error("Exception ",ex);

		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	}
	
	

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
