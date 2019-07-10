package org.resource.transacciones;

public class Parametro {

	private String tipo;
	private String dataName;
	private String decPlaces;
	private String length;
	private String signed;
	private String defaultValue;
	private String padChar;
	private String justify;
	private String nullCheck;
	private String fixedLength;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDecPlaces() {
		return decPlaces;
	}

	public void setDecPlaces(String decPlaces) {
		this.decPlaces = decPlaces;
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

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getPadChar() {
		return padChar;
	}

	public void setPadChar(String padChar) {
		this.padChar = padChar;
	}

	public String getJustify() {
		return justify;
	}

	public void setJustify(String justify) {
		this.justify = justify;
	}

	public String getNullCheck() {
		return nullCheck;
	}

	public void setNullCheck(String nullCheck) {
		this.nullCheck = nullCheck;
	}

	public String getFixedLength() {
		return fixedLength;
	}

	public void setFixedLength(String fixedLength) {
		this.fixedLength = fixedLength;
	}

}
