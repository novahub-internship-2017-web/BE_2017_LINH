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

<jsp:include page="header.jsp"/>

<div class="container">
  <h3>Detail of book</h3>

  <div class="row detail-box">
    <div class="col col-md-6">
      <div class="img-container">
        <img src="${book.imageUrl}" alt="book picture">
      </div>
      <%--Button display if this book belong to user--%>
      <security:authorize access="isAuthenticated()">
        <security:authentication var="principal" property="principal" />
      </security:authorize>
      <c:if test="${principal.username eq book.user.email}">
        <div class="upload-container">
          <button type="button" class="btn btn-info signup-btn" data-toggle="modal" data-target="#uploadModal">Upload file</button>
        </div>
      </c:if>
    </div>
    <div class="col col-md-6">
      <h2 class="title">${book.title}</h2>
      <input type="hidden" id="book-id" value="${book.id}">
      <div class="author">Written by <span>${book.author}</span></div>
      <div class="date">Created at: <span>${book.createdAt}</span></div>
      <div class="date">Updated at: <span>${book.updatedAt}</span></div>
      <div class="created-by">Created by: <span>${book.user.email}</span></div>
      <c:if test="${permission == false}">
        <div style="color: red">You are not permitted to modify this book</div>
      </c:if>
      <div>
        <c:if test="${principal.username eq book.user.email}">
          <button type="button" id="modify-book" class="btn btn-primary" data-toggle="modal" data-target="#addBookModal">
            Modify book
          </button>
        </c:if>
      </div>
    </div>

    <div class="col col-md-12">
      Description:
      <div>
        ${book.description}
      </div>
    </div>
  </div>
  <div class="row comments">
      <form class="col col-md-12" id="post-comment" action="/api/comment">
        <div>
          <label for="comment-box">COMMENTS</label>
        </div>
        <div>
          <textarea name="comment-box" id="comment-box" rows="4" cols="100"></textarea>
        </div>
        <input type="submit" id="submit-comment" class="hidden">
      </form>
    <div class="col col-md-12 comment hidden">
      <div class="user">
        Linh
      </div>
      <div class="comment-content">
        This book is awesome!
      </div>
    </div>
  </div>
</div>

<jsp:include page="addbook-modal.jsp"/>
<jsp:include page="upload-modal.jsp"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/event-handler.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/book-detail.js"></script>
</body>
</html>