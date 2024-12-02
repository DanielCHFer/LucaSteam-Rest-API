package com.ejemplos.spring.controller.error;

public class ValidationException extends RuntimeException{

	 public ValidationException(String message) {
	        super(message);
	    }
}
