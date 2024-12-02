package com.ejemplos.spring.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="juegos")
public class Juego {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idjuego;
	
	//tiene que ser mayor que 3 y menor que 30
	@NotEmpty(message = "El nombre no debe ser vacío")
	@NotNull
	private String nombre;
	@NotEmpty(message = "la plataforma no puede estar vacia")
	private String plataforma;
	@Positive(message ="no se puede introducir un anyo negativo")
	private int anyo;
	@NotEmpty(message = "El genero no puede estar vacio")
	private String genero;
	
	@ManyToOne()
	@JoinColumn(name="ideditor", referencedColumnName="ideditor")
	private Editor editor;
	
	private double ventasna;
	private double ventaseu;
	private double ventasjp;
	private double ventasotras;
	private double ventasglobales;
	
	public Juego() {
		super();
	}



	public Juego(String nombre, String plataforma, int anyo, String genero, Editor editor, double ventasna,
			double ventaseu, double ventasjp, double ventasotras, double ventasglobales) {
		super();
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.anyo = anyo;
		this.genero = genero;
		this.editor = editor;
		this.ventasna = ventasna;
		this.ventaseu = ventaseu;
		this.ventasjp = ventasjp;
		this.ventasotras = ventasotras;
		this.ventasglobales = ventasglobales;
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

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
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
				+ ", genero=" + genero + ", editor=" + editor + ", ventasna=" + ventasna + ", ventaseu=" + ventaseu
				+ ", ventasjp=" + ventasjp + ", ventasotras=" + ventasotras + ", ventasglobales=" + ventasglobales
				+ "]";
	}

}
