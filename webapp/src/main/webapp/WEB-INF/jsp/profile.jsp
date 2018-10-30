<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/profile.css'/>">

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

    <div>
        <h1 class="tittle"><spring:message code="profile"/></h1>
        <div class="left-container">
            <ul class="nav nav-pills nav-stacked">
                <li>
                    <button id="edit-profile-button" class="btn btn-primary button">
                        <spring:message code="edit_profile"/>
                    </button>
                </li>
                <li>
                    <button id="posts-button" class="btn btn-primary button">
                        <spring:message code="show_posts"/>
                    </button>
                </li>
                <li>
                    <button id="questions-button" class="btn btn-primary button">
                        <spring:message code="questions"/>
                    </button>
                </li>
            </ul>
        </div>

        <%-- TRANSACTIONS --%>
        <%--<div class="right-container">--%>
            <%--<div id="transactions">--%>
                <%--<h2><spring:message code="show_transactions"/></h2>--%>
                <%--<br>--%>
                <%--<c:choose>--%>
                    <%--<c:when test="${empty transactions}">--%>
                        <%--<p><spring:message code="no_transactions"/></p>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<c:forEach items="${transactions}" var="transaction" varStatus="loop">--%>
                            <%--<div class="post">--%>
                                <%--<p><spring:message code="product_name"/><c:out value="${transaction.productName}"/></p>--%>
                                <%--<p><spring:message code="visits"/><c:out value="${transaction.visits}"/></p>--%>
                                <%--<p><spring:message code="price_"/><c:out value="${transaction.price}"/></p>--%>
                            <%--</div>--%>
                        <%--</c:forEach>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%-- EDIT PROFILE --%>
        <div class="right-container">
            <div id="edit-profile" class="padding">
                <h2><spring:message code="edit_profile"/></h2>
                <br>
                <c:url value="/profile" var="postPath"/>
                <form:form class="form" modelAttribute="updateUserForm" action="${postPath}" method="post">
                    <div>
                        <div class="form-group">
                            <form:label class="label" path="password"><spring:message code="password"/></form:label>
                            <form:input type="password" path="password" class="form-control"/>
                            <c:if test="${password_error}">
                                <p><spring:message code="username_error"/></p>
                            </c:if>
                            <br/>
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <form:label class="label" path="repeatPassword"><spring:message
                                    code="repeat_password"/></form:label>
                            <form:input type="password" path="repeatPassword" class="form-control"/>
                            <c:if test="${repeat_password}">
                                <p><spring:message code="repeat_password_error"/></p>
                            </c:if>
                            <br/>
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <form:label class="label" path="email">
                                <spring:message code="email"/>
                            </form:label>
                            <form:input type="text" path="email" value="${user.email}" class="form-control"/>
                            <form:errors class="error" path="email" element="p"><br><spring:message
                                    code="email_error"/></form:errors>
                            <br/>
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <form:label class="label" path="phone"><spring:message code="phone"/></form:label>
                            <form:input type="text" path="phone" value="${user.phone}" class="form-control"/>
                            <form:errors class="error" path="phone" element="p"><br><spring:message
                                    code="phone_error"/></form:errors>
                            <br/>
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <form:label class="label" path="birthdate"><spring:message code="birthdate"/></form:label>
                            <form:input id="dateInput" type="date" path="birthdate" value="${user.birthdate}" class="form-control"/>
                            <form:errors class="error" path="birthdate" element="p"><br><spring:message
                                    code="birthdate_error"/></form:errors>
                            <br/>
                        </div>
                    </div>
                    <div>
                        <button class="registerButton btn btn-primary" type="submit"><spring:message
                                code="register"/></button>
                    </div>
                </form:form>
            </div>
        </div>

        <%-- POSTS --%>
        <div class="right-container">
            <div id="posts">
                <h2><spring:message code="show_posts"/></h2>
                <br>
                <c:choose>
                    <c:when test="${empty posts}">
                        <p><spring:message code="no_posts"/></p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${posts}" var="post" varStatus="loop">
                            <div class="post">
                                <p><spring:message code="product_name"/><c:out value="${post.product.productName}"/></p>
                                <p><spring:message code="description_"/><c:out value="${post.description}"/></p>
                                <p><spring:message code="visits"/><c:out value="${post.visits}"/></p>
                                <a class="btn btn-primary" role="button" href="<c:url value="/editPost?postId=${post.postId}" />">
                                    <spring:message code="edit"/>
                                </a>
                                <a class="btn btn-primary" role="button" href="<c:url value="/post?postId=${post.postId}&&profile=true" />">
                                    <spring:message code="view"/>
                                </a>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <%-- QUESTIONS --%>
        <div class="right-container">
            <div id="questions">
                <h2><spring:message code="questions"/></h2>
                <br>
                <c:choose>
                    <c:when test="${empty questions}">
                        <p><spring:message code="no_questions"/></p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${questions}" var="question" varStatus="loop">
                            <div class="post">
                                <p><spring:message code="product_name"/><c:out value="${question.postToAsk.product.productName}"/></p>
                                <p><spring:message code="description_"/><c:out value="${question.postToAsk.description}"/></p>
                                <p><spring:message code="from_"/><c:out value="${question.userWhoAsk.username}"/></p>
                                <p><spring:message code="question_"/><c:out value="${question.question}"/></p>
                                <a class="btn btn-primary" role="button" href="<c:url value="/answer?questionId=${question.questionId}" />">
                                    <spring:message code="answer"/>
                                </a>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        if (${formError} || ${repeat_password} || ${password_error}) {
            $("#posts").hide();
            $("#questions").hide();
        } else {
            $("#posts").hide();
            $("#questions").hide();
        }

        // $("#transactions-button").click(function () {
        //     $("#edit-profile, #posts").hide();
        //     $("#transactions").show();
        // });

        $("#edit-profile-button").click(function () {
            $("#posts").hide();
            $("#questions").hide();
            $("#edit-profile").show();
        });

        $("#posts-button").click(function () {
            $("#edit-profile").hide();
            $("#questions").hide();
            $("#posts").show();
        });

        $("#questions-button").click(function () {
            $("#posts").hide();
            $("#edit-profile").hide();
            $("#questions").show();
        });

        date();
    });

    //Set max date for today
    function date() {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!
        var yyyy = today.getFullYear();
        if(dd<10){
            dd='0'+dd
        }
        if(mm<10){
            mm='0'+mm
        }

        today = yyyy+'-'+mm+'-'+dd;
        document.getElementById("dateInput").setAttribute("max", today);
    }
</script>

</body>
</html>
