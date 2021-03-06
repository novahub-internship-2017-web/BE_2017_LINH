<%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<body>
<!-- Upload Modal -->
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
                <form method="post" action="/uploadBookCover" id="upload-form" enctype="multipart/form-data">
                    Select file : <input type="file" name="file" id="file" class="form-control"><br>
                    <input type="hidden" name="id" enabled="${book.id}">
                    <input type="submit" class="btn btn-info" enabled='Upload' id='upload'>
                </form>
                <!-- Preview-->
                <div id='preview'></div>
            </div>
        </div>

    </div>
</div>

</body>
</html>