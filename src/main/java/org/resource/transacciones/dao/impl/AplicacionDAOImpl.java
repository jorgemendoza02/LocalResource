package org.resource.transacciones.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.resource.transacciones.dao.AplicacionDAO;
import org.resource.transacciones.dao.Conexion;
import org.resource.transacciones.model.Aplicacion;

public class AplicacionDAOImpl implements AplicacionDAO {

	private Connection cx;

	public AplicacionDAOImpl() {
		cx = Conexion.conectar();
	}

	public List<Aplicacion> listarAplicaciones() throws Exception {
		List<Aplicacion> lstAplicacion = new ArrayList<Aplicacion>();
		String query = "select * from aplicacion order by descripcion asc;";
		PreparedStatement preparedStatement = cx.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {

			Aplicacion aplicacion = new Aplicacion();
			aplicacion.setDescripcion(resultSet.getString("descripcion"));
			aplicacion.setCodigo(resultSet.getString("id_aplicacion"));

			lstAplicacion.add(aplicacion);
		}
		resultSet.close();
		preparedStatement.close();
		return lstAplicacion;
	}
}
