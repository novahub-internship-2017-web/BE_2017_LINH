package com.linhtran.unimanager.model.menu;

import com.linhtran.unimanager.model.employee.Employee;
import com.linhtran.unimanager.model.employee.Lecturer;
import com.linhtran.unimanager.model.employee.Officer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeAdderTest {
    List<Employee> employeeList = new ArrayList<>();
    EmployeeAdder employeeAdder = new EmployeeAdder();
    Employee lecturer1 = new Lecturer("Nguyen Anh", 1988, "Hue", 3.0,
            "Xay dung", "Thac sy");
    Employee lecturer2 = new Lecturer("Vo Bao", 1988, "Hue", 3.0,
            "Xay dung", "Tien sy");

    Employee officer1 = new Officer("Le Chuong", 1980, "Ha Noi", 2.34,
            "Thanh Tra", "Truong Phong");
    Employee officer2 = new Officer("Le Cuong", 1980, "Ha Noi", 5.00,
            "Thanh Tra", "Truong Phong");
    @Test
    public void testAddLastEmployee() {
        employeeAdder.addLastEmployee(employeeList, lecturer1);
        employeeAdder.addLastEmployee(employeeList, lecturer2);

        //lecture1 should be the first of the list
        assertEquals(lecturer1, employeeList.get(0));
    }

    @Test
    public void testAddFirstEmployee() {
        employeeAdder.addFirstEmployee(employeeList, lecturer1);
        employeeAdder.addFirstEmployee(employeeList, lecturer2);

        //lecture2 should be the first of the list
        assertEquals(lecturer2, employeeList.get(0));
    }

    @Test
    public void testAddEmployee() {
        employeeAdder.addLastEmployee(employeeList, lecturer1);
        employeeAdder.addLastEmployee(employeeList, lecturer2);
        employeeAdder.addEmployee(employeeList, officer1, 0);
        employeeAdder.addEmployee(employeeList, officer2, 2);

        //officer1 should be at the index 1
        assertEquals(officer1, employeeList.get(1));

        //officer2 should be at the index 3
        assertEquals(officer2, employeeList.get(3));
    }

    @Test (expected = Exception.class)
    public void testAddEmployee_throwException() {
        employeeAdder.addLastEmployee(employeeList, lecturer1);
        employeeAdder.addLastEmployee(employeeList, lecturer2);
        employeeAdder.addEmployee(employeeList, officer2, 2);
    }



}