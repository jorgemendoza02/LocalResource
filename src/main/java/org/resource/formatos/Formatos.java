package org.resource.formatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Formatos {

	@SuppressWarnings("resource")
	public static List<String> searchIdFormato(File file) throws FileNotFoundException {

		String transaccion = "";
		String formato = "";
		List<String> lstId = new ArrayList<>();
		String line;
		String search = "<refFormat name=hostBodySendFormat refId=";
		String search2 = "<GenericServerOper id=";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {

			line = input.nextLine();
			if (line.contains(search2)) {
				formato = line;
				formato = StringUtils.substringBetween(formato, "<GenericServerOper id=", ">");

			}

			if (line.contains(search)) {
				transaccion = line;
				transaccion = StringUtils.substringBetween(transaccion, "<refFormat name=hostBodySendFormat refId=",
						">");
				lstId.add(transaccion + " " + formato);
			}

		}

		return lstId;
	}

	public static void main(String[] args) throws FileNotFoundException {

		File folder = new File("C:\\Users\\Usuario\\Desktop\\dseoper");
		File[] files = folder.listFiles();
		List<String> lst = new ArrayList<>();

		for (File file : files) {
			lst = searchIdFormato(file);
		}

		for (String val : lst) {
			System.out.println(val);
		}

	}
}
