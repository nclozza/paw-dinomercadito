<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/review.css'/>">
</head>
<body>
<c:url value="/userReview" var="postPath"/>
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

    <div class="review-container">
        <h1><spring:message code="review"/></h1>
        <br>
        <form:form modelAttribute="userReview" action="${postPath}" method="post" autocomplete="off">
            <div class="form-group">
                <form:label class="rating-tittle" path="rating"><spring:message code="rating"/></form:label>
                <form:hidden path="userId" value="${userId}"/>
                <form:hidden path="postId" value="${postId}"/>
                <form:hidden path="profile" value="${profile}"/>
                <form:hidden path="filter" value="${filter}"/>
                <div class="rating">
                    <br/>
                    <span><input type="radio" name="rating" id="str5" value="5"><label for="str5"></label></span>
                    <span><input type="radio" name="rating" id="str4" value="4"><label for="str4"></label></span>
                    <span><input type="radio" name="rating" id="str3" value="3"><label for="str3"></label></span>
                    <span><input type="radio" name="rating" id="str2" value="2"><label for="str2"></label></span>
                    <span><input type="radio" name="rating" id="str1" value="1"><label for="str1"></label></span>
                </div>
                <form:hidden id="rating" path="rating" value=""/>
                <br/>
                <br/>
                <form:errors class="error" path="rating" element="p"><br><spring:message
                        code="rating_error"/><br></form:errors>
                <br/>
            </div>
            <div class="form-group">
                <form:label class="label" path="description"><spring:message code="description"/></form:label>
                <form:textarea path="description" class="form-control input-size"/>
                <form:errors class="error" path="description" element="p"><br><spring:message
                        code="description_error"/><br></form:errors>
                <br/>
                <c:if test="${same_user_error}">
                    <p><spring:message code="same_user_error"/></p>
                </c:if>
                <c:if test="${check_user_error}">
                    <p><spring:message code="check_user_error"/></p>
                </c:if>
                <c:if test="${already_buyer_error}">
                    <p><spring:message code="already_buyer_error"/></p>
                </c:if>
                <br/>
            </div>
            <div>
                <a class="left-button btn" href="<c:url value="/post?filter=${filter}&&postId=${postId}&&profile=${profile}"/>"><spring:message code="cancel"/></a>
                <input type="submit" class="btn btn-primary"
                       value="<spring:message code="submit"/>"/>
            </div>
        </form:form>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        // Check Radio-box
        $(".rating input:radio").attr("checked", false);

        $('.rating input').click(function () {
            $(".rating span").removeClass('checked');
            $(this).parent().addClass('checked');
        });

        $('input:radio').change(
            function(){
                var userRating = this.value;
                document.getElementById("rating").value = userRating;
            });
    });
</script>

</body>
</html>