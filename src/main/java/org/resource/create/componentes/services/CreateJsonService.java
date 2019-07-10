package org.resource.create.componentes.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class CreateJsonService {

	SecureRandom random = new SecureRandom();
	String text = new BigInteger(130, random).toString(32);

	public String createJson(File file) throws JSONException, IOException {
		String json;
		String xml = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
		JSONObject xmlJSONObj = XML.toJSONObject(xml);
		json = xmlJSONObj.toString(4);
		return json;

	}

	public void createFileJson(String nameFile, String json) {
		try {
			nameFile = nameFile.replace(".xml", "");
			new File("reporte").mkdir();
			new File("reporte/json-" + text).mkdir();
			new File("reporte/json-" + text + "/" + nameFile).mkdir();
			String ruta = "reporte/json-" + text + "/" + nameFile + "/" + nameFile + ".json";
			String contenido = json;
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