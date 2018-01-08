package com.linhtran.unimanager.model.menu;

import com.linhtran.unimanager.model.employee.Employee;
import com.linhtran.unimanager.model.employee.Lecturer;
import com.linhtran.unimanager.model.employee.Officer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeSearchTest {
    List<Employee> employeeList = new ArrayList<>();
    List<Employee> resultList = new ArrayList<>();
    EmployeeSearch employeeSearch = new EmployeeSearch();
    Employee lecturer1 = new Lecturer("Nguyen Anh", 1988, "Hue", 3.0,
            "Xay dung", "Thac sy");
    Employee lecturer2 = new Lecturer("Vo Bao", 1988, "Hue", 3.0,
            "Xay dung", "Tien sy");

    Employee officer1 = new Officer("Le Chuong", 1980, "Ha Noi", 2.34,
            "Thanh Tra", "Truong Phong");
    Employee officer2 = new Officer("Le Cuong", 1980, "Ha Noi", 5.00,
            "Thanh Tra", "Truong Phong");
    @Test
    public void searchEmployeeByName() {
        addEmployeeToList();

        //Searching with no result
        resultList = employeeSearch.searchEmployeeByName(employeeList, "vien");
        assertTrue(resultList.isEmpty());

        //Searching with a result
        resultList = employeeSearch.searchEmployeeByName(employeeList, "anh");
        assertFalse(resultList.isEmpty());

    }

    @Test
    public void searchEmployeeByBirthYear() {
        addEmployeeToList();

        //Searching with no result
        resultList = employeeSearch.searchEmployeeByBirthYear(employeeList, 1970);
        assertTrue(resultList.isEmpty());

        //Searching with a result
        resultList = employeeSearch.searchEmployeeByBirthYear(employeeList, 1988);
        assertFalse(resultList.isEmpty());
    }

    private void addEmployeeToList() {
        employeeList.add(lecturer1);
        employeeList.add(lecturer2);
        employeeList.add(officer1);
        employeeList.add(officer2);
    }

}