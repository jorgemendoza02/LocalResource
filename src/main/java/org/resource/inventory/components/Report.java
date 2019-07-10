package org.resource.inventory.components;

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

public class Report {
	public void generateExcel(List<Views> lstView)
			throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException {

		File excel = new File("Componentes.xlsx");
		XSSFWorkbook workbook;

		if (excel.exists()) {

			FileInputStream file = new FileInputStream(new File("Componentes.xlsx"));
			workbook = new XSSFWorkbook(file);

		} else {
			workbook = new XSSFWorkbook();
			workbook.createSheet();
		}

		String[] columns = { "Ventana", "Llamadas a Impresos", "Botones", "Relaciones Cruzadas", "Textfield",
				"Combotextfield", "Llamadas a Ventanas", "Llamadas a Transacciones", "Llamadas a servicio de tablas" };
		XSSFSheet sheet = workbook.getSheetAt(0);
		// Sheet sheet = workbook.createSheet("Resumen");

		// "Llamadas a Impresos, Botones", "Relaciones Cruzadas", "Textfield",
		// "Combotextfield", "Llamadas a Ventanas", "Llamadas a Transacciones",
		// "Llamadas a servicio de tablas"

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

		int rowNum = 1;
		for (Views view : lstView) {

			Row row = sheet.createRow(rowNum++);

			// row.createCell(0).setCellValue(view.getWindows());
			// row.createCell(1).setCellValue(view.getTitle());
			// row.createCell(2).setCellValue(view.getBSCHButton());
			// row.createCell(3).setCellValue(view.getBSCHCrossRelation());
			// row.createCell(4).setCellValue(view.getBSCHCheckBox());
			// row.createCell(5).setCellValue(view.getBSCHComboBox());
			// row.createCell(6).setCellValue(view.getBSCHTextField());
			// row.createCell(7).setCellValue(view.getBSCHGroupPanel());
			// row.createCell(8).setCellValue(view.getBSCHLabel());
			// row.createCell(9).setCellValue(view.getBSCHLongTextAreaPanel());
			// row.createCell(10).setCellValue(view.getBSCHPasswordField());
			// row.createCell(11).setCellValue(view.getBSCHRadioButton());
			// row.createCell(12).setCellValue(view.getBSCHTable());
			// row.createCell(13).setCellValue(view.getBSCHTextFieldComboBoxBS());
			// row.createCell(14).setCellValue(view.getBSCHTextFieldComboBox());
			// row.createCell(15).setCellValue(view.getBSCHTextFieldComboBoxRepetirCuenta());
			// row.createCell(16).setCellValue(view.getBSCHTextFieldComboBoxRepetirCuentaBR());
			// row.createCell(17).setCellValue(view.getBSCHTitledEmbeddedPanel());
			// row.createCell(18).setCellValue(view.getBSCHVirtualTextField());
			// row.createCell(19).setCellValue(view.getBSCHButtonTextField());
			// row.createCell(20).setCellValue(view.getTransaction());
			// row.createCell(21).setCellValue(view.getCallsWindows());
			// row.createCell(22).setCellValue(view.getPrinted());

			row.createCell(0).setCellValue(view.getWindows());
			row.createCell(1).setCellValue(view.getPrinted());
			row.createCell(2).setCellValue(view.getBSCHButton());
			row.createCell(3).setCellValue(view.getBSCHCrossRelation());
			row.createCell(4).setCellValue(view.getBSCHTextField());
			row.createCell(5).setCellValue(view.getBSCHTextFieldComboBoxBS() + view.getBSCHTextFieldComboBox());
			row.createCell(6).setCellValue(view.getCallsWindows());
			row.createCell(7).setCellValue(view.getTransaction());
			row.createCell(8).setCellValue(view.getSetTableName());

		}

		for (int i = 0; i < columns.length; i++) {

			sheet.autoSizeColumn(i);

		}

		FileOutputStream fileOut = new FileOutputStream("Componentes.xlsx");
		workbook.write(fileOut);
		fileOut.close();

		workbook.close();
	}

}
