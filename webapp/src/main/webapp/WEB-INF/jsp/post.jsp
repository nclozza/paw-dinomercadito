<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <h1 class="title"><spring:message code="details"/></h1>
        <div class="posts-left">
            <!-- ACA IRIA LA FOTO -->
        </div>
        <div class="posts-right">
            <div class="details">
                <h3><spring:message code="product_name"/><c:out value="${product.productName}"/></h3>
                <h3><spring:message code="price_"/><c:out value="${post.price}"/></h3>
                <h3><spring:message code="description_"/><c:out value="${post.description}"/></h3>
                <h3><spring:message code="product_quantity"/><c:out value="${post.productQuantity}"/></h3>
                <h3><spring:message code="seller_username_"/><c:out value="${user.username}"/></h3>

                <%--TODO Ask this--%>
                <%--<c:url value="/post?postId=${post.postId}" var="postPath"/>--%>

                <c:url value="/post" var="postPath"/>
                <form:form modelAttribute="transactionForm" action="${postPath}" method="post" autocomplete="off">

                    <form:label path="productQuantity" class="product-quantity-label"><spring:message code="product_quantity"/></form:label>
                    <form:input type="number" path="productQuantity"/>

                    <form:hidden path="postId" value="${post.postId}"/>

                    <input type="submit" class="btn btn-success" value="<spring:message code="buy"/>"/>

                    <form:errors class="error" path="productQuantity" element="p"><br><spring:message
                            code="product_quantity_error"/></form:errors>

                    <c:if test="${funds_error}">
                        <p><spring:message code="funds_error"/></p>
                    </c:if>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>