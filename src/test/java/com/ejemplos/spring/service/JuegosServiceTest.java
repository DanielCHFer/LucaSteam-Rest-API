package com.ejemplos.spring.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
<<<<<<< HEAD
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
=======
>>>>>>> dani

import java.util.Collections;

import com.ejemplos.spring.model.Juego;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(JuegosService.class)
class JuegosServiceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JuegosService juegoService; // Simula el servicio inyectado en el controlador

    @Test
	void testDeleteJuegoDevuelveJuego() throws Exception {
		//test
	}
    
=======
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

>>>>>>> dani
}
