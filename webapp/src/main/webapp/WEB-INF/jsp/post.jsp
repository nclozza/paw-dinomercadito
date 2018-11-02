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
            <c:choose>
                <c:when test="${profile}">
                    <a class="btn btn-primary" href="<c:url value="/profile"/>"><spring:message code="go_back"/></a>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-primary" href="<c:url value="/posts?filter=${filter}&&productId=${post.productPosted.productId}"/>"><spring:message code="go_back"/></a>
                </c:otherwise>
            </c:choose>
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
                <h3 class="label"><spring:message code="product_quantity"/></h3>
                <h3 class="value"><c:out value="${post.productQuantity}"/></h3>
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
                <h3 class="label"><spring:message code="rating_"/></h3>
                <h3 class="value"><c:out value="${user.rating}"/>   </h3>
                <br>
                <br>
                <c:if test="${!same_user}">
                    <a class="btn btn btn-success button-margin" role="button" href="<c:url value="/buy?filter=${filter}&&postId=${post.postId}&&profile=${profile}" />">
                        <spring:message code="buy"/>
                    </a>
                </c:if>
                <c:if test="${alreadyBuy}">
                    <a class="btn btn btn-success button-margin" role="button" href="<c:url value="/userReview?filter=${filter}&&postId=${post.postId}&&profile=${profile}&&userId=${post.userSeller.userId}" />">
                        <spring:message code="add_review"/>
                    </a>
                </c:if>
                <c:if test="${!same_user}">
                    <a class="btn btn btn-success button-margin" role="button" href="<c:url value="/question?filter=${filter}&&profile=${profile}&&postId=${post.postId}" />">
                        <spring:message code="ask"/>
                    </a>
                </c:if>
                <a class="btn btn btn-success button-margin" role="button" href="<c:url value="/userReviews?filter=${filter}&&postId=${post.postId}&&profile=${profile}&&userId=${post.userSeller.userId}" />">
                    <spring:message code="find_reviews"/>
                </a>
                <a class="btn btn btn-success button-margin" role="button" href="<c:url value="/questions?filter=${filter}&&profile=${profile}&&postId=${post.postId}" />">
                    <spring:message code="find_questions"/>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>