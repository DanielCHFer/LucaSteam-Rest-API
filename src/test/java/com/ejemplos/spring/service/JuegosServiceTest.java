package com.ejemplos.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.ejemplos.spring.model.Editor;
import com.ejemplos.spring.model.Juego;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ejemplos.spring.repository.EditoresDAO;
import com.ejemplos.spring.repository.JuegosDAO;

@SpringBootTest
public class JuegosServiceTest {

	@Mock
	JuegosDAO juegoRepository;

	@Mock
	EditoresDAO editorRepository;

	@InjectMocks
	JuegosServiceImpl juegoService;

	@Test
	void testSeInsertaJuego() {
		when(juegoRepository.save(any(Juego.class))).thenReturn(new Juego());
		juegoService.saveJuego(new Juego());
		verify(juegoRepository, times(1)).save((any(Juego.class)));
	}

	@Test
	void testGuardarJuego() {
		Juego j = new Juego("Hollow Knight", "Switch", 2016, "Metroidvania", new Editor("Team Cherry"), 200, 200, 200,
				200, 200);
		when(juegoRepository.save(any(Juego.class))).thenReturn(j);
		Juego resultado = juegoService.saveJuego(j);
		assertEquals("Hollow Knight", resultado.getNombre());
		verify(juegoRepository, times(1)).save(j);
	}

	@Test
	void testGuardarJuegoRepetido() {
		Juego j = new Juego("Hollow Knight", "Switch", 2016, "Metroidvania", new Editor("Team Cherry"), 200, 200, 200,
				200, 200);
		Juego j2 = new Juego("Hollow Knight", "Switch", 2016, "Metroidvania", new Editor("Team Cherry"), 200, 200, 200,
				200, 200);
		when(juegoRepository.save(any(Juego.class))).thenReturn(j);
		juegoService.saveJuego(j);
		juegoService.saveJuego(j2);
		verify(juegoRepository, times(1)).save(j2);
	}

	@Test
	void testComprobarNombre() {
		Juego j = new Juego("Hollow Knight", "Switch", 2016, "Metroidvania", new Editor("Team Cherry"), 200, 200, 200,
				200, 200);
		juegoService.saveJuego(j);
		when(juegoRepository.findByNombre("Hollow Knight")).thenReturn(Optional.ofNullable(j));
		Juego resultado = juegoService.findByNombre("Hollow Knight").get();
		assertEquals("Hollow Knight", resultado.getNombre());
		verify(juegoRepository, times(1)).findByNombre(j.getNombre());
	}

	@Test
	void testEncontrarTotalElementos() {
		Juego j = new Juego("Hollow Knight", "Switch", 2016, "Metroidvania", new Editor("Team Cherry"), 200, 200, 200,
				200, 200);
		Juego j2 = new Juego("Mouthwashing", "Switch", 2024, "Horror", new Editor("Wrong Organ"), 200, 200, 200, 200,
				200);
		juegoService.saveJuego(j);
		juegoService.saveJuego(j2);
		when(juegoRepository.findAll()).thenReturn(Arrays.asList(j, j2));

		List<Juego> listaResultado = juegoService.findAll();
		assertEquals(2, listaResultado.size());
		assertEquals("Mouthwashing", listaResultado.get(1).getNombre());
		verify(juegoRepository, times(1)).save(j);
		verify(juegoRepository, times(1)).save(j2);
	}

	@Test
	void testEditarJuego() {
		Juego j = new Juego("Hollow Knight", "Switch", 2016, "Metroidvania", new Editor("Team Cherry"), 200, 200, 200,
				200, 200);
		juegoService.saveJuego(j);
		when(juegoRepository.save(j)).thenReturn(j);
		juegoService.updateJuego(j);
		verify(juegoRepository, times(1)).save(j);
	}

	@Test
	void testEditarJuegoInexistente() {
		Juego j = new Juego("Hollow Knight", "Switch", 2016, "Metroidvania", new Editor("Team Cherry"), 200, 200, 200,
				200, 200);
		juegoService.saveJuego(j);
		when(juegoRepository.save(j)).thenReturn(j);
		Juego jsinguardar = new Juego("Mouthwashing", "Switch", 2024, "Horror", new Editor("Wrong Organ"), 200, 200,
				200, 200, 200);
		juegoService.updateJuego(jsinguardar);
		verify(juegoRepository, times(0)).save(jsinguardar);
	}

}
