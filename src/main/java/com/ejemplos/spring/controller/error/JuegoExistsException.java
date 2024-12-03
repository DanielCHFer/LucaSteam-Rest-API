package com.ejemplos.spring.controller.error;

public class JuegoExistsException extends RuntimeException {

	// Constructor con mensaje
	/*
	 * public JuegoExistsException(String message) { super(message); }
	 * 
	 * // Constructor con mensaje y causa public JuegoExistsException(String
	 * message, Throwable cause) { super(message, cause); }
	 */

	public JuegoExistsException() {
		super("El juego ya existe.");
	}

}
