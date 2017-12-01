package com.linhtran.unimanager.model.menu;

import com.linhtran.unimanager.model.employee.Employee;
import com.linhtran.unimanager.model.employee.Lecturer;
import com.linhtran.unimanager.model.employee.Officer;

import java.util.List;
import java.util.Scanner;

//This class contain all method of Menu2
public class EmployeeModifier {

    public void modifyEmployeeInformation(Employee employee, int indexOfInformation, String newInformation)
                                            throws  IllegalArgumentException{
            switch (indexOfInformation) {
                case 1:
                    employee.setFullName(newInformation);
                    break;
                case 2:
                    employee.setBirthYear(Integer.parseInt(newInformation));
                    break;
                case 3:
                    employee.setHomeTown(newInformation);
                    break;
                case 4:
                    employee.setSalaryFactor(Double.parseDouble(newInformation));
                    break;
                case 5:
                    if (employee instanceof Lecturer) {
                        ((Lecturer) employee).setFaculty(newInformation);
                    } else {
                        ((Officer) employee).setDepartment(newInformation);
                    }
                    break;
                case 6:
                    if (employee instanceof Lecturer) {
                        ((Lecturer) employee).setDegree(newInformation);
                    } else {
                        ((Officer) employee).setPosition(newInformation);
                    }
                    break;
                case 7:
                    if (employee instanceof Lecturer) {
                        ((Lecturer) employee).setTeachingQuantity(Integer.parseInt(newInformation));
                    } else {
                        ((Officer) employee).setNumberOfWorkingDays(Integer.parseInt(newInformation));
                    }
                    break;
                default:
                    break;
            }


    }

}
