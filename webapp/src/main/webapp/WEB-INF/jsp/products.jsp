<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/products.css'/>">
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
    <div class="col-md-3">
        <div class="sidenav">
            <h2 class="title"><spring:message code="filters"/></h2>
            <br>
            <div class="row flex-row">
                <h4 class="title"><spring:message code="os_filter"/></h4>
                <div class="button-group" role="group" aria-label="Operative systems for filter">
                    <c:forEach items="${operativeSystems}" var="operativeSystem" varStatus="loop">
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-secondary btn-lg button-margin"
                                    data-toggle="button">${operativeSystem}</button>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <br>
            <div class="row flex-row">
                <h4 class="title"><spring:message code="brand_filter"/></h4>
                <div class="button-group" role="group" aria-label="Brands for filter">
                    <c:forEach items="${brands}" var="brand" varStatus="loop">
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-secondary btn-lg button-margin"
                                    data-toggle="button">${brand}</button>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <br>
            <div class="row flex-row">
                <h4 class="title"><spring:message code="ram_filter"/></h4>
                <div class="button-group" role="group" aria-label="Minimum RAM sizes for filter">
                    <c:forEach items="${ramSizes}" var="minimumRamSize" varStatus="loop">
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-secondary btn-lg button-margin"
                                    data-toggle="button">${minimumRamSize}</button>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <br>
            <div class="row flex-row">
                <h4 class="title"><spring:message code="rom_filter"/></h4>
                <div class="button-group" role="group" aria-label="Minimum ROM sizes for filter">
                    <c:forEach items="${storageSizes}" var="minimumStorageSize" varStatus="loop">
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-secondary btn-lg button-margin"
                                    data-toggle="button">${minimumStorageSize}</button>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-10 products">
        <div class="row flex-row">
            <c:forEach items="${products}" var="product" varStatus="loop">
                <div class="col-md-3">
                    <div class="thumbnail">
                        <div class="image-container">
                            <img class="product img-responsive" src="" alt="Phone ${loop.index + 1}">
                        </div>
                        <div class="caption">
                            <h4><c:out value="${product.productName}"/></h4>
                            <p><c:out value="${product.brand}"/></p>
                        </div>
                        <div class="view">
                            <a href="<c:url value="/posts?productId=${product.productId}"/>"><span
                                    class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> View</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>
