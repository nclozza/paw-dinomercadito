<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>DinoMercadito | Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/profile.css'/>">

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

    <div>
        ESTOS BOTONES DEBERIAN IR AL COSTADO IZQUIERDO, COMO EN MELI
        <aside>
            <a id="transactions-button" class="btn btn-info" role="button">
                <spring:message code="show_transactions"/>
            </a>
            <a id="edit-profile-button" class="btn btn-info" role="button">
                <spring:message code="edit_profile"/>
            </a>
            <a id="posts-button" class="btn btn-info" role="button">
                <spring:message code="show_posts"/>
            </a>
            <a class="btn btn-info" role="button" href="<c:out value="/profile/addFunds" />">
                <spring:message code="show_add_funds"/>
            </a>
        </aside>

        <%-- TRANSACTIONS --%>
        <div id="transactions">
            TRANSACTIONS
            <c:forEach items="${transactions}" var="transaction" varStatus="loop">
                <div class="post">
                    <p><spring:message code="product_name"/><c:out value="${transaction.productName}"/></p>
                    <p><spring:message code="product_quantity"/><c:out value="${transaction.productQuantity}"/></p>
                    <p><spring:message code="price_"/><c:out value="${transaction.price}"/></p>
                </div>
            </c:forEach>
        </div>

        <%-- EDIT PROFILE --%>
        <div id="edit-profile">
            EDIT
            <c:url value="/profile" var="postPath"/>
            <form:form class="form" modelAttribute="updateUserForm" action="${postPath}" method="post">
                <div>
                    <form:label class="label" path="password"><spring:message code="password"/></form:label>
                    <form:input type="password" path="password"/>
                    <c:if test="${password_error}">
                        <p><spring:message code="username_error"/></p>
                    </c:if>
                </div>
                <div>
                    <form:label class="label" path="repeatPassword"><spring:message
                            code="repeat_password"/></form:label>
                    <form:input type="password" path="repeatPassword"/>
                    <c:if test="${repeat_password}">
                        <p><spring:message code="repeat_password_error"/></p>
                    </c:if>
                </div>
                <div>
                    <form:label class="label" path="email">
                        <spring:message code="email"/>
                    </form:label>
                    <form:input type="text" path="email" value="${user.email}"/>
                    <form:errors class="error" path="email" element="p"><p/><spring:message
                            code="email_error"/></form:errors>
                </div>
                <div>
                    <form:label class="label" path="phone"><spring:message code="phone"/></form:label>
                    <form:input type="text" path="phone" value="${user.phone}"/>
                    <form:errors class="error" path="phone" element="p"><p/><spring:message
                            code="phone_error"/></form:errors>
                </div>
                <div>
                    <form:label class="label" path="birthdate"><spring:message code="birthdate"/></form:label>
                    <form:input type="text" path="birthdate" value="${user.birthdate}"/>
                    <form:errors class="error" path="birthdate" element="p"><p/><spring:message
                            code="birthdate_error"/></form:errors>
                </div>
                <div>
                    <button class="registerButton" type="submit"><spring:message code="register"/></button>
                </div>
            </form:form>
        </div>

        <%-- POSTS --%>
        <div id="posts">
            POSTS
            <c:forEach items="${posts}" var="post" varStatus="loop">
                <div class="post">
                    <p><spring:message code="product_name"/><c:out value="${post.postId}"/></p>
                    <p><spring:message code="description_"/><c:out value="${post.description}"/></p>
                    <a class="btn btn-info" role="button" href="<c:url value="/editPost?postId=${post.postId}" />">
                        <spring:message code="edit"/>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        if (${formError} || ${repeat_password} || ${password_error}) {
            $("#transactions, #posts").hide();
        } else {
            $("#edit-profile, #posts").hide();
        }

        $("#transactions-button").click(function () {
            $("#edit-profile, #posts").hide();
            $("#transactions").show();
        });

        $("#edit-profile-button").click(function () {
            $("#transactions, #posts").hide();
            $("#edit-profile").show();
        });

        $("#posts-button").click(function () {
            $("#transactions, #edit-profile").hide();
            $("#posts").show();
        });
    });
</script>

</body>
</html>
