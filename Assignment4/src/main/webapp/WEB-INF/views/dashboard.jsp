<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Books list</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard.css">
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
          <li><a href="/book/mybook">My list</a></li>
          <li><a href="/user/profile">User manager</a></li>
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
  <h3>List of books</h3>
  <form class="row search-form" action="/book/search" method="post">
    <div class="col-md-3">
      <div>
        <select name="search-type" class="form-control search">
          <option value="by-title">Search by title</option>
          <option value="by-author">Search by author</option>
        </select>
      </div>
    </div>
    <div class="col-md-9">
      <div class="input-group">
        <input name="search-value" type="text" class="search-query form-control" placeholder="Search" />
        <span class="input-group-btn">
          <button class="btn btn-primary search-btn" type="submit">
            <span class=" glyphicon glyphicon-search"></span>
          </button>
        </span>
      </div>
    </div>
  </form>
    <table class="hidden">
      <thead>
      <tr>
        <th>Index</th>
        <th>Title</th>
        <th>Author</th>
        <th>Created by</th>
        <th>Detail</th>
      </tr>
      </thead>
      <%--<tbody>--%>
        <%--<tr>--%>
          <%--<td></td>--%>
          <%--<td></td>--%>
          <%--<td></td>--%>
          <%--<td></td>--%>
          <%--<td><a href="#"><i class="glyphicon glyphicon-folder-open"></i></a></td>--%>
        <%--</tr>--%>
      <%--</tbody>--%>
    </table>


  <%--<c:if test="${noResult == true}">--%>
    <%--<div class="error" style="color: green">No result can found!</div>--%>
  <%--</c:if>--%>
  <div class="row">
    <div class="col-md-12">
      <!-- Button trigger modal -->
      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addBookModal">
        Add new book
      </button>
      <jsp:include page="addbook-modal.jsp"/>
    </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/resources/js/dashboard.js"></script>
</body>
</html>