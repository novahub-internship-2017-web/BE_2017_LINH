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
          <li><a href="/">Home</a></li>
          <li><a href="/dashboard">Dashboard</a></li>
          <security:authorize access="hasRole('ADMIN')">
            <li><a href="/admin/user-manager">User manager</a></li>
          </security:authorize>
          <security:authorize access="isAuthenticated()">
            <li>
              <a href="#" class="dropdown-toggle">Menu</a>
              <div class="dropdown">
                <ul class="dropdown-menu">
                  <li><a id="my-list" href="#">My list</a></li>
                  <li><a href="#" data-target=".bs-example-modal-sm" data-toggle="modal">Log out</a></li>
                </ul>
              </div>
            </li>
          </security:authorize>
        </ul>
      </div>

      <div tabindex="-1" class="modal bs-example-modal-sm" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-sm">
          <div class="modal-content">
            <div class="modal-header"><h4>Logout <i class="fa fa-lock"></i></h4></div>
            <div class="modal-body"><i class="fa fa-question-circle"></i> Are you sure you want to log-off?</div>
            <div class="modal-footer"><a class="btn btn-primary btn-block" href="/signout">Logout</a></div>
          </div>
        </div>
      </div>
    </nav>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="/resources/js/header.js"></script>
</body>
</html>