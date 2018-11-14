<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/posts.css'/>">
    <%@ include file="favicon.jsp" %>
</head>
<body>
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

    <div class="posts-center">
        <div class="return">
            <a class="btn btn-primary" href="<c:url value="/products?filter=${filter}"/>"><spring:message code="go_back"/></a>
        </div>
        <h1 class="title"><spring:message code="specifications"/></h1>
        <div class="posts-left">
            <div class="image-container">
                <img class="image" src="<c:url value="/images/${product.productName}.png" />"
                     alt="${product.productName}"/>
            </div>
        </div>
        <div class="posts-right">
            <div class="details">
                <h3 class="label"><spring:message code="product_name"/></h3>
                <h3 class="value"><c:out value="${product.productName}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="brand_"/></h3>
                <h3 class="value"><c:out value="${product.brand}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="ram_"/></h3>
                <h3 class="value"><c:out value="${product.ram}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="storage_"/></h3>
                <h3 class="value"><c:out value="${product.storage}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="operative_system_"/></h3>
                <h3 class="value"><c:out value="${product.operativeSystem}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="processor_"/></h3>
                <h3 class="value"><c:out value="${product.processor}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="rear_camera_"/></h3>
                <h3 class="value"><c:out value="${product.rearCamera}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="front_camera_"/></h3>
                <h3 class="value"><c:out value="${product.frontCamera}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="screen_size_"/></h3>
                <h3 class="value"><c:out value="${product.screenSize}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="screen_ratio_"/></h3>
                <h3 class="value"><c:out value="${product.screenRatio}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="body_size_"/></h3>
                <h3 class="value"><c:out value="${product.bodySize}"/></h3>
                <br>
                <br>
                <a class="btn btn-success button-margin" role="button" href="<c:url value="/posts?filter=${filter}&&productId=${product.productId}" />">
                    <spring:message code="search_post"/>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
