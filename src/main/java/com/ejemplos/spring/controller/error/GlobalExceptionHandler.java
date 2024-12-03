package com.ejemplos.spring.controller.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.DateFormat;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	
	@ExceptionHandler(JuegoFormatException.class)
	public ResponseEntity<String> handleValidationException(JuegoFormatException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(NoSuchElementException ex, WebRequest request) {
		String errorMessage = "Recurso no encontrado";
		
		
		// Cuerpo de la respuesta
		Map<String, Object> body = new LinkedHashMap<>();
		
		Object timestamp = body.get("timestamp");
		if (timestamp == null) {
			body.put("timestamp", dateFormat.format(new Date()));
		} else {
			body.put("timestamp", dateFormat.format((Date) timestamp));
		}
		body.put("timestamp", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", ex.getLocalizedMessage());
		body.put("message", errorMessage);

		// Devuelve la respuesta con código 404 y el cuerpo de error
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

}
