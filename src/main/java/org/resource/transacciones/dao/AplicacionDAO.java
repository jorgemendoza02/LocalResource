package org.resource.transacciones.dao;

import java.util.List;

import org.resource.transacciones.model.Aplicacion;

public interface AplicacionDAO {

	public List<Aplicacion> listarAplicaciones() throws Exception;

}
