package com.ejemplos.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
