<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="DinoMercadito"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/index.css'/>">

</head>
<body>

<div class="first-container bg-1">
    <!-- Navbar -->
    <c:choose>
        <c:when test="${loggedIn}">
            <%@ include file="navbarLogout.jsp" %>
        </c:when>
        <c:otherwise>
            <%@ include file="navbarLogin.jsp" %>
        </c:otherwise>
    </c:choose>

    <!-- First Container -->
    <div class="container-fluid full-height text-center ">
        <div class="vertical-align">
            <div class="horizontal-align">
                <h1><spring:message code="search_products"/></h1>
                <h4><spring:message code="products_types"/></h4>
                <div class="wrap">
                    <c:url value="/index" var="searchUrl"/>
                    <form class="form" action="${searchUrl}" method="post"
                          enctype="application/x-www-form-urlencoded">
                        <div class="row">
                            <div class="input-group">
                                <c:choose>
                                    <c:when test="${error}">
                                        <input path="search" name="search" type="text" class="searchTerm form-control"
                                               placeholder="<spring:message code="wrong_input"/>">
                                    </c:when>
                                    <c:otherwise>
                                        <input path="search" name="search" type="text" class="searchTerm form-control"
                                               placeholder="<spring:message code="looking_for"/>">
                                    </c:otherwise>
                                </c:choose>
                                <span class="input-group-btn">
                                        <button type="submit" class="btn btn-default">
                                            <spring:message code="search"/>
                                        </button>
                                    </span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Second Container -->
    <div class="container-fluid bg-2 text-center">
        <div class="container">
            <h2>New Releases</h2>
            <hr>
            <div class="row">
                <div class="col-md-12">
                    <div class="carousel slide multi-item-carousel" id="theCarousel">
                        <div class="carousel-inner">
                            <div class="item active">
                                <div class="col-xs-4"><a href="#1"><img src="http://placehold.it/300/f44336/000000"
                                                                        class="img-responsive"></a></div>
                            </div>
                            <div class="item">
                                <div class="col-xs-4"><a href="#1"><img src="http://placehold.it/300/e91e63/000000"
                                                                        class="img-responsive"></a></div>
                            </div>
                            <div class="item">
                                <div class="col-xs-4"><a href="#1"><img src="http://placehold.it/300/9c27b0/000000"
                                                                        class="img-responsive"></a></div>
                            </div>
                            <div class="item">
                                <div class="col-xs-4"><a href="#1"><img src="http://placehold.it/300/673ab7/000000"
                                                                        class="img-responsive"></a></div>
                            </div>
                            <div class="item">
                                <div class="col-xs-4"><a href="#1"><img src="http://placehold.it/300/4caf50/000000"
                                                                        class="img-responsive"></a></div>
                            </div>
                            <div class="item">
                                <div class="col-xs-4"><a href="#1"><img src="http://placehold.it/300/8bc34a/000000"
                                                                        class="img-responsive"></a></div>
                            </div>
                            <!-- add  more items here -->
                            <!-- Example item start:  -->
                            <div class="item">
                                <div class="col-xs-4"><a href="#1"><img src="http://placehold.it/300/8bc34a/000000"
                                                                        class="img-responsive"></a></div>
                            </div>
                            <!--  Example item end -->
                        </div>
                        <a class="left carousel-control" href="#theCarousel" data-slide="prev"><i
                                class="glyphicon glyphicon-chevron-left"></i></a>
                        <a class="right carousel-control" href="#theCarousel" data-slide="next"><i
                                class="glyphicon glyphicon-chevron-right"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
