package com.linhtran.employeemanage.controller;

import com.linhtran.employeemanage.model.Employee;
import com.linhtran.employeemanage.model.Officer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", urlPatterns = {"/Admin"})
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatabaseQuery databaseQuery = new DatabaseQuery();
        String databaseName = "EmployeeDB";
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null || !(employee instanceof Officer) || !((Officer) employee).getPosition().equalsIgnoreCase("admin")) {
            response.sendRedirect("dashboard.jsp");
        } else {
            List<Employee> employeeList = databaseQuery.getEmployeeList(databaseName);
            request.setAttribute("employeeList", employeeList);
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
        }
    }
}
