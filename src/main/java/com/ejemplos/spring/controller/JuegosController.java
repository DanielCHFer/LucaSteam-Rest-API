package com.ejemplos.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ejemplos.spring.controller.error.JuegoNotFoundException;
import com.ejemplos.spring.controller.error.JuegoFormatException;
import com.ejemplos.spring.model.Juego;
import com.ejemplos.spring.service.JuegosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
	
	/**
	 * Cargar juegos desde un archivo CSV.
	 *
	 * @return Una lista de juegos con los datos del csv para agregarlos a la base de datos.
	 */
	@Operation(
		summary = "Listar juegos segun anyo de salida",
		description = "Devuelve un listado de los juegos almacenados filtrando por el anyo de salida especificado."
	)
	@GetMapping("/anyo/{anyo}")
    public List<Juego> listJuegosByAnyo(@PathVariable int anyo) {
        return serv.findByAnyo(anyo);
    }
	
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
	        throw new JuegoFormatException(errors.toString());
	    }
		return serv.saveJuego(juego);
	}
	
	//se hace un update, se llama al server y nos devuelver unos valores y en caso de que no haya valor devuelto lanza una excepcion.
	@PutMapping
	public Juego updateJuego(@RequestBody Juego juego)
	{
		return serv.updateJuego(juego).orElseThrow(JuegoNotFoundException::new); 
	}

	@DeleteMapping("/juegos/{id}")
	public Optional<Juego> deleteJuego(@PathVariable int id) {
	    Optional<Juego> juego = serv.findById(id);

	    if (juego.isPresent()) {
	        serv.deleteJuego(juego.get());
	        return Optional.of(juego.get()); // Devuelve el juego eliminado
	    } else {
	        throw new JuegoNotFoundException(id);
	    }
	    
	}
	
	/**
	 * Listar los juegos del siglo XX.
	 *
	 * @return Una lista de juegos con los juegos creados durante el siglo XX.
	 */
	@Operation(summary = "Obtener lista de juegos del siglo XX", 
            description = "Este endpoint devuelve una lista de todos los juegos cuya fecha esté entre 1900 y 1999.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de juegos obtenida exitosamente"),
			@ApiResponse(responseCode = "204", description = "No se encontraron juegos en el rango de fechas"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")}
	)
	@GetMapping("/sigloveinte")
    public List<Juego> listSigloXX() {
        return serv.listSigloXX();
    }
}
