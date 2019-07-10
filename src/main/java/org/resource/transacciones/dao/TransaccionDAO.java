package org.resource.transacciones.dao;

import java.util.List;

import org.resource.transacciones.model.Transaccion;

public interface TransaccionDAO {

	public List<Transaccion> listarTransacciones() throws Exception;

	public List<Transaccion> listarTransaccionesPorClasificacion(String clasificacion, String id_app) throws Exception;

}
