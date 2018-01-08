package com.linhtran.employeemanage.controller;

import com.linhtran.employeemanage.model.Employee;
import com.linhtran.employeemanage.model.EmployeeHelpers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SortingServlet", urlPatterns = {"/Sort"})
public class SortingServlet extends HttpServlet {
    static boolean isSorted;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String type = request.getParameter("type");
        HttpSession session = request.getSession(false);
        String listAttrName = "employeeList";
        List<Employee> employeeList = (List<Employee>) session.getAttribute(listAttrName);

        if (!isSorted) {
            List<Employee> originList = new ArrayList<>();
            originList.addAll(employeeList);
            session.setAttribute("originList", originList);
        }


        if (type.equals("byname")) {
            EmployeeHelpers.sortByName(employeeList);
            session.setAttribute(listAttrName, employeeList);
            isSorted = true;
        } else if (type.equals("bysalary")) {
            EmployeeHelpers.sortBySalary(employeeList);
            session.setAttribute(listAttrName, employeeList);
            isSorted = true;
        } else {
            List originList = (List<Employee>) session.getAttribute("originList");
            session.setAttribute("employeeList", originList);
            isSorted = false;
        }

        response.sendRedirect("admin.jsp");


    }
}
