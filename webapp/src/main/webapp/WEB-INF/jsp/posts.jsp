<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>DinoMercadito | Posts</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>

    <body>
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

        <div class="col-md-9">
            <c:forEach items="${posts}" var="post" varStatus="loop">
                <div class="col-md-3">
                    <div class="thumbnail">
                        <a href="#">
                            <div class="caption">
                                <p><c:out value="description: ${post.description}"/></p>
                                <p><c:out value="price: ${post.price}"/></p>
                                <p><c:out value="productQuantity: ${post.productQuantity}"/></p>
                                <p><c:out value="postId: ${post.postId}"/></p>
                                <p><c:out value="productId: ${post.productId}"/></p>
                                <p><c:out value="userId: ${post.userId}"/></p>
                            </div>
                        </a>
                        <button type="button" class="btn btn-success" onclick="">Buy</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>