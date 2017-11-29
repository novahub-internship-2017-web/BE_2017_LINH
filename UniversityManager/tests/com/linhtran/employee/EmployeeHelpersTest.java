package com.linhtran.employee;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmployeeHelpersTest {
    Employee lecturer1 = new Lecturer("Nguyen Anh", 1988, "Hue", 3.0,
            "Xay dung", "Thac sy");
    Employee lecturer2 = new Lecturer("Vo Bao", 1988, "Hue", 3.0,
            "Xay dung", "Tien sy");

    Employee officer1 = new Officer("Le Chuong", 1980, "Ha Noi", 2.34,
            "Thanh Tra", "Truong Phong");
    Employee officer2 = new Officer("Le Cuong", 1980, "Ha Noi", 5.00,
            "Thanh Tra", "Truong Phong");
    ArrayList<Employee> employeeArrayList = new ArrayList<>();

    @Test
    public void sortByName() {
        employeeArrayList.add(lecturer1);
        employeeArrayList.add(lecturer2);
        employeeArrayList.add(officer1);
        employeeArrayList.add(officer2);
        EmployeeHelpers.sortByName(employeeArrayList);

        //Officer "Le Chuong" should be at the first of list
        assertEquals("Le Chuong", employeeArrayList.get(0).getFullName());

        //Lecturer "Vo Bao" should be at the last of list
        assertEquals("Vo Bao", employeeArrayList.get(3).getFullName());

    }

    @Test
    public void sortBySalary() {
        employeeArrayList.add(lecturer1);
        employeeArrayList.add(lecturer2);
        employeeArrayList.add(officer1);
        employeeArrayList.add(officer2);
        EmployeeHelpers.sortBySalary(employeeArrayList);

        /* Salary of each employee
        lecturer1: 3090
        lecturer2: 4190
        officer1: 2708
        officer2: 4650
         */

        // After sort "Le Chuong" should be at the first of the list
        assertEquals("Le Chuong", employeeArrayList.get(0).getFullName());

        //After sort "Le Cuong" should be at the last of the list
        assertEquals("Le Cuong", employeeArrayList.get(3).getFullName());
    }

}