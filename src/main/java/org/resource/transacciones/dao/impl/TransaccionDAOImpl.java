package org.resource.transacciones.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.resource.transacciones.dao.Conexion;
import org.resource.transacciones.dao.TransaccionDAO;
import org.resource.transacciones.model.Transaccion;

public class TransaccionDAOImpl implements TransaccionDAO {

	private Connection cx;

	public TransaccionDAOImpl() {
		cx = Conexion.conectar();
	}

	public List<Transaccion> listarTransacciones() throws Exception {
		List<Transaccion> lstTransacciones = new ArrayList<Transaccion>();
		Statement statement = cx.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT nombre FROM public.transaccion ORDER BY id_transaccion;");
		while (resultSet.next()) {
			Transaccion transaccion = new Transaccion();
			transaccion.setNombre(resultSet.getString("id_transaccion"));
			lstTransacciones.add(transaccion);
		}
		resultSet.close();
		statement.close();

		return lstTransacciones;
	}

	public List<Transaccion> listarTransaccionesPorClasificacion(String clasificacion, String id_app) throws Exception {
		List<Transaccion> lstTransacciones = new ArrayList<Transaccion>();
		Statement statement = cx.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"SELECT transaccion.id_transaccion FROM public.transaccion inner join clasificacion on clasificacion.id_clasificacion = transaccion.id_clasificacion where clasificacion.nombre like '%"
						+ clasificacion + "%'and id_aplicacion ='" + id_app + "';");
		while (resultSet.next()) {
			Transaccion transaccion = new Transaccion();
			transaccion.setNombre(resultSet.getString("id_transaccion"));
			lstTransacciones.add(transaccion);
		}
		resultSet.close();
		statement.close();

		return lstTransacciones;
	}

}
