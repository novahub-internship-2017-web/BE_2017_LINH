package com.linhtran.employeemanage.controller;

import com.linhtran.employeemanage.model.Employee;
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

@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        out.println("<font color=red>Username or password are wrong</font>");
        String databaseName = "EmployeeDB";
        boolean result = false;
        DatabaseQuery databaseQuery = new DatabaseQuery();


        try {
            String pass = databaseQuery.getCellValue(databaseName, "password",
                                                       "username", username);
            result = pass.equals(Encrypt.encryptPass(password));

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rq = request.getRequestDispatcher("login.html");


        if (!result) {
            rq.include(request, response);
        } else {
            Employee employee = databaseQuery.getEmployeeFromDatabase(databaseName, username);
            HttpSession session = request.getSession();
            session.setAttribute("employee", employee);
            if (employee instanceof Officer && ((Officer) employee).getPosition().equalsIgnoreCase("admin")) {
                 response.sendRedirect("Admin");
            } else {
                response.sendRedirect("dashboard.jsp");
            }
        }


    }
}
