package org.resource.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class FileTFC {

	public void deleteFiles(String nameFile) throws URISyntaxException, IOException {

		File address = new File(nameFile);
		File path = new File(address.getAbsolutePath());
		File[] folderFiles = path.listFiles();

		if (folderFiles != null) {
			for (File folder : folderFiles) {
				File[] folder2 = folder.listFiles();
				if (folder2 != null) {
					for (File fileTFCs : folder2) {
						File[] file = fileTFCs.listFiles();
						if (file != null) {
							for (File ar : file) {
								ar.delete();
							}
						}
						fileTFCs.delete();
					}
				}
				folder.delete();
			}
		}

		path.delete();

	}

}
