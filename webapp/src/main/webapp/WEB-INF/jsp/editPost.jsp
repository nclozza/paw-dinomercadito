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
<c:url value="/editPost" var="postPath"/>
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
        <h1><spring:message code="edit_post"/></h1>
        <br>
        <form:form modelAttribute="editPost" action="${postPath}" method="post" autocomplete="off">
            <div class="form-group">
                <form:label path="productId" class="label"><spring:message code="product"/></form:label>
                <form:select path="productId" name="productId" class="form-control input-size">
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
                <form:hidden path="postId" value="${post.postId}"/>
            </div>
            <div class="form-group">
                <form:label path="price" class="label"><spring:message code="price"/></form:label>
                <form:input type="text" path="price" value="${post.price}" class="form-control"/>
                <form:errors path="price" cssClass="formError" element="p"/>
            </div>
            <div class="form-group">
                <form:label path="description" class="label"><spring:message code="description"/></form:label>
                <form:input type="text" path="description" value="${post.description}" class="form-control"/>
                <form:errors path="description" cssClass="formError" element="p"/>
            </div>
            <div class="form-group">
                <form:label path="productQuantity" class="label"><spring:message code="product_quantity"/></form:label>
                <form:input type="text" path="productQuantity" value="${post.productQuantity}" class="form-control"/>
                <form:errors path="productQuantity" cssClass="formError" element="p"><spring:message code="product_quantity_error"/></form:errors>
            </div>
            <div>
                <a class="left-button btn" href="<c:url value="/profile"/>"><spring:message code="cancel"/></a>
                <input type="submit" class="btn btn-primary"
                       value="<spring:message code="edit_post"/>"/>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>