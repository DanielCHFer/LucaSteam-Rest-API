package com.ejemplos.spring.controller.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(JuegoFormatException.class)
	    public ResponseEntity<String> handleValidationException(JuegoFormatException ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	
}
