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
                        <li><a href="/book/list">Books list</a></li>
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
                    <form class="register" action="/api/users/register" method="post">
                        <input type="text" name="firstName" class="form-control text-input" placeholder="First name"/>
                        <input type="text" name="lastName" class="form-control text-input" placeholder="Last name"/>
                        <input type="email" name="email" class="form-control text-input" placeholder="Email address"/>
                        <input type="password" name="password" class="form-control text-input" placeholder="Password"/>
                        <input type="password" name="confirmPassword" class="form-control text-input" placeholder="Confirm password"/>
                        <div>
                            <input type="submit" class="btn btn-primary signup-btn" value="Sign up"/>
                        </div>
                    </form>
                </div>
            </div>   
        </div>           
    
    </header>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/home.js"></script>
</body>
</html>
