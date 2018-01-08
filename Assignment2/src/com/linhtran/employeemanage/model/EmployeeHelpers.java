package com.linhtran.employeemanage.model;

import java.util.List;

/* -----------------------------------------------------------------
   This class contain some helper constant value and static methods
   for Employee class and its child classes
   ----------------------------------------------------------------*/
public class EmployeeHelpers {
    private EmployeeHelpers() {
        throw new UnsupportedOperationException();
    }

    public static final int UNIT_SALARY = 730;

    public static final int BACHELOR_ALLOWANCE = 300;
    public static final int MASTER_ALLOWANCE = 900;
    public static final int DOCTOR_ALLOWANCE = 2000;

    public static final int HEAD_DEPARTMENT_ALLOWANCE = 1000;
    public static final int DEPUTY_DEPARTMENT_ALLOWANCE = 600;
    public static final int REGULAR_EMPLOYEE_ALLOWANCE = 400;
    public static final int ADMIN_ALLOWANCE = 1000;

    //Bubble sort by name for employee list
    public static void sortByName(List<Employee> employeeList) {
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
    public static void sortBySalary(List<Employee> employeeList) {
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