package com.ejemplos.spring.controller.error;

public class JuegoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public JuegoNotFoundException() {
		super("Epic Fail: No existe el Juego");
	}
	
	
	public JuegoNotFoundException(int id) {
		super("Epic Fail: No existe el Juego con el id"+ id);
	}
	
	
	
}
