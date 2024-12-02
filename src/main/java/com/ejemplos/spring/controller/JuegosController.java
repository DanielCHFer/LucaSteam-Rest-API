package com.ejemplos.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.spring.model.Juego;
import com.ejemplos.spring.service.JuegosService;

@RestController
public class JuegosController {
	
	@Autowired
	private JuegosService serv;
	
	@GetMapping("/juegos")
	public List<Juego> listJuegos(){
		return serv.findAll();
	}

	//
	/**
	 * Adrian: Crear Endpoint @PostMapping("/juegos") saveJuego(Juego juego) en la capa de Control devuelve Optional<Juego>.
	 */
	@PostMapping("/juegos")
	public Optional<Juego> saveJuego(@RequestBody Juego j){

		serv.saveJuego(j);
		return Optional.ofNullable(j);
	}
}
