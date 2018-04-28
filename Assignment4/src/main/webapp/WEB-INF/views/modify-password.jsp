<%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<body>
<!-- Modal add book -->
<div class="modal fade" id="password-modify" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="modalLabel">Change password</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" class="pass-modify">
                    <div class="form-group">
                        <label for="current-password">Current password: </label>
                        <input type="password" name="current-password" id="current-password" class="form-control text-input" placeholder="Current password">
                    </div>

                    <div class="form-group">
                        <label for="new-password">New password: </label>
                        <input type="password" name="new-password" id="new-password" class="form-control text-input" placeholder="New password">
                    </div>

                    <div class="form-group">
                        <label for="confirm-password">Confirm new password: </label>
                        <input type="password" name="confirm-password" id="confirm-password" class="form-control text-input" placeholder="Confirm new password">
                    </div>

                    <input type="submit" id="submit-change-password" class="hidden">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <label for="submit-change-password" class="btn btn-primary">Update</label>
            </div>
        </div>
    </div>
</div>

</body>
</html>