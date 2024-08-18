package com.hackerthon.main;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.hackerthon.common.XmlTransformer;
import com.hackerthon.services.EmployeeServiceImpl;

public class ExecuteMain {

    /**
     * @param args
     */
    public static void main(String[] args) {

        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        try {
            XmlTransformer.requestTransform();
            employeeService.manageEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}