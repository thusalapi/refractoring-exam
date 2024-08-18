package com.hackerthon.common;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class responsible for loading and parsing XML queries.
 */
public class QueryLoader extends ConfigureLoader {

    private static final Logger LOGGER = Logger.getLogger(QueryLoader.class.getName());

    /**
     * Loads and parses the EmployeeQuery.xml file.
     * 
     * @return NodeList containing the query elements.
     */
    public NodeList loadQueries() {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new File(properties.getProperty("employeeQueryPath")));
            return document.getElementsByTagName("query");
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.SEVERE, "Parser configuration error while parsing the XML file", e);
            throw new RuntimeException(e);
        } catch (SAXException e) {
            LOGGER.log(Level.SEVERE, "SAX error while parsing the XML file", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO error while parsing the XML file", e);
            throw new RuntimeException(e);
        }
    }
}