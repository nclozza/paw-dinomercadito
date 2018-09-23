<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <%--<link rel="stylesheet" href="<c:url value="/css/style.css"/>" />--%>
</head>
<body>
<c:url value="/createPost" var="postPath"/>
<form:form modelAttribute="registerForm" action="${postPath}" method="post" autocomplete="off">
    <div>
        <spring:message code="product"/>
        <select name="productId">
            <c:forEach items="${productList}" var="product">
                <option value="${product.productId}">
                    <c:out value="${product.brand} - ${product.productName}"/>
                </option>
            </c:forEach>
        </select>
    </div>
    <div>
        <form:label path="price"><spring:message code="price"/></form:label>
        <form:input type="text" path="price"/>
        <form:errors path="price" cssClass="formError" element="p"/>
    </div>
    <div>
        <form:label path="description"><spring:message code="description"/></form:label>
        <form:textarea path="description"/>
        <form:errors path="description" cssClass="formError" element="p"/>
    </div>
    <div>
        <form:label path="productQuantity"><spring:message code="productQuantity"/></form:label>
        <form:input type="number" path="productQuantity"/>
        <form:errors path="productQuantity" cssClass="formError" element="p"/>
    </div>
    <div>
        <input type="submit" value="<spring:message code="create_post"/>"/>
    </div>
</form:form>
</body>
</html>