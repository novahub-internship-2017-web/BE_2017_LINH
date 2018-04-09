<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<div class="navbar-div">
  <div class="container">
    <nav class="row">
      <div class="col-md-6">
        <h1 class="logo">Book Management</h1>
      </div>
      <div class="col-md-6 navbar">
        <ul class="nav navbar-nav navbar-right nav-menu">
          <li><a href="/book/list">Books list</a></li>
          <security:authorize access="hasRole('ADMIN')">
            <li><a href="/admin/user-manager">User manager</a></li>
          </security:authorize>
          <li><a href="/book/mybook">My list</a></li>
          <li><a href="/signout" onclick="return confirm('Do you really want to sign out?')">Sign out</a></li>
        </ul>
      </div>
    </nav>
  </div>
</div>
</body>
</html>