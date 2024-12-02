package com.ejemplos.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ejemplos.spring.controller.error.JuegoNotFoundException;
import com.ejemplos.spring.controller.error.ValidationException;
import com.ejemplos.spring.model.Juego;
import com.ejemplos.spring.service.JuegosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/juegos")
@Tag(name = "Juegos Controller", description = "Controlador para gestionar los juegos.")
public class JuegosController {
	
	@Autowired
	private JuegosService serv;
	
	/**
	 * Listar todos los juegos
	 *
	 * @return La lista de objetos Juego
	 */
	@Operation(
		summary = "Listar Juegos",
		description = "Devuelve una lista de todos los juegos almacenados en la base de datos."
	)
	@GetMapping
	public List<Juego> listJuegos(){
		return serv.findAll();
	}
	
	/**
	 * Cargar juegos desde un archivo CSV.
	 *
	 * @return Una lista de juegos con los datos del csv para agregarlos a la base de datos.
	 */
	@Operation(
		summary = "Cargar juegos desde CSV",
		description = "Carga los juegos desde un archivo CSV y devuelve la lista de juegos."
	)
	@GetMapping("/cargar")
    public List<Juego> readJuegos() {
        return serv.readJuegos();
    }

	//
	/**
	 * Adrian: Crear Endpoint @PostMapping("/juegos") saveJuego(Juego juego) en la capa de Control.
	 * @return Optional<Juego>
	 */
	
	@PostMapping
	public Juego saveJuego(@Valid @RequestBody Juego juego, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
	        StringBuilder errors = new StringBuilder();
	        bindingResult.getFieldErrors().forEach(error -> 
	            errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
	        );
	        throw new ValidationException(errors.toString());
	    }
		return serv.saveJuego(juego);
	}
	
	/*@PostMapping
	public Juego saveJuego(@Valid @RequestBody Juego juego, BindingResult bindingResult) {

	    if (bindingResult.hasErrors()) {
	        // Construir un mensaje de error a partir de los errores de validación
	        StringBuilder errors = new StringBuilder();
	        bindingResult.getFieldErrors().forEach(error -> 
	            errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
	        );

	        // Lanza una excepción con el estado 400 y el mensaje de error
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
	    }

	    // Si no hay errores, guarda el juego
	    return serv.saveJuego(juego);
	}*/
	
	//se hace un update, se llama al server y nos devuelver unos valores y en caso de que no haya valor devuelto lanza una excepcion.
	@PutMapping("/update")
	public Juego updateJugo(@RequestBody Juego juego)
	{
		return serv.updateJuego(juego).orElseThrow(JuegoNotFoundException::new); 
	}
}
