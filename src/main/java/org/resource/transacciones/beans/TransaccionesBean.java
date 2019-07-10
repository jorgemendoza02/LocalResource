package org.resource.transacciones.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.resource.transacciones.dao.impl.AplicacionDAOImpl;
import org.resource.transacciones.dao.impl.AtributoDAOImpl;
import org.resource.transacciones.dao.impl.ClasificacionDAOImpl;
import org.resource.transacciones.dao.impl.FormatoDAOImpl;
import org.resource.transacciones.dao.impl.TransaccionDAOImpl;
import org.resource.transacciones.model.Aplicacion;
import org.resource.transacciones.model.Atributo;
import org.resource.transacciones.model.Clasificacion;
import org.resource.transacciones.model.Formato;
import org.resource.transacciones.model.Transaccion;

@ManagedBean
@ViewScoped
public class TransaccionesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();

	private String transac;

	private String clasif;
	private Map<String, String> clasifnes;
	private Map<String, String> transacciones;

	private String app;
	private Map<String, String> aplicacione;
	private Map<Integer, String> aplicacione2;
	private Integer aplicacions;

	private TransaccionDAOImpl daoTransaccion = new TransaccionDAOImpl();
	private ClasificacionDAOImpl daoClasificacion = new ClasificacionDAOImpl();
	private AplicacionDAOImpl daoAplicacion = new AplicacionDAOImpl();
	private FormatoDAOImpl daoFormato = new FormatoDAOImpl();
	private AtributoDAOImpl daoAtributo = new AtributoDAOImpl();
	private AtributoBean atributo = new AtributoBean();
	private List<Atributo> lstAtributo = new ArrayList<Atributo>();
	private List<Transaccion> lstTransaccion = new ArrayList<Transaccion>();
	private List<Clasificacion> lstClasificacion = new ArrayList<Clasificacion>();
	private List<Aplicacion> lstAplicaciones = new ArrayList<Aplicacion>();
	private List<Formato> lstFormatos = new ArrayList<Formato>();
	private String[] selectedTransaccion;
	private String[] salida;
	private List<String> salidaNoComunes;
	private String out = "";

	@PostConstruct
	public void init() throws Exception {

		clasifnes = new HashMap<String, String>();
		aplicacione = new HashMap<String, String>();
		aplicacione2 = new HashMap<Integer, String>();
		lstClasificacion = daoClasificacion.listarClasificaciones();
		lstAplicaciones = daoAplicacion.listarAplicaciones();

		for (Clasificacion clasificacion : lstClasificacion) {
			// lstClasificacionNombre.add(clasificacion.getNombre());
			clasifnes.put(clasificacion.getDescripcion(), clasificacion.getNombre());
		}

		for (Aplicacion aplicacion : lstAplicaciones) {

			aplicacione.put(aplicacion.getDescripcion(), aplicacion.getCodigo());
			aplicacione2.put(aplicacion.getId_aplicacion(), aplicacion.getDescripcion());
		}

	}
	
	public void onAppChange() throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
			lstTransaccion = daoTransaccion.listarTransaccionesPorClasificacion("A", app);
		for (Transaccion transaccion : lstTransaccion) {
			map.put(transaccion.getNombre(), transaccion.getNombre());
		}
		data.put("A", map);

		map = new HashMap<String, String>();
		lstTransaccion = daoTransaccion.listarTransaccionesPorClasificacion("B", app);
		for (Transaccion transaccion : lstTransaccion) {
			map.put(transaccion.getNombre(), transaccion.getNombre());
		}
		data.put("B", map);

		map = new HashMap<String, String>();
		lstTransaccion = daoTransaccion.listarTransaccionesPorClasificacion("M", app);
		for (Transaccion transaccion : lstTransaccion) {
			map.put(transaccion.getNombre(), transaccion.getNombre());
		}
		data.put("M", map);

		map = new HashMap<String, String>();
		lstTransaccion = daoTransaccion.listarTransaccionesPorClasificacion("C", app);
		for (Transaccion transaccion : lstTransaccion) {
			map.put(transaccion.getNombre(), transaccion.getNombre());
		}
		data.put("C", map);

		map = new HashMap<String, String>();
		lstTransaccion = daoTransaccion.listarTransaccionesPorClasificacion("CS", app);
		for (Transaccion transaccion : lstTransaccion) {
			map.put(transaccion.getNombre(), transaccion.getNombre());
		}
		data.put("S", map);

		map = new HashMap<String, String>();
		lstTransaccion = daoTransaccion.listarTransaccionesPorClasificacion("MT", app);
		for (Transaccion transaccion : lstTransaccion) {
			map.put(transaccion.getNombre(), transaccion.getNombre());
		}
		data.put("MT", map);
	}

	public void onClasifChange() throws Exception {
		if (clasif != null && !clasif.equals("")) {
			transacciones = data.get(clasif);
		} else {
			transacciones = new HashMap<String, String>();
		}

		

	}

	public String[] getSelectedTransaccion() throws Exception {
		return selectedTransaccion;
	}

	public void setSelectedTransaccion(String[] selectedTransaccion) throws Exception {
		this.selectedTransaccion = selectedTransaccion;
		if (selectedTransaccion != null) {
			if (selectedTransaccion.length > 0) {
				int n = 0;
				for (String nombre : selectedTransaccion) {
					Formato formato = daoFormato.listarFormatoEntradaPorNombre(nombre);
					lstFormatos.add(formato);
					lstAtributo = daoAtributo.listarAtributo(formato.getNombre());
					this.selectedTransaccion[n] = nombre + " - Formato de Entrada: " + formato.getNombre() + " - "
							+ lstAtributo.size() + " Atributos.";
					n++;
				}
				salida = atributo.compararAtributosFormatos(lstFormatos);
				salidaNoComunes = atributo.getLstAtributoTotal();
				for (String salida : salidaNoComunes) {
					out = out + "-" + salida;
				}
			}

		}
	}

	public String getClasif() {
		return clasif;
	}

	public void setClasif(String clasif) {
		this.clasif = clasif;
	}

	public Map<String, String> getClasifnes() {
		return clasifnes;
	}

	public void setClasifnes(Map<String, String> clasifnes) {
		this.clasifnes = clasifnes;
	}

	public Map<String, String> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Map<String, String> transacciones) {
		this.transacciones = transacciones;
	}

	public Map<String, Map<String, String>> getData() {
		return data;
	}

	public Integer getAplicacions() {
		return aplicacions;
	}

	public void setAplicacions(Integer aplicacions) {
		this.aplicacions = aplicacions;
	}

	public void setLstTransaccion(List<Transaccion> lstTransaccion) {
		this.lstTransaccion = lstTransaccion;
	}

	public String[] getSalida() {
		return salida;
	}

	public void setSalida(String[] salida) {
		this.salida = salida;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public Map<String, String> getAplicacione() {
		return aplicacione;
	}

	public void setAplicacione(Map<String, String> aplicacione) {
		this.aplicacione = aplicacione;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public void displayLocation() {
		FacesMessage msg;
		if (transac != null && clasif != null)
			msg = new FacesMessage("Selected", transac + " of " + clasif);
		else
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "transac is not selected.");

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}