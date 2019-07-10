package org.resource.inventory.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.resource.util.FileTFC;
import org.resource.util.Zipper;

@Named
@ViewScoped
public class InventoryComponents implements Serializable {

	private static final long serialVersionUID = 1L;
	private Components components = new Components();
	private Report report = new Report();
	private FileTFC fileTFC = new FileTFC();
	private String url;

	public void processCounter() throws IOException, URISyntaxException, EncryptedDocumentException,
			InvalidFormatException, InterruptedException {

		String title = "";
		List<Views> lstViews = new ArrayList<>();
		File folder = new File(getUrl());
		File[] files = folder.listFiles();

		for (File file : files) {
			int quanBSCHButton = 0, quanBSCHCrossRelation = 0, quanBSCHCheckBox = 0, quanBSCHComboBox = 0,
					quanBSCHTextField = 0, quanBSCHGroupPanel = 0, quanBSCHLabel = 0, quanBSCHLongTextAreaPanel = 0,
					quanBSCHPasswordField = 0, quanBSCHRadioButton = 0, quanBSCHTable = 0,
					quanBSCHTextFieldComboBoxBS = 0, quanBSCHTextFieldComboBox = 0,
					quanBSCHTextFieldComboBoxRepetirCuenta = 0, quanBSCHTextFieldComboBoxRepetirCuentaBR = 0,
					quanBSCHTitledEmbeddedPanel = 0, quanBSCHVirtualTextField = 0, quanBSCHButtonTextField = 0,
					quanTransaction = 0, quanCallsWindows = 0, quanPrinted = 0, quansetTableName = 0;

			System.out.println(file.getName() + " - Voy a contar componentes");

			title = components.searchTitle(file);
			quanBSCHButton = components.counterBSCHButton(file);
			quanBSCHComboBox = components.counterBSCHComboBox(file);
			quanBSCHCrossRelation = components.counterBSCHCrossRelation(file);
			quanBSCHGroupPanel = components.counterBSCHGroupPanel(file);
			quanBSCHLabel = components.counterBSCHLabel(file);
			quanBSCHLongTextAreaPanel = components.counterBSCHLongTextAreaPanel(file);
			quanBSCHPasswordField = components.counterBSCHPasswordField(file);
			quanBSCHRadioButton = components.counterBSCHRadioButton(file);
			quanBSCHTable = components.counterBSCHTable(file);
			quanBSCHTextField = components.counterBSCHTextField(file);
			quanBSCHTextFieldComboBox = components.counterBSCHTextFieldComboBox(file);
			quanBSCHTextFieldComboBoxBS = components.counterBSCHTextFieldComboBoxBS(file);
			quanBSCHTextFieldComboBoxRepetirCuenta = components.counterBSCHTextFieldComboBoxRepetirCuenta(file);
			quanBSCHTextFieldComboBoxRepetirCuentaBR = components.counterBSCHTextFieldComboBoxRepetirCuentaBR(file);
			quanBSCHTitledEmbeddedPanel = components.counterBSCHTitledEmbeddedPanel(file);
			quanBSCHButtonTextField = components.counterBSCHButtonTextField(file);
			quanBSCHCheckBox = components.counterBSCHCheckBox(file);
			quanTransaction = components.counterTransaction(file);
			quanCallsWindows = components.counterCallsWindows(file);
			quanPrinted = components.counterPrinted(file);
			quansetTableName = components.countersetTableName(file);

			System.out.println(file.getName() + " - Agregue a la lista");

			lstViews.add(new Views(file.getName(), title, quanBSCHButton, quanBSCHCrossRelation, quanBSCHCheckBox,
					quanBSCHComboBox, quanBSCHTextField, quanBSCHGroupPanel, quanBSCHLabel, quanBSCHLongTextAreaPanel,
					quanBSCHPasswordField, quanBSCHRadioButton, quanBSCHTable, quanBSCHTextFieldComboBoxBS,
					quanBSCHTextFieldComboBox, quanBSCHTextFieldComboBoxRepetirCuenta,
					quanBSCHTextFieldComboBoxRepetirCuentaBR, quanBSCHTitledEmbeddedPanel, quanBSCHVirtualTextField,
					quanBSCHButtonTextField, quanTransaction, quanCallsWindows, quanPrinted, quansetTableName));

		}
		report.generateExcel(lstViews);

	}

	public StreamedContent downloadFile() throws URISyntaxException, IOException {

		SecureRandom random = new SecureRandom();
		String text = new BigInteger(130, random).toString(32);

		new File("download").mkdir();
		File folder = new File("Componentes.xlsx");
		File zip = new File("download/sourced-" + text + ".zip");
		Zipper z = new Zipper(zip);
		z.zip(folder);

		StreamedContent download = new DefaultStreamedContent();
		File file = new File("sourced-" + text + ".zip");
		InputStream input = new FileInputStream("download/" + file);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		download = new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName());
		System.out.println("PREP = " + download.getName());

		fileTFC.deleteFiles("Componentes.xlsx");

		return download;
	}

	public StreamedContent getDownloadValue() throws Exception {
		processCounter();
		return downloadFile();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
