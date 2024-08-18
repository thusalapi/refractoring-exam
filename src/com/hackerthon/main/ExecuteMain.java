package com.hackerthon.main;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.hackerthon.common.XmlTransformer;

import k.l.m.EmployeeService;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeService();
		try {
			XmlTransformer.requestTransform();
			employeeService.employeesFromXml();
			employeeService.employeeTableCreate();
			employeeService.ddasEeyolpme();
			employeeService.employeeDisplay();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
