package com.ejemplos.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplos.spring.model.Juego;
import com.ejemplos.spring.repository.JuegosDAO;

@Service
public class JuegosServiceImpl implements JuegosService{

	@Autowired
	private JuegosDAO juegosDao;
	
	@Override
	public List<Juego> findAll() {
		return juegosDao.findAll();
	}

}
