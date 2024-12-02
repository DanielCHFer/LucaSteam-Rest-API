package com.ejemplos.spring.service;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplos.spring.model.Juego;
import com.ejemplos.spring.repository.JuegosDAO;

@Service
public class JuegosServiceImpl implements JuegosService{

	@Autowired
	private JuegosDAO juegosDao;
	
	@Override
	public List<Juego> findAll() {
		return juegosDao.findAll();
	}

	
	//
	/**
	 * Adrian: Crear readJuegos() de Servicios devuelve List<Juego> para cargar del csv.
	 */
	@Override
	public List<Juego> readJuegos() {

		String fichero = "src/main/resources/vgsales.csv";
		String linea;
		String [] datosCSV = null;
		List<Juego> juegosCSV = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fichero));
			
			//desechamos la primera linea porque son los nombres de las columnas
			linea = reader.readLine();
			
			while((linea = reader.readLine()) != null) {
				//cargamos los datos de las distintas columnas en este array
				 datosCSV = linea.split(",");
				if(datosCSV[3].equals("N/A"))
					datosCSV[3]="0";

				Juego j = new Juego(Integer.parseInt(datosCSV[0]),datosCSV[1],datosCSV[2],Integer.parseInt(datosCSV[3]),
						datosCSV[4],Double.parseDouble(datosCSV[6]),Double.parseDouble(datosCSV[7]),Double.parseDouble(datosCSV[8]),
						Double.parseDouble(datosCSV[9]),Double.parseDouble(datosCSV[10]));	
				juegosCSV.add(j);
				System.out.println(j);
			}
			
		} catch (FileNotFoundException e) {
			//logger.error("Fichero CSV no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			//logger.error("Excepcion de Entrada/Salida");			
			e.printStackTrace();
		}catch (NumberFormatException e) {
			System.out.println(datosCSV[1]);
		}
		
		for(Juego j: juegosCSV)
			saveJuego(j);
		
		return juegosCSV;

	}

}
