<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">
</head>
<body>

<c:url value="/authentication" var="authenticationPath"/>
<div class="central-wrapper">
    <div class="signInCenter">
        <form:form class="form" modelAttribute="authenticationForm" action="${authenticationPath}" method="post">
            <div>
                <form:label path="code"><spring:message code="code"/></form:label>
                <p/>
                <form:input path="code" type="text"/>
                <form:errors path="code" element="p"><p/><spring:message
                        code="code_error"/></form:errors>
            </div>
            <div>
                <button class="submitButton" type="submit"><spring:message code="validate"/></button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>