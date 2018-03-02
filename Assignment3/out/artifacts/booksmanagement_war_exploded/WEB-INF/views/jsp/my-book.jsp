<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
          <li><a href="/book/add">Add new book </a></li>
          <li><a href="/book/list">Book list </a></li>
          <li><a href="/signout" onclick="return confirm('Do you really want to sign out?')">Sign out</a></li>
        </ul>
      </div>
    </nav>
  </div>
</div>
<c:set var="index" value="0" scope="page" />
<div class="container">
  <h3>My list of books</h3>
  <c:if test="${books.size() != 0}">
    <table class="table table-striped">
      <thead>
      <tr>
        <th>Index</th>
        <th>Title</th>
        <th>Author</th>
        <th>Detail</th>
        <th>Modify</th>
        <th>Delete</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="book" items="${books}">
        <c:set var="index" value="${index + 1}" scope="page"/>
        <tr>
          <td>${index}</td>
          <td>${book.title}</td>
          <td>${book.author}</td>
          <td class="col-md-1"><a href="/book/detail?id=${book.id}"><i class="glyphicon glyphicon-folder-open"></i></a></td>
          <td class="col-md-1"><a href="/book/modify?id=${book.id}"><i class="glyphicon glyphicon-edit"></i></a></td>
          <td class="col-md-1"><a href="/book/delete?id=${book.id}" onclick="return confirm('Are you sure?')"><i class="glyphicon glyphicon-remove-circle"></i></a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>    
  </c:if>
  <c:if test="${books.size() == 0}">
    <h3>There are no book in your list! Please adding new one.</h3>
  </c:if>
  
  <div class="row">
    <div class="col-md-12">
      <a href="/book/add" class="btn btn-primary">Add new book</a>
    </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>