package com.linhtran.employeemanage.controller;

import com.linhtran.employeemanage.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchEmployeeServlet", urlPatterns = {"/SearchEmployee"})
public class SearchEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String searchText = request.getParameter("search-text").trim().toLowerCase();
        String searchType = request.getParameter("search-type");
        DatabaseQuery databaseQuery = new DatabaseQuery();
        String databaseName = "EmployeeDB";
        List<Employee> originEmployeeList = databaseQuery.getEmployeeList(databaseName);
        List<Employee> employeeList = new ArrayList<>();


        for (Employee employee : originEmployeeList) {
            switch (searchType) {
                case "Name":
                    String fullName = employee.getFullName().toLowerCase();
                    if (fullName.contains(searchText)) {
                        employeeList.add(employee);
                    }
                    break;
                case "Birth year":
                    String birthyear = employee.getBirthYear() + "";
                    if (birthyear.contains(searchText)) {
                        employeeList.add(employee);
                    }
                    break;
                case "Type":
                    String type = employee.getType().toLowerCase();
                    if (type.contains(searchText)) {
                        employeeList.add(employee);
                    }
                    break;
                case "Home town":
                    String homeTown = employee.getHomeTown().toLowerCase();
                    if (homeTown.contains(searchText)) {
                        employeeList.add(employee);
                    }
            }
        }

        if (employeeList.isEmpty()) {
            String msg = "No result";
            request.setAttribute("msg", msg);
        }
        request.setAttribute("employeeList", employeeList);
        RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
        rd.forward(request, response);
    }

}
