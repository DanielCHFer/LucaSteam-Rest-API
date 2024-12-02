package com.ejemplos.spring;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import com.ejemplos.spring.controller.JuegosController;
import com.ejemplos.spring.controller.error.JuegoExistsException;
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
class RestLucaSteamApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JuegosService juegoService; // Simula el servicio inyectado en el controlador

    @Test
    void testListJuegosDevuelveLista() throws Exception {
    	//Dada una solicitud GET deberian devolverse una lista de los juegos almacenados
    	// Verifica que los juegos están almacenados en la base de datos
    	List<Juego> juegos = juegoService.findAll();

        // Realiza la solicitud GET y verifica la respuesta
        mockMvc.perform(get("/juegos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    
    @Test
    void testJuegoExistsException() throws Exception {
        // Dado un juego repetido se debería lanzar la excepción JuegoExistsException
        Juego juego = new Juego();
        juego.setIdjuego(1);
        juego.setNombre("JuegoExistente");
        String json = objectMapper.writeValueAsString(juego);

        // Simula que el servicio lanza la excepción JuegoExistsException
       // when(juegoService.findByNombre("JuegoExistente")).thenThrow(JuegoExistsException.class);

        // Realiza la solicitud POST y verifica la respuesta
        mockMvc.perform(put("/juegos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        		.andExpect(status().isNotFound());
        		
    }
    
}
