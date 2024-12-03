package com.ejemplos.spring.service;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplos.spring.model.Editor;
import com.ejemplos.spring.model.Juego;
import com.ejemplos.spring.repository.EditoresDAO;
import com.ejemplos.spring.repository.JuegosDAO;

@Service
public class JuegosServiceImpl implements JuegosService {

	@Autowired
	private JuegosDAO juegosDao;

	@Autowired
	private EditoresDAO editoresDao;

	@Override
	public List<Juego> findAll() {
		return juegosDao.findAll();
	}

	//
	/**
	 * Adrian: Crear readJuegos() de Servicios devuelve List<Juego> para cargar del
	 * csv.
	 */
	@Override
	public List<Juego> readJuegos() {

		String fichero = "src/main/resources/vgsales.csv";
		String linea;
		String[] datosCSV = null;
		List<Juego> juegosCSV = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fichero));

			// desechamos la primera linea porque son los nombres de las columnas
			linea = reader.readLine();

			while ((linea = reader.readLine()) != null) {
				// cargamos los datos de las distintas columnas en este array
				datosCSV = linea.split(",");
				if (datosCSV[3].equals("N/A"))
					datosCSV[3] = "0";

				Juego j = new Juego(datosCSV[1], datosCSV[2], Integer.parseInt(datosCSV[3]), datosCSV[4],
						new Editor(datosCSV[5]), Double.parseDouble(datosCSV[6]), Double.parseDouble(datosCSV[7]),
						Double.parseDouble(datosCSV[8]), Double.parseDouble(datosCSV[9]),
						Double.parseDouble(datosCSV[10]));

				juegosCSV.add(j);
				System.out.println(j);
			}

			reader.close();
			
		} catch (FileNotFoundException e) {
			// logger.error("Fichero CSV no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			// logger.error("Excepcion de Entrada/Salida");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(datosCSV[1]);
		}

		for (Juego j : juegosCSV)
			saveJuego(j);

		return juegosCSV;

	}

	@Override
	public Juego saveJuego(Juego juego) {
		Editor editor = juego.getEditor();

		if (editor != null) {
			Optional<Editor> existingEditor = editoresDao.findByNombre(juego.getEditor().getNombre());

			if (!existingEditor.isPresent()) {
				editoresDao.save(editor); // Persistir el nuevo editor
			} else {
				juego.setEditor(existingEditor.get()); // Usar el editor existente
			}
		}
		return juegosDao.save(juego);
	}

	@Override
	public Editor saveEditor(Editor editor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Juego> findByNombre(String nombre) {
		return juegosDao.findByNombre(nombre);
	}

	@Override
	public Optional<Juego> updateJuego(Juego juego) {
		Optional<Juego> juegoActual = juegosDao.findById(juego.getIdjuego());

		if (juegoActual.isPresent())
			juegosDao.save(juego);

		return juegoActual;
	}

	@Override
	public List<Juego> findByAnyo(int anyo) {
		return juegosDao.findByAnyo(anyo);
	}

	@Override
	public Optional<Juego> findById(int id) {
		return juegosDao.findById(id);
	}

	@Override
	public Juego deleteJuego(Juego j) {

		juegosDao.delete(j);

		return j;
	}

}
