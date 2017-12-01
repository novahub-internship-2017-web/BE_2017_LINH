package com.linhtran.unimanager.controller;

import com.linhtran.unimanager.model.employee.Employee;
import com.linhtran.unimanager.model.employee.EmployeeHelpers;
import com.linhtran.unimanager.model.employee.Lecturer;
import com.linhtran.unimanager.model.employee.Officer;
import com.linhtran.unimanager.model.menu.EmployeeAdder;
import com.linhtran.unimanager.model.menu.EmployeeModifier;
import com.linhtran.unimanager.model.menu.EmployeeSearch;
import com.linhtran.unimanager.view.PrintScreen;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Employee> employeeList = new ArrayList<>();
    private PrintScreen printScreen = new PrintScreen();
    private EmployeeAdder employeeAdder = new EmployeeAdder();
    private EmployeeModifier employeeModifier = new EmployeeModifier();
    private EmployeeSearch employeeSearch = new EmployeeSearch();

    public Controller() { //Constructor for test
        Employee lecturer1 = new Lecturer("Tran Linh", 1988, "Hue", 3.0,
                "Xay dung", "Thac sy", 200);
        Lecturer lecturer2 = new Lecturer("Nguyen Hoan", 1980, "Da Nang", 3.0,
                "Kien truc", "Tien sy", 300);
        Employee officer1 = new Officer("Tran Manh", 1989, "Quang Nam", 3.33,
                "Dao Tao", 26, "Nhan Vien");
        //Down cast for Officer
        Officer officer2 = new Officer("Nguyen Van", 1980, "Ha Noi", 5.00,
                "Thanh Tra", 20, "Truong Phong");
        employeeList.add(lecturer1);
        employeeList.add(lecturer2);
        employeeList.add(officer1);
        employeeList.add(officer2);
    }

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
                    default:
                        break;
                }
            }
        } catch (Exception e) {    //If having any trouble, just restart the program
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
    HANDLE EVENT WHEN USER CHOSE 2 FROM THE MAIN MENU
    ------------------------------------------------------- */
    private void handleMenuTwo() {
        int employeeIndex = -1;

        //Repeat the input process until it is valid
        while (employeeIndex < 0 || employeeIndex >= employeeList.size()) {
            employeeIndex = printScreen.printMenu2(employeeList);
        }
        //Chose the index of information needed to modify
        Employee employee = employeeList.get(employeeIndex );
        int indexOfInformation = printScreen.printFieldsToModify(employee);

        //if index of information is not valid, restart program
        if (indexOfInformation > 7 || indexOfInformation < 1) {
            startProgram();
        } else {
            String newInformation = printScreen.modifyEmployee(indexOfInformation, employee.getType());
            try {
                employeeModifier.modifyEmployeeInformation(employee, indexOfInformation, newInformation);
            } catch (Exception e) { //Exception when user type wrong type of input
                System.out.println(e.getMessage());
                handleMenuTwo();
            }

        }
        handleMenuTwo(); //restart menu 2 when done a modification
    }

    /*------------------------------------------------------
    HANDLE EVENT WHEN USER CHOSE 3 FROM THE MAIN MENU
    ------------------------------------------------------- */
    private void handleMenuThree() {

        int employeeIndex = printScreen.printMenu3(employeeList);

        if (employeeIndex == employeeList.size()) {//If index = size --> restart program
            startProgram();
        } else if (employeeIndex < 0 || employeeIndex > employeeList.size()) {
            handleMenuThree(); //restart menu 3 if input is not valid
        } else {
            employeeList.remove(employeeIndex);
            handleMenuThree();
        }
    }

    /*------------------------------------------------------
    HANDLE EVENT WHEN USER CHOSE 4 FROM THE MAIN MENU
    ------------------------------------------------------- */
    private void handleMenuFour() {
        List<Employee> copyEmployeeList = new ArrayList<>();
        int choice = printScreen.printMenu4();
        switch (choice) {
            case 1:
                printScreen.printEmployeeList(employeeList);
                break;
            case 2:
                copyEmployeeList.addAll(employeeList);
                EmployeeHelpers.sortBySalary(copyEmployeeList);
                printScreen.printEmployeeList(copyEmployeeList);
                break;
            case 3:
                copyEmployeeList.addAll(employeeList);
                EmployeeHelpers.sortByName(copyEmployeeList);
                printScreen.printEmployeeList(copyEmployeeList);
                break;
            case 4:
                String searchName = printScreen.inputEmployeeName();
                List<Employee> resultList = employeeSearch.searchEmployeeByName(employeeList, searchName);
                printScreen.printResult(resultList);
                break;
            case 5:
                int year = printScreen.inputEmployeeBirthYear();
                List<Employee> resultList1 = employeeSearch.searchEmployeeByBirthYear(employeeList, year);
                printScreen.printResult(resultList1);
                break;
            case 6:
                startProgram();
                break;
            default:
                handleMenuFour();
                break;
        }
        if (choice > 0 && choice < 6) {
            handleMenuFour();
        }
    }

}
