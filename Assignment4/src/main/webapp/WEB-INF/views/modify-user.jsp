<%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<body>
<!-- Modal add book -->
<div class="modal fade" id="profile-modify" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="modalLabel">Modify user information</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" class="user-modify">
                    <div class="form-group">
                        <label for="user-email">Your email: </label>
                        <div id="user-email"></div>
                    </div>

                    <div class="form-group">
                        <label for="first-name">First name:</label>
                        <input type="text" name="first-name" id="first-name" class="form-control text-input" placeholder="First name">
                    </div>

                    <div class="form-group">
                        <label for="last-name">Last name</label>
                        <input type="text" name="last-name" id="last-name" class="form-control text-input" placeholder="Last name">
                    </div>

                    <input type="submit" id="submit-change-profile" class="hidden">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <label for="submit-change-profile" class="btn btn-primary">Update</label>
            </div>
        </div>
    </div>
</div>

</body>
</html>