package com.ejemplos.spring.service;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import com.ejemplos.spring.controller.JuegosController;
import com.ejemplos.spring.controller.error.JuegoExistsException;
import com.ejemplos.spring.model.Editor;
import com.ejemplos.spring.model.Juego;
import com.ejemplos.spring.service.JuegosService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.SelectorResolutionResult.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(JuegosController.class)
class NombreApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JuegosService juegoService; // Simula el servicio inyectado en el controlador

   
	
	 @Test
	    void testSaveJuegoConDatosInvalidos() throws Exception {
	        // Mock de un juego de entrada con datos inválidos
	        Juego juego = new Juego();
	        juego.setNombre(""); // Campo inválido
	        juego.setPlataforma("Plataforma");
	        juego.setAnyo(1980);
	        juego.setGenero("Genero");
	        juego.setVentasna(100);
	        juego.setVentaseu(100);
	        juego.setVentasjp(100);
	        juego.setVentasotras(100);
	        juego.setVentasglobales(100);

	        // Realizar la solicitud POST y verificar que lanza una excepción
	        mockMvc.perform(post("/juegos")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(juego)))
	                .andExpect(status().isBadRequest()) // Verifica que la respuesta es 400
	                .andExpect(content().string(org.hamcrest.Matchers.containsString("nombre: El nombre no debe ser vacío; ")));
	    }
    
}
