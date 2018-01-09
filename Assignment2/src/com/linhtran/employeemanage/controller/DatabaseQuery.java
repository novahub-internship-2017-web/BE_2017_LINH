package com.linhtran.employeemanage.controller;

import com.linhtran.employeemanage.model.Employee;
import com.linhtran.employeemanage.model.Lecturer;
import com.linhtran.employeemanage.model.Officer;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQuery {
    private Context ctx = null;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Statement statement = null;
    private DataSource ds = null;

    /*---------------------------------------------------------------------------------------*/
    private void getConnection(String databaseName) {
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/" + databaseName);
            con = ds.getConnection();//
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /*---------------------------------------------------------------------------------------*/
    public void insertDatabase (String databaseName, Employee employee, String passHash) {
        getConnection(databaseName);
        String typeOfEmployee = employee.getType();
        String fullName = employee.getFullName();
        int birthYear = employee.getBirthYear();
        String homeTown = employee.getHomeTown();
        double salaryFactor = employee.getSalaryFactor();
        double salary = employee.getSalary();
        String username = employee.getUsername();


        try {
            ps = con.prepareStatement("INSERT INTO employee (type, name, " +
                    "birth_year, home_town, salary_factor, salary, faculty_department, " +
                    "degree_position, quantity_days, username, password) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, typeOfEmployee);
            ps.setString(2, fullName);
            ps.setInt(3, birthYear);
            ps.setString(4, homeTown);
            ps.setDouble(5, salaryFactor);
            ps.setDouble(6, salary);

            if (employee instanceof Lecturer) {
                String faculty = ((Lecturer) employee).getFaculty();
                String degree = ((Lecturer) employee).getDegree();
                int teachingQuantity = ((Lecturer) employee).getTeachingQuantity();
                ps.setString(7, faculty);
                ps.setString(8, degree);
                ps.setInt(9, teachingQuantity);

            } else if (employee instanceof Officer) {
                String department = ((Officer) employee).getDepartment();
                String position = ((Officer) employee).getPosition();
                int numberOfWorkingDays = ((Officer) employee).getNumberOfWorkingDays();
                ps.setString(7, department);
                ps.setString(8, position);
                ps.setInt(9, numberOfWorkingDays);
            }
            ps.setString(10, username);
            ps.setString(11, passHash);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase();
        }
    }

    /*-------------------------------------------------------------------------------------------------*/
    public List<Employee> getEmployeeList(String databaseName) {
        getConnection(databaseName);
        List<Employee> employeeList = new ArrayList<>();
        Employee employee;

        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                String type = rs.getString("type");
                String fullName = rs.getString("name");
                int birthYear = rs.getInt("birth_year");
                String homeTown = rs.getString("home_town");
                double salaryFactor = rs.getDouble("salary_factor");
                String facultyOrDepartment = rs.getString("faculty_department");
                String degreeOrPosition = rs.getString("degree_position");
                int quantityOrWorkingDays = rs.getInt("quantity_days");
                String username = rs.getString("username");
                if (type.equals("Lecturer")) {
                    employee = new Lecturer(fullName, birthYear, homeTown, salaryFactor,
                                            facultyOrDepartment, degreeOrPosition, quantityOrWorkingDays, username);
                } else {
                    employee = new Officer(fullName, birthYear, homeTown, salaryFactor,
                                           facultyOrDepartment, degreeOrPosition, quantityOrWorkingDays, username);
                }
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase();
        }

        return employeeList;
    }


    //-----------------------------------------------------------------------------------------------------
    public Employee getEmployeeFromDatabase(String databaseName, String username) {
        getConnection(databaseName);
        List<Employee> employeeList = getEmployeeList(databaseName);
        for (Employee employee: employeeList) {
            if (employee.getUsername().equals(username)) {
                closeDatabase();
                return employee;
            }
        }
        closeDatabase();
        return null;
    }


    //-----------------------------------------------------------------------------------------------------

    public String getCellValue(String databaseName, String queryColumn, String conditionColumn,
                               String conditionValue) {
        getConnection(databaseName);
        String returnString = null;

        try {
            ps = con.prepareStatement("SELECT * FROM employee WHERE "+ conditionColumn + "=?");
            ps.setString(1, conditionValue);

            rs = ps.executeQuery();
            while (rs.next()) {
                returnString = rs.getString(queryColumn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase();
        }
        return returnString;
    }
     /*----------------------------------------------------------------------------------------------*/
     public void updateDatabase(String databaseName, Employee employee) {
         getConnection(databaseName);
         try {
             ps = con.prepareStatement("UPDATE employee SET " +
                     "name=?, " +
                     "home_town=?, " +
                     "salary_factor=?, " +
                     "salary=?, " +
                     "faculty_department=?, " +
                     "degree_position=?, " +
                     "quantity_days=?, " +
                     "birth_year=?  WHERE username=?");
             ps.setString(1, employee.getFullName());
             ps.setString(2, employee.getHomeTown());
             ps.setDouble(3, employee.getSalaryFactor());
             ps.setDouble(4, employee.getSalary());
             if (employee instanceof Lecturer) {
                 ps.setString(5, ((Lecturer) employee).getFaculty());
                 ps.setString(6, ((Lecturer) employee).getDegree());
                 ps.setInt(7, ((Lecturer) employee).getTeachingQuantity());
             } else if (employee instanceof Officer) {
                 ps.setString(5, ((Officer) employee).getDepartment());
                 ps.setString(6, ((Officer) employee).getPosition());
                 ps.setInt(7, ((Officer) employee).getNumberOfWorkingDays());
             } else {
                 throw new IllegalArgumentException("Type of employee is wrong");
             }
             ps.setInt(8, employee.getBirthYear());
             ps.setString(9, employee.getUsername());
             ps.execute();
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             closeDatabase();
         }

     }

     public void deleteEmployee(String databaseName, String username) {
         getConnection(databaseName);
         try {
             ps = con.prepareStatement("DELETE FROM employee WHERE username=?");
             ps.setString(1, username);
             ps.execute();
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             closeDatabase();
         }
     }

    /*----------------------------------------------------------------------------------------------*/
    private void closeDatabase() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (ps !=null ) {
                ps.close();
            }
            con.close();
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
