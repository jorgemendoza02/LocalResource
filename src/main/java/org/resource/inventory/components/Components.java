package org.resource.inventory.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Components {

	@SuppressWarnings("resource")
	public String searchTitle(File file) throws FileNotFoundException {

		String title = "";
		String line;
		String search = ".setTitle";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search)) {
				title = line;
				title = StringUtils.substringBetween(title, "\"", "\"");
				break;
			}
		}

		return title;
	}

	@SuppressWarnings("resource")
	public int counterBSCHButton(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHButton";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHButton";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHButtonTextField(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHButtonTextField";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHButtonTextField";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHCrossRelation(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHCrossRelation";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHCrossRelation";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHComboBox(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHComboBox";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHComboBox";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHGroupPanel(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHGroupPanel";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHGroupPanel";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHLabel(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHLabel";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHLabel";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHLongTextAreaPanel(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHLongTextAreaPanel";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHLongTextAreaPanel";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHPasswordField(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHPasswordField";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHPasswordField";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHRadioButton(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHRadioButton";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHRadioButton";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHTable(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHTable";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHTable";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHTextField(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHTextField";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHTextField";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHTextFieldComboBoxBS(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHTextFieldComboBoxBS";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHTextFieldComboBoxBS";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHTextFieldComboBox(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHTextFieldComboBox";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHTextFieldComboBox";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHTextFieldComboBoxRepetirCuenta(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHTextFieldComboBoxRepetirCuenta";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHTextFieldComboBoxRepetirCuenta";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHTextFieldComboBoxRepetirCuentaBR(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHTextFieldComboBoxRepetirCuentaBR";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHTextFieldComboBoxRepetirCuentaBR";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHTitledEmbeddedPanel(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHTitledEmbeddedPanel";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHTitledEmbeddedPanel";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHVirtualTextField(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHVirtualTextField";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHVirtualTextField";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterBSCHCheckBox(File file) throws FileNotFoundException {

		int counter = 0;
		String line;
		String search = "new BSCHCheckBox";
		String search2 = "new com.ibm.dse.gui.extensions.BSCHCheckBox";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search) || line.contains(search2)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterTransaction(File file) throws FileNotFoundException {
		int counter = 0;
		String line;
		String search = "com.ibm.bsch.client.launcher.ExecuteTransaction";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterCallsWindows(File file) throws FileNotFoundException {
		int counter = 0;
		String line;
		String search = "com.ibm.bsch.client.launcher.ModalWindowOperation";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int counterPrinted(File file) throws FileNotFoundException {
		int counter = 0;
		String line;
		String search = "com.ibm.bsch.client.launcher.LauncherPrintScreen";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search)) {
				counter++;
			}
		}

		return counter;
	}

	@SuppressWarnings("resource")
	public int countersetTableName(File file) throws FileNotFoundException {
		int counter = 0;
		String line;
		String search = ".setTableName";
		Scanner input;

		input = new Scanner(file);

		while (input.hasNext()) {
			line = input.nextLine();
			if (line.contains(search)) {
				counter++;
			}
		}

		return counter;
	}

}
