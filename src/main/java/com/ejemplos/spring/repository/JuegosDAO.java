package com.ejemplos.spring.repository;

import org.springframework.stereotype.Repository;

import com.ejemplos.spring.model.Juego;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegosDAO extends JpaRepository<Juego,Integer>{

	Optional<Juego> findByNombre(String nombre);

	List<Juego> findByAnyo(int anyo);

    @Query("FROM sql8748460.editores INNER JOIN sql8748460.juegos ON \r\n"
    		+ "sql8748460.editores.ideditor=sql8748460.juegos.ideditor WHERE sql8748460.editores.nombre = 'Nintendo';")
	List<Juego> listNintendo();
}
