package com.hackerthon.common;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible for transforming XML and extracting data using XPath.
 */
public class XmlTransformer extends ConfigurationLoader {

	private static final Logger LOGGER = Logger.getLogger(XmlTransformer.class.getName());
	private static final ArrayList<Map<String, String>> employeeDataList = new ArrayList<>();

	/**
	 * Transforms the XML using the specified XSLT.
	 */
	public static void requestTransform() {
		try {
			Source xmlSource = new StreamSource(new File("src/b/c/d/EmployeeRequest.xml"));
			Source xsltSource = new StreamSource(new File("src/b/c/d/Employee-modified.xsl"));
			Result outputResult = new StreamResult(new File("src/b/c/d/EmployeeResponse.xml"));
			TransformerFactory.newInstance().newTransformer(xsltSource).transform(xmlSource, outputResult);
		} catch (TransformerException e) {
			LOGGER.log(Level.SEVERE, "Error during XML transformation", e);
		}
	}

	/**
	 * Extracts data from the transformed XML using XPath.
	 * 
	 * @return List of maps containing employee data.
	 */
	public static ArrayList<Map<String, String>> extractXmlData() {
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse("src/b/c/d/EmployeeResponse.xml");
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xPath.compile("//Employees/Employee").evaluate(document,
					XPathConstants.NODESET);
			List<Element> elements = toElementList(nodeList);

			for (Element element : elements) {
				Map<String, String> employeeData = new HashMap<>();
				employeeData.put("employeeId",
						(String) xPath.compile("EmployeeID/text()").evaluate(element, XPathConstants.STRING));
				employeeData.put("employeeName",
						(String) xPath.compile("EmployeeFullName/text()").evaluate(element, XPathConstants.STRING));
				employeeData.put("employeeAddress",
						(String) xPath.compile("EmployeeFullAddress/text()").evaluate(element, XPathConstants.STRING));
				employeeData.put("facultyName",
						(String) xPath.compile("FacultyName/text()").evaluate(element, XPathConstants.STRING));
				employeeData.put("department",
						(String) xPath.compile("Department/text()").evaluate(element, XPathConstants.STRING));
				employeeData.put("designation",
						(String) xPath.compile("Designation/text()").evaluate(element, XPathConstants.STRING));
				employeeDataList.add(employeeData);
			}
		} catch (ParserConfigurationException e) {
			LOGGER.log(Level.SEVERE, "Parser configuration error while parsing the XML file", e);
			throw new RuntimeException(e);
		} catch (SAXException e) {
			LOGGER.log(Level.SEVERE, "SAX error while parsing the XML file", e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "IO error while parsing the XML file", e);
			throw new RuntimeException(e);
		} catch (XPathExpressionException e) {
			LOGGER.log(Level.SEVERE, "XPath expression error while parsing the XML file", e);
			throw new RuntimeException(e);
		}
		return employeeDataList;
	}

	/**
	 * Converts a NodeList to a List of Elements.
	 * 
	 * @param nodeList
	 *                     The NodeList to convert.
	 * @return A List of Elements.
	 */
	private static List<Element> toElementList(NodeList nodeList) {
		List<Element> elements = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			elements.add((Element) nodeList.item(i));
		}
		return elements;
	}
}