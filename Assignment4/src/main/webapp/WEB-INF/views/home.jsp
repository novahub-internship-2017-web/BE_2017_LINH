<%@ page contentType="text/html;
charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home page</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Patua+One" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard.css">
</head>
<body>  
    <header>       
        <div class="container">
            <nav class="row">
                <div class="col-md-6">
                    <h1 class="logo">Book Management</h1>
                </div>
                <div class="col-md-6 navbar">
                    <ul class="nav navbar-nav navbar-right nav-menu">
                        <li class="active"><a href="/">Home</a></li>
                        <li><a href="/dashboard">Books list</a></li>
                    </ul>
                </div>
            </nav>
            <div class="row">
                <div class="col-md-7 hero-text">
                    <h1>SO MANY BOOKS, SO LITTE TIME</h1>
                </div>
                <div class="col-md-5 signup-form"> 
                    <h4>Already have a account? Please sign in</h4>
                    <form action="signin" method="post" >                          
                        <input type="email" name="email" class="form-control text-input" placeholder="Enter your email address">
                        <input type="password" name="password" class="form-control text-input" placeholder="Your password">
                        <c:if test="${loginFailured == true}">
                            <div class="error">Your username or password are wrong.</div>
                        </c:if>
                        <c:if test="${enabledUser == false}">
                            <div class="error">Your account is blocked! Please contact to admin to open.</div>
                        </c:if>
                        <input type="submit" class="btn btn-primary signup-btn" value="Sign in">
                    </form>
                    <h4>New here? Create a new account!</h4>
                    <<form:form action="register" method="post" modelAttribute="user">
                    <form:input path="firstName" type="text" class="form-control text-input" placeholder="First name"/>
                    <form:errors path="firstName" class="error"/>
                    <form:input path="lastName" type="text" class="form-control text-input" placeholder="Last name"/>
                    <form:errors path="lastName" class="error"/>
                    <form:input path="email" type="email" class="form-control text-input" placeholder="Email address"/>
                    <form:errors path="email" class="error"/>
                    <form:input path="password" type="password" class="form-control text-input" placeholder="Password"/>
                    <form:errors path="password" class="error"/>
                    <form:input path="confirmPassword" type="password" class="form-control text-input" placeholder="Confirm password"/>
                    <c:if test="${confirmError == true}">
                        <div class="error">Password and confirm password is not match!</div>
                    </c:if>
                    <c:if test="${registerSucceed == true}">
                        <div class="error" style="color: green">Register succeed!</div>
                    </c:if>
                    <c:if test="${isTakenEmail == true}">
                        <div class="error" >This email is registered! Please chose another one.</div>
                    </c:if>
                    <div>
                        <input type="submit" class="btn btn-primary signup-btn" value="Sign up"/>
                    </div>
                </form:form>
                </div>
            </div>   
        </div>           
    
    </header>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
