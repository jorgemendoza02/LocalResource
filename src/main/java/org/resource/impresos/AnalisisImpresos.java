package org.resource.impresos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.resource.util.FileTFC;
import org.resource.util.Zipper;

@Named
@ViewScoped
public class AnalisisImpresos implements Serializable {

	private String url;
	private String url2;
	private FileTFC fileTFC = new FileTFC();

	public StreamedContent downloadFile()
			throws URISyntaxException, IOException, EncryptedDocumentException, InvalidFormatException {
		SecureRandom random = new SecureRandom();
		String text = new BigInteger(130, random).toString(32);

		new File("download").mkdir();
		File folder = new File("Reporte_Impresos.xlsx");
		File zip = new File("download/sourced-" + text + ".zip");
		Zipper z = new Zipper(zip);
		z.zip(folder);

		StreamedContent download = new DefaultStreamedContent();
		File file = new File("sourced-" + text + ".zip");
		InputStream input = new FileInputStream("download/" + file);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		download = new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName());
		System.out.println("PREP = " + download.getName());

		fileTFC.deleteFiles("Reporte_Impresos.xlsx");
		return download;

	}

	public StreamedContent getDownloadValue() throws Exception {
		analizar();
		return downloadFile();
	}

	public void analizar() throws IOException, EncryptedDocumentException, InvalidFormatException {

		String title = "setTitle", ProcessData = "setClickProcessData",
				libreria1 = "com.ibm.bsch.client.launcher.LauncherPrintScreen",
				libreria2 = "com.ibm.bsch.client.launcher.LauncherOpenDoc", tieneGrilla = "", parametro = "", grilla,
				linea, titulo = null, nombre = null, nombre2 = null, tipo = null;

		int contCalidad = 0, contSistema = 0;
		Reporte reporte = new Reporte();
		Scanner entrada = null;
		List<Impreso> lstImpresos = new ArrayList<>();

		File folder = new File(getUrl());// TODO BUSCAR RUTA DE ARCHIVOS.
		File[] files = folder.listFiles();
		Format format = new Format();

		String dsefmts = getUrl2();

		for (File f : files) {

			boolean encontroLib1 = false;
			boolean encontroLib2 = false;

			try {

				System.out.println("--------------------------------------------");
				System.out.println("El nombre de la Ventana es: " + f.getName());
				entrada = new Scanner(f);

				boolean calidad = false;
				boolean entroProcessData = false;

				while (entrada.hasNext()) {

					linea = entrada.nextLine();

					if (linea.contains(title)) {

						titulo = linea;
						titulo = StringUtils.substringBetween(titulo, "\"", "\"");

					} else if (linea.contains(libreria1)) {
						encontroLib1 = true;
					} else if (linea.contains(libreria2)) {
						encontroLib2 = true;
					}

					if (encontroLib1 || encontroLib2) {
						if (entroProcessData == false) {
							if (linea.contains(ProcessData)) {

								nombre = linea;
								nombre = StringUtils.substringBetween(nombre, "\"", "\"");
								System.out.println("palabra " + nombre);

								int idxComa = nombre.indexOf(",");

								nombre2 = nombre.substring(0, idxComa);
								parametro = nombre.substring(idxComa + 1);
								System.out.println(nombre2);
								System.out.println(parametro);
								System.out.println("-------------------------------------");
								grilla = format.searchNameFormat(folder, nombre2);
								tieneGrilla = format.haveGrid(dsefmts, grilla);
								entroProcessData = true;

							}
						}
					}

					if (linea.contains("setClickProcessParameters(\"PRINT_QUALITY\")")) {
						tipo = "Calidad";
						calidad = true;
					}

				}
				if (!calidad) {
					tipo = "Sistema";
				}

				lstImpresos.add(new Impreso(f.getName(), titulo, nombre2, parametro, tipo, tieneGrilla));

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		reporte.generarExcel(lstImpresos);

		System.out.println("Los impresos de calidad son: " + contCalidad);
		System.out.println("Los impresos de sistema son: " + contSistema);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

}