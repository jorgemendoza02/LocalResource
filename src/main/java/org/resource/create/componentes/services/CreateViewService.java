package org.resource.create.componentes.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

public class CreateViewService {

	SecureRandom random = new SecureRandom();
	String text = new BigInteger(130, random).toString(32);

	public String createViewHtml() {
		String htmlcode = "";
		htmlcode = "<p>\r\n" + "  ejemplo works!\r\n" + "</p>";
		return htmlcode;
	}

	public String createViewHtml(String selector) {
		String htmlcode = "";
		htmlcode = "<p>\r\n" + "  ejemplo works!\r\n" + "</p>\r\n" + "<tabla" + selector + "></tabla" + selector + ">";
		return htmlcode;
	}

	public String createViewHtml(List<String> lstComponent, String type) {

		String htmlcode = "";

		if (type.equals("button")) {
			for (String button : lstComponent) {
				htmlcode = htmlcode + "\n <app-bsrbutton (clickBoton)=\"clickBoton($event)\" [nombreButton]=\"'"
						+ button + "'\" [idButton]=\"buttonMovimientos\" [tipoBoton]=\"'primario'\"></app-bsrbutton>\n";
				System.out.println(button);
			}
			return htmlcode;
		} else if (type.equals("combobox")) {
			for (String comboBox : lstComponent) {
				htmlcode = htmlcode + "\n <div class=\"col-4\">\r\n" + "    <app-bsrcombobox [labelHijo]=\"'" + comboBox
						+ "'\"></app-bsrcombobox>\r\n" + "</div>\n";
				System.out.println(comboBox);
			}
			return htmlcode;
		} else {
			return "";
		}

	}

	public void createFileViewHtml(String nameFile, String htmlcode) {
		try {
			new File("reporte").mkdir();
			new File("reporte/vista-" + text).mkdir();
			new File("reporte/vista-" + text + "/" + "vista" + nameFile).mkdir();
			String ruta = "reporte/vista-" + text + "/" + "vista" + nameFile + "/" + "vista" + nameFile
					+ ".component.html";
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

	public String createViewTs(String nameFile) {
		String tscode = "";

		tscode = "import { Component, OnInit } from '@angular/core';\r\n" + "\r\n" + "@Component({\r\n"
				+ "  selector: 'vista-" + nameFile + "',\r\n" + "  templateUrl: './vista" + nameFile
				+ ".component.html',\r\n" + "  styleUrls: ['./vista" + nameFile + ".component.css']\r\n" + "})\r\n"
				+ "export class vista" + nameFile + "Component implements OnInit {\r\n" + "\r\n"
				+ "  constructor() { }\r\n" + "\r\n" + "  ngOnInit() {\r\n" + "  }\r\n" + "\r\n" + "}\r\n" + "";

		return tscode;

	}

	public void createFileViewTs(String nameFile, String tscode) {
		try {
			String ruta = "reporte/vista-" + text + "/" + "vista" + nameFile + "/" + "vista" + nameFile
					+ ".component.ts";
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

	public void createViewCss() {

	}

	public void createFileViewCss(String nameFile) {
		try {

			String ruta = "reporte/vista-" + text + "/" + "vista" + nameFile + "/" + "vista" + nameFile
					+ ".component.css";
			// String contenido = ccscode;
			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			// bw.write(contenido);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String createAppVistasTs(File[] files) {
		String appVistasTs = "";
		String nameFile;

		appVistasTs = "//vistas\r\n";

		for (File file : files) {

			nameFile = file.getName().replace(".java", "");
			nameFile = nameFile.toUpperCase();

			appVistasTs = appVistasTs + "import { " + "vista" + nameFile + "Component } from './privadas/vistas/vista"
					+ nameFile + "/vista" + nameFile + ".component';\r\n";
		}
		appVistasTs = appVistasTs + "@NgModule({\r\n" + "  declarations: [";
		for (File file : files) {

			nameFile = file.getName().replace(".java", "");
			nameFile = nameFile.toUpperCase();

			appVistasTs = appVistasTs + "     vista" + nameFile + "Component,\r\n";
		}
		appVistasTs = appVistasTs + "  ],\r\n" + "})\r\n" + "";

		return appVistasTs;

	}

	public void createFileAppVistasTs(String appVistasCode) {
		try {

			String ruta = "reporte/app.vistas.ts";
			String contenido = appVistasCode;
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

	public String createAppRouters(File[] files) {
		String routerscode = "";
		String nameFile;

		routerscode = routerscode + "import { ModuleWithProviders } from '@angular/core';\r\n"
				+ "import { Routes, RouterModule } from '@angular/router';\r\n";
		for (File file : files) {

			nameFile = file.getName().replace(".java", "");
			nameFile = nameFile.toUpperCase();

			routerscode = routerscode + "import {vista" + nameFile + "Component }from './privadas/vistas/vista"
					+ nameFile + "/vista" + nameFile + ".component';\r\n";

		}

		routerscode = routerscode + "@NgModule({\r\n" + "  declarations: [";
		routerscode = routerscode + "const appRoutes: Routes = [\r\n";
		for (File file : files) {

			nameFile = file.getName().replace(".java", "");
			nameFile = nameFile.toUpperCase();

			routerscode = routerscode + "    { path: 'vista" + nameFile + "', component: vista" + nameFile
					+ "Component },\r\n";

		}
		routerscode = routerscode + "];\r\n";
		routerscode = routerscode + "\r\n" + "export const appRoutingProviders: any[] = [];\r\n"
				+ "export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);";

		return routerscode;

	}

	public void createFileAppRoutesTs(String appRoutesCode) {
		try {
			String ruta = "reporte/app.routes.ts";
			String contenido = appRoutesCode;
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
