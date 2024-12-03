package com.ejemplos.spring.service;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.ejemplos.spring.controller.JuegosController;
import com.ejemplos.spring.controller.error.JuegoExistsException;
import com.ejemplos.spring.model.Editor;
import com.ejemplos.spring.model.Juego;
import com.ejemplos.spring.repository.JuegosDAO;
import com.ejemplos.spring.service.JuegosService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.SelectorResolutionResult.Status;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class EditarApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private JuegosServiceImpl juegoService;

    @Mock
    private JuegosDAO juegoRepository;  // Si necesitas mockear el repositorio

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks correctamente
    }
	
	/*@Test
	void datosInsertadosCorrectamenteTest() 
	{    
		when(juegoRepository.saveAll(Collections.emptyList())).thenReturn(Collections.singletonList(new Juego()));  
		when(juegoRepository.findAll()).thenReturn(Collections.emptyList());  
		juegoService.rellenarBBDD();   
		verify(juegoRepository, times(1)).saveAll(anyList());
	}
	
	@Test void datosNoSeInsertanSiYaExistenTest() 
	{    
		when(juegoRepository.findAll()).thenReturn(Collections.singletonList(new Juego()));   
		when(juegoRepository.saveAll(Collections.emptyList())).thenReturn(Collections.singletonList(new Juego())); 
		juegoService.rellenarBBDD();    verify(juegoRepository, never()).saveAll(anyList());
	}*/
	
	@Test void saveJuegoCorrectamente() {
		
		Juego juego1 = new Juego("Juego1", "Wii", 2000, "Plataformas", new Editor("Nintendo"), 100, 100, 100, 100, 100);    
		
		when(juegoRepository.save(juego1)).thenReturn(juego1);    
		Juego resultado = juegoService.saveJuego(juego1);    
		Assertions.assertNotNull(resultado);  
		Assertions.assertEquals("Super Mario Bros", resultado.getNombre());   
		verify(juegoRepository, times(1)).save(juego1);
		
	}
    
}
