package com.ejemplos.spring.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
	
    private static final Logger logger = LoggerFactory.getLogger(JuegosController.class);
	
	@Autowired
	private JuegosService serv;

	/**
	 * Listar todos los juegos
	 *
	 * @return La lista de objetos Juego
	 */
	@Operation(summary = "Listar Juegos", 
			description = "Devuelve una lista de todos los juegos almacenados en la base de datos.")
	@GetMapping

	public List<Juego> listJuegos() {
		logger.info("Listando todos los juegos");
		return serv.findAll();
	}

	/**
	 * Cargar juegos desde un archivo CSV.
	 *
	 * @return Una lista de juegos con los datos del csv para agregarlos a la base
	 *         de datos.
	 */
	@Operation(summary = "Cargar juegos desde CSV", 
			description = "Carga los juegos desde un archivo CSV y devuelve la lista de juegos.")
	@GetMapping("/cargar")
    public List<Juego> readJuegos() {
		logger.info("Cargando los juegos desde el CSV");
        return serv.readJuegos();
    }
	
	/**
     * Listar juegos según el año de salida.
     *
     * @param anyo El año para filtrar los juegos.
     * @return Una lista de los juegos filtrados por el año especificado.
     */
    @Operation(
        summary = "Listar juegos por año",
        description = "Devuelve un listado de los juegos almacenados filtrados por el año de salida especificado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de juegos obtenida exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron juegos para el año especificado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
	@GetMapping("/anyo/{anyo}")
    public List<Juego> listJuegosByAnyo(@PathVariable int anyo) {
		logger.info("Listando los juegos lanzados en el año " + anyo);
        return serv.findByAnyo(anyo);
    }

	/**
     * Crear un nuevo juego.
     *
     * @param Juego El objeto Juego que se desea guardar.
     * @param bindingResult Contiene los errores de validación, si existen.
     * @return El juego recién guardado.
     */
    @Operation(
        summary = "Crear un juego",
        description = "Crea un nuevo juego y lo guarda en la base de datos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Juego creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en los datos proporcionados"),
        @ApiResponse(responseCode = "500", description = "Error al crear el juego")
    })
	@PostMapping
	public Juego saveJuego(@Valid @RequestBody Juego juego, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.info("Introduciendo "+juego.getNombre()+" en la base de datos:");
			StringBuilder errors = new StringBuilder();
			bindingResult.getFieldErrors().forEach(error -> errors.append(error.getField()).append(": ")
					.append(error.getDefaultMessage()).append("; "));
			throw new JuegoFormatException(errors.toString());
		}
		return serv.saveJuego(juego);
	}

    /**
     * Actualizar un juego existente.
     *
     * @param juego El juego con los nuevos datos.
     * @return El juego actualizado.
     */
	@Operation(summary = "Actualizar datos de un juego", description = "Recibe un objeto juego con id y reemplaza el juego correspondiente con los nuevos datos.")
	@PutMapping
	public ResponseEntity<Juego> updateJuego1(@RequestBody Juego juego) {
		Optional<Juego> result = this.serv.updateJuego(juego);
		if (result.isEmpty()) {
			// No encontrado
			logger.warn("El juego con el ID "+ juego.getIdjuego()+" no existe");
			throw new NoSuchElementException("El juego con el ID proporcionado no existe");
		}
		return ResponseEntity.of(result);
	}

	/**
     * Eliminar un juego.
     *
     * @param id El ID del juego que se desea eliminar.
     * @return El juego eliminado.
     */
    @Operation(
        summary = "Eliminar un juego",
        description = "Elimina un juego por su ID y devuelve el juego eliminado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Juego eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontró el juego para eliminar"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
	@DeleteMapping("/{id}")
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
