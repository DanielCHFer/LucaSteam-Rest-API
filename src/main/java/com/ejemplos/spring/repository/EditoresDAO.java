package com.ejemplos.spring.repository;

import org.springframework.stereotype.Repository;

import com.ejemplos.spring.model.Editor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EditoresDAO extends JpaRepository<Editor,Integer>{

	Optional<Editor> findByNombre(String nombre);
	
}
