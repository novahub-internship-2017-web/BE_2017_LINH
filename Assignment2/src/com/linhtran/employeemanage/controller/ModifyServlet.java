package com.linhtran.employeemanage.controller;

import com.linhtran.employeemanage.filter.ValidateInput;
import com.linhtran.employeemanage.model.Employee;
import com.linhtran.employeemanage.model.Lecturer;
import com.linhtran.employeemanage.model.Officer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ModifyServlet", urlPatterns = {"/ModifyInformation"})
public class ModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        DatabaseQuery databaseQuery = new DatabaseQuery();
        String formType = request.getParameter("form-name");
        //Modify as admin


        if (formType != null) {
            String username = request.getParameter("username");
            String fullName = request.getParameter("full-name");
            Employee modifyingEmployee = databaseQuery.getEmployeeFromDatabase("EmployeeDB", username);
            modifyingEmployee.setFullName(request.getParameter("full-name"));
            modifyingEmployee.setBirthYear(Integer.parseInt(request.getParameter("birth-year")));
            modifyingEmployee.setHomeTown(request.getParameter("home-town"));
            modifyingEmployee.setSalaryFactor(Double.parseDouble(request.getParameter("salary-factor")));
            if (modifyingEmployee instanceof Lecturer) {
                ((Lecturer) modifyingEmployee).setFaculty(request.getParameter("faculty"));
                ((Lecturer) modifyingEmployee).setDegree(request.getParameter("degree"));
                ((Lecturer) modifyingEmployee).setTeachingQuantity(Integer.parseInt(request.getParameter("quantity")));
            } else if (modifyingEmployee instanceof Officer) {
                ((Officer) modifyingEmployee).setDepartment(request.getParameter("department"));
                ((Officer) modifyingEmployee).setPosition(request.getParameter("position"));
                ((Officer) modifyingEmployee).setNumberOfWorkingDays(Integer.parseInt(request.getParameter("working-days")));
            }
            ValidateInput.checkEmployeeProperties(modifyingEmployee);
            databaseQuery.updateDatabase("EmployeeDB", modifyingEmployee);
            List<Employee> employeeList = databaseQuery.getEmployeeList("EmployeeDB");
            session.setAttribute("employeeList", employeeList);
            response.sendRedirect("admin.jsp");
        } else {

            //Modify as a regular client
            String parameterNames = request.getParameterNames().nextElement();
            String parameter = request.getParameter(parameterNames);
            switch (parameterNames) {
                //------------------------------------------------------------
                //Modify from dashboard
                case "name":
                    employee.setFullName(parameter);
                    break;
                case "year":
                    int birthYear = Integer.parseInt(parameter);
                    employee.setBirthYear(birthYear);
                    break;
                case "town":
                    employee.setHomeTown(parameter);
                    break;
                //---------------------------------------------------------
                //Modify from admin control-panel
                default:

                    break;
            }
            ValidateInput.checkEmployeeProperties(employee);
            databaseQuery.updateDatabase("EmployeeDB", employee);
            response.sendRedirect("Admin");
        }

    }

}
