<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>

<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/posts.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/review.css'/>">
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

    <div class="reviews-center">
        <div class="return">
            <a class="btn btn-primary" href="<c:url value="/post?filter=${filter}&&postId=${postId}&&profile=${profile}"/>"><spring:message code="go_back"/></a>
        </div>
        <h1 class="title"><spring:message code="reviews"/></h1>

        <div class="reviews">
            <c:choose>
                <c:when test="${empty userReviews}">
                    <br/>
                    <br/>
                    <p class="label"><spring:message code="no_reviews_available"/></p>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${userReviews}" var="userReview" varStatus="loop">
                        <div class="review">
                            <p class="label"><spring:message code="username_"/></p><p class="value"><c:out value="${userReview.userWhoReview.username}"/></p>
                            <br>
                            <p class="label"><spring:message code="rating_"/></p><p class="value"><c:out value="${userReview.rating}"/></p>
                            <br>
                            <p class="label"><spring:message code="description_"/></p>
                            <br/>
                            <p class="value"><c:out value="${userReview.description}"/></p>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <a class="btn btn btn-success button-margin button-position" role="button" href="<c:url value="/userReview?filter=${filter}&&postId=${userReview.postId}&&profile=${profile}&&userId=${userReview.userId}" />">
            <spring:message code="add_review"/>
        </a>
    </div>
</div>
</body>
</html>