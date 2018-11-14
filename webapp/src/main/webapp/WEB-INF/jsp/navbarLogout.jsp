<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a href="<c:url value='/index'/>" class="navbar-brand">
                <img style="width: 40px;margin-top: -15%; margin-right: 40%;"
                     src="<c:url value="/images/logo.png"/>" alt="logo"></a>
            <a class="navbar-brand" href="<c:url value='/index'/>"><spring:message code="DinoMercadito"/></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value='/products'/>"><spring:message code="products"/></a></li>
                <li><a href="<c:url value='/newPost'/>"><spring:message code="create_post"/></a></li>
                <li><a href="<c:url value='/profile'/>"><spring:message code="profile"/></a></li>
                <li><a href="<c:url value='/logout'/>"><spring:message code="logout"/></a></li>
            </ul>
        </div>
    </div>
</nav>