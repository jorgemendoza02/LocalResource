package org.resource.transacciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.resource.formatos.Formatos;

public class Transacciones {

	public static String searchTipo(String line) throws FileNotFoundException {

		String searchTipoNumerico = "fNumericPS7";
		String searchTipoString = "fString";
		String searchTipoFecha = "fDatePS7";

		if (line.contains(searchTipoNumerico)) {
			return searchTipoNumerico;
		} else if (line.contains(searchTipoString)) {
			return searchTipoString;
		} else if (line.contains(searchTipoFecha)) {
			return searchTipoFecha;
		} else {
			return "Sin definir";
		}
	}

	public static String searchDataName(String line) throws FileNotFoundException {
		
		String line2;
		String search = "dataName";

		if (line.contains(search)) {
			line = StringUtils.substringBetween(line, "dataName=", " ");
			line2 = StringUtils.substringBetween(line, "", ">");
			if(line2==null) {
				line2 = line;
			}
			return line2;
		} else {
			return "Sin definir";
		}

	}

	public static String searchDecPlaces(String line) throws FileNotFoundException {

		String search = "decPlaces";

		if (line.contains(search)) {
			line = StringUtils.substringBetween(line, "decPlaces=", " ");
			return line;
		} else {
			return "Sin definir";
		}

	}

	public static String searchLength(String line) throws FileNotFoundException {

		String search = "length";

		if (line.contains(search)) {
			line = StringUtils.substringBetween(line, "length=", " ");
			return line;
		} else {
			return "Sin definir";
		}

	}

	public static String searchSigned(String line) throws FileNotFoundException {

		String search = "signed";

		if (line.contains(search)) {
			line = StringUtils.substringBetween(line, "signed=", " ");
			return line;
		} else {
			return "Sin definir";
		}

	}

	public static String searchDefaultValue(String line) throws FileNotFoundException {

		String search = "defaultValue";

		if (line.contains(search)) {
			line = StringUtils.substringBetween(line, "defaultValue=", " ");
			return line;
		} else {
			return "Sin definir";
		}

	}

	public static String searchPadChar(String line) throws FileNotFoundException {

		String search = "padChar";

		if (line.contains(search)) {
			line = StringUtils.substringBetween(line, "padChar=\"", "\"");
			return line;
		} else {
			return "Sin definir";
		}

	}

	public static String searchJustify(String line) throws FileNotFoundException {

		String search = "justify";
		String data;
		if (line.contains(search)) {
			data = StringUtils.substringBetween(line, "justify=", " ");
			if (data == null) {
				data = StringUtils.substringBetween(line, "justify=", ">");
			}
			return data;
		} else {
			return "Sin definir";
		}

	}

	public static String searchNullCheck(String line) throws FileNotFoundException {

		String search = "nullCheck";

		if (line.contains(search)) {
			line = "TRUE";
			return line;
		} else {
			return "Sin definir";
		}

	}

	public static String searchFixedLength(String line) throws FileNotFoundException {

		String search = "fixedLength";

		if (line.contains(search)) {
			line = "TRUE";
			return line;
		} else {
			return "Sin definir";
		}

	}

	@SuppressWarnings("resource")
	public static List<Parametro> searchFormato(File file, String formato) throws FileNotFoundException {

		String line;
		String search = "<fmtDef id=" + formato + ">";
		Scanner input;
		List<Parametro> lstParametros = new ArrayList<Parametro>();

		boolean dRecord = false;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine(); // salta linea <dRecord>
			if (line.contains(search)) {
				line = input.nextLine();
				while (!dRecord) {
					line = input.nextLine();
					if (line.contains("</dRecord>")) {
						dRecord = true;
					} else {

						Parametro parametro = new Parametro();
						parametro.setTipo(searchTipo(line));
						parametro.setDataName(searchDataName(line));
						parametro.setDecPlaces(searchDecPlaces(line));
						parametro.setLength(searchLength(line));
						parametro.setSigned(searchSigned(line));
						parametro.setDefaultValue(searchDefaultValue(line));
						parametro.setPadChar(searchPadChar(line));
						parametro.setJustify(searchJustify(line));
						parametro.setNullCheck(searchNullCheck(line));
						parametro.setFixedLength(searchFixedLength(line));
						lstParametros.add(parametro);
					}
				}
			}
		}

		return lstParametros;
	}

	public static void main(String[] args) throws FileNotFoundException {

		File folder = new File("C:\\Users\\Usuario\\Desktop\\dseoper");
		File[] files = folder.listFiles();
		File folder2 = new File("C:\\Users\\Usuario\\Desktop\\dseftms");
		File[] files2 = folder2.listFiles();
		Map<String, List<Parametro>> formatoAtributo = new HashMap<String, List<Parametro>>();

		List<String> lstFormatos = new ArrayList<String>();
		System.out.println("Inicia");
		for (File file : files) {
			lstFormatos = Formatos.searchIdFormato(file);
		}
		System.out.println("Busco los Formatos");
		for (File file2 : files2) {
			for (String formato : lstFormatos) {
				List<Parametro> lstParametros = new ArrayList<Parametro>();
				lstParametros = searchFormato(file2, formato);
				formatoAtributo.put(formato, lstParametros);
			}
		}
		System.out.println("Termina");

		System.out.println(formatoAtributo);

		for (Map.Entry<String, List<Parametro>> entry : formatoAtributo.entrySet()) {
			System.out.println("*----------------------------------*");
			int id = 1;
			for (Parametro paramentro : entry.getValue()) {
				System.out.print(id + " - " + entry.getKey() + " * " + paramentro.getDataName());
				System.out.print(" * " + paramentro.getTipo());
				System.out.print(" * " + paramentro.getDecPlaces());
				System.out.print(" * " + paramentro.getLength());
				System.out.print(" * " + paramentro.getSigned());
				System.out.print(" * " + paramentro.getDefaultValue());
				System.out.print(" * " + paramentro.getPadChar());
				System.out.print(" * " + paramentro.getJustify());
				System.out.print(" * " + paramentro.getNullCheck());
				System.out.print(" * " + paramentro.getFixedLength() + "\n");
				id++;
			}


		}

	}
}
