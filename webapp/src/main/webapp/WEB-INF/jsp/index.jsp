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

    <style type="text/css">
        <%@ include file="../assets/css/index.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="first-container">
    <!-- Navbar -->
    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#"><spring:message code="DinoMercadito"/></a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="<c:url value='/products'/>"><spring:message code="products"/></a></li>
                    <li><a href="<c:url value='/signUp'/>"><spring:message code="signUp"/></a></li>
                    <li><a href="<c:url value='/login'/>"><spring:message code="login"/></a></li>
                    <li><a href="<c:url value='/logout'/>"><spring:message code="logout"/></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- First Container -->
    <div class="container-fluid bg-1 text-center ">
        <div class="vertical-align">
            <div class="horizontal-align">
                <h1><spring:message code="search_products"/></h1>
                <h4><spring:message code="products_types"/></h4>
                <div class="wrap">
                    <div class="search input-group-sm">
                        <c:url value="/index" var="searchUrl"/>
                        <form class="form" action="${searchUrl}" method="post" enctype="application/x-www-form-urlencoded">
                            <input id="search" name="search" type="text" class="searchTerm form-control" placeholder="<spring:message code="looking_for"/>">
                            <br>
                            <button type="submit" class="btn btn-default search-button">
                                <spring:message code="search"/>
                            </button>
                        </form>
                    </div>
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
</body>
<script>
    // Instantiate the Bootstrap carousel
    $('.multi-item-carousel').carousel({
        interval: false
    });

    // for every slide in carousel, copy the next slide's item in the slide.
    // Do the same for the next, next item.
    $('.multi-item-carousel .item').each(function () {
        var next = $(this).next();
        if (!next.length) {
            next = $(this).siblings(':first');
        }
        next.children(':first-child').clone().appendTo($(this));

        if (next.next().length > 0) {
            next.next().children(':first-child').clone().appendTo($(this));
        } else {
            $(this).siblings(':first').children(':first-child').clone().appendTo($(this));
        }
    });
</script>
</html>
