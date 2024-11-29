package com.ejemplos.spring.repository;

import org.springframework.stereotype.Repository;

import com.ejemplos.spring.model.Juego;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegosDAO extends JpaRepository<Juego,Integer>{

}
