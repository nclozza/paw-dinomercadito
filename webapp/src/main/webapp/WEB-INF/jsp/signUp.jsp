<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <%--<link rel="stylesheet" href="<c:url value="/css/style.css"/>" />--%>
</head>
<body>
<c:url value="/create" var="postPath"/>
<form:form modelAttribute="registerForm" action="${postPath}" method="post">
    <div>
        <form:label path="username"><spring:message code="username"/></form:label>
        <form:input type="text" path="username"/>
        <form:errors path="username" cssClass="formError" element="p"/>
    </div>
    <div>
        <form:label path="password"><spring:message code="password"/></form:label>
        <form:input type="password" path="password" />
        <form:errors path="password" cssClass="formError" element="p"/>
    </div>
    <div>
        <form:label path="repeatPassword"><spring:message code="repeat_password"/></form:label>
        <form:input type="password" path="repeatPassword"/>
        <form:errors path="repeatPassword" cssClass="formError" element="p"/>
    </div>
    <div>
        <form:label path="email"><spring:message code="email"/></form:label>
        <form:input type="text" path="email"/>
        <form:errors path="email" cssClass="formError" element="p"/>
    </div>
    <div>
        <form:label path="phone"><spring:message code="phone"/></form:label>
        <form:input type="text" path="phone"/>
        <form:errors path="phone" cssClass="formError" element="p"/>
    </div>
    <div>
        <form:label path="birthdate"><spring:message code="birthdate"/></form:label>
        <form:input type="text" path="birthdate"/>
        <form:errors path="birthdate" cssClass="formError" element="p"/>
    </div>
    <div>
        <input type="submit" value="<spring:message code="register"/>"/>
    </div>
</form:form>
</body>
</html>