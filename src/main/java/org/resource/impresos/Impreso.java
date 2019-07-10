package org.resource.impresos;

public class Impreso {
	private String ventana;
	private String titulo;
	private String nombre;
	private String parametro;
	private String tipo;
	private String grilla;

	public Impreso(String ventana, String titulo, String nombre, String parametro, String tipo, String grilla) {
		super();
		this.ventana = ventana;
		this.titulo = titulo;
		this.nombre = nombre;
		this.parametro = parametro;
		this.tipo = tipo;
		this.grilla = grilla;
	}

	public String getGrilla() {
		return grilla;
	}

	public void setGrilla(String grilla) {
		this.grilla = grilla;
	}

	public String getVentana() {
		return ventana;
	}

	public void setVentana(String ventana) {
		this.ventana = ventana;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
