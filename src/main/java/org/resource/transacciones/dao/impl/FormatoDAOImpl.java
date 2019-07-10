package org.resource.transacciones.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.resource.transacciones.dao.Conexion;
import org.resource.transacciones.dao.FormatoDAO;
import org.resource.transacciones.model.Formato;

public class FormatoDAOImpl implements FormatoDAO {
	private Connection cx;

	public FormatoDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public Formato listarFormatoEntradaPorNombre(String nombre) throws Exception {
		Formato formato = new Formato();
		String query = "SELECT * FROM formato WHERE (id_transaccion = '" + nombre + "') AND (tipo = 'A' OR tipo = 'E');";
		PreparedStatement preparedStatement = cx.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			formato.setNombre(resultSet.getString("id_formato"));
		}
		resultSet.close();
		preparedStatement.close();
		return formato;
	}

}
