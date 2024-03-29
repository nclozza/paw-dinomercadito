<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <a class="btn btn-primary" href="<c:url value="/product?filter=${filter}&&productId=${product.productId}"/>"><spring:message code="go_back"/></a>
        </div>
        <h1 class="title"><spring:message code="posts"/></h1>


        <div class="posts-left">
            <div class="image-container">
                <img class="image" src="<c:url value="/images/${product.productName}.png" />"
                     alt="${product.productName}"/>
            </div>
        </div>
        <div class="posts-right">
            <c:choose>
                <c:when test="${empty posts}">
                    <p class="responsive-label"><spring:message code="no_posts_available"/></p>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${posts}" var="post" varStatus="loop">
                        <a href="<c:url value="/post?filter=${filter}&&postId=${post.postId}"/>">
                            <div class="post">
                                <p class="label"><spring:message code="price_"/></p><p class="value"><c:out value="${post.price}"/></p>
                                <br>
                                <p class="label"><spring:message code="visits"/></p><p class="value"><c:out value="${post.visits}"/></p>
                                <br>
                                <p class="label"><spring:message code="user_rating_"/></p><p class="value">
                                <c:choose>
                                    <c:when test="${post.userSeller.rating >= 0}">
                                        <c:out value="${post.userSeller.rating}"/>
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>
                                </p>
                                <br>
                                <p class="label"><spring:message code="product_quantity"/></p><p class="value"><c:out value="${post.productQuantity}"/></p>
                                <br>
                                <br>
                                <button type="button" class="btn btn-success button-margin"><spring:message code="view"/></button>
                            </div>
                        </a>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </div>
    </div>

</div>
</body>
</html>