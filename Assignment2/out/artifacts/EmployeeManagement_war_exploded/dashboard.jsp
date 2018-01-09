<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<h1 class="header"><c:out value="${sessionScope.employee.username}"/>'s Profile</h1>
<section class="profile">
    <div class="container">
        <div class="row full-name">
            <div class="col col-sm-4 field-name">Full name</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.fullName}"/></div>
            <div class="col col-sm-2 modify"><input name="name" type="button" class="modify-btn" value="Modify"></div>
        </div>
        <div class="row birth-year">
            <div class="col col-sm-4 field-name">Birth year</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.birthYear}"/></div>
            <div class="col col-sm-2 modify"><input name="year" type="button" class="modify-btn" value="Modify"></div>
        </div>

        <div class="row home-town">
            <div class="col col-sm-4 field-name">Home town</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.homeTown}"/></div>
            <div class="col col-sm-2 modify"><input name="town" type="button" class="modify-btn" value="Modify"></div>
        </div>

        <div class="row type">
            <div class="col col-sm-4 field-name">Type</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.type}"/></div>
        </div>

        <%--Display field for lecturer--%>
        <c:if test="${sessionScope.employee.type=='Lecturer'}">
        <div class="row faculty lecturer">
            <div class="col col-sm-4 field-name">Faculty</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.faculty}"/></div>
        </div>

        <div class="row degree lecturer">
            <div class="col col-sm-4 field-name">Degree</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.degree}"/></div>
        </div>

        <div class="row quantity lecturer">
            <div class="col col-sm-4 field-name">Teaching quantity</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.teachingQuantity}"/></div>
        </div>
        </c:if>

        <%--Display field for officer--%>
        <c:if test="${sessionScope.employee.type=='Officer'}">
        <div class="row department officer">
            <div class="col col-sm-4 field-name">Department</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.department}"/></div>
        </div>

        <div class="row position officer">
            <div class="col col-sm-4 field-name">Officer</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.position}"/></div>
        </div>

        <div class="row working-days officer">
            <div class="col col-sm-4 field-name">Number of working days</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.numberOfWorkingDays}"/></div>
        </div>
        </c:if>


        <div class="row salary-factor">
            <div class="col col-sm-4 field-name">Salary factor</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.salaryFactor}"/></div>
        </div>

        <div class="row salary">
            <div class="col col-sm-4 field-name">Salary</div>
            <div class="col col-sm-6 value"><c:out value="${sessionScope.employee.salary}"/></div>
        </div>
    </div>
</section>
 <div class="col-sm logout">
     <a class="btn btn-primary" href="Logout">Logout</a>
     <c:if test="${(sessionScope.employee.type eq 'Officer') && (sessionScope.employee.position eq 'Admin')}">
     <a class="btn btn-info" href="Admin">Admin control panel</a>
     </c:if>
 </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="js/dashboard.js"></script>
</body>
</html>
