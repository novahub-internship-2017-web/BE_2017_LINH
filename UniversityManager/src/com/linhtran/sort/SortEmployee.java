package com.linhtran.sort;

import com.linhtran.employee.Employee;

import java.util.ArrayList;

public class SortEmployee {
    private SortEmployee(){

    }


    //Bubble sort by name for employee list
    public static void sortByName(ArrayList<Employee> employeeList) {
        for (int i = 0; i < employeeList.size() - 1; i++) {
            for (int j = i; j < employeeList.size(); j++) {
                if (employeeList.get(i).compareByNameTo(employeeList.get(j)) > 0) {
                    Employee temp = employeeList.get(i);
                    employeeList.set(i, employeeList.get(j));
                    employeeList.set(j, temp);
                }
            }
        }
    }

    //Bubble sort by salary for employee list
    public static void sortBySalary(ArrayList<Employee> employeeList) {
        for (int i = 0; i < employeeList.size() - 1; i++) {
            for (int j = i; j < employeeList.size(); j++) {
                if (employeeList.get(i).compareBySalaryTo(employeeList.get(j)) > 0) {
                    Employee temp = employeeList.get(i);
                    employeeList.set(i, employeeList.get(j));
                    employeeList.set(j, temp);
                }
            }
        }
    }
}
