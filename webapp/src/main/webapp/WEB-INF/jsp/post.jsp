<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title><spring:message code="DinoMercadito"/></title>
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
        <div class="return">
            <a class="btn btn-primary" href="<c:url value="/posts?productId=${post.productId}"/>"><spring:message code="go_back"/></a>
        </div>
        <h1 class="title"><spring:message code="details"/></h1>
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
                <h3 class="label"><spring:message code="price_"/></h3>
                <h3 class="value"><c:out value="${post.price}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="description_"/></h3>
                <h3 class="value"><c:out value="${post.description}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="visits"/></h3>
                <h3 class="value"><c:out value="${post.visits}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="seller_username_"/></h3>
                <h3 class="value"><c:out value="${user.username}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="email_"/></h3>
                <h3 class="value"><c:out value="${user.email}"/></h3>
                <br>
                <br>
                <h3 class="label"><spring:message code="phone_"/></h3>
                <h3 class="value"><c:out value="${user.phone}"/></h3>
                <br>
                <br>
                <%--TODO Ask this--%>
                <%--<c:url value="/post?postId=${post.postId}" var="postPath"/>--%>

                <%--<c:url value="/post" var="postPath"/>--%>
                <%--<form:form modelAttribute="transactionForm" action="${postPath}" method="post" autocomplete="off">--%>

                    <%--<form:label path="productQuantity" class="label"><spring:message--%>
                            <%--code="product_quantity"/></form:label>--%>
                    <%--<form:input type="number" path="productQuantity"/>--%>

                    <%--<form:hidden path="postId" value="${post.postId}"/>--%>

                    <%--<br>--%>
                    <%--<br>--%>
                    <%--<input type="submit" class="btn btn-success btn-lg button-margin" value="<spring:message code="buy"/>"/>--%>

                    <%--<form:errors class="error" path="productQuantity" element="p"><br><spring:message--%>
                            <%--code="product_quantity_error"/></form:errors>--%>
                    <%--<form:errors class="error" path="postId" element="p"><br><spring:message--%>
                            <%--code="post_error"/></form:errors>--%>

                    <%--<c:if test="${funds_error}">--%>
                        <%--<p><spring:message code="funds_error"/></p>--%>
                    <%--</c:if>--%>
                <%--</form:form>--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>