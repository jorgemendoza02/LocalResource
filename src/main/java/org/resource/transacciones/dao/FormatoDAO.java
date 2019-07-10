package org.resource.transacciones.dao;

import org.resource.transacciones.model.Formato;

public interface FormatoDAO {
	
	public Formato listarFormatoEntradaPorNombre(String nombre) throws Exception;


}
