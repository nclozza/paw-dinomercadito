<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>DinoMercadito</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">

    <style type="text/css">
        <%@ include file="../assets/css/loginModal.css" %>
    </style>
    <style type="text/css">
        <%@ include file="../assets/css/index.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="first-container">
    <!-- Navbar -->
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
                    <li><a onclick="document.getElementById('signUpModal').style.display='block'">Sign Up</a></li>
                    <li><a onclick="document.getElementById('loginModal').style.display='block'">Login</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <%-- LOGIN MODAL--%>
    <c:url value="/login" var="loginUrl"/>
    <div id="loginModal" class="modal">

        <form class="modal-content animate" action="${loginUrl}" method="post"
              enctype="application/x-www-form-urlencoded">
            <div class="container">
                <label><b><spring:message code="username"/></b></label>
                <input class="loginInput" type="text" placeholder="<spring:message code="enterUsername"/>"
                       name="j_username">

                <label><b><spring:message code="password"/></b></label>
                <input class="loginInput" type="password" placeholder="<spring:message code="enterUsername"/>"
                       name="j_password" required>

                <button class="loginButton" type="submit">
                    <spring:message code="login"/>
                </button>
                <label>
                    <input class="loginInput" type="checkbox" name="j_rememberme">
                    <spring:message code="remember_me"/>
                </label>
            </div>

            <div class="container" style="background-color:#f1f1f1">
                <button type="button" onclick="document.getElementById('loginModal').style.display='none'"
                        class="cancelbtn">
                    <spring:message code="cancel"/>
                </button>
            </div>
        </form>
    </div>
    <%-- END LOGIN MODAL--%>

    <%-- SIGN UP MODAL --%>
    <div id="signUpModal" class="modal">
        <span onclick="document.getElementById('signUpModal').style.display='none'" class="close"
              title="Close Modal">&times;</span>
        <form class="modal-content" action="">
            <div class="container">
                <h1>Sign Up</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>
                <label><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="email" required>

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="psw" required>

                <label><b>Repeat Password</b></label>
                <input type="password" placeholder="Repeat Password" name="psw-repeat" required>

                <label>
                    <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
                </label>

                <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

                <div class="clearfix">
                    <button type="button" onclick="document.getElementById('signUpModal').style.display='none'"
                            class="signUpCancelbtn">Cancel
                    </button>
                    <button type="submit" class="signupbtn">Sign Up</button>
                </div>
            </div>
        </form>
    </div>
    <%-- END SIGN UP MODAL--%>

    <script>
        // Get the modal
        var modal = document.getElementById('loginModal');
        var signUpModal = document.getElementById('signUpModal');

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function (event) {
            if (event.target == modal || event.target == signUpModal) {
                modal.style.display = "none";
                signUpModal.style.display = "none";
            }
        }
    </script>


    <!-- First Container -->
    <div class="container-fluid bg-1 text-center ">
        <h3 class="margin">Search products</h3>
        <div class="wrap">
            <div class="search">
                <input type="text" class="searchTerm" placeholder="What are you looking for?">
                <button type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Second Container -->
<div class="container-fluid bg-2 text-center">
    <div class="container">
        <h1>New Releases</h1>
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

<!-- Third Container (Grid) -->
<div class="container-fluid bg-3 text-center">
    <h3 class="margin">Where To Find Me?</h3><br>
    <div class="row">
        <div class="col-sm-4">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua.</p>
            <img src="#" class="img-responsive margin" style="width:100%" alt="Image">
        </div>
        <div class="col-sm-4">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua.</p>
            <img src="#" class="img-responsive margin" style="width:100%" alt="Image">
        </div>
        <div class="col-sm-4">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua.</p>
            <img src="#" class="img-responsive margin" style="width:100%" alt="Image">
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
