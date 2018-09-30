<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <div>
            <p><spring:message code="product_name"/><c:out value="${product.productName}"/></p>
            <p><spring:message code="price_"/><c:out value="${post.price}"/></p>
            <p><spring:message code="description_"/><c:out value="${post.description}"/></p>
            <p><spring:message code="product_quantity"/><c:out value="${post.productQuantity}"/></p>
            <p><spring:message code="seller_username_"/><c:out value="${user.username}"/></p>

            <%--TODO Ask this--%>
            <%--<c:url value="/post?postId=${post.postId}" var="postPath"/>--%>

            <c:url value="/post" var="postPath"/>
            <form:form modelAttribute="transactionForm" action="${postPath}" method="post" autocomplete="off">

                <form:label path="productQuantity"><spring:message code="product_quantity"/></form:label>
                <form:input type="number" path="productQuantity"/>

                <form:hidden path="postId" value="${post.postId}"/>

                <input type="submit" class="btn btn-success" value="<spring:message code="buy"/>"/>

                <form:errors class="error" path="productQuantity" element="p"><p/><spring:message
                        code="product_quantity_error"/></form:errors>

                <c:if test="${found_error}">
                    <p><spring:message code="found_error"/></p>
                </c:if>
            </form:form>

        </div>
    </div>
</div>
</body>
</html>