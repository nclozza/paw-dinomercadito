<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<style type="text/css">
    <%@ include file="../assets/css/login.css" %>
</style>
<body>
<c:url value="/signUp" var="postPath"/>
<div class="central-wrapper">
    <div class="signUpCenter">
        <form:form class="form" modelAttribute="registerForm" action="${postPath}" method="post">
            <div>
                <form:label class="label" path="username"><spring:message code="username"/></form:label>
                <form:input type="text" path="username"/>
                <form:errors path="username" element="p"><p/><spring:message
                        code="username_error"/></form:errors>
                <c:if test="${username_repeated}">
                    <p><spring:message code="username_repeated_error"/></p>
                </c:if>
            </div>
            <div>
                <form:label class="label" path="password"><spring:message code="password"/></form:label>
                <form:input type="password" path="password"/>
                <form:errors class="error" path="password" element="p"><p/><spring:message
                        code="username_error"/></form:errors>
            </div>
            <div>
                <form:label class="label" path="repeatPassword"><spring:message code="repeat_password"/></form:label>
                <form:input type="password" path="repeatPassword"/>
                <form:errors class="error" path="repeatPassword" element="p"/>
            </div>
            <div>
                <form:label class="label" path="email">
                    <spring:message code="email"/>
                </form:label>
                <form:input type="text" path="email"/>
                <form:errors class="error" path="email" element="p"><p/><spring:message
                        code="email_error"/></form:errors>
            </div>
            <div>
                <form:label class="label" path="phone"><spring:message code="phone"/></form:label>
                <form:input type="text" path="phone"/>
                <form:errors class="error" path="phone" element="p"><p/><spring:message
                        code="phone_error"/></form:errors>
            </div>
            <div>
                <form:label class="label" path="birthdate"><spring:message code="birthdate"/></form:label>
                <form:input type="text" path="birthdate" placeholder = "dd-mm-yyyy"/>
                <form:errors class="error" path="birthdate" element="p"><p/><spring:message
                        code="birthdate_error"/></form:errors>
            </div>
            <div>
                <button class="registerButton" type="submit"><spring:message code="register"/></button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>