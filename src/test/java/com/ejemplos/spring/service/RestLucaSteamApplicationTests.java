package com.ejemplos.spring.service;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import com.ejemplos.spring.controller.JuegosController;
import com.ejemplos.spring.controller.error.JuegoExistsException;
import com.ejemplos.spring.model.Editor;
import com.ejemplos.spring.model.Juego;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

		// creo un juego
		Juego juego = new Juego();
		juego.setNombre("capo");
		juego.setPlataforma("Plataforma");
		juego.setAnyo(1980);
		juego.setGenero("Genero");
		juego.setVentasna(100);
		juego.setVentaseu(100);
		juego.setVentasjp(100);
		juego.setVentasotras(100);
		juego.setVentasglobales(100);

		List<Juego> juegos = Arrays.asList(juego);

		when(juegoService.findAll()).thenReturn(juegos);

		// Realiza la solicitud GET y verifica la respuesta
		mockMvc.perform(get("/juegos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0].nombre").value("capo"));
	}

	@Test
	public void testObtenerJuegos() throws Exception {
		// Crear juegos con distintos años
		Juego juego1 = new Juego("Juego1", "Wii", 2000, "Plataformas", new Editor("Nintendo"), 100, 100, 100, 100, 100);
		Juego juego2 = new Juego("Juego2", "Wii", 1999, "Plataformas", new Editor("Nintendo"), 100, 100, 100, 100, 100);
		Juego juego3 = new Juego("Juego3", "Wii", 1999, "Plataformas", new Editor("Nintendo"), 100, 100, 100, 100, 100);
		Juego juego4 = new Juego("Juego4", "Wii", 1999, "Plataformas", new Editor("Nintendo"), 100, 100, 100, 100, 100);

		// Crear lista de todos los juegos
		List<Juego> juegosTotales = Arrays.asList(juego1, juego2, juego3, juego4);

		// Simular que el servicio devuelve una lista de juegos del año 1999
		when(juegoService.findByAnyo(1999)).thenReturn(Arrays.asList(juego2, juego3, juego4));

		// Realizar la solicitud GET para obtener juegos del año 1999
		mockMvc.perform(get("/juegos/anyo/1999").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()) // Verifica
																														// que
																														// la
																														// respuesta
																														// es
																														// 200
																														// OK
				.andExpect(jsonPath("$.length()").value(3)) // Verifica que la lista tiene 3 elementos (solo los juegos
															// de 1999)
				.andExpect(jsonPath("$[0].nombre").value("Juego2")) // Verifica el nombre del primer juego
				.andExpect(jsonPath("$[1].nombre").value("Juego3")) // Verifica el nombre del segundo juego
				.andExpect(jsonPath("$[2].nombre").value("Juego4")); // Verifica el nombre del tercer juego
	}

	@Test
	void testJuegoUpdateException() throws Exception {
		// Dado un juego repetido se debería lanzar la excepción JuegoExistsException
		Juego juego = new Juego("Juego1", "Wii", 2000, "Plataformas", new Editor("Nintendo"), 100, 100, 100, 100, 100);
		String json = objectMapper.writeValueAsString(juego);

		// Simula que el servicio lanza la excepción JuegoExistsException
		when(juegoService.findByNombre("Juego1")).thenThrow(JuegoExistsException.class);

		mockMvc.perform(
				post("/juegos").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(juego)))
				.andExpect(status().isOk()).andExpect(content().string("El juego ya existe"));
	}

}
