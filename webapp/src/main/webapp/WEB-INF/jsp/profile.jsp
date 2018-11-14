<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/profile.css'/>">
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
                <li>
                    <button id="sells-button" class="btn btn-primary button">
                        <spring:message code="sells"/>
                    </button>
                </li>
                <li>
                    <button id="buys-button" class="btn btn-primary button">
                        <spring:message code="buys"/>
                    </button>
                </li>
            </ul>
        </div>

        <%-- EDIT PROFILE --%>
        <div class="right-container">
            <div id="edit-profile" class="padding">
                <h2><spring:message code="edit_profile"/></h2>
                <br>
                <c:url value="/profile" var="postPath"/>
                <form:form class="form" modelAttribute="updateProfileForm" action="${postPath}" method="post">
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
                            <form:input id="dateInput" type="date" path="birthdate" value="${user.birthdate}"
                                        class="form-control"/>
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
                                <p><spring:message code="product_name"/><c:out
                                        value="${post.productPosted.productName}"/></p>
                                <p><spring:message code="description_"/><c:out value="${post.description}"/></p>
                                <p><spring:message code="visits"/><c:out value="${post.visits}"/></p>
                                <c:choose>
                                    <c:when test="${post.disable}">
                                        <p><spring:message code="status_"/><spring:message code="disable"/></p>
                                    </c:when>
                                    <c:otherwise>
                                        <p><spring:message code="status_"/><spring:message code="enable"/></p>
                                    </c:otherwise>
                                </c:choose>
                                <a class="btn btn-primary" role="button"
                                   href="<c:url value="/editPost?postId=${post.postId}" />">
                                    <spring:message code="edit"/>
                                </a>
                                <c:if test="${!post.disable}">
                                    <a class="btn btn-primary" role="button"
                                       href="<c:url value="/post?postId=${post.postId}&&profile=true" />">
                                        <spring:message code="view"/>
                                    </a>
                                </c:if>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <%-- QUESTIONS --%>
        <div class="right-container">
            <div id="questions">
                <div class="tab" id="navbar">
                    <ul class="nav nav-tabs nav-justified" role="tablist">
                        <li id="pending-questions-button" class="active">
                            <a><spring:message code="pending"/></a>
                        </li>
                        <li id="my-question-button">
                            <a><spring:message code="my_questions"/></a>
                        </li>
                    </ul>
                </div>
                <div id="pending-questions" class="tab-container">
                    <br>
                    <c:choose>
                        <c:when test="${empty pendingQuestions}">
                            <p><spring:message code="no_pending_questions"/></p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${pendingQuestions}" var="question" varStatus="loop">
                                <div class="post">
                                    <p><spring:message code="product_name"/><c:out
                                            value="${question.postAsked.productPosted.productName}"/></p>
                                    <p><spring:message code="description_"/><c:out
                                            value="${question.postAsked.description}"/></p>
                                    <p><spring:message code="from_"/><c:out
                                            value="${question.userWhoAsk.username}"/></p>
                                    <p><spring:message code="question_"/><c:out value="${question.question}"/></p>
                                    <a class="btn btn-primary" role="button"
                                       href="<c:url value="/answer?questionId=${question.questionId}" />">
                                        <spring:message code="answer"/>
                                    </a>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div id="my-questions" class="tab-container">
                    <br>
                    <c:choose>
                        <c:when test="${empty myQuestions}">
                            <p><spring:message code="no_my_questions"/></p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${myQuestions}" var="question" varStatus="loop">
                                <div class="post">
                                    <p><spring:message code="product_name"/><c:out
                                            value="${question.postAsked.productPosted.productName}"/></p>
                                    <p><spring:message code="description_"/><c:out
                                            value="${question.postAsked.description}"/></p>
                                    <p><spring:message code="question_"/><c:out value="${question.question}"/></p>
                                    <p><spring:message code="answer_"/><c:out value="${question.answer}"/></p>
                                    <a class="btn btn-primary" role="button"
                                       href="<c:url value="/post?postId=${question.postAsked.postId}&&profile=true" />">
                                        <spring:message code="view_post"/>
                                    </a>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <%-- SELLS --%>
        <div class="right-container">
            <div id="sells">
                <div class="tab" id="navbar">
                    <ul class="nav nav-tabs nav-justified" role="tablist">
                        <li id="pending-sells-button" class="active">
                            <a><spring:message code="pending"/></a>
                        </li>
                        <li id="confirmed-sells-button">
                            <a><spring:message code="confirmed"/></a>
                        </li>
                    </ul>
                </div>
                <div id="pending-sells" class="tab-container">
                    <c:choose>
                        <c:when test="${empty pendingSells}">
                            <br/>
                            <p><spring:message code="no_pending_sells"/></p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${pendingSells}" var="sell" varStatus="loop">
                                <div class="post">
                                    <p><spring:message code="product_name"/><c:out value="${sell.productName}"/></p>
                                    <p><spring:message code="price_"/><c:out value="${sell.price}"/></p>
                                    <p><spring:message code="product_quantity"/><c:out
                                            value="${sell.productQuantity}"/></p>
                                    <p><spring:message code="username_"/><c:out value="${sell.buyerUser.username}"/></p>
                                    <p><spring:message code="email_"/><c:out value="${sell.buyerUser.email}"/></p>
                                    <p><spring:message code="phone_"/><c:out value="${sell.buyerUser.phone}"/></p>
                                    <c:if test="${sell.postBought.productQuantity >= sell.productQuantity}">
                                        <c:url value="/confirmTransaction" var="transactionPath"/>
                                        <form:form class="form button-container" modelAttribute="updateProfileForm"
                                                   action="${transactionPath}" method="post">
                                            <form:hidden path="transactionId" value="${sell.transactionId}"/>
                                            <button class="registerButton btn btn-primary" type="submit"><spring:message
                                                    code="confirm"/></button>
                                        </form:form>
                                    </c:if>
                                    <c:url value="/declineTransaction" var="transactionPath"/>
                                    <form:form class="form button-container" modelAttribute="updateProfileForm"
                                               action="${transactionPath}" method="post">
                                        <form:hidden path="transactionId" value="${sell.transactionId}"/>
                                        <button class="registerButton btn btn-primary" type="submit"><spring:message
                                                code="decline"/></button>
                                    </form:form>
                                    <br/>
                                    <c:if test="${sell.postBought.productQuantity < sell.productQuantity}">
                                        <spring:message code="product_quantity_error"/>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <br>
                <div id="confirmed-sells" class="tab-container">
                    <c:choose>
                        <c:when test="${empty confirmedSells}">
                            <p><spring:message code="no_confirmed_sells"/></p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${confirmedSells}" var="sell" varStatus="loop">
                                <div class="post">
                                    <p><spring:message code="product_name"/><c:out value="${sell.productName}"/></p>
                                    <p><spring:message code="price_"/><c:out value="${sell.price}"/></p>
                                    <p><spring:message code="product_quantity"/><c:out
                                            value="${sell.productQuantity}"/></p>
                                    <p><spring:message code="username_"/><c:out value="${sell.buyerUser.username}"/></p>
                                    <p><spring:message code="email_"/><c:out value="${sell.buyerUser.email}"/></p>
                                    <p><spring:message code="phone_"/><c:out value="${sell.buyerUser.phone}"/></p>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <%-- BUYS --%>
        <div class="right-container">
            <div id="buys">
                <div class="tab">
                    <ul class="nav nav-tabs nav-justified" role="tablist">
                        <li id="pending-buys-button" class="active">
                            <a><spring:message code="pending"/></a>
                        </li>
                        <li id="confirmed-buys-button">
                            <a><spring:message code="confirmed"/></a>
                        </li>
                    </ul>
                </div>
                <br>
                <div id="pending-buys" class="tab-container">
                    <c:choose>
                        <c:when test="${empty pendingBuys}">
                            <p><spring:message code="no_pending_buys"/></p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${pendingBuys}" var="buy" varStatus="loop">
                                <div class="post">
                                    <p><spring:message code="product_name"/><c:out value="${buy.productName}"/></p>
                                    <p><spring:message code="price_"/><c:out value="${buy.price}"/></p>
                                    <p><spring:message code="product_quantity"/><c:out
                                            value="${buy.productQuantity}"/></p>
                                    <c:url value="/declineTransaction" var="transactionPath"/>
                                    <form:form class="form button-container" modelAttribute="updateProfileForm"
                                               action="${transactionPath}" method="post">
                                        <form:hidden path="transactionId" value="${buy.transactionId}"/>
                                        <button class="registerButton btn btn-primary" type="submit"><spring:message
                                                code="decline"/></button>
                                    </form:form>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div id="confirmed-buys" class="tab-container">
                    <c:choose>
                        <c:when test="${empty confirmedBuys}">
                            <p><spring:message code="no_confirmed_buys"/></p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${confirmedBuys}" var="buy" varStatus="loop">
                                <div class="post">
                                    <p><spring:message code="product_name"/><c:out value="${buy.productName}"/></p>
                                    <p><spring:message code="price_"/><c:out value="${buy.price}"/></p>
                                    <p><spring:message code="product_quantity"/><c:out
                                            value="${buy.productQuantity}"/></p>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
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
            $("#sells").hide();
            $("#confirmed-sells").hide();
            $("#buys").hide();
            $("#confirmed-buys").hide();
            $("#my-questions").hide();
        } else {
            $("#posts").hide();
            $("#questions").hide();
            $("#sells").hide();
            $("#confirmed-sells").hide();
            $("#buys").hide();
            $("#confirmed-buys").hide();
            $("#my-questions").hide();
        }

        $("#sells-button").click(function () {
            $("#edit-profile, #posts ,#questions, #buys").hide();
            $("#sells").show();
        });

        $("#buys-button").click(function () {
            $("#edit-profile, #posts ,#questions, #sells").hide();
            $("#buys").show();
        });

        $("#edit-profile-button").click(function () {
            $("#posts").hide();
            $("#questions").hide();
            $("#sells").hide();
            $("#buys").hide();
            $("#edit-profile").show();
        });

        $("#posts-button").click(function () {
            $("#edit-profile").hide();
            $("#questions").hide();
            $("#sells").hide();
            $("#buys").hide();
            $("#posts").show();
        });

        $("#questions-button").click(function () {
            $("#posts").hide();
            $("#edit-profile").hide();
            $("#sells").hide();
            $("#buys").hide();
            $("#questions").show();
        });

        $("#confirmed-sells-button").click(function () {
            $("#pending-sells").hide();
            $("#pending-sells-button").removeClass("active");
            $("#confirmed-sells-button").addClass("active");
            $("#confirmed-sells").show();
        });

        $("#pending-sells-button").click(function () {
            $("#confirmed-sells").hide();
            $("#confirmed-sells-button").removeClass("active");
            $("#pending-sells-button").addClass("active");
            $("#pending-sells").show();
        });

        $("#confirmed-buys-button").click(function () {
            $("#pending-buys").hide();
            $("#pending-buys-button").removeClass("active");
            $("#confirmed-buys-button").addClass("active");
            $("#confirmed-buys").show();
        });

        $("#pending-buys-button").click(function () {
            $("#confirmed-buys").hide();
            $("#confirmed-buys-button").removeClass("active");
            $("#pending-buys-button").addClass("active");
            $("#pending-buys").show();
        });

        $("#pending-questions-button").click(function () {
            $("#my-questions").hide();
            $("#pending-questions-button").addClass("active");
            $("#my-question-button").removeClass("active");
            $("#pending-questions").show();
        });

        $("#my-question-button").click(function () {
            $("#pending-questions").hide();
            $("#pending-questions-button").removeClass("active");
            $("#my-question-button").addClass("active");
            $("#my-questions").show();
        });

        date();
    });

    //Set max date for today
    function date() {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1; //January is 0!
        var yyyy = today.getFullYear();
        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }

        today = yyyy + '-' + mm + '-' + dd;
        document.getElementById("dateInput").setAttribute("max", today);
    }
</script>

</body>
</html>
