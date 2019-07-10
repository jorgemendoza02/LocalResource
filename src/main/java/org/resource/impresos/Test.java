package org.resource.impresos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Test {

	public static void main(String[] args) {
		String excelFilePath = "Reporte_Impresos.xlsx";

		try {
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = WorkbookFactory.create(inputStream);

			// Sheet sheet = workbook.getSheetAt(0);
			Sheet sheet = workbook.createSheet("mendoza");

			Row row = sheet.createRow(0);

			Cell cell = row.createCell(0);
			cell.setCellValue("jorge");

			inputStream.close();

			FileOutputStream outputStream = new FileOutputStream("Reporte_Impresos.xls");
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();

		} catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
			ex.printStackTrace();
		}
	}

	public static void searchNameFormat(File dir, String impreso) {
		try {
			Scanner entrada;
			String linea, formatoFile;
			String formato = "<format name=\"";
			File[] files = dir.listFiles();
			for (File file : files) {

				if (file.getCanonicalPath().contains(impreso)) {

					File f = new File(file.getCanonicalPath());
					entrada = new Scanner(f);

					while (entrada.hasNext()) {
						linea = entrada.nextLine();
						if (linea.contains(formato)) {
							formatoFile = linea;
							formatoFile = StringUtils.substringBetween(formatoFile, "\"", "\"");
							System.out.println("El formato tiene el siguiente valor: " + formatoFile);
						}
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}