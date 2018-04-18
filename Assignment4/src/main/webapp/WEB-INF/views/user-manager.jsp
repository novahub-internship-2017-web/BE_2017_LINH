<%@ page contentType="text/html;
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
  <link rel="stylesheet" href="/resources/css/dashboard.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
  <h3>User manager</h3>
  <c:if test="${users.size() != 0}">
    <table class="table table-striped">
      <thead>
      <tr>
        <th>Index</th>
        <th>Full name</th>
        <th>Email address</th>
        <th>Number of books</th>
        <th>Role</th>
        <th>Enabled</th>
        <th>Delete</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="user" items="${users}">
        <c:set var="index" value="${index + 1}" scope="page"/>
        <tr>
          <td>${user.id}</td>
          <td>${user.firstName} ${user.lastName}</td>
          <td>${user.email}</td>
          <td>${user.books.size()}</td>
          <td>${user.roleId}</td>
          <td>
            <c:if test="${user.roleId ne 1}">
            <input type="checkbox" name="enabled" id="${user.id}" <c:if test="${user.enabled == true}">checked</c:if>>
            </c:if>
          </td>
          <td>
            <c:if test="${user.roleId ne 1}">
            <a href="/admin/delete?id=${user.id}" onclick="return confirm('Do you really want to delete this user')">
              <i class="glyphicon glyphicon-remove-circle"></i>
            </a>
            </c:if>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:if>
  <c:if test="${deleteSucceed == true}">
    <div class="error" style="color: green">
      Delete user with email ${userEmail} succeed!
    </div>
  </c:if>
  <c:if test="${deleteSucceed == false}">
    <div class="error" style="color: green">
      You can not delete admin!
    </div>
  </c:if>
  <c:if test="${noResult == true}">
    <div class="error" style="color: green">No result can found!</div>
  </c:if>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/resources/js/user-manager.js"></script>
</body>
</html>