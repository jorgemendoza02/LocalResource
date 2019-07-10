package org.resource.transacciones.model;

public class Atributo {

	private String nombre;
	private String tipo;
	private String decplaces;
	private String length;
	private String signed;
	private String defaultvalue;
	private String padchar;
	private String justify;
	private String nullcheck;
	private String fixedlength;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDecplaces() {
		return decplaces;
	}

	public void setDecplaces(String decplaces) {
		this.decplaces = decplaces;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getSigned() {
		return signed;
	}

	public void setSigned(String signed) {
		this.signed = signed;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getPadchar() {
		return padchar;
	}

	public void setPadchar(String padchar) {
		this.padchar = padchar;
	}

	public String getJustify() {
		return justify;
	}

	public void setJustify(String justify) {
		this.justify = justify;
	}

	public String getNullcheck() {
		return nullcheck;
	}

	public void setNullcheck(String nullcheck) {
		this.nullcheck = nullcheck;
	}

	public String getFixedlength() {
		return fixedlength;
	}

	public void setFixedlength(String fixedlength) {
		this.fixedlength = fixedlength;
	}

}