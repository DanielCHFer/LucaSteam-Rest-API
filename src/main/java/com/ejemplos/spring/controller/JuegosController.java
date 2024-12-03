package com.ejemplos.spring.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.ejemplos.spring.RestLucaSteamApplication;
import com.ejemplos.spring.controller.error.JuegoFormatException;
import com.ejemplos.spring.model.Juego;
import com.ejemplos.spring.service.JuegosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/juegos")
@Tag(name = "Juegos Controller", description = "Controlador para gestionar los juegos.")
public class JuegosController {
	
    private static final Logger logger = LoggerFactory.getLogger(JuegosController.class);
	
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
		logger.info("Listando todos los juegos");
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
		logger.info("Cargando los juegos desde el CSV");
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
		logger.info("Listando los juegos lanzados en el año " + anyo);
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
			logger.warn("Juego con formato no válido");
	        throw new JuegoFormatException(errors.toString());
	    }
		logger.info("Introduciendo "+juego.getNombre()+" en la base de datos:");
		return serv.saveJuego(juego);
	}
	
	//se hace un update, se llama al server y nos devuelver unos valores y en caso de que no haya valor devuelto lanza una excepcion.
	@PutMapping
	public Juego updateJuego(@RequestBody Juego juego)
	{
		logger.info("Modificando" + juego.getNombre()+ "en la base de datos");
		return serv.updateJuego(juego).orElseThrow(JuegoNotFoundException::new); 
	}
	
	//hacemos un update y en caso de que no se pueda devolvemos un mensaje personalizado
	@PutMapping("/modicar")
	public ResponseEntity<Juego> updateJuego1(@RequestBody Juego juego)
	{
		Optional<Juego> result = this.serv.updateJuego(juego);
		if (result.isEmpty()) {
			// No encontrado
			logger.warn("El juego con el ID "+ juego.getIdjuego()+" no existe");
			throw new NoSuchElementException("El juego con el ID proporcionado no existe");
		}
		return ResponseEntity.of(result);
	}

	@DeleteMapping("/juegos/{id}")
	public Optional<Juego> deleteJuego(@PathVariable int id) {
	    Optional<Juego> juego = serv.findById(id);

	    if (juego.isPresent()) {
	        serv.deleteJuego(juego.get());
	        logger.info(juego.get().getNombre()+" borrado");
	        return Optional.of(juego.get()); // Devuelve el juego eliminado
	    } else {
	    	logger.warn("No existe ningun juego con el id "+id );
	        throw new JuegoNotFoundException(id);
	    }
	}
	
	/**
	 * Crear Endpoint @GetMapping(“/nintendo”) en la capa de control y el método listNintendo(), devuelve List<Juego>.
	 * @return la lista de juegos editados por nintendo
	 */
	
	@GetMapping("/nintendo")
	public List<Juego> listNintendo(){
		logger.info("Listando juegos editados por Nintendo");
		return serv.listNintendo();
	}
}
