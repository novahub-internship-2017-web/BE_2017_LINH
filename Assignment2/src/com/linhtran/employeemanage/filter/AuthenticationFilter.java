package com.linhtran.employeemanage.filter;

import com.linhtran.employeemanage.model.Employee;
import com.linhtran.employeemanage.model.Officer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);

		if(session == null && (uri.endsWith("jsp") || uri.endsWith("add-employee.html"))){
			res.sendRedirect("login.html");
		} else if (session!=null && (uri.endsWith("add-employee.html") || uri.endsWith("admin.jsp") || uri.contains("DeleteEmployee"))) {
			Employee employee = (Employee) session.getAttribute("employee");
			String position = null;
			if (employee instanceof Officer) {
				position = ((Officer) employee).getPosition();
			}
			if (position !=null && position.equals("Admin")) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect("dashboard.jsp");
			}

		} else if (session !=null && (uri.endsWith("login.html")|| uri.equals("/"))){
			res.sendRedirect("dashboard.jsp");
		} else  {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}


	}

	public void destroy() {
		//close any resources here
	}

}
