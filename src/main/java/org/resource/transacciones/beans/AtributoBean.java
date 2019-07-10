package org.resource.transacciones.beans;

import java.util.ArrayList;
import java.util.List;

import org.resource.transacciones.dao.impl.AtributoDAOImpl;
import org.resource.transacciones.model.Atributo;
import org.resource.transacciones.model.Formato;

public class AtributoBean {

	private AtributoDAOImpl dao = new AtributoDAOImpl();
	private List<Atributo> lstAtributo = new ArrayList<Atributo>();
	private List<List<Atributo>> lstTotalAtributo = new ArrayList<List<Atributo>>();
	private List<Atributo> lstAtributo_1 = new ArrayList<Atributo>();
	private List<Atributo> lstAtributo_2 = new ArrayList<Atributo>();
	private List<Atributo> lstAtributoFiltro = new ArrayList<Atributo>();
	private List<Atributo> lstAtributoFiltroAcumulado = new ArrayList<Atributo>();
	private List<String> lstAtributoTotal = new ArrayList<String>();
	private ReporteBean reporte = new ReporteBean();
	private String[] resultado;

	public List<List<Atributo>> buscarAtributos(List<Formato> lstFormatos) throws Exception {

		for (Formato formato : lstFormatos) {
			lstAtributo = new ArrayList<Atributo>();
			lstAtributo = dao.listarAtributo(formato.getNombre());
			lstTotalAtributo.add(lstAtributo);
		}

		return lstTotalAtributo;
	}

	public String comparaCadaAtributo(Atributo atributo1, Atributo atributo2) {

		String respuesta = "nook";

		if (atributo1.getDecplaces().equalsIgnoreCase(atributo2.getDecplaces())) {
			if (atributo1.getDefaultvalue().equalsIgnoreCase(atributo2.getDefaultvalue())) {
				if (atributo1.getFixedlength().equalsIgnoreCase(atributo2.getFixedlength())) {
					if (atributo1.getJustify().equalsIgnoreCase(atributo2.getJustify())) {
						if (atributo1.getLength().equalsIgnoreCase(atributo2.getLength())) {
							if (atributo1.getLength().equalsIgnoreCase(atributo2.getLength())) {
								if (atributo1.getNullcheck().equalsIgnoreCase(atributo2.getNullcheck())) {
									if (atributo1.getPadchar().equalsIgnoreCase(atributo2.getPadchar())) {
										if (atributo1.getSigned().equalsIgnoreCase(atributo2.getSigned())) {
											if (atributo1.getTipo().equalsIgnoreCase(atributo2.getTipo())) {
												respuesta = "ok";
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return respuesta;

	}

	public List<Atributo> compara2Listas(List<Atributo> lstAtributo_1, List<Atributo> lstAtributo_2) {

		String todosAtributosIguales;
		Atributo atributo1, atributo2;
		boolean agrego = false;

		for (int n = 0; n <= (lstAtributo_1.size() - 1); n++) {
			atributo1 = lstAtributo_1.get(n);
			String atributoNombre = atributo1.getNombre();

			for (int y = 0; y <= (lstAtributo_2.size() - 1); y++) {
				atributo2 = lstAtributo_2.get(y);
				String atributoNombre2 = atributo2.getNombre();

				if (atributoNombre.equalsIgnoreCase(atributoNombre2)) {

					todosAtributosIguales = comparaCadaAtributo(atributo1, atributo2);

					if (todosAtributosIguales.equalsIgnoreCase("ok")) {
						lstAtributoFiltro = agregarValorLstAtributoFiltro(lstAtributoFiltro, atributo1);
						agrego = true;
						break;
					}

				} else {
					agrego = false;
				}

			}
			if (!agrego) {
				if (!lstAtributoFiltro.isEmpty()) {
					lstAtributoFiltro.remove(atributo1);
				}
			}

		}

		return lstAtributoFiltro;

	}

	public List<Atributo> agregarValorLstAtributoFiltro(List<Atributo> lstAtributoFiltro, Atributo atributo1) {

		boolean encontro = false;

		if (lstAtributoFiltro.isEmpty()) {
			lstAtributoFiltro.add(atributo1);
		} else {

			for (Atributo atributo : lstAtributoFiltro) {
				if (atributo.getNombre().equalsIgnoreCase(atributo1.getNombre())) {
					encontro = true;
					break;
				} else {
					encontro = false;
				}

			}
			if (!encontro) {
				lstAtributoFiltro.add(atributo1);
			}

		}

		return lstAtributoFiltro;
	}

	public String[] resultadoComparacion(List<Atributo> lstAtributoFiltroCopy) {
		String[] respuesta = { "No hay valores iguales" };

		if (!lstAtributoFiltroCopy.isEmpty()) {
			int i = lstAtributoFiltroCopy.size();
			respuesta = new String[i];
			int idx = 0;
			System.out.println("salida:");
			for (Atributo xs : lstAtributoFiltroCopy) {
				respuesta[idx] = xs.getNombre();
				System.out.println(xs.getNombre());
				idx++;
			}
		}

		return respuesta;
	}

	public List<Atributo> guardarArtributosIguales(List<Atributo> lstAtributoFiltro) {

		for (Atributo atributo : lstAtributoFiltro) {

			if (!lstAtributoFiltroAcumulado.contains(atributo)) {
				lstAtributoFiltroAcumulado.add(atributo);
			}

		}

		return lstAtributoFiltroAcumulado;

	}

	public List<String> guadarTodosLosAtributos(List<Atributo> lstAtributo_1) {

		for (Atributo atributo : lstAtributo_1) {
			if (!lstAtributoTotal.contains(atributo.getNombre())) {

				lstAtributoTotal.add(atributo.getNombre());

			}
		}

		return lstAtributoTotal;

	}

	public List<String> borrarAtributosComunes(List<String> lstAtributoTotal,
			List<Atributo> lstAtributoFiltroAcumulado) {
		for (Atributo atributo : lstAtributoFiltroAcumulado) {

			if (lstAtributoTotal.contains(atributo.getNombre())) {

				lstAtributoTotal.remove(atributo.getNombre());

			}
		}
		return lstAtributoTotal;
	}

	public List<String> getLstAtributoTotal() {
		return lstAtributoTotal;
	}

	public String[] compararAtributosFormatos(List<Formato> lstFormatos) throws Exception {

		lstTotalAtributo = new ArrayList<List<Atributo>>();
		lstAtributo_1 = new ArrayList<Atributo>();
		lstAtributo_2 = new ArrayList<Atributo>();
		lstAtributoFiltro = new ArrayList<Atributo>();
		lstAtributoFiltroAcumulado = new ArrayList<Atributo>();

		lstTotalAtributo = buscarAtributos(lstFormatos);
		for (int i = 0; i <= (lstTotalAtributo.size() - 1); i++) {

			int w = i + 1;

			if (w < lstTotalAtributo.size()) {

				if (lstAtributoFiltroAcumulado.isEmpty()) {

					lstAtributo_1 = lstTotalAtributo.get(i);
					lstAtributo_2 = lstTotalAtributo.get((i + 1));

					lstAtributoFiltro = compara2Listas(lstAtributo_1, lstAtributo_2);

					lstAtributoFiltroAcumulado = guardarArtributosIguales(lstAtributoFiltro);

					lstAtributoTotal = guadarTodosLosAtributos(lstAtributo_1); // NEW

					lstAtributoTotal = guadarTodosLosAtributos(lstAtributo_2); // NEW

					if (lstAtributoFiltroAcumulado.isEmpty()) {
						// los 2 primeros lstAtributo no tiene ningún atributo en común
						break;
					}

					lstAtributoTotal = borrarAtributosComunes(lstAtributoTotal, lstAtributoFiltroAcumulado);

				} else {

					lstAtributo_2 = lstTotalAtributo.get((i + 1));
					lstAtributoFiltroAcumulado = compara2Listas(lstAtributoFiltroAcumulado, lstAtributo_2);

					lstAtributoTotal = guadarTodosLosAtributos(lstAtributo_2); // NEW

					if (lstAtributoFiltroAcumulado.isEmpty()) {
						break;
					}

					lstAtributoTotal = borrarAtributosComunes(lstAtributoTotal, lstAtributoFiltroAcumulado);

				}

			}

		}

		// reporte.generateExcel(lstTotalAtributo);
		resultado = resultadoComparacion(lstAtributoFiltroAcumulado);

		return resultado;
	}

}
