package org.resource.create.componentes.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class CreateTableService {

	SecureRandom random = new SecureRandom();
	String text = new BigInteger(130, random).toString(32);

	@SuppressWarnings("resource")
	public List<Map> searchTables(File file) throws FileNotFoundException {

		String line;
		String component;
		String searchComponent = ".BSCHTable";
		String nameobject;
		String column;
		String nameColumn;
		String nametableBD;
		String endTable;
		Scanner input;
		Map<String, List> map = new HashMap<String, List>();
		List<String> lstColumn = new ArrayList<>();
		List<Map> lstTableColum = new ArrayList<>();
		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();

			if (line.contains(searchComponent)) {

				component = line;
				nameobject = StringUtils.substringBetween(component, "BSCHTable", "=");
				if (nameobject != null) {
					nameobject = nameobject.replace(" ", "");
					nametableBD = nameobject + ".setDataNameForTable";
					while (input.hasNext()) {

						line = input.nextLine();
						column = nameobject + ".setColumn";
						endTable = "add(" + nameobject + ")";

						if (line.contains(nametableBD)) {
							nametableBD = StringUtils.substringBetween(line, "\"", "\"");
						}

						if (line.contains(column)) {
							nameColumn = StringUtils.substringBetween(line, "\"", ";");
							nameColumn = nameColumn.replace(" ", "");
							nameColumn = nameColumn.replace(".", "");
							nameColumn = nameColumn.replaceAll("[^\\dA-Za-z]", "");
							nameColumn = nameColumn.replaceAll("[\\W]|_", "");
							nameColumn = nameColumn.replaceAll("[^a-zA-Z0-9]", "");
							nameColumn = nameColumn.toLowerCase();

							if (lstTableColum.isEmpty()) {
								lstColumn.add(nameColumn);
							} else {
								lstColumn.add(nameColumn);
							}
						}
						if (line.contains(endTable)) {
							break;
						}

					}
					lstTableColum.add(map);
					map.put(nametableBD, lstColumn);
					System.out.println(map);
					map = new HashMap<String, List>();
					lstColumn = new ArrayList<>();
				}
			}

		}
		return lstTableColum;
	}

	@SuppressWarnings("resource")
	public boolean searchButtonAceptar(File file) throws FileNotFoundException {

		String line;
		String component;
		String searchComponent = "BSCHButton";
		String nameobject;
		String endButton;
		String button;
		Scanner input;
		boolean haveButton = false;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();

			if (line.contains(searchComponent)) {
				component = line;
				nameobject = StringUtils.substringBetween(component, "BSCHButton", "=");
				if (nameobject != null) {
					nameobject = nameobject.replace(" ", "");
					while (input.hasNext()) {
						line = input.nextLine();
						button = nameobject + ".setText(\"Aceptar\");";
						endButton = "add(" + nameobject + ")";
						if (line.contains(button)) {
							haveButton = true;
						}
						if (line.contains(endButton)) {
							break;
						}
					}
				}
			}
		}

		return haveButton;
	}

	public String createTableHmtl(boolean haveButtonAceptar, List<Map> lstTables) {
		String htmlcode = "";
		String deleteCorchetesKey;
		String key;
		int cantTable = 0;
		List<String> table;

		for (Map keyTable : lstTables) {

			deleteCorchetesKey = keyTable.keySet().toString().replace("[", "");
			key = deleteCorchetesKey.replace("]", "");
			table = (List<String>) keyTable.get(key);

			htmlcode = htmlcode
					+ " <table mat-table [dataSource]=\"dataSource\" class=\"border thead-dark table-borderer shadow-none mat-elevation-z8 w-100\" id=\""
					+ key + "\" >\r\n";
			if (haveButtonAceptar) {
				htmlcode = htmlcode + "    <!-- Radio Button Column -->\r\n"
						+ " <ng-container matColumnDef=\"select\">\r\n"
						+ "      <th mat-header-cell *matHeaderCellDef>\r\n" + "      </th>\r\n"
						+ "      <mat-radio-group aria-label=\"Select an option\">\r\n"
						+ "      <td mat-cell *matCellDef=\"let row\">\r\n"
						+ "          <mat-radio-button value=\"{{row.code}}\"\r\n"
						+ "            (change)=\"selecciona(row)\">\r\n" + "          </mat-radio-button>\r\n"
						+ "        </td>\r\n" + "      </mat-radio-group>\r\n" + "    </ng-container>\r\n" + "";
			}

			htmlcode = htmlcode + "<ng-container matColumnDef=\"entidad\">\r\n"
					+ "      <th mat-header-cell *matHeaderCellDef> Entidad </th>\r\n"
					+ "      <td mat-cell *matCellDef=\"let element\"> {{element.entidad}} </td>"
					+ "    </ng-container>\r\n";

			for (String component : table) {
				if (component.startsWith("prod") || component.startsWith("subpro")) {
					htmlcode = htmlcode + "    <ng-container matColumnDef=\"" + component + "\">\r\n"
							+ "        <th mat-header-cell *matHeaderCellDef> " + component + " </th>\r\n"
							+ "        <td mat-cell *matCellDef=\"let element\"> {{element." + component + "}}\r\n"
							+ "		<span class=\"info-descripcion pr-2 float-right\"  matTooltipClass=\"tooltip-lg\" mat-raised-button\r\n"
							+ "          matTooltip=\"TITULOS\" aria-label=\"botón que despliega descripcipon de "
							+ component + "\"><i class=\"material-icons align-middle\">info</i></span> \r\n"
							+ "        </td>\r\n" + "      </ng-container>\r\n";
				} else {
					htmlcode = htmlcode + " <ng-container matColumnDef=\"" + component + "\">\r\n"
							+ "      <th mat-header-cell *matHeaderCellDef> " + component + " </th>\r\n"
							+ "      <td mat-cell *matCellDef=\"let element\"> {{element." + component + "}} </td>\r\n"
							+ "    </ng-container>\r\n";
				}
			}

			htmlcode = htmlcode + "\r\n" + "    <tr mat-header-row *matHeaderRowDef=\"displayedColumns\"></tr>\r\n"
					+ "    <tr mat-row *matRowDef=\"let row; columns: displayedColumns;\"></tr>\r\n" + "\r\n"
					+ "</table>\r\n"
					+ "<mat-paginator [pageSizeOptions]=\"[3, 4, 10]\" showFirstLastButtons></mat-paginator>\r\n"
					+ "\r\n" + "";
			cantTable++;
		}
		System.out.println("Tiene " + cantTable + " tablas");
		return htmlcode;
	}

	public void createFileHtml(String nameFile, String htmlcode) {
		try {

			new File("reporte").mkdir();
			new File("reporte/tabla-" + text).mkdir();
			new File("reporte/tabla-" + text + "/" + nameFile).mkdir();
			String ruta = "reporte/tabla-" + text + "/" + nameFile + "/" + nameFile + ".component.html";
			String contenido = htmlcode;
			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contenido);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String creteTableTs(boolean haveButtonAceptar, String nameFile, List<Map> lstTables) {
		String tscode = "";
		String row;
		String deleteCorchetesKey;
		String key;
		int n;
		int cantTable = 0;
		List<String> table;

		tscode = "import { Component, OnInit, ViewChild } from '@angular/core';\r\n"
				+ "import {MatTableDataSource, MatPaginator, MatPaginatorIntl} from '@angular/material';\r\n"
				+ "import {SelectionModel} from '@angular/cdk/collections';\r\n\r\n";
		n = 1;

		for (Map keyTable : lstTables) {

			deleteCorchetesKey = keyTable.keySet().toString().replace("[", "");
			key = deleteCorchetesKey.replace("]", "");

			table = (List<String>) keyTable.get(key);

			tscode = tscode + "export interface valoresTabla" + n + "{\r\n";
			for (String component : table) {
				tscode = tscode + "  " + component + ": string;\r\n";
			}
			tscode = tscode + "}\r\n\r\n";
			n++;
		}

		n = 1;

		for (Map keyTable : lstTables) {

			deleteCorchetesKey = keyTable.keySet().toString().replace("[", "");
			key = deleteCorchetesKey.replace("]", "");

			table = (List<String>) keyTable.get(key);
			if (!table.isEmpty()) {
				tscode = tscode + "const ELEMENT_DATA: valoresTabla" + n + "[] = [\r\n";
				row = "  {";
				String lastElement = table.get(table.size() - 1);
				for (String component : table) {
					if (component.equalsIgnoreCase(lastElement)) {
						row = row + component + ": 'data'";
					} else {
						row = row + component + ": 'data', ";
					}

				}
				row = row + "}";

				for (int i = 0; i <= 3; i++) {

					if (i == 3) {
						tscode = tscode + row;
					} else {
						tscode = tscode + row + ",\r\n";
					}
				}

				tscode = tscode + "];\r\n\r\n";
				n++;
			}
		}

		tscode = tscode + "@Component({\r\n" + "  selector: '" + nameFile + "',\r\n" + "  templateUrl: './" + nameFile
				+ ".component.html'\r\n})\r\n\r\n";

		tscode = tscode + "export class " + nameFile + "Component implements OnInit {\r\n" + " //tabla\r\n";

		n = 1;
		for (Map keyTable : lstTables) {

			deleteCorchetesKey = keyTable.keySet().toString().replace("[", "");
			key = deleteCorchetesKey.replace("]", "");

			table = (List<String>) keyTable.get(key);
			if (haveButtonAceptar) {
				tscode = tscode + "displayedColumns: string[] = ['select',";
			} else {
				tscode = tscode + "displayedColumns: string[] = [";
			}

			for (String component : table) {

				String lastElement = table.get(table.size() - 1);

				if (component.equalsIgnoreCase(lastElement)) {
					tscode = tscode + "'" + component + "'";
				} else {
					tscode = tscode + "'" + component + "',";
				}
			}
			tscode = tscode + "];\r\n";
			tscode = tscode + " dataSource = new MatTableDataSource<valoresTabla" + n + ">(ELEMENT_DATA);\r\n"
					+ " selection = new SelectionModel<valoresTabla" + n + ">(true, []);\r\n";
			n++;
		}

		tscode = tscode + " //paginador tabla\r\n" + "  @ViewChild(MatPaginator) paginator: MatPaginator;\r\n" + "\r\n"
				+ " //termina tabla\r\n" + "  constructor() { }\r\n"
				+ "    /** Whether the number of selected elements matches the total number of rows. */\r\n"
				+ "	\r\n" + "	//aceptar->\r\n" + "    isAllSelected() {\r\n"
				+ "      const numSelected = this.selection.selected.length;\r\n"
				+ "      const numRows = this.dataSource.data.length;\r\n" + "      return numSelected === numRows;\r\n"
				+ "    }\r\n" + "  \r\n"
				+ "    /** Selects all rows if they are not all selected; otherwise clear selection. */\r\n"
				+ "    masterToggle() {\r\n" + "      this.isAllSelected() ?\r\n"
				+ "          this.selection.clear() :\r\n"
				+ "          this.dataSource.data.forEach(row => this.selection.select(row));\r\n" + "    }\r\n"
				+ "\r\n" + "  ngOnInit() {\r\n" + "    //paginador\r\n"
				+ "    this.dataSource.paginator = this.paginator;\r\n" + "  }\r\n" + "\r\n";

		if (haveButtonAceptar) {
			tscode = tscode + "  selecciona(row) {\r\n" + "    console.log(row);\r\n" + "  }\r\n";
		}
		tscode = tscode + "}\r\n";

		return tscode;

	}

	public void createFileTs(String nameFile, String tscode) {
		try {

			String ruta = "reporte/tabla-" + text + "/" + nameFile + "/" + nameFile + ".component.ts";
			String contenido = tscode;
			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contenido);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String createAppModuleTs(List<String> lstFilesTable) {
		String modulecode = "";

		modulecode = "//tablas\r\n";

		for (String fileTable : lstFilesTable) {

			modulecode = modulecode + "import { " + fileTable + "Component } from './privadas/tablas/" + fileTable + "/"
					+ fileTable + ".component';\r\n";
		}
		modulecode = modulecode + "@NgModule({\r\n" + "  declarations: [";
		for (String fileTable : lstFilesTable) {
			modulecode = modulecode + "    " + fileTable + "Component,\r\n";
		}
		modulecode = modulecode + "  ],\r\n" + "})\r\n" + "";

		return modulecode;

	}

	public void createFileAppModuloTs(String appModuloCode) {
		try {

			String ruta = "reporte/tabla-" + text + "/ app.module.ts";
			String contenido = appModuloCode;
			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contenido);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
