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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/book-detail.css">
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
  <h3>Detail of book</h3>
  <div class="row panel panel-default box">
    <div class="col col-md-6">
      <div class="row">
        <img src="${book.imageUrl}" alt="book picture" class="pull-right">
      </div>

      <security:authorize access="isAuthenticated()">
        <security:authentication var="principal" property="principal" />
      </security:authorize>

      <c:if test="${principal.username eq book.user.email}">
        <div class="row">
          <button type="button" class="btn btn-info signup-btn pull-right" data-toggle="modal" data-target="#uploadModal">Upload file</button>
        </div>
      </c:if>

      <!-- Modal -->
      <div id="uploadModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">File upload form</h4>
            </div>
            <div class="modal-body">
              <!-- Form -->
              <form method="post" action="/uploadImage?id=${book.id}" enctype="multipart/form-data">
                Select file : <input type="file" name="file" id="file" class="form-control"><br>
                <input type="submit" class="btn btn-info" value='Upload' id='upload'>
              </form>

              <!-- Preview-->
              <div id='preview'></div>
            </div>

          </div>

        </div>
      </div>

    </div>
    <div class="col col-md-6">
      <h2 class="title">${book.title}</h2>
      <div class="author">Written by <span>${book.author}</span></div>
      <div class="date">Created at: <span>${book.createdAt}</span></div>
      <div class="date">Updated at: <span>${book.updatedAt}</span></div>
      <div class="created-by">Created by: <span>${book.user.email}</span></div>
      <div class="created-by">
        Description:
        <div>
          ${book.description}
        </div>
      </div>
      <c:if test="${permission == false}">
        <div style="color: red">You are not permitted to modify this book</div>
      </c:if>
      <div><a href="/book/modify?id=${book.id}" class="btn btn-primary signup-btn">Modify</a></div>
    </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>