package com.ejemplos.spring.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="juegos")
public class Juego {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idjuego;
	private String nombre;
	private String plataforma;
	private int anyo;
	private String genero;
	private int ideditor;
	private double ventasna;
	private double ventaseu;
	private double ventasjp;
	private double ventasotras;
	private double ventasglobales;
	
	public Juego() {
		super();
	}

	public int getIdjuego() {
		return idjuego;
	}

	public void setIdjuego(int idjuego) {
		this.idjuego = idjuego;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getIdeditor() {
		return ideditor;
	}

	public void setIdeditor(int ideditor) {
		this.ideditor = ideditor;
	}

	public double getVentasna() {
		return ventasna;
	}

	public void setVentasna(double ventasna) {
		this.ventasna = ventasna;
	}

	public double getVentaseu() {
		return ventaseu;
	}

	public void setVentaseu(double ventaseu) {
		this.ventaseu = ventaseu;
	}

	public double getVentasjp() {
		return ventasjp;
	}

	public void setVentasjp(double ventasjp) {
		this.ventasjp = ventasjp;
	}

	public double getVentasotras() {
		return ventasotras;
	}

	public void setVentasotras(double ventasotras) {
		this.ventasotras = ventasotras;
	}

	public double getVentasglobales() {
		return ventasglobales;
	}

	public void setVentasglobales(double ventasglobales) {
		this.ventasglobales = ventasglobales;
	}

	@Override
	public String toString() {
		return "Juego [idjuego=" + idjuego + ", nombre=" + nombre + ", plataforma=" + plataforma + ", anyo=" + anyo
				+ ", genero=" + genero + ", ideditor=" + ideditor + ", ventasna=" + ventasna + ", ventaseu=" + ventaseu
				+ ", ventasjp=" + ventasjp + ", ventasotras=" + ventasotras + ", ventasglobales=" + ventasglobales
				+ "]";
	}
}
