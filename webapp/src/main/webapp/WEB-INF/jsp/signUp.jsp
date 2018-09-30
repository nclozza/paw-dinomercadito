<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style type="text/css">
        <%@ include file="../assets/css/login.css" %>
    </style>
</head>
<body>

<c:url value="/signUp" var="postPath"/>

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
    <div class="signUpCenter">
        <h1><spring:message code="sign_up"/></h1>
        <form:form class="form" modelAttribute="registerForm" action="${postPath}" method="post">
            <div class="form-group">
                <form:label class="label" path="username"><spring:message code="username"/></form:label>
                <form:input type="text" path="username" class="form-control"/>
                <form:errors path="username" element="p"><br><spring:message
                        code="username_error"/><br></form:errors>
            </div>
            <div class="form-group">
                <form:label class="label" path="password"><spring:message code="password"/></form:label>
                <form:input type="password" path="password" class="form-control"/>
                <form:errors class="error" path="password" element="p"><br><spring:message
                        code="username_error"/><br></form:errors>
            </div>
            <div class="form-group">
                <form:label class="label" path="repeatPassword"><spring:message code="repeat_password"/></form:label>
                <form:input type="password" path="repeatPassword" class="form-control"/>
                <form:errors class="error" path="repeatPassword" element="p"/>
            </div>
            <div class="form-group">
                <form:label class="label" path="email"><spring:message code="email"/></form:label>
                <form:input type="text" path="email" class="form-control"/>
                <form:errors class="error" path="email" element="p"><br><spring:message
                        code="email_error"/><br></form:errors>
            </div>
            <div class="form-group">
                <form:label class="label" path="phone"><spring:message code="phone"/></form:label>
                <form:input type="text" path="phone" class="form-control"/>
                <form:errors class="error" path="phone" element="p"><br><spring:message
                        code="phone_error"/><br></form:errors>
            </div>
            <div class="form-group">
                <form:label class="label" path="birthdate"><spring:message code="birthdate"/></form:label>
                <form:input type="text" path="birthdate" class="form-control"/>
                <form:errors class="error" path="birthdate" element="p"><br><spring:message
                        code="birthdate_error"/><br></form:errors>
            </div>
            <div>
                <button class="registerButton btn btn-primary" type="submit"><spring:message code="register"/></button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>