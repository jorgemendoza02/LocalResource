package org.resource.transacciones.dao;

import java.util.List;

import org.resource.transacciones.model.Clasificacion;

public interface ClasificacionDAO {

	public List<Clasificacion> listarClasificaciones() throws Exception;

}
