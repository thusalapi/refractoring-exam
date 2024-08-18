package com.hackerthon.services;

import java.util.ArrayList;

public interface EmployeeService {
    void loadEmployeesFromXml();

    void createEmployeeTable();

    void saveEmployees();

    void getEmployeeById(String employeeId);

    void deleteEmployee(String employeeId);

    void displayAllEmployees();

    void displayEmployees(ArrayList<Y> employeeList);

}
