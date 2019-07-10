package org.resource.transacciones.dao;

import java.util.List;

import org.resource.transacciones.model.Atributo;

public interface AtributoDAO {

	public List<Atributo> listarAtributo(String id_formato) throws Exception;

}
