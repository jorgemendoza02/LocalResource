package org.resource.create.componentes.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class CreateComboboxService {
	@SuppressWarnings("resource")
	public static List<String> searchBSCHComboBox(File file) throws FileNotFoundException {

		String line;
		String search = "new BSCHComboBox";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHComboBox";
		String nameObject;
		String nameObjectAttr;
		String nameComboBox;
		Scanner input;
		List<String> lstComboBox = new ArrayList<String>();

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				nameObject = StringUtils.substringBetween(line, "BSCHComboBox", " =");
				nameObjectAttr = nameObject + ".setName";
				while (input.hasNext()) {
					line = input.nextLine();
					if (line.contains(nameObjectAttr)) {
						nameComboBox = StringUtils.substringBetween(line, "(\"", "\");");
						lstComboBox.add(nameComboBox);
						break;
					}
				}

			}
		}

		return lstComboBox;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws FileNotFoundException {
		List<String> lstButton = new ArrayList<String>();
		List<String> lstComboBox = new ArrayList<String>();
		File folder = new File("C:\\Users\\Usuario\\Desktop\\view");
		File[] files = folder.listFiles();
		String htmlCodeCombox, htmlCodeButton, htmlCode;
		String nameFile;
		CreateViewService viewService = new CreateViewService();
		CreateButtonService buttonViewService = new CreateButtonService();

		for (File file : files) {

			nameFile = file.getName().replace(".java", "");
			System.out.println("Archivo " + nameFile);
			lstComboBox = searchBSCHComboBox(file);
			htmlCodeCombox = viewService.createViewHtml(lstComboBox, "combobox");
			lstButton = CreateButtonService.searchBSCHButton(file);
			htmlCodeButton = viewService.createViewHtml(lstButton, "button");
			htmlCode = htmlCodeCombox + htmlCodeButton;

			viewService.createFileViewHtml(nameFile, htmlCode);

		}

	}

}
