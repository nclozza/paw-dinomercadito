<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/posts.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/review.css'/>">
</head>
<body>
<c:url value="/admin/posts" var="postPath"/>
<div class="central-wrapper">
    <!-- Navbar -->
    <c:choose>
        <c:when test="${loggedIn}">
            <%@ include file="../navbarLogout.jsp" %>
        </c:when>
        <c:otherwise>
            <%@ include file="../navbarLogin.jsp" %>
        </c:otherwise>
    </c:choose>

    <div class="reviews-center">
        <div class="return">
            <a class="btn btn-primary" href="<c:url value="/admin/index"/>"><spring:message code="go_back"/></a>
            <h1 class="title"><spring:message code="posts"/></h1>
        </div>
        <div class="posts">
            <c:choose>
                <c:when test="${empty posts}">
                    <br/>
                    <br/>
                    <p class="label"><spring:message code="no_posts_available_admin"/></p>
                </c:when>
                <c:otherwise>
                        <c:forEach items="${posts}" var="post" varStatus="loop">
                            <div class="review">
                                <p class="label"><spring:message code="userSeller_"/></p><p class="value"><c:out value="${post.userSeller.username}"/></p>
                                <br>
                                <p class="label"><spring:message code="product_name"/></p><p class="value"><c:out value="${post.productPosted.productName}"/></p>
                                <br>
                                <p class="label"><spring:message code="description_"/></p>
                                <br/>
                                <p class="value"><c:out value="${post.description}"/></p>
                                <form:form modelAttribute="disableForm" action="${postPath}" method="post" autocomplete="off">
                                    <form:hidden path="postId" value="${post.postId}"/>
                                    <form:hidden path="filter" value="${filter}"/>
                                    <c:choose>
                                        <c:when test="${!post.disable}">
                                            <button class="registerButton btn btn-primary" type="submit"><spring:message code="disable"/></button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="registerButton btn btn-primary" type="submit"><spring:message code="enable"/></button>
                                        </c:otherwise>
                                    </c:choose>
                                </form:form>
                            </div>
                        </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
