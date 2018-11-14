<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/question.css'/>">
</head>
<body>
<c:url value="/admin/index" var="postPath"/>
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

    <div class="search-container">
        <h1><spring:message code="search_post"/></h1>
        <br>
        <form:form modelAttribute="searchForm" action="${postPath}" method="post" autocomplete="off">
                <div>
                    <c:choose>
                        <c:when test="${error}">
                            <input path="search" type="text" class="form-control" placeholder="<spring:message code="wrong_input"/>"/>
                        </c:when>
                        <c:otherwise>
                            <input path="search" name="search" type="text" class="form-control" placeholder="<spring:message code="search"/>"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            <br>
            <button class="btn btn-primary" type="submit"><spring:message code="search"/></button>
        </form:form>
    </div>
</div>
</body>
</html>
