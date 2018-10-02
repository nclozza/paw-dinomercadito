<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>DinoMercadito | Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style type="text/css">
        <%@ include file="../assets/css/products.css" %>
    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">DinoMercadito</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Menu</a></li>
                <li><a href="#">Sign Up</a></li>
                <li><a href="#">Login</a></li>
            </ul>
        </div>
    </div>
</nav>

        <div class="col-md-3">
            <div class="sidebar">
                <div class="row">
                    <div class="button-group" role="group" aria-label="Operative systems for filter">
                        <c:forEach items="${operativeSystems}" var="operativeSystem" varStatus="loop">
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-secondary btn-lg" data-toggle="button">${operativeSystem}</button>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="row">
                    <div class="button-group" role="group" aria-label="Brands for filter">
                        <c:forEach items="${brands}" var="brand" varStatus="loop">
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-secondary btn-lg" data-toggle="button">${brand}</button>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="row">
                    <div class="button-group" role="group" aria-label="Minimum RAM sizes for filter">
                        <c:forEach items="${ramSizes}" var="minimumRamSize" varStatus="loop">
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-secondary btn-lg" data-toggle="button">${minimumRamSize}</button>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="row">
                    <div class="button-group" role="group" aria-label="Minimum ROM sizes for filter">
                        <c:forEach items="${storageSizes}" var="minimumStorageSize" varStatus="loop">
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-secondary btn-lg" data-toggle="button">${minimumStorageSize}</button>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <c:forEach items="${products}" var="product" varStatus="loop">
                <div class="col-md-3">
                    <div class="thumbnail">
                        <a href="<c:url value="/posts?productId=${product.productId}"/>">
                            <img src="#" alt="Phone ${loop.index + 1}" style="width:100%">
                            <div class="caption">
                                <p>Phone #${loop.index + 1}</p>
                                <p><c:out value="${product.productName}"/></p>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<div class="container">
    <h2 class="title">Products</h2>
    <hr>
    <div class="sidenav col-md-2">
        <a href="#about">About</a>
        <a href="#services">Services</a>
        <a href="#clients">Clients</a>
        <a href="#contact">Contact</a>
    </div>

    <div class="col-md-10">
        <div class="row product-row">
            <c:forEach items="${products}" var="product" varStatus="loop">
                <div class="col-md-3">
                    <div class="thumbnail">
                            <img class="product img-responsive" src="" alt="Phone ${loop.index + 1}">
                            <div class="caption">
                                <h4><c:out value="${product.productName}"/></h4>
                                <p><c:out value="${product.brand}"/></p>
                            </div>
                            <div class="view">
                                <a href="#"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> View</a>
                            </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>
