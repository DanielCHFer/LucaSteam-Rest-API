package com.ejemplos.spring.controller.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	 @ExceptionHandler(JuegoFormatException.class)
	    public ResponseEntity<String> handleValidationException(JuegoFormatException ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	 
	 @ExceptionHandler(NoSuchElementException.class)
	 public ResponseEntity<Object> handleResourceNotFoundException(NoSuchElementException ex, WebRequest request) {
	     String errorMessage = "Recurso no encontrado";
	     
	     // Cuerpo de la respuesta
	     Map<String, Object> body = new LinkedHashMap<>();
	     body.put("timestamp", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
	     body.put("status", HttpStatus.NOT_FOUND.value());
	     body.put("error", ex.getLocalizedMessage());
	     body.put("message", errorMessage);

	     // Devuelve la respuesta con código 404 y el cuerpo de error
	     return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	 }
	
}
