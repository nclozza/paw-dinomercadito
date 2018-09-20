<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <%--<link rel="stylesheet" href="<c:url value="/css/style.css"/>" />--%>
</head>
<body>
<h2><spring:message code="user.greeting" arguments="${username}"/></h2>
<h2><spring:message code="user.id" arguments="${userId}"/></h2>
</body>
</html>