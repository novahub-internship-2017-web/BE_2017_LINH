package com.linhtran.employeemanage.controller;

import com.linhtran.employeemanage.filter.ValidateInput;
import com.linhtran.employeemanage.model.Employee;
import com.linhtran.employeemanage.model.Lecturer;
import com.linhtran.employeemanage.model.Officer;
import com.linhtran.employeemanage.security.Encrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "AddEmployeeServlet", urlPatterns = {"/AddEmployee.do"})
public class AddEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String typeOfEmployee = request.getParameter("employee-type");
        String fullName = request.getParameter("name");
        int birthYear = Integer.parseInt(request.getParameter("birth-year"));
        String homeTown = request.getParameter("home-town");
        double salaryFactor = Double.parseDouble(request.getParameter("salary-factor"));
        DatabaseQuery databaseQuery = new DatabaseQuery();


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Employee employee;
        //Check username if available
        RequestDispatcher rd = request.getRequestDispatcher("add-employee.html");
        PrintWriter out = response.getWriter();
        String testString = databaseQuery.getCellValue("EmployeeDB", "home_town", "username", username);
        if (testString != null) {
            out.println("<font color=red>This username is not available. Please try another one</font>");
            rd.include(request, response);
        } else {
            if (typeOfEmployee.equals("lecturer")) {
                String faculty = request.getParameter("faculty");
                String degree = request.getParameter("degree");
                int teachingQuantity = Integer.parseInt(request.getParameter("quantity"));
                employee = new Lecturer(fullName, birthYear, homeTown, salaryFactor, faculty,
                        degree, teachingQuantity, username);
            } else if (typeOfEmployee.equals("officer")){
                String department = request.getParameter("department");
                String position = request.getParameter("position");
                int numberOfWorkingDays = Integer.parseInt(request.getParameter("working-days"));
                employee = new Officer(fullName, birthYear, homeTown, salaryFactor, department,
                        position, numberOfWorkingDays, username);
            } else {
                throw new IOException("Type of employee is wrong.");
            }

            //Validate input
            ValidateInput.checkEmployeeProperties(employee);


            //Convert password to hash
            String passHash = Encrypt.encryptPass(password);


            databaseQuery.insertDatabase("EmployeeDB", employee, passHash);
            List<Employee> employeeList = databaseQuery.getEmployeeList("EmployeeDB");
            HttpSession session = request.getSession();
            session.setAttribute("employeeList", employeeList);
            response.sendRedirect("admin.jsp");
        }

    }

}
