package com.hackerthon.services;

import java.util.ArrayList;

public abstract class EmployeeServiceTemplate {

    protected abstract void loadEmployeesFromXml();

    protected abstract void createEmployeeTable();

    protected abstract void saveEmployees();

    protected abstract void getEmployeeById(String employeeId);

    protected abstract void deleteEmployee(String employeeId);

    protected abstract void displayAllEmployees();

    protected abstract void displayEmployees(ArrayList<Y> employeeList);

    // Template method
    public final void manageEmployees() {
        loadEmployeesFromXml();
        createEmployeeTable();
        saveEmployees();
        displayAllEmployees();
    }
}