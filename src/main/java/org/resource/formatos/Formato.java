package org.resource.formatos;

import java.util.List;

public class Formato {

	private String nombre;
	private List<Etiqueta> etiqueta;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Etiqueta> getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(List<Etiqueta> etiqueta) {
		this.etiqueta = etiqueta;
	}

}
