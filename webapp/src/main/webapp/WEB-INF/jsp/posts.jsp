<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>

<head>
    <title>DinoMercadito | Posts</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/posts.css'/>">
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
        <h1 class="title"><spring:message code="posts"/></h1>
        <div class="posts-left">
            <div class="image-container">
                <!--ACA IRIA LA IMAGEN-->
                <img class="image" src=""/>
            </div>
        </div>
        <div class="posts-right">
            <c:forEach items="${posts}" var="post" varStatus="loop">
                <a href="<c:url value="/post?postId=${post.postId}"/>">
                    <div class="post">
                        <p><spring:message code="product_quantity"/><c:out value="${post.productQuantity}"/></p>
                        <p><spring:message code="price_"/><c:out value="${post.price}"/></p>
                        <button type="button" class="btn btn-success"><spring:message code="view"/></button>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>

</div>
</body>
</html>