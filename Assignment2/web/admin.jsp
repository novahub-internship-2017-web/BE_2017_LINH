<%--
  Created by IntelliJ IDEA.
  User: tranmanhlinh
  Date: 29/12/2017
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin control panel</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/admin.css" rel="stylesheet">
</head>
<body>
<c:set var="index" value="0" scope="page" />
<h1>Admin Control Panel</h1>
<div class="search">
    <input type="text" id="search-box" placeholder="Search employee">
     Search by
    <select name="search-type" id="search-type">
        <option value="Name">Name</option>
        <option value="Birth year">Birth year</option>
        <option value="Type">Type</option>
    </select>
</div>
<div class="sort">
    <a href="Sort?type=byname" class="name-sort"><input type="button" value="Sort by name"></a>
    <a href="Sort?type=bysalary" class="salary-sort"><input type="button" value="Sort by salary"></a>
    <a href="Sort?type=origin" class="origin"><input type="button" value="Origin order"></a>
</div>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Index</th>
        <th>Username</th>
        <th>Full name</th>
        <th>Birth year</th>
        <th>Home town</th>
        <th>Type</th>
        <th>Faculty/Department</th>
        <th>Degree/Position</th>
        <th>Quantity</th>
        <th>Allowance</th>
        <th>Salary factor</th>
        <th>Salary</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.employeeList}" var="employee">
        <c:set var="index" value="${index + 1}" scope="page"/>
            <tr class="info-row">
                <td>${index}</td>
                <td class="username">${employee.username}</td>
                <td class="full-name">${employee.fullName}</td>
                <td class="birth-year">${employee.birthYear}</td>
                <td class="home-town">${employee.homeTown}</td>
                <td class="type">${employee.type}</td>
                <c:if test="${employee.type eq 'Lecturer'}">
                    <td class="faculty">${employee.faculty}</td>
                    <td class="degree">${employee.degree}</td>
                    <td class="quantity">${employee.teachingQuantity}</td>
                </c:if>
                <c:if test="${employee.type eq 'Officer'}">
                    <td class="department">${employee.department}</td>
                    <td class="position">${employee.position}</td>
                    <td class="working-days">${employee.numberOfWorkingDays}</td>
                </c:if>
                <td class="allowance">${employee.allowance}</td>
                <td class="salary-factor">${employee.salaryFactor}</td>
                <td class="salary">${employee.salary}</td>
                <td>
                    <input type="button" class="modify-btn" value="Modify">
                    <c:if test="${employee.type eq 'Lecturer' || employee.position ne 'Admin'}">
                        <a href="DeleteEmployee?username=${employee.username}">
                            <input type="button" class="delete-btn" value="Delete">
                        </a>
                    </c:if>
                </td>
            </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <form method="post" action="ModifyInformation">
        <h3>Modify employee's information</h3>
        <div id="regular-infor">
            <label for="username"></label>Username:
            <input type="text" id="username" name="username" readonly>
            <br><br>
            <input type="hidden" id="form-name" name="form-name" value="admin-form">
            <input type="hidden" id="type" name="type">

            <label for="full-name">Full name:</label>
            <input type="text" id="full-name" maxlength="20" name="full-name">
            <br><br>

            <label for="birth-year">Birth year:</label>
            <input type="number" id="birth-year" step="1" min="1960" max="1999" name="birth-year">
            <br><br>

            <label for="home-town">Home town:</label>
            <input type="text" id="home-town" maxlength="20" name="home-town">
            <br><br>

            <label for="salary-factor">Salary factor:</label>
            <input type="number" step="0.01" min="1.00" max="10.00" id="salary-factor" name="salary-factor">
            <br><br>
        </div>

        <div id="lecturer">
            <label for="faculty">Faculty:</label>
            <select id="faculty" name="faculty">
                <option value="Civil Engineering">Civil Engineering</option>
                <option value="Economy">Economy</option>
                <option value="Information Technology">Information Technology</option>
                <option value="Architecture">Architecture</option>
            </select>
            <br><br>

            <label for="degree">Degree:</label>
            <select id="degree" name="degree">
                <option value="Doctor">Doctor</option>
                <option value="Master">Master</option>
                <option value="Bachelor">Bachelor</option>
            </select>
            <br><br>

            <label for="quantity">Teaching quantity:</label>
            <input type="number" step="1" min="1" max="300" id="quantity" name="quantity">
            <br><br>
        </div>

        <div id="officer">
            <label for="department">Department:</label>
            <select id="department" name="department">
                <option value="Academic Affairs">Academic Affairs</option>
                <option value="Inspection">Inspection</option>
                <option value="General Administration">General Administration</option>
                <option value="Human Resources">Human Resources</option>
            </select>
            <br><br>

            <label for="position">Position:</label>
            <select id="position" name="position">
                <option value="Regular employee">Regular employee</option>
                <option value="Deputy of department">Deputy of department</option>
                <option value="Head of department">Head of department</option>
                <option value="Admin">Admin</option>
            </select>
            <br><br>

            <label for="working-days">Number of working days:</label>
            <input type="number" step="1" min="1" max="31" id="working-days" name="working-days">
            <br><br>
        </div>

        <div id="submit-btn">
            <input type="submit" name="submit" value="Apply">
        </div>
    </form>
</div>

<div><a href="add-employee.html"><input type="button" value="Add new employee"></a></div>
<div>
    <a href="dashboard.jsp"><input type="button" value="Dashboard"></a>
    <a href="Logout"><input type="button" value="Logout"></a>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/admin.js"></script>
</body>
</html>
