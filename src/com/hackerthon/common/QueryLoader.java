package com.hackerthon.common;

import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.TransformerException;
import java.io.File;
import org.xml.sax.SAXException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import javax.xml.transform.TransformerConfigurationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible for loading and retrieving SQL queries from an XML file.
 */
public class QueryLoader extends ConfigurationLoader {

	private static final Logger LOGGER = Logger.getLogger(QueryLoader.class.getName());

	/**
	 * Retrieves the SQL query associated with the given ID from the XML file.
	 * 
	 * @param id
	 *               The ID of the query to retrieve.
	 * @return The SQL query as a string.
	 * @throws Exception
	 *                       If an error occurs while parsing the XML file.
	 */
	public static String getQueryById(String id) throws Exception {
		NodeList nodeList;
		Element element = null;
		try {
			nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new File("src/b/c/d/EmployeeQuery.xml")).getElementsByTagName("query");
			for (Element element : nodeList) {
				if (element.getAttribute("id").equals(id)) {
					break;
				}
			}
		} catch (ParserConfigurationException e) {
			LOGGER.log(Level.SEVERE, "Parser configuration error while parsing the XML file", e);
			throw e;
		} catch (SAXException e) {
			LOGGER.log(Level.SEVERE, "SAX error while parsing the XML file", e);
			throw e;
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "IO error while parsing the XML file", e);
			throw e;
		}
		if (element == null) {
			throw new Exception("Query with ID " + id + " not found.");
		}
		return element.getTextContent().trim();
	}
}