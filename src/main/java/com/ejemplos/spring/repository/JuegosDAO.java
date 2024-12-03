package com.ejemplos.spring.repository;

import org.springframework.stereotype.Repository;

import com.ejemplos.spring.model.Juego;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface JuegosDAO extends JpaRepository<Juego, Integer> {

	Optional<Juego> findByNombre(String nombre);

	List<Juego> findByAnyo(int anyo);
	
	@Query("SELECT j FROM Juego j WHERE j.anyo >= 1900 AND j.anyo <= 1999")
	//@Query("SELECT j FROM Juego WHERE anyo >= 1900 AND anyo <= 1999")
    List<Juego> listSigloXX();

    @Query("SELECT j FROM Juego j JOIN editor e WHERE e.nombre = 'Nintendo'")
    List<Juego> listNintendo();
}
