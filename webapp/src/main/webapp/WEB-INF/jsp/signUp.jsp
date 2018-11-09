<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">

</head>
<body>

<div class="central-wrapper">
<c:url value="/signUp" var="postPath"/>
    <!-- Navbar -->
    <c:choose>
        <c:when test="${loggedIn}">
            <%@ include file="navbarLogout.jsp" %>
        </c:when>
        <c:otherwise>
            <%@ include file="navbarLogin.jsp" %>
        </c:otherwise>
    </c:choose>


    <div class="big-container">
        <h1><spring:message code="sign_up"/></h1>
        <form:form class="form" modelAttribute="registerForm" action="${postPath}" method="post">
            <div class="form-group">
                <form:label class="label" path="username"><spring:message code="username"/></form:label>
                <form:input type="text" path="username" class="form-control"/>
                <form:errors path="username" element="p"><br><spring:message
                        code="username_error"/><br></form:errors>
                <c:if test="${sameUsername_error}">
                    <p><spring:message code="sameUsername_error"/></p>
                </c:if>
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
                <form:errors class="error" path="repeatPassword" element="p"><br><spring:message
                        code="repeat_password_error"/><br></form:errors>
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
                <form:input id ="dateInput" type="date" path="birthdate" class="form-control"/>
                <form:errors class="error" path="birthdate" element="p"><br><spring:message
                        code="birthdate_error"/><br></form:errors>
            </div>
            <div>
                <a href="<c:url value="/index"/>" class="btn-lg btn"><spring:message code="cancel"/></a>
                <button class="btn-lg btn btn-primary" type="submit"><spring:message code="register"/></button>
            </div>
            <br>
            <div>
                <a href="<c:url value="/login"/> "><spring:message code="login_redirect"/></a><br>
                <a href="<c:url value="/authentication"/> "><spring:message code="auth_redirect"/></a>
            </div>
        </form:form>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        date();
    });

    //Set max date for today
    function date() {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!
        var yyyy = today.getFullYear();
        if(dd<10){
            dd='0'+dd
        }
        if(mm<10){
            mm='0'+mm
        }

        today = yyyy+'-'+mm+'-'+dd;
        document.getElementById("dateInput").setAttribute("max", today);
    }
</script>

</body>
</html>