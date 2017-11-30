package com.linhtran.unimanager.controller;

import com.linhtran.unimanager.model.employee.Employee;
import com.linhtran.unimanager.model.menu.EmployeeAdder;
import com.linhtran.unimanager.view.PrintScreen;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Employee> employeeList = new ArrayList<>();
    private PrintScreen printScreen = new PrintScreen();
    private EmployeeAdder employeeAdder = new EmployeeAdder();

    public void startProgram() {
        try {
            int mainMenuChoice = printScreen.printMainMenu();
            if (mainMenuChoice > 0 && mainMenuChoice <= 5) {
                switch (mainMenuChoice) {
                    case 1:
                        handleMenuOne(); //Add employee in the list
                        break;
                    case 2:
                        handleMenuTwo(); //Modify employee's information
                        break;
                    case 3:
                        handleMenuThree(); //Remove employee
                        break;
                    case 4:
                        handleMenuFour(); //Display employee in option
                        break;
                    case 5:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Some thing went wong");
            startProgram();
        }

    }

    /*------------------------------------------------------
    HANDLE EVENT WHEN USER CHOSE 1 ON THE MAIN MENU
    ------------------------------------------------------- */
    private void handleMenuOne() {
        int choice = printScreen.printMenu1();
        try {
            switch (choice) {
                case 1:
                case 2:
                case 3:
                    Employee employee = printScreen.inputNewEmployee();
                    if (choice == 1) {
                        employeeAdder.addLastEmployee(employeeList, employee);

                    } else if (choice == 2) {
                        employeeAdder.addFirstEmployee(employeeList, employee);

                    } else {
                        int k = printScreen.inputEmployeeIndex(employeeList);
                        employeeAdder.addEmployee(employeeList, employee, k);
                    }
                    handleMenuOne();
                    break;
                case 4:
                    startProgram();
                    break;

                default:
                    handleMenuOne();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            handleMenuOne();
        }
     }


    /*------------------------------------------------------
    HANDLE EVENT WHEN USER CHOSE 2 ON THE MAIN MENU
    ------------------------------------------------------- */
    private void handleMenuTwo() {

    }

    /*------------------------------------------------------
    HANDLE EVENT WHEN USER CHOSE 3 ON THE MAIN MENU
    ------------------------------------------------------- */
    private void handleMenuThree() {

    }

    /*------------------------------------------------------
    HANDLE EVENT WHEN USER CHOSE 4 ON THE MAIN MENU
    ------------------------------------------------------- */
    private void handleMenuFour() {
        printScreen.printEmployeeList(employeeList);
        startProgram();
    }



}
