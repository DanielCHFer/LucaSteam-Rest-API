package com.ejemplos.spring;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import com.ejemplos.spring.controller.JuegosController;
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
}
