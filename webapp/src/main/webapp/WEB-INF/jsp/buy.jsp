<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/posts.css'/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%@ include file="favicon.jsp" %>
</head>
<body>
<c:url value="/buy" var="postPath"/>
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

    <div class="buy-container">
        <h1><spring:message code="buy"/></h1>
        <br>
        <form:form modelAttribute="transactionForm" action="${postPath}" method="post" autocomplete="off">
            <div class="form-group">
                <form:hidden path="postId" value="${post.postId}"/>
                <form:hidden path="filter" value="${filter}"/>
                <form:hidden path="profile" value="${profile}"/>

                <form:label path="productQuantity" class="label"><spring:message code="productQuantity"/></form:label>
                <form:select path="productQuantity" class="form-control input-size">
                    <c:forEach var = "cant" begin = "1" end = "${post.productQuantity}">
                        <option value="${cant}">${cant}</option>
                    </c:forEach>
                </form:select>
                <form:errors path="productQuantity" cssClass="formError" element="p"><br><spring:message
                        code="productQuantity_error"/><br></form:errors>
                <br/>
                <c:if test="${same_user}">
                    <p><spring:message code="post_error"/></p>
                </c:if>
                <br/>
            </div>
            <div>
                <a class="left-button btn" href="<c:url value="/post?filter=${filter}&&postId=${post.postId}&&profile=${profile}"/>"><spring:message code="cancel"/></a>
                <input type="button" class="btn btn-primary" data-toggle="modal" data-target="#confirmModal" value="<spring:message code="buy"/>"/>
            </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="confirmModal" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><spring:message code="confirmation"/></h4>
                </div>
                <div class="modal-body">
                    <p><spring:message code="confirmation_ask"/></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"/></button>
                    <input type="submit" class="btn btn-primary"  value="<spring:message code="confirm"/>"/>
                </div>
            </div>
        </div>
    </div>
    </form:form>
</div>
</body>
</html>
