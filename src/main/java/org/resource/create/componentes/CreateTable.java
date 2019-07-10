package org.resource.create.componentes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.resource.create.componentes.services.CreateTableService;
import org.resource.create.componentes.services.CreateViewService;
import org.resource.util.FileTFC;
import org.resource.util.Zipper;

@Named
@ViewScoped
public class CreateTable implements Serializable {

	private static final long serialVersionUID = 1L;

	private String htmlcode;
	private String nameFile;
	private String tscode;
	private String appModuloCode;
	private String appVistasTs;
	private String routerscode;
	private String url;

	private boolean haveButtonAceptar;
	private boolean tablaRepetida;

	private List<Map> lstTables;
	private List<List<String>> lstTablasNoRepetidas = new ArrayList<>();
	private List<String> lstFilesTable = new ArrayList<>();
	private Map<List<String>, String> tableFile = new HashMap<List<String>, String>();
	private CreateTableService service = new CreateTableService();
	private CreateViewService viewService = new CreateViewService();
	private FileTFC fileTFC = new FileTFC();
	private CreateView view = new CreateView();

	public void processTables() throws IOException, URISyntaxException, InterruptedException {
		File folder = new File(getUrl());
		File[] files = folder.listFiles();
		int num = 0;
		for (File file : files) {
			lstTables = service.searchTables(file);
			tablaRepetida = false;

			// Busco tablas repetidas.
			if (lstTables.size() >= 1) {

				for (Map table : lstTables) {
					String key = table.keySet().toString().replace("[", "");
					key = key.replace("]", "");
					List<String> table2 = (List<String>) table.get(key);

					if (!lstTablasNoRepetidas.contains(table2)) {
						lstTablasNoRepetidas.add(table2);
						tableFile.put(table2, file.getName());
						view.createFile(file, file.getName());
						tablaRepetida = false;
						num++;
					} else {
						view.createFile(file, tableFile.get(table2));
						System.out.println("Repetida");
						tablaRepetida = true;
						num++;
					}
				}

			}

			// Si tiene la Tablas es repetida no creo el archivo
			if (!tablaRepetida || num == 2) {
				nameFile = file.getName().replace(".java", "");
				nameFile = "tabla" + nameFile.toLowerCase();
				if (!lstTables.isEmpty()) {
					haveButtonAceptar = service.searchButtonAceptar(file);
					if (haveButtonAceptar) {
						System.out.println("Tiene boton Aceptar");
					}
					htmlcode = service.createTableHmtl(haveButtonAceptar, lstTables);
					service.createFileHtml(nameFile, htmlcode);
					tscode = service.creteTableTs(haveButtonAceptar, nameFile, lstTables);
					service.createFileTs(nameFile, tscode);
					lstFilesTable.add(nameFile);
					appModuloCode = service.createAppModuleTs(lstFilesTable);
					service.createFileAppModuloTs(appModuloCode);
				}
			}
			haveButtonAceptar = false;

		}
		appVistasTs = viewService.createAppVistasTs(files);
		viewService.createFileAppVistasTs(appVistasTs);

		routerscode = viewService.createAppRouters(files);
		viewService.createFileAppRoutesTs(routerscode);

		FacesMessage msg = new FacesMessage("Succesful");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public StreamedContent downloadFile() throws URISyntaxException, IOException {
		SecureRandom random = new SecureRandom();
		String text = new BigInteger(130, random).toString(32);

		new File("download").mkdir();
		File folder = new File("reporte");
		File zip = new File("download/sourced-" + text + ".zip");
		Zipper z = new Zipper(zip);
		z.zip(folder);

		StreamedContent download = new DefaultStreamedContent();
		File file = new File("sourced-" + text + ".zip");
		InputStream input = new FileInputStream("download/" + file);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		download = new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName());
		System.out.println("PREP = " + download.getName());

		fileTFC.deleteFiles("reporte");
		return download;
	}

	public StreamedContent getDownloadValue() throws Exception {
		processTables();
		return downloadFile();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
