package com.hackerthon.services;

import org.xml.sax.SAXException;

import com.hackerthon.common.ConfigurationLoader;
import com.hackerthon.common.QueryLoader;
import com.hackerthon.common.DBConnectionUtil;

import java.sql.Connection;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
import javax.xml.xpath.XPathExpressionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import java.io.IOException;

import j.k.l.Y;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class EmployeeServiceImpl extends ConfigurationLoader implements EmployeeService {

    private final ArrayList<Y> employees = new ArrayList<>();
    private static Connection connection;
    private static Statement statement;
    private PreparedStatement preparedStatement;

    public EmployeeServiceImpl() {
        connection = DBConnectUtil.getInstance().getConnection();
    }

    @Override
    public void loadEmployeesFromXml() {
        try {
            for (Map<String, String> data : XmlTransformer.XMLXPATHS()) {
                Y employee = new Y();
                employee.setEmployeeId(data.get("XpathEmployeeIDKey"));
                employee.setFullName(data.get("XpathEmployeeNameKey"));
                employee.setAddress(data.get("XpathEmployeeAddressKey"));
                employee.setFacultyName(data.get("XpathFacultyNameKey"));
                employee.setDepartment(data.get("XpathDepartmentKey"));
                employee.setDesignation(data.get("XpathDesignationKey"));
                employees.add(employee);
                System.out.println(employee.toString() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEmployeeTable() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(Qlitu.Q("q2"));
            statement.executeUpdate(Qlitu.Q("q1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveEmployees() {
        try {
            preparedStatement = connection.prepareStatement(Qlitu.Q("q3"));
            connection.setAutoCommit(false);
            for (Y employee : employees) {
                preparedStatement.setString(1, employee.getEmployeeId());
                preparedStatement.setString(2, employee.getFullName());
                preparedStatement.setString(3, employee.getAddress());
                preparedStatement.setString(4, employee.getFacultyName());
                preparedStatement.setString(5, employee.getDepartment());
                preparedStatement.setString(6, employee.getDesignation());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getEmployeeById(String employeeId) {
        Y employee = new Y();
        try {
            preparedStatement = connection.prepareStatement(Qlitu.Q("q4"));
            preparedStatement.setString(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setEmployeeId(resultSet.getString(1));
                employee.setFullName(resultSet.getString(2));
                employee.setAddress(resultSet.getString(3));
                employee.setFacultyName(resultSet.getString(4));
                employee.setDepartment(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
            }
            ArrayList<Y> employeeList = new ArrayList<>();
            employeeList.add(employee);
            displayEmployees(employeeList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(String employeeId) {
        try {
            preparedStatement = connection.prepareStatement(Qlitu.Q("q6"));
            preparedStatement.setString(1, employeeId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayAllEmployees() {
        ArrayList<Y> employeeList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(Qlitu.Q("q5"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Y employee = new Y();
                employee.setEmployeeId(resultSet.getString(1));
                employee.setFullName(resultSet.getString(2));
                employee.setAddress(resultSet.getString(3));
                employee.setFacultyName(resultSet.getString(4));
                employee.setDepartment(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        displayEmployees(employeeList);
    }

    @Override
    public void displayEmployees(ArrayList<Y> employeeList) {
        System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
                + "Department" + "\t\t" + "Designation" + "\n");
        System.out.println(
                "================================================================================================================");
        for (Y employee : employeeList) {
            System.out.println(employee.getEmployeeId() + "\t" + employee.getFullName() + "\t\t" + employee.getAddress()
                    + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t"
                    + employee.getDesignation() + "\n");
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------");
        }
    }
}