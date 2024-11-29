package com.ejemplos.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ejemplos.spring.model.Juego;

public class JuegosServiceImpl implements JuegosService{

	@Autowired
	private JuegoRepository repo;
	
	@Override
	public List<Juego> findAll() {
		return repo.findAll();
	}

}
