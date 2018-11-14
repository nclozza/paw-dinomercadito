<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/products.css'/>">
    <%@ include file="favicon.jsp" %>
</head>
<body>

<!-- Navbar -->
<c:choose>
    <c:when test="${loggedIn}">
        <%@ include file="navbarLogout.jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="navbarLogin.jsp" %>
    </c:otherwise>
</c:choose>

<div class="row">
    <h1 class="title"><spring:message code="products"/></h1>
    <br>
    <div class="products">
        <div class="row flex-row">
            <c:forEach items="${products}" var="product" varStatus="loop">
                <div class="col-md-3">
                    <div class="thumbnail">
                        <a href="<c:url value="/product?filter=${filter}&&productId=${product.productId}"/>">
                            <div class="image-container">
                                <img class="product img-responsive"
                                     src="<c:url value="/images/${product.productName}.png" />"
                                     alt="Phone ${loop.index + 1}">
                            </div>
                            <div class="caption">
                                <h4><c:out value="${product.productName}"/></h4>
                                <p><c:out value="${product.brand}"/></p>
                            </div>
                            <div class="view">
                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                <spring:message code="view"/>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${zeroProducts}">
                <br>
                <p class="title"><spring:message code="zero_products"/></p>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
