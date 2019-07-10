package org.resource.create.componentes.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class CreateButtonService {

	@SuppressWarnings("resource")
	public static List<String> searchBSCHButton(File file) throws FileNotFoundException {

		String line;
		String search = "new BSCHButton";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHButton";
		String nameobject;
		String nameobjectAttr;
		String nameButton;
		Scanner input;
		List<String> lstButton = new ArrayList<String>();

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				nameobject = StringUtils.substringBetween(line, "BSCHButton", " =");
				nameobjectAttr = nameobject + ".setName";
				while (input.hasNext()) {
					line = input.nextLine();
					if (line.contains(nameobjectAttr)) {
						nameButton = StringUtils.substringBetween(line, "(\"", "\");");
						lstButton.add(nameButton);
						break;
					}
				}

			}
		}

		return lstButton;
	}

	public static void main(String[] args) throws FileNotFoundException {
		List<String> lstButton = new ArrayList<String>();
		File folder = new File("C:\\Users\\jorge\\Documents\\Migrador\\TestView");
		File[] files = folder.listFiles();
		String htmlCode;
		String nameFile;
		CreateViewService viewService = new CreateViewService();

		for (File file : files) {
			nameFile = file.getName().replace(".java", "");
			System.out.println("Archivo " + nameFile);
			lstButton = searchBSCHButton(file);
			htmlCode = viewService.createViewHtml(lstButton, "button");
			viewService.createFileViewHtml(nameFile, htmlCode);

		}

	}
}
