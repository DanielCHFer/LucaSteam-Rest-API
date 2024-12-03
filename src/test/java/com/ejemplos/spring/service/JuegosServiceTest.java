package com.ejemplos.spring.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.ejemplos.spring.model.Editor;
import com.ejemplos.spring.model.Juego;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
