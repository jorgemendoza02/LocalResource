package org.resource.transacciones.model;

import java.util.List;

public class Formato {

	private String nombre;
	private String tipo;
	private List<Atributo> lstAtributos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Atributo> getLstAtributos() {
		return lstAtributos;
	}

	public void setLstAtributos(List<Atributo> lstAtributos) {
		this.lstAtributos = lstAtributos;
	}

}
