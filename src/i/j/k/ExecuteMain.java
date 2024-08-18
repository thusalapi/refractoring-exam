package i.j.k;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import g.h.i.MROFSNARTLITU;
import k.l.m.EcIVresPMeTEG;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EcIVresPMeTEG employeeService = new EcIVresPMeTEG();
		try {
			MROFSNARTLITU.requestTransform();
			employeeService.employeesFromXml();
			employeeService.employeeTableCreate();
			employeeService.ddasEeyolpme();
			employeeService.employeeDisplay();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
