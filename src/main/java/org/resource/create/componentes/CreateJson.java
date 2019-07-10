package org.resource.create.componentes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.SecureRandom;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.json.JSONException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.resource.create.componentes.services.CreateJsonService;
import org.resource.util.FileTFC;
import org.resource.util.Zipper;

@Named
@ViewScoped
public class CreateJson implements Serializable {

	private static final long serialVersionUID = -9211488178485180971L;
 
	private String url;
	private String json;
	private CreateJsonService jsonService = new CreateJsonService();
	private FileTFC fileTFC = new FileTFC();

	public void processJson() throws JSONException, IOException {
		File folder = new File(getUrl());
		File[] files = folder.listFiles();

		for (File file : files) {
			json = jsonService.createJson(file);
			jsonService.createFileJson(file.getName(), json);
		}
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
		processJson();
		StreamedContent zip = downloadFile();
//		RequestContext.getCurrentInstance().execute("PF('wdlgCargando').hide()");
		return zip;

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
