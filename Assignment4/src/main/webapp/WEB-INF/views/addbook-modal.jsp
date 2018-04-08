<%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<body>
<!-- Modal add book -->
<div class="modal fade" id="addBookModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="modalLabel">ADD NEW BOOK</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" class="addbook-form">
                    <label for="title">Title: </label>
                    <input type="text" name="title" id="title" class="form-control text-input" placeholder="Book's title">
                    <label for="author">Author: </label>
                    <input type="text" name="author" id="author" class="form-control text-input" placeholder="Author">
                    <label for="description">Description: </label>
                    <textarea name="description" id="description" class="form-control text-input" placeholder="Book's description"></textarea>
                    <input type="submit" id="submit-form" class="hidden">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <label for="submit-form" class="btn btn-primary">Save book</label>
            </div>
        </div>
    </div>
</div>

</body>
</html>