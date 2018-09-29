<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
<style type="text/css">
    <%@ include file="../assets/css/login.css" %>
</style>
<c:url value="/login" var="loginUrl"/>
<div class="central-wrapper">
    <div class="signInCenter">
        <form class="form" action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">
            <div>
                <label class="label" for="username"><spring:message code="username"/></label>
                <p/>
                <input id="username" name="j_username" type="text"/>
            </div>
            <div>
                <label class="label" for="password"><spring:message code="password"/></label>
                <p/>
                <input id="password" name="j_password" type="password"/>
            </div>
            <div>
                <label>
                    <input name="j_rememberme" type="checkbox"/>
                    <spring:message code="remember_me"/>
                </label>
            </div>
            <div>
                <button class="submitButton" type="submit"><spring:message code="submit"/></button>
            </div>
        </form>
    </div>
</div>
</body>
</html>