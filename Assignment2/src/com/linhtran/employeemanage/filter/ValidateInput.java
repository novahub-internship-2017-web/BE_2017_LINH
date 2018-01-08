package com.linhtran.employeemanage.filter;

import com.linhtran.employeemanage.model.Employee;
import com.linhtran.employeemanage.model.Lecturer;
import com.linhtran.employeemanage.model.Officer;

public class ValidateInput {
    private ValidateInput(){
        //Protect some class make objects of this class
    }

    public static void checkEmployeeProperties(Employee employee) {
        //Check username
        if (employee.getUsername().length() > 20) {
            throw new IllegalArgumentException("Username's string too long (>20 characters)");
        }

        //Check full name
        if (employee.getFullName().length() > 20) {
            throw new IllegalArgumentException("Full name's string too long (>20 characters)");
        }

        //Check home town
        if (employee.getHomeTown().length() > 20) {
            throw new IllegalArgumentException("Home town's string too long (>20 characters)");
        }

        //Check birth year
        int birthYear = employee.getBirthYear();
        if (birthYear < 1960 || birthYear > 1999) {
            throw new IllegalArgumentException("Birth year must be from 1960 to 1999");
        }

        //Check salary factor
        double salaryFactor = employee.getSalaryFactor();
        if (salaryFactor <0 || salaryFactor > 10) {
            throw new IllegalArgumentException("Salary factor must be from 1 to 10");
        }

        //Check teaching quantity and number of working days
        if (employee instanceof Lecturer) {
            int quantity = ((Lecturer) employee).getTeachingQuantity();
            if (quantity < 0 || quantity > 300) {
                throw new IllegalArgumentException("Teaching quantity must be from 1 to 300");
            }

        } else if (employee instanceof Officer) {
            int workingDays = ((Officer) employee).getNumberOfWorkingDays();
            if (workingDays < 0 || workingDays > 31) {
                throw new IllegalArgumentException("Number of working days must be from 0 to 31");
            }
        }


    }
}
