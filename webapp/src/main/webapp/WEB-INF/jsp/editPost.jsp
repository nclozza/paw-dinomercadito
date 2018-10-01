<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <%--<link rel="stylesheet" href="<c:url value="/css/style.css"/>" />--%>
</head>
<body>
<c:url value="/editPost" var="postPath"/>
<form:form modelAttribute="editPost" action="${postPath}" method="post" autocomplete="off">
    <div>
        <form:label path="productId"><spring:message code="product"/></form:label>
        <form:select path="productId" name="productId">
            <c:forEach items="${productList}" var="product">
                <c:choose>
                    <c:when test="${post.productId == product.productId}">
                        <option value="${product.productId}" selected="selected">
                            <c:out value="${product.brand} - ${product.productName}"/>
                        </option>
                    </c:when>
                    <c:otherwise>
                        <option value="${product.productId}">
                            <c:out value="${product.brand} - ${product.productName}"/>
                        </option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>
    <div>
        <form:hidden path="postId" value="${post.postId}" />
    </div>
    <div>
        <form:label path="price"><spring:message code="price"/></form:label>
        <form:input type="text" path="price" value="${post.price}"/>
        <form:errors path="price" cssClass="formError" element="p"/>
    </div>
    <div>
        <form:label path="description"><spring:message code="description"/></form:label>
        <form:input type="text" path="description" value="${post.description}" />
        <form:errors path="description" cssClass="formError" element="p"/>
    </div>
    <div>
        <form:label path="productQuantity"><spring:message code="productQuantity"/></form:label>
        <form:input type="number" path="productQuantity" value="${post.productQuantity}"/>
        <form:errors path="productQuantity" cssClass="formError" element="p"/>
    </div>
    <div>
        <input type="submit" value="<spring:message code="edit_post"/>"/>
    </div>
</form:form>
</body>
</html>