package com.ejemplos.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="editores")
public class Editor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ideditor;
	private String nombre;
	
	public Editor() {
		super();
	}
	
	public Editor(String nombre) {
		this.nombre = nombre;
	}

	public int getIdeditor() {
		return ideditor;
	}

	public void setIdeditor(int ideditor) {
		this.ideditor = ideditor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Editor [ideditor=" + ideditor + ", nombre=" + nombre + "]";
	}
}
