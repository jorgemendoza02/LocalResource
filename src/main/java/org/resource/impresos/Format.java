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

public class Format {

	public String searchNameFormat(File dir, String impreso) {

		Scanner entrada;
		String linea, formatoFile = null;
		String formato = "<format name=\"";
		File[] files = dir.listFiles();
		try {
			for (File file : files) {

				if (file.getCanonicalPath().contains(impreso)) {

					File f = new File(file.getCanonicalPath());
					entrada = new Scanner(f);

					while (entrada.hasNext()) {
						linea = entrada.nextLine();
						if (linea.contains(formato)) {
							formatoFile = linea;
							formatoFile = StringUtils.substringBetween(formatoFile, "\"", "\"");
							// System.out.println("Nombre del Formato: " + formatoFile);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return formatoFile;
	}

	public String haveGrid(String dir, String format) throws InvalidFormatException, IOException {

		Scanner input;
		String line;
		String grillaXml = "";
		String haveGrid = "";
		boolean findFormat = false;

		try {

			File file = new File(dir);
			input = new Scanner(file);
			while (input.hasNextLine()) {
				line = input.nextLine();
				if (line.contains(format) || findFormat) {
					grillaXml = grillaXml + line + "\r\n";
					findFormat = true;
					if (line.contains("iColl")) {
						haveGrid = "si";
					}
					if (line.contains("</fmtDef>")) {
						break; // si encuentra el tag que cierra, sal del bucle.
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		//sendReport(format, grillaXml);
		if (haveGrid.isEmpty()) {
			haveGrid = "no";
		}
		return haveGrid;
	}

	public void sendReport(String format, String grillaXml) {

		String excelFilePath = "Reporte_Impresos.xlsx";

		if (format != null) {
			try {
				FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
				Workbook workbook = WorkbookFactory.create(inputStream);

				// Sheet sheet = workbook.getSheetAt(0);
				Sheet sheet = workbook.createSheet(format);

				Row row = sheet.createRow(0);

				Cell cell = row.createCell(0);
				cell.setCellValue(grillaXml);

				inputStream.close();

				FileOutputStream outputStream = new FileOutputStream("Reporte_Impresos.xlsx");
				workbook.write(outputStream);
				workbook.close();
				outputStream.close();

			} catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
				ex.printStackTrace();
			}
		}

	}
}
