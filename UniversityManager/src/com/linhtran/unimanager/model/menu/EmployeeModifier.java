package com.linhtran.unimanager.model.menu;

import com.linhtran.unimanager.model.employee.Employee;

import java.util.List;
import java.util.Scanner;

//This class contain all method of Menu2
public class EmployeeModifier {
    Scanner scanner = new Scanner(System.in);
    public void modifyEmployeeInformation(List<Employee> employeeList, int index) {
        if (index < 0 || index >= employeeList.size()) {
            throw new IndexOutOfBoundsException();
        }

        System.out.printf("");
    }

}
