package org.resource.impresos;

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

public class Reporte {

	public void generarExcel(List<Impreso> lstImpresos)
			throws IOException, EncryptedDocumentException, InvalidFormatException {

		File excel = new File("Reporte_Impresos.xlsx");
		XSSFWorkbook workbook;

		if (excel.exists()) {

			FileInputStream file = new FileInputStream(new File("Componentes.xlsx"));
			workbook = new XSSFWorkbook(file);

		} else {
			workbook = new XSSFWorkbook();
			workbook.createSheet();
		}

		String[] columns = { "Ventana", "Titulo", "Nombre", "Parametro", "Tipo", "Grilla" };

		XSSFSheet sheet = workbook.getSheetAt(0);

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
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

		int rowNum = 1;
		for (Impreso impreso : lstImpresos) {

			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(impreso.getVentana());
			row.createCell(1).setCellValue(impreso.getTitulo());
			row.createCell(2).setCellValue(impreso.getNombre());
			row.createCell(3).setCellValue(impreso.getParametro());
			row.createCell(4).setCellValue(impreso.getTipo());
			row.createCell(5).setCellValue(impreso.getGrilla());
		}

		for (int i = 0; i < columns.length; i++) {

			sheet.autoSizeColumn(i);

		}

		FileOutputStream fileOut = new FileOutputStream("Reporte_Impresos.xlsx");
		workbook.write(fileOut);
		fileOut.close();

		workbook.close();
	}
}
