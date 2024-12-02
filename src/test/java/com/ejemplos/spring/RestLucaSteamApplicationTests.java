package com.ejemplos.spring;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import com.ejemplos.spring.controller.JuegosController;
import com.ejemplos.spring.controller.error.JuegoExistsException;
import com.ejemplos.spring.model.Juego;
import com.ejemplos.spring.service.JuegosService;
import org.junit.jupiter.api.Test;
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

    @MockBean
    private JuegosService juegoService; // Simula el servicio inyectado en el controlador

    @Test
    void testListJuegosDevuelveLista() throws Exception {
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
        // Dado un juego con un nombre que debería lanzar la excepción JuegoExistsException
        Juego juego = new Juego();
        juego.setIdjuego(1);
        juego.setNombre("JuegoExistente");

        // Simula que el servicio lanza la excepción JuegoExistsException
        when(juegoService.findByNombre("JuegoExistente")).thenThrow(JuegoExistsException.class);

        // Realiza la solicitud GET y verifica la respuesta
        mockMvc.perform(get("/juegos/cargar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict()); // Se espera un error 409 (Conflict) al lanzar la excepción
    }
    
}
