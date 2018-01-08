package com.linhtran.unimanager.model.menu;

import com.linhtran.unimanager.model.employee.Employee;

import java.util.ArrayList;
import java.util.List;

//FOR MENU 4
public class EmployeeSearch {

    public List<Employee> searchEmployeeByName(List<Employee> employeeList, String name) {
        List<Employee> resultList = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getFullName().toUpperCase().contains(name.toUpperCase())) {
                resultList.add(employee);
            }
        }
        return resultList;
    }

    public List<Employee> searchEmployeeByBirthYear(List<Employee> employeeList, int year) {
        List<Employee> resultList = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getBirthYear() == year) {
                resultList.add(employee);
            }
        }
        return resultList;
    }
}
