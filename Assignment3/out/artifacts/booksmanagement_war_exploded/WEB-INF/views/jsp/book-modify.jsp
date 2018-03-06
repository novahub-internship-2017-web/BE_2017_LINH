<%@ page language="java" contentType="text/html; 
charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Books list</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
  <link rel="stylesheet" href="/resources/css/home.css">
  <link rel="stylesheet" href="/resources/css/bookslist-style.css">
  <link rel="stylesheet" href="/resources/css/book-modify.css">
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
          <li><a href="/user/profile">Your profile</a></li>
          <security:authorize access="hasRole('ADMIN')">
            <li><a href="/admin/user-manager">User manager</a></li>
          </security:authorize>
          <li><a href="/book/list">Books list</a></li>
          <li><a href="/book/mybook">My list</a></li>
          <li><a href="/signout" onclick="return confirm('Do you really want to sign out?')">Sign out</a></li>
        </ul>
      </div>
    </nav>
  </div>
</div>

<div class="container">
  <form:form action="/book/modify" method="post" modelAttribute="book">
    <form:input path="id" type="hidden" class="form-control text-input" value="${book.id}"/>
    <form:input path="title" type="text" class="form-control text-input" value="${book.title}"/>
    <form:errors path="title" class="error"/>
    <form:input path="author" type="text" class="form-control text-input" value="${book.author}"/>
    <form:errors path="author" class="error"/>
    <form:textarea path="description" type="text" class="form-control text-input" value="${book.description}"/>
    <c:if test="${updated == true}">
      <div style="color:green;">Your book is updated successfully.</div>
    </c:if>
    <div>
      <input type="submit" class="btn btn-primary signup-btn" value="Update book"/>
    </div>
  </form:form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>