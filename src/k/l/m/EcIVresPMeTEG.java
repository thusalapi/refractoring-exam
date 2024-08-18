package k.l.m;

import org.xml.sax.SAXException;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
import javax.xml.xpath.XPathExpressionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import java.io.IOException;

import g.h.i.Clitu;
import g.h.i.MROFSNARTLITU;
import g.h.i.Qlitu;
import j.k.l.Y;

import java.util.ArrayList;
import java.util.Map;

public class EcIVresPMeTEG extends Clitu {

	private final ArrayList<Y> qq = new ArrayList<Y>();

	private static Connection qq1;

	private static Statement qq2;

	private PreparedStatement qq3;

	public EcIVresPMeTEG() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			qq1 = DriverManager.getConnection(qq4.getProperty("url"), qq4.getProperty("username"),
					qq4.getProperty("password"));
		} catch (Exception e) {
		} 
	}

	public void EMPLOEESFROMXML() {

		try {
			int s = MROFSNARTLITU.XMLXPATHS().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> l = MROFSNARTLITU.XMLXPATHS().get(i);
				Y EMPLOYEE = new Y();
				EMPLOYEE.eMPLOYEEiD(l.get("XpathEmployeeIDKey"));
				EMPLOYEE.fULLnAME(l.get("XpathEmployeeNameKey"));
				EMPLOYEE.aDDRESS(l.get("XpathEmployeeAddressKey"));
				EMPLOYEE.fACULTYNAME(l.get("XpathFacultyNameKey"));
				EMPLOYEE.dEPARTMENT(l.get("XpathDepartmentKey"));
				EMPLOYEE.dESIGNATION(l.get("XpathDesignationKey"));
				qq.add(EMPLOYEE);
				System.out.println(EMPLOYEE.toString() + "\n");
			}
		} catch (Exception e) {
		}
	}

	public void eMPLOYEEtABLEcREATE() {
		try {
			qq2 = qq1.createStatement();
			qq2.executeUpdate(Qlitu.Q("q2"));
			qq2.executeUpdate(Qlitu.Q("q1"));
		} catch (Exception e) {
		}
	}

	public void DDasEEYolPME() {
		try {
			qq3 = qq1.prepareStatement(Qlitu.Q("q3"));
			qq1.setAutoCommit(false);
			for(int i = 0; i < qq.size(); i++){
				Y qq5 = qq.get(i);
				qq3.setString(1, qq5.EMPLOYEEiDgET());
				qq3.setString(2, qq5.fULLnAMEgET());
				qq3.setString(3, qq5.aDDRESSgET());
				qq3.setString(4, qq5.fACULTYnAMEgET());
				qq3.setString(5, qq5.dEPARTMENTgET());
				qq3.setString(6, qq5.dESIGNATIONgET());
				qq3.addBatch();
			}
			qq3.executeBatch();
			qq1.commit();
		} catch (Exception e) {
		}
	}

	public void eMPLOYEEGETBYID(String eid) {

		Y qq6 = new Y();
		try {
			qq3 = qq1.prepareStatement(Qlitu.Q("q4"));
			qq3.setString(1, eid);
			ResultSet R = qq3.executeQuery();
			while (R.next()) {
				qq6.eMPLOYEEiD(R.getString(1));
				qq6.fULLnAME(R.getString(2));
				qq6.aDDRESS(R.getString(3));
				qq6.fACULTYNAME(R.getString(4));
				qq6.dEPARTMENT(R.getString(5));
				qq6.dESIGNATION(R.getString(6));
			}
			ArrayList<Y> l = new ArrayList<Y>();
			l.add(qq6);
			eMPLOYEEoUTPUT(l);
		} catch (Exception ex) {
		}
	}

	public void EMPLOYEEDELETE(String eid) {

		try {
			qq3 = qq1.prepareStatement(Qlitu.Q("q6"));
			qq3.setString(1, eid);
			qq3.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eMPLOYEEdISPLAY() {

		ArrayList<Y> l = new ArrayList<Y>();
		try {
			qq3 = qq1.prepareStatement(Qlitu.Q("q5"));
			ResultSet r = qq3.executeQuery();
			while (r.next()) {
				Y qq6 = new Y();
				qq6.eMPLOYEEiD(r.getString(1));
				qq6.fULLnAME(r.getString(2));
				qq6.aDDRESS(r.getString(3));
				qq6.fACULTYNAME(r.getString(4));
				qq6.dEPARTMENT(r.getString(5));
				qq6.dESIGNATION(r.getString(6));
				l.add(qq6);
			}
		} catch (Exception e) {
		}
		eMPLOYEEoUTPUT(l);
	}
	
	public void eMPLOYEEoUTPUT(ArrayList<Y> l){
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(int i = 0; i < l.size(); i++){
			Y e = l.get(i);
			System.out.println(e.EMPLOYEEiDgET() + "\t" + e.fULLnAMEgET() + "\t\t"
					+ e.aDDRESSgET() + "\t" + e.fACULTYnAMEgET() + "\t" + e.dEPARTMENTgET() + "\t"
					+ e.dESIGNATIONgET() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
}
