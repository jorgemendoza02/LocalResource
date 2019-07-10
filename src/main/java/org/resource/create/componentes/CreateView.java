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

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.resource.create.componentes.services.CreateViewService;
import org.resource.util.FileTFC;
import org.resource.util.Zipper;

@Named
@ViewScoped
public class CreateView implements Serializable {

	private static final long serialVersionUID = 1L;

	private String htmlcode;
	private String nameFile;
	private String tscode;
	private String url;

	private CreateViewService service = new CreateViewService();
	private FileTFC fileTFC = new FileTFC();

	public void processView() throws IOException, URISyntaxException {
		File folder = new File(getUrl());
		File[] files = folder.listFiles();

		for (File file : files) {
			nameFile = file.getName().replace(".java", "");
			System.out.println(nameFile);
			nameFile = nameFile.toUpperCase();

			htmlcode = service.createViewHtml();
			tscode = service.createViewTs(nameFile);

			service.createFileViewHtml(nameFile, htmlcode);
			service.createFileViewTs(nameFile, tscode);
			service.createFileViewCss(nameFile);
		}
	}

	public void createFile(File file, String selector) throws IOException, URISyntaxException {

		nameFile = file.getName().replace(".java", "");
		nameFile = nameFile.toUpperCase();

		htmlcode = service.createViewHtml(selector.toLowerCase());
		tscode = service.createViewTs(nameFile);

		service.createFileViewHtml(nameFile, htmlcode);
		service.createFileViewTs(nameFile, tscode);
		service.createFileViewCss(nameFile);

	}

	public StreamedContent getDownloadValue() throws Exception {
		processView();
		return downloadFile();
	}

	public StreamedContent downloadFile() throws Exception {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
