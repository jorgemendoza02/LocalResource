package org.resource.transacciones.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.resource.transacciones.model.Atributo;

public class ReporteBean {

	public void generateExcel(List<List<Atributo>> lstTotalAtributo) // List<List<Atributo>>
			throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException {

		File excel = new File("Atributo.xlsx");
		XSSFWorkbook workbook;

		if (excel.exists()) {

			FileInputStream file = new FileInputStream(new File("Atributo.xlsx"));
			workbook = new XSSFWorkbook(file);

		} else {
			workbook = new XSSFWorkbook();
			workbook.createSheet();
		}

		String[] columns = { "Atributos", " A", "B" };
		XSSFSheet sheet = workbook.getSheetAt(0);

		Font headerFont = workbook.createFont();
		// headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {

			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);

		}

		int columna = 0;

		// Row row = sheet.createRow(0);
		// row.createCell(1).setCellValue("hola");
		// row.createCell(2).setCellValue("mundo")
		// ;
		// for (List<Atributo> lstAtributos : lstTotalAtributo) {
		//
		// }
		for (List<Atributo> lstAtributos : lstTotalAtributo) {

			for (Atributo atributo : lstAtributos) {
				int fila = 0;
				Row row = sheet.createRow(fila++);
				row.createCell(columna).setCellValue(atributo.getNombre());
				break;
			}

			columna++;
		}

		for (int i = 0; i < columns.length; i++) {

			sheet.autoSizeColumn(i);

		}

		FileOutputStream fileOut = new FileOutputStream("Atributo.xlsx");
		workbook.write(fileOut);
		fileOut.close();

		workbook.close();
	}

}
