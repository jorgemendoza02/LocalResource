package org.resource.transacciones.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.resource.transacciones.dao.AtributoDAO;
import org.resource.transacciones.dao.Conexion;
import org.resource.transacciones.model.Atributo;

public class AtributoDAOImpl implements AtributoDAO {
	private Connection cx;

	public AtributoDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public List<Atributo> listarAtributo(String id_formato) throws Exception {

		List<Atributo> lstAtributos = new ArrayList<>();
		Statement statement = cx.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM atributo where id_formato = '" + id_formato + "';");

		while (resultSet.next()) {

			Atributo atributo = new Atributo();

			atributo.setNombre(resultSet.getString("dataname"));
			atributo.setDecplaces(resultSet.getString("decpleces"));
			atributo.setDefaultvalue(resultSet.getString("defaultvalue"));
			atributo.setFixedlength(resultSet.getString("fixedlength"));
			atributo.setJustify(resultSet.getString("justify"));
			atributo.setLength(resultSet.getString("length"));
			atributo.setNullcheck(resultSet.getString("nullcheck"));
			atributo.setPadchar(resultSet.getString("padchar"));
			atributo.setSigned(resultSet.getString("signed"));
			atributo.setTipo(resultSet.getString("tipo"));

			lstAtributos.add(atributo);

		}

		resultSet.close();
		statement.close();

		return lstAtributos;

	}

}
