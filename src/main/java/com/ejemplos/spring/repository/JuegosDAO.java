package com.ejemplos.spring.repository;

import org.springframework.stereotype.Repository;

import com.ejemplos.spring.model.Juego;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JuegosDAO extends JpaRepository<Juego, Integer> {

	Optional<Juego> findByNombre(String nombre);

	List<Juego> findByAnyo(int anyo);

}
