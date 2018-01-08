package com.linhtran.employeemanage.controller;

import com.linhtran.employeemanage.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteEmployeeServlet", urlPatterns = {"/DeleteEmployee"})
public class DeleteEmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        DatabaseQuery databaseQuery = new DatabaseQuery();
        String username = request.getParameter("username");
        databaseQuery.deleteEmployee("EmployeeDB", username);
        List<Employee> employeeList = databaseQuery.getEmployeeList("EmployeeDB");
        HttpSession session = request.getSession();
        session.setAttribute("employeeList", employeeList);
        response.sendRedirect("admin.jsp");
    }
}
