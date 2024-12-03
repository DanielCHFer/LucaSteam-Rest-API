package com.ejemplos.spring.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ejemplos.spring.model.Juego;

import io.swagger.v3.oas.models.media.MediaType;

@SpringBootTest
public class JuegosServiceTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private JuegosService juegoService;
	
	@Test
	void testDeleteJuegoDevuelveJuego() throws Exception {
		//test
	}


}
