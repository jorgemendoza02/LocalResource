package org.resource.transacciones.model;

import java.util.List;

public class Transaccion {

	private String nombre;
	private String aplicacion;
	private String programa;
	private String descripcion;
	private List<Formato> lstFormato;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Formato> getLstFormato() {
		return lstFormato;
	}

	public void setLstFormato(List<Formato> lstFormato) {
		this.lstFormato = lstFormato;
	}

}
