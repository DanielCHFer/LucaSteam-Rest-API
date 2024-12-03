package com.ejemplos.spring.service;

import java.util.List;
import java.util.Optional;

import com.ejemplos.spring.model.Editor;
import com.ejemplos.spring.model.Juego;

public interface JuegosService {

	//Devuelve todos los juegos 
	public List<Juego> findAll();
	
	public List<Juego> readJuegos();

	public Juego saveJuego(Juego juego);
	
	public Editor saveEditor(Editor editor);
	
	public Optional<Juego> findByNombre(String nombre);

	public Optional<Juego> updateJuego(Juego juego);

	public List<Juego> findByAnyo(int anyo);

	public Optional<Juego> findById(int id);

	public Juego deleteJuego(Juego j);

	public List<Juego> listSigloXX();

}
