package org.resource.inventory.components;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Test {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	public static String TEST_XML_STRING = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n" + "<messages>\r\n"
			+ "<ps7Message id=\"BE10\">\r\n" + "  <refInFormat name=\"BE10\"/>\r\n" + "</ps7Message>\r\n"
			+ "</messages>";

	public static void main(String[] args) {
		try {
			JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
			String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			System.out.println(jsonPrettyPrintString);
		} catch (JSONException je) {
			System.out.println(je.toString());
		}
	}
}
