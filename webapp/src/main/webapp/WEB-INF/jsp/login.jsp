<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style type="text/css">
        <%@ include file="../assets/css/login.css" %>
    </style>
</head>
<body>

<c:url value="/login" var="loginUrl"/>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">DinoMercadito</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Menu</a></li>
                <li><a href="#">Sign Up</a></li>
                <li><a href="#">Login</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="central-wrapper">
    <div class="signInCenter">
        <h1><spring:message code="login"/></h1>
        <form class="form" action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">
            <div class="form-group">
                <label class="label" for="username"><spring:message code="username"/></label>
                <input id="username" name="j_username" type="text" class="form-control"/>
            </div>
            <div class="form-group">
                <label class="label" for="password"><spring:message code="password"/></label>
                <input id="password" name="j_password" type="password" class="form-control"/>
            </div>
            <div class="checkbox">
                <label class="checkbox-label">
                    <input name="j_rememberme" type="checkbox"/>
                    <spring:message code="remember_me"/>
                </label>
            </div>
            <div>
                <button class="submitButton btn btn-primary" type="submit"><spring:message code="submit"/></button>
            </div>
        </form>
    </div>
</div>
</body>
</html>