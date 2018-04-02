<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Books list</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
  <link rel="stylesheet" href="/resources/css/home.css">
  <link rel="stylesheet" href="/resources/css/bookslist-style.css">
</head>
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

<div class="container">
  <h3>User profile</h3>
  <form:form action="/user/modify" method="post" modelAttribute="user">
    <div class="form-group">
      <label for="firstName">First name:</label>
      <form:input path="firstName" type="text" id="firstName" class="form-control"
                  value="${user.firstName}"/>
    </div>

    <form:input path="email" type="hidden" id="email" class="form-control"
                value="${user.email}"/>

    <div class="form-group">
      <label for="lastName">Last name:</label>
      <form:input path="lastName" type="text" id="lastName" class="form-control"/>
    </div>

    <div class="form-group">
      <label for="currentPass">Current password:</label>
      <input type="password" name="currentPass" id="currentPass" class="form-control" placeholder="Your current password"/>
      <c:if test="${notification eq 'wrongCurrentPass'}">
        <div class="error">Wrong current password.</div>
      </c:if>
    </div>

    <div class="form-group">
      <label for="newPass">New password:</label>
      <input type="password" name="newPass"  id="newPass" class="form-control" placeholder="Your new password">
    </div>

    <div class="form-group">
      <label for="confirmNewPass">Confirm new password:</label>
      <input type="password" name="confirmNewPass" id="confirmNewPass" class="form-control" placeholder="Confirm new password">
      <c:if test="${notification eq 'wrongConfirmPass'}">
        <div class="error">New password and confirm new password did not match.</div>
      </c:if>
    </div>
    <c:if test="${updateSucceed == true}">
      <div class="error" style="color: green">Update user information successfully.</div>
    </c:if>
    <div>
      <input type="submit" class="btn btn-primary signup-btn" value="Update"/>
    </div>
  </form:form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>