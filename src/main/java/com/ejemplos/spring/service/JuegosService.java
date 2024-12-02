package com.ejemplos.spring.service;

import java.util.List;

import com.ejemplos.spring.model.Juego;

public interface JuegosService {

	//Devuelve todos los juegos 
	public List<Juego> findAll();
	
	public List<Juego> readJuegos();
}
