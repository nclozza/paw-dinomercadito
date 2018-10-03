<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">
</head>
<body>

<c:url value="/newPost" var="postPath"/>
<div class="central-wrapper">
    <!-- Navbar -->
    <c:choose>
        <c:when test="${loggedIn}">
            <%@ include file="navbarLogout.jsp" %>
        </c:when>
        <c:otherwise>
            <%@ include file="navbarLogin.jsp" %>
        </c:otherwise>
    </c:choose>

    <div class="small-container">
        <h1><spring:message code="create_post"/></h1>
        <br>
        <form:form modelAttribute="registerForm" action="${postPath}" method="post" autocomplete="off">
            <div class="form-group">
                <label class="label" for="productId"><spring:message code="product"/></label>
                <select id="productId" name="productId" class="form-control input-size">
                    <c:forEach items="${productList}" var="product">
                        <option value="${product.productId}">
                            <c:out value="${product.brand} - ${product.productName}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <form:label class="label" path="price"><spring:message code="price"/></form:label>
                <form:input type="text" path="price" class="form-control"/>
                <form:errors path="price" cssClass="formError" element="p"/>
            </div>
            <div class="form-group">
                <form:label class="label" path="description"><spring:message code="description"/></form:label>
                <form:textarea path="description" class="form-control input-size"/>
                <form:errors path="description" cssClass="formError" element="p"/>
            </div>
            <div class="form-group">
                <form:label class="label" path="productQuantity"><spring:message code="productQuantity"/></form:label>
                <form:input type="number" path="productQuantity" class="form-control input-size"/>
                <form:errors path="productQuantity" cssClass="formError" element="p"/>
            </div>
            <br>
            <div>
                <input class="left-button btn" value="<spring:message code="cancel"/>"/>
                <input type="submit" class="btn btn-primary" value="<spring:message code="create_post"/>"/>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>