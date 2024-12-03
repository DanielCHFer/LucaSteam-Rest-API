package com.ejemplos.spring.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ejemplos.spring.model.Juego;
import com.ejemplos.spring.repository.JuegosDAO;

@SpringBootTest
public class JuegosServiceTest {

	@Mock
	JuegosDAO juegoRepository;

	@InjectMocks
	JuegosServiceImpl juegoService;

	@Test
	void testSeInsertaJuego() {
		when(juegoRepository.save(any(Juego.class))).thenReturn(new Juego());
		juegoService.saveJuego(new Juego());
		verify(juegoRepository, times(1)).save((any(Juego.class)));
	}

}
