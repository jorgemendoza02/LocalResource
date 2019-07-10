package org.resource.transacciones.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.resource.transacciones.dao.ClasificacionDAO;
import org.resource.transacciones.dao.Conexion;
import org.resource.transacciones.model.Clasificacion;

public class ClasificacionDAOImpl implements ClasificacionDAO {
	private Connection cx;

	public ClasificacionDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public List<Clasificacion> listarClasificaciones() throws Exception {
		List<Clasificacion> lstClasificacion = new ArrayList<Clasificacion>();
		String query = "SELECT id_clasificacion, nombre, descripcion FROM clasificacion WHERE id_clasificacion <> 4 and id_clasificacion <> 5  order by nombre asc;";
		PreparedStatement preparedStatement = cx.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Clasificacion clasificacion = new Clasificacion();
			clasificacion.setNombre(resultSet.getString("nombre"));
			clasificacion.setDescripcion(resultSet.getString("descripcion"));
			lstClasificacion.add(clasificacion);
		}
		resultSet.close();
		preparedStatement.close();
		return lstClasificacion;
	}

}
