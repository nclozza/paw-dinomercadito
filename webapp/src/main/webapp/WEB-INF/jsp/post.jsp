<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>

<head>
    <title>DinoMercadito | Posts</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style type="text/css">
        <%@ include file="../assets/css/posts.css" %>
    </style>
</head>
<body>
<div class="central-wrapper">
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
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

    <div class="posts-center">
        <a href="#">
            <div>
                <p><spring:message code="product_name"/><c:out value="${product.productName}"/></p>
                <p><spring:message code="price_"/><c:out value="${post.price}"/></p>
                <p><spring:message code="description_"/><c:out value="${post.description}"/></p>
                <p><spring:message code="product_quantity"/><c:out value="${post.productQuantity}"/></p>
                <p><spring:message code="username_"/><c:out value="${user.username}"/></p>
                <button type="button" class="btn btn-success"><spring:message code="buy"/></button>
            </div>
        </a>
    </div>

</div>
</body>
</html>