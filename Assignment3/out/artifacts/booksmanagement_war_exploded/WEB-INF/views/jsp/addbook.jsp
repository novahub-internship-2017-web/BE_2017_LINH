<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new book</title>
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
          <li><a href="/book/list">Book list </a></li>
          <li><a href="/user/profile">Your profile</a></li>
          <li><a href="/book/mybook">My list </a></li>
          <security:authorize access="hasRole('ADMIN')">
            <li><a href="/admin/user-manager">User manager</a></li>
          </security:authorize>
          <li><a href="/signout" onclick="return confirm('Do you really want to sign out?')">Sign out</a></li>
        </ul>
      </div>
    </nav>
  </div>
</div>
<div class="container">
  <h3>Adding new book </h3>
  <form action="/book/add" method="POST">
    <input type="text" name="title" id="title" class="form-control text-input" placeholder="Book's title">
    <c:if test="${emptyTitle == true}">
      <div class="error">Title of the book must not be empty.</div>
    </c:if>
    <input type="text" name="author" id="author" class="form-control text-input" placeholder="Author">
    <c:if test="${emptyAuthor == true}">
      <div class="error">Author of the book must not be empty.</div>
    </c:if>
    <textarea name="description" class="form-control text-input"></textarea>
    <c:if test="${isSucceed eq true}">
      <p style="color:green">Adding new book successful!</p>
    </c:if>
    <input type="submit" class="btn btn-primary signup-btn" value="Save book">
  </form>

</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>