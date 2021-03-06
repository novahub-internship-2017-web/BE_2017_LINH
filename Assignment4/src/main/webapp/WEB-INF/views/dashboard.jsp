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
  <%--<div class="row">--%>
    <%--<div class="col col-md-4">--%>
            <%----%>
    <%--</div>--%>
    <%--<div class="col col-md-4">--%>

    <%--</div>--%>
    <%--<div class="col col-md-4">--%>
      <%----%>
    <%--</div>    --%>
  <%--</div>--%>
  <h3 id="list-name">List of books</h3>
    <form class="row search-form" action="/book/search" method="post">
      <div class="col-md-12">
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
  <div class="maxbooks-select">
    Display
    <select class="form-control" id="max-books">
      <option value="5">5</option>
      <option value="10">10</option>
      <option value="15">15</option>
    </select>
       books per page.
  </div>

    <table class="hidden">
      <thead>
      <tr>
        <th>Index  </th>
        <th class="clickable" id="th-title">Title  <i class="glyphicon"></i></th>
        <th class="clickable" id="th-author">Author <i class="glyphicon"></i></th>
        <th class="clickable" id="th-created">Created by <i class="glyphicon"></i></th>
        <th>Action</th>
        <c:if test="${user.roleId == 1}">
          <th>Enabled</th>
        </c:if>
      </tr>
      </thead>
    </table>
  <ul class="pagination" id="page">
    <li class="first"><a href="#">First</a></li>
    <li class="previous"><a href="#">Previous</a></li>
    <li class="page-number"><a href="#">1</a></li>
    <li class="next"><a href="#">Next</a></li>
    <li class="last"><a href="#">Last</a></li>
  </ul>
  <div>This list have <span id="total-books"></span> books in total.</div>

  <div class="row">
    <div class="col-md-12">
      <!-- Button trigger modal -->
      <security:authorize access="isAuthenticated()">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addBookModal">
          Add new book
        </button>
      </security:authorize>
      <jsp:include page="addbook-modal.jsp"/>
      <jsp:include page="modify-user.jsp"/>
      <jsp:include page="modify-password.jsp"/>
    </div>
  </div>
  <%--Logged in user id--%>
  <input type="hidden" id="userId" value="${user.id}">
  <input type="hidden" id="roleId" value="${user.roleId}">
  <input type="hidden" id="firstName" value="${user.firstName}">
  <input type="hidden" id="lastName" value="${user.lastName}">
  <input type="hidden" id="email" value="${user.email}">

  <%--Page information--%>
  <input type="hidden" id="sortType" value="unsorted">
  <input type="hidden" id="isMyList">

    <%--Confirm deleting book--%>
  <div tabindex="-1" class="modal confirm-delete" role="dialog" aria-hidden="true">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <div class="modal-header"><h4>Confirm deleting book <i class="fa fa-lock"></i></h4></div>
          <div class="modal-body"><i class="fa fa-question-circle"></i> Are you sure to delete this book?</div>
          <div class="modal-footer"><a class="btn btn-primary btn-block" href="#">Ok</a></div>
        </div>
      </div>
  </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/event-handler.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/edit-profile.js"></script>
</body>
</html>