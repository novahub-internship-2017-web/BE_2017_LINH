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
<jsp:include page="header.jsp"/>
<div class="container">
  <h3>List of books</h3>

  <%--Only login user could see this form--%>
  <security:authorize access="isAuthenticated()">
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
  </security:authorize>

    <table class="hidden">
      <thead>
      <tr>
        <th>Index  </th>
        <th class="clickable" id="th-title">Title  <i class="glyphicon glyphicon-sort-by-alphabet"></i></th>
        <th class="clickable" id="th-author">Author <i class="glyphicon glyphicon-sort-by-alphabet"></i></th>
        <th class="clickable" id="th-created">Created by <i class="glyphicon glyphicon-sort-by-alphabet"></i></th>
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
      <security:authorize access="isAuthenticated()">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addBookModal">
          Add new book
        </button>
      </security:authorize>
      <jsp:include page="addbook-modal.jsp"/>
    </div>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/resources/js/event-handler.js"></script>
<script src="/resources/js/dashboard.js"></script>
</body>
</html>