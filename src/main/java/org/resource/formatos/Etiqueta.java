package org.resource.formatos;

import java.util.ArrayList;
import java.util.List;

public class Etiqueta {

	private String nombre;
	private List<String> lstAtributos = new ArrayList<>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getLstAtributos() {
		return lstAtributos;
	}

	public void setLstAtributos(List<String> lstAtributos) {
		this.lstAtributos = lstAtributos;
	}

}
